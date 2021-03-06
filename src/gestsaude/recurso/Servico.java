package gestsaude.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import gestsaude.util.Consultas;

/**
 * Representa um Servi�o
 */
public class Servico {

	private static final int ATRASA_UTENTE = 4;
	public static final int LISTA_INDICE_ZERO = 0;
	private String servicoId;
	private String servicoNome;
	private ArrayList<Consulta> consultadasMarcadasServico = new ArrayList<Consulta>();
	private ArrayList<Senha> senhasAtender = new ArrayList<Senha>();

	public Servico(String servicoId, String servicoNome) {
		setServicoId(servicoId);
		setServicoNome(servicoNome);
	}

	/**
	 * Retorna a pr�xima senha a ser atendida, ou null, caso n�o haja
	 * 
	 * @return a pr�xima senha a ser atendida, ou null, caso n�o haja
	 */
	public Senha getProximaSenha() {
		if (senhasAtender.size() == 0)
			return null;
		return getSenhasaAtender().get(LISTA_INDICE_ZERO);
	}

	public Senha getProximaSenha(int idx) {
		if (senhasAtender.size() == 0)
			return null;
		return getSenhasaAtender().get(idx);
	}

	/**
	 * processo para rejeitar a pr�xima senha, caso o utente seja muito atrasado
	 */
	public void rejeitaProximaSenha() {
		if (senhasAtender.size() < ATRASA_UTENTE) {
			senhasAtender.add(senhasAtender.size(), getProximaSenha());
			senhasAtender.remove(LISTA_INDICE_ZERO);
		} else {
			senhasAtender.add(ATRASA_UTENTE, getProximaSenha());
			senhasAtender.remove(LISTA_INDICE_ZERO);
		}
	}

	/** processo de terminar a consulta associada � senha */
	public void terminaConsulta(Senha s) {
		if (senhasAtender.contains(s)) {
			senhasAtender.remove(s);
		}
		return;
	}

	public String getServicoId() {
		return servicoId;
	}

	public void setServicoId(String servicoId) {
		this.servicoId = servicoId;
	}

	public String getServicoNome() {
		return servicoNome;
	}

	public void setServicoNome(String servicoNome) {
		this.servicoNome = servicoNome;
	}

	public List<Consulta> getConsultasMarcadasServico() {
		return Collections.unmodifiableList(consultadasMarcadasServico);
	}

	public void addConsultasServico(Consulta consulta) {
		if (consultadasMarcadasServico.contains(consulta)) {
			return;
		}
		Consultas.addConsultaOrdemData(consultadasMarcadasServico, consulta);
	}

	public void removeConsultasServico(Consulta consulta) {
		if (!consultadasMarcadasServico.contains(consulta)) // Se a consulta n�o existe na lista n�o pode ser removida
			return;
		consultadasMarcadasServico.remove(consulta);
	}

	public List<Senha> getSenhasaAtender() {
		return Collections.unmodifiableList(senhasAtender);
	}

	public void addSenhasAoServi�o(Senha senha) {
		if (senhasAtender.contains(senha)) { // Se a Senha j� existe na lista n�o pode ser adicionada novamente
			return;
		}
		ordenharSenhas(senha);
	}

	private void ordenharSenhas(Senha senha) {

		int idx = 0;

		if (!senhasAtender.isEmpty()) {
			if (senha.getDataHoraEntrada().toLocalTime().isAfter(senha.getConsulta().getHoraConsulta())) {
				for (Senha senhas : senhasAtender) {
					if (senha.getDataHoraEntrada().toLocalTime().isAfter(senhas.getConsulta().getHoraConsulta()))
						idx++;
				}
				senhasAtender.add(idx, senha);
			} else {
				for (Senha senhas : senhasAtender) {
					if (senha.getConsulta().getHoraConsulta().isAfter(senhas.getConsulta().getHoraConsulta()))
						idx++;
				}
				senhasAtender.add(idx, senha);
			}
		} else
			senhasAtender.add(senha);
	}

	public void removeSenhaServico(Senha senha) {
		if (!senhasAtender.contains(senha)) // Se a senha n�o existe na lista n�o pode ser removida
			return;
		senhasAtender.remove(senha);
	}

	@Override
	public String toString() {
		return "Servico [servicoId=" + servicoId + ", servicoNome=" + servicoNome + "]";
	}

}
