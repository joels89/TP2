package gestsaude.recurso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import gestsaude.util.Consultas;
import gestsaude.util.RelogioSimulado;

/**
 * Representa o sistema
 */
public class GEstSaude {

	// Constantes para os erros possíveis nos métodos de aceitar chamadas
	public static final int CONSULTA_INVALIDA = -1; // indica que a consulta é aceite
	public static final int CONSULTA_ACEITE = 0; // indica que a consulta é aceite
	public static final int UTENTE_TEM_CONSULTA = 1; // indica que o utente já tem consulta
	public static final int SERVICO_TEM_CONSULTA = 2; // indica que o serviço já tem consulta
	public static final int DATA_JA_PASSOU = 3; // indica que a data já passou
	public static final int ALTERACAO_INVALIDA = 4; // indica que a alteração é inválida

	public static final int TRES_HORAS = 3;
	public static final LocalTime HORARIO_INICIO = LocalTime.of(8, 10);
	public static final LocalTime HORARIO_FIM = LocalTime.of(19, 50);

	// mapas com as listas de todas as informações
	private HashMap<String, Utente> utentes = new HashMap<String, Utente>();
	private HashMap<String, Servico> servicos = new HashMap<String, Servico>();
	private HashMap<String, Senha> senhas = new HashMap<String, Senha>();
	private ArrayList<Consulta> consultas = new ArrayList<Consulta>();

	// definição da próxima senha
	private int proxSenha = 1;

	public GEstSaude() {
	}

	public Senha emiteSenha(Consulta c, LocalDateTime t) {
		Collection<Senha> senhas = getSenhas().values();
		for (Senha s : senhas) {
			if(s.getConsulta().getDataConsulta().getDayOfMonth() != t.getDayOfMonth())
			{
				resetSenhas();				
			}
			if (s.getConsulta().equals(c))
				return s;
		}

		Senha senha = new Senha(utentes.get(c.getNumeroSNSUtente()), c, t, getProximoIdSenha());
		addSenha(senha);
		return senha;
	}

	public Utente getUtente(String numeroSNSUtente) {
		return utentes.get(numeroSNSUtente);
	}

	public void addUtente(Utente utente) {
		utentes.put(utente.getNumeroSNS(), utente);
	}

	public void removeUtente(String numeroSNSUtente) {
		utentes.remove(numeroSNSUtente);
	}

	public void addServico(Servico servico) {
		servicos.put(servico.getServicoId(), servico);
	}

	public void removeServico(String servicoId) {
		servicos.remove(servicoId);
	}

	public void addSenha(Senha senha) {
		senhas.put(senha.getIdSenha(), senha);
		senha.addServicosVistar(servicos.get(senha.getConsulta().getServicoId()));
		servicos.get(senha.getConsulta().getServicoId()).addSenhasAoServiço(senha);
	}

