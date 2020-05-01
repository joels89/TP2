package gestsaude.recurso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Representa uma Senha
 */
public class Senha {

	private Utente utente;
	private Consulta consulta;
	private String idSenha;
	private LocalDateTime dataHoraEntrada;
	public List<Servico> servicosVisitar = new ArrayList<Servico>();
	
	public Senha (Utente utente, Consulta consulta, LocalDateTime dataHoraEntrada, String idSenha) {// *** TODO **No enunciado nao diz que o contrutor deve ter o utente e denha id é necessario?
		setUtente(utente);
		setConsulta(consulta);
		this.dataHoraEntrada = dataHoraEntrada; // Nao percebi damos uma data e hora e fazemos get e set so da hora?
		setIdSenha(idSenha);
	}
	
	/** retorna o próximo serviço associado a esta senha 
	 * @return o próximo serviço associado a esta senha
	 * 	 */
	public Servico proxServico() {
		return servicosVisitar.get(0);
	}

	/** faz o processamento do fim da consulta por um dado serviço
	 */
	public void terminaConsulta(Servico servico) {//********TODO a sconsultas nunca sao removidas ????
		servicosVisitar.remove(servico);
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public LocalDateTime getHoraEntrada() {
		return dataHoraEntrada;
	}

	public void setHoraEntrada(LocalDateTime Data) {
		this.dataHoraEntrada = Data;
	}
	
	public String getIdSenha() {
		return idSenha;
	}

	public void setIdSenha(String idSenha) {
		this.idSenha = idSenha;
	}

	public void addServicosVistar(Servico servico) {
		if (servicosVisitar.contains(servico)) {
			System.out.println("ja existe"); // TODo retirar no final se tiver tudo bem
			return;
		}
		servicosVisitar.add(servico);   	
	}
	
	public void removeServicosVistar(Servico servico) {
		servicosVisitar.remove(servico);
	}
	
	public List<Servico> getConsultasMarcadasServico() {// este get nao deveria chmar-se "getServicosVisitar" ????????????????
		return Collections.unmodifiableList(servicosVisitar);
	}

	@Override
	public String toString() {
		return "idSenha=" + idSenha + ", DataeHoradeEntrada="
				+ dataHoraEntrada + "]";
	}
	
}
