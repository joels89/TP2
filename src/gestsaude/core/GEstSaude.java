package gestsaude.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import gestsaude.util.Consultas;
import gestsaude.recurso.*;

/**
 * Representa o sistema
 */
public class GEstSaude {

	// Constantes para os erros poss�veis nos m�todos de aceitar chamadas
	public static final int CONSULTA_INVALIDA = -1; // indica que a consulta � aceite
	public static final int CONSULTA_ACEITE = 0; // indica que a consulta � aceite
	public static final int UTENTE_TEM_CONSULTA = 1; // indica que o utente j� tem consulta
	public static final int SERVICO_TEM_CONSULTA = 2; // indica que o servi�o j� tem consulta
	public static final int DATA_JA_PASSOU = 3; // indica que a data j� passou
	public static final int ALTERACAO_INVALIDA = 4; // indica que a altera��o � inv�lida

	public static final int TRES_HORAS = 3;
	public static final LocalTime HORARIO_INICIO = LocalTime.of(8, 10);
	public static final LocalTime HORARIO_FIM = LocalTime.of(19, 50);

	// mapas com as listas de todas as informa��es
	private HashMap<String, Utente> utentes = new HashMap<String, Utente>();
	private HashMap<String, Servico> servicos = new HashMap<String, Servico>();
	private HashMap<String, Senha> senhas = new HashMap<String, Senha>();
	private ArrayList<Consulta> consultas = new ArrayList<Consulta>();

	// defini��o da pr�xima senha
	private int proxSenha = 1;

	public GEstSaude() {
	}

	public Senha emiteSenha(Consulta c, LocalDateTime t) {
		if (c.getSenha() != null) {
			return c.getSenha();
		}
		Senha senha = new Senha(utentes.get(c.getNumeroSNSUtente()), c, t, getProximoIdSenha());
		c.setSenha(senha);
		addSenha(senha);
		return senha;
	}

	public Utente getUtente(String numeroSNSUtente) {
		return utentes.get(numeroSNSUtente);
	}

	public void addUtente(Utente utente) {
		if (utentes.containsKey(utente.getNumeroSNS()))
			return;
		utentes.put(utente.getNumeroSNS(), utente);
	}

	public void removeUtente(String numeroSNSUtente) {
		if (!utentes.containsKey(numeroSNSUtente))
			return;
		utentes.remove(numeroSNSUtente);
	}

	public void addServico(Servico servico) {
		if (servicos.containsKey(servico.getServicoId()))
			return;
		servicos.put(servico.getServicoId(), servico);
	}

	public void removeServico(String servicoId) {
		if (!servicos.containsKey(servicoId))
			return;
		servicos.remove(servicoId);
	}

	public void addSenha(Senha senha) {
		senhas.put(senha.getIdSenha(), senha);
		senha.addServicosVistar(servicos.get(senha.getConsulta().getServicoId()));
		servicos.get(senha.getConsulta().getServicoId()).addSenhasAoServi�o(senha);
	}

	public void resetSenhas() {
		getSenhasFromMap().clear();
		proxSenha = 1;
	}

	private String getProximoIdSenha() {
		String id = "A" + proxSenha;
		proxSenha++;
		return id;
	}

	public int podeAceitarConsulta(Consulta c) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e
		// enunciado)
		if (servicos.get(c.getServicoId()) == null)
			return CONSULTA_INVALIDA;

		if (utentes.get(c.getNumeroSNSUtente()) == null)
			return CONSULTA_INVALIDA;

		if (c.getDataConsulta() == null || c.getHoraConsulta() == null)
			return CONSULTA_INVALIDA;

		if ((c.getHoraConsulta().compareTo(HORARIO_INICIO) < 0) || (c.getHoraConsulta().compareTo(HORARIO_FIM) > 0))
			return CONSULTA_INVALIDA;

		for (Consulta consultaListada : Consultas.getConsultaEntreDatas(consultas,
				c.getDataConsulta().atTime(c.getHoraConsulta().minusHours(TRES_HORAS)),
				c.getDataConsulta().atTime(c.getHoraConsulta().plusHours(TRES_HORAS)))) {
			if (consultaListada.getNumeroSNSUtente().equals(c.getNumeroSNSUtente()))
				return UTENTE_TEM_CONSULTA;
		}

		for (Consulta consultaListada : consultas) {
			if ((c.getServicoId() == consultaListada.getServicoId())
					&& ((c.getDataConsulta().equals(consultaListada.getDataConsulta())
							&& (c.getHoraConsulta().equals(consultaListada.getHoraConsulta()))))) {
				return SERVICO_TEM_CONSULTA;
			}
		}

		if (c.getDataConsulta().compareTo(LocalDate.now()) < 0) {
			return DATA_JA_PASSOU;
		}
		return CONSULTA_ACEITE;
	}

	public int addConsulta(Consulta c) {
		if (consultas.contains(c))
			return CONSULTA_INVALIDA;

		if ((podeAceitarConsulta(c) != CONSULTA_ACEITE))
			return CONSULTA_INVALIDA;

		if (!aceitaConsulta(servicos.get(c.getServicoId()))) // Condicao para evitar adicionar consultas inv�lidas a
																// partir da classe Arranque
			return CONSULTA_INVALIDA;

		Consultas.addConsultaOrdemData(consultas, c);
		servicos.get(c.getServicoId()).addConsultasServico(c);
		utentes.get(c.getNumeroSNSUtente()).addConsulta(c);
		return CONSULTA_ACEITE;
	}

	public void removeConsulta(Consulta c) {
		if (!consultas.contains(c))
			return;
		consultas.remove(c);
		servicos.get(c.getServicoId()).removeConsultasServico(c);
		utentes.get(c.getNumeroSNSUtente()).removeConsulta(c);

	}

	public boolean aceitaConsulta(Servico servico) {
		if (servico.getServicoId().equals("Rad") || servico.getServicoId().equals("Audio")
				|| servico.getServicoId().equals("Scopia") || servico.getServicoId().equals("Enf")
				|| servico.getServicoId().equals("NeuLab")) {

			return false;
		}
		return true;
	}

	public int podeAlterarConsulta(Consulta antiga, Consulta nova) {
		removeConsulta(antiga);
		if ((podeAceitarConsulta(nova) != CONSULTA_ACEITE)) {
			addConsulta(antiga);
			return CONSULTA_INVALIDA;
		}
		addConsulta(antiga);
		return CONSULTA_ACEITE;
	}

	public int alteraConsulta(Consulta antiga, Consulta nova) {
		if ((podeAlterarConsulta(antiga, nova) != CONSULTA_ACEITE)) {
			return ALTERACAO_INVALIDA;
		}
		removeConsulta(antiga);
		addConsulta(nova);
		return CONSULTA_ACEITE;
	}

	public Collection<Servico> getServicos() {
		return Collections.unmodifiableCollection(servicos.values());
	}

	public List<Consulta> getConsultas() {
		return Collections.unmodifiableList(consultas);
	}

	public Servico getServico(String servicoId) {
		return servicos.get(servicoId);
	}

	public HashMap<String, Senha> getSenhasFromMap() {
		return senhas;
	}

}