	public void resetSenhas() {
		getSenhas().clear();
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
		if (servicos.get(c.getServicoId()) == null) {
			System.out.println(
					"        **** ATENCAO CONSULTA INVALIDA ****  \n Servico nao existe na Lista de Servicos  \n ");// TODO tirar no final
			return CONSULTA_INVALIDA;
		}
		if (utentes.get(c.getNumeroSNSUtente()) == null) {
			System.out.println("        **** ATENCAO CONSULTA INVALIDA ***  \n Utente nao existe na Lista de Utentes \n");// TODO tirar no final
			return CONSULTA_INVALIDA;
		}
		if (c.getDataConsulta() == null || c.getHoraConsulta() == null) {
			System.out.println("        **** ATENCAO CONSULTA INVALIDA ***  \n Consulta sem hora ou data marcada \n");// TODO tirar no final
			return CONSULTA_INVALIDA;
		}

		if ((c.getHoraConsulta().compareTo(HORARIO_INICIO) < 0) || (c.getHoraConsulta().compareTo(HORARIO_FIM) > 0)) {
			System.out.println(
					"        **** ATENCAO CONSULTA INVALIDA *** \n Consulta fora do Horario de Funcionamento. Volte a remarcar entre as 8:10 e as 19:50 \n");// TODO tirar no final
			return CONSULTA_INVALIDA;
		}
			
		for (Consulta consultaListada : Consultas.getConsultaEntreDatas(consultas,
			c.getDataConsulta().atTime(c.getHoraConsulta().minusHours(TRES_HORAS)),
			c.getDataConsulta().atTime(c.getHoraConsulta().plusHours(TRES_HORAS)))) {
			if (c.getNumeroSNSUtente() == consultaListada.getNumeroSNSUtente()) {
				System.out.println(
						"        **** ATENCAO CONSULTA INVALIDA - UTENTE_TEM_CONSULTA *** \n A segunda consulta no mesmo dia deve iniciar no mínimo após 3 horas do inicio da primeira \n");// TODO tirar no final
				return UTENTE_TEM_CONSULTA;
			}
		}
		
		for (Consulta consultaListada : consultas) {
			if ((c.getServicoId() == consultaListada.getServicoId()) &&
				((c.getDataConsulta().equals(consultaListada.getDataConsulta()) &&
				(c.getHoraConsulta().equals(consultaListada.getHoraConsulta()))))) {
				System.out.println(
						"        **** ATENCAO CONSULTA INVALIDA - SERVICO_TEM_CONSULTA *** \n O servico ja tem uma consulta marcada para esta data e hora \n");// TODO tirar no final
				return SERVICO_TEM_CONSULTA;
			}
		}

		if (c.getDataConsulta().compareTo(LocalDate.now()) < 0) {
			System.out.println(
					"        **** ATENCAO CONSULTA INVALIDA - DATA_JA_PASSOU *** \n Consulta tem de ser sempre marcada para o dia seguinte. Volte para uma data a partir de amanha \n");// TODO tirar no final
			return DATA_JA_PASSOU;
		}
		return CONSULTA_ACEITE;
	}

	public int addConsulta(Consulta c) {
		if ((podeAceitarConsulta(c) != CONSULTA_ACEITE)) {
			return CONSULTA_INVALIDA;
		}
		// ************************** Este if deixou de ter senntido?????????? TODO******************* testar aparentemente funciona ma s ver se rebemta o cof~digo
		/*
		 * if (!aceitaConsulta(servicos.get(c.getServicoId()))) { return/
		 * CONSULTA_INVALIDA; }
		 */
		Consultas.addConsultaOrdemData(consultas, c);
		servicos.get(c.getServicoId()).addConsultasServico(c);
		utentes.get(c.getNumeroSNSUtente()).addConsulta(c);
		return CONSULTA_ACEITE;
	}

	public void removeConsulta(Consulta c) {
		if (consultas.contains(c)) {
			consultas.remove(c);
			servicos.get(c.getServicoId()).removeConsultasServico(c);
			utentes.get(c.getNumeroSNSUtente()).removeConsulta(c);
		}
	}

	public boolean aceitaConsulta(Servico servico) {
		if (servico.getServicoId() == "Rad" || servico.getServicoId() == "Audio" || servico.getServicoId() == "Scopia"
				|| servico.getServicoId() == "Enf" || servico.getServicoId() == "NeuLab") {
			return false;
		}
		return true;
	}

	public int podeAlterarConsulta(Consulta antiga, Consulta nova) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e
		// enunciado)
		removeConsulta(antiga);
		if ((podeAceitarConsulta(nova) != CONSULTA_ACEITE)) {
			addConsulta(antiga);
			return CONSULTA_INVALIDA;
		}
		return CONSULTA_ACEITE;
	}

	public int alteraConsulta(Consulta antiga, Consulta nova) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e
		// enunciado)
		if ((podeAlterarConsulta(antiga, nova) != CONSULTA_ACEITE)) {
			return ALTERACAO_INVALIDA;
		}
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

	public HashMap<String, Senha> getSenhas() {// TODO mudar o nome
		return senhas;
	}
		
}
