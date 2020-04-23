package gestsaude.recurso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gestsaude.menu.MenuServico;
import gestsaude.util.gestSaudeUtilitarios;

/** Representa uma Senha
 */
public class Senha {

	private Utente utente;
	private Consulta consulta;
	private String idSenha;
	private LocalDateTime DataeHoradeEntrada;
	public List<Servico> servicosaVisitar = new ArrayList<Servico>();
	
	public Senha (Utente utente, Consulta consulta, LocalDateTime DataeHoradeEntrada, String idSenha) {
		this.utente = utente;
		this.consulta = consulta;
		this.DataeHoradeEntrada = DataeHoradeEntrada;
		this.idSenha = idSenha;
	}
	
	/** retorna o pr�ximo servi�o associado a esta senha 
	 * @return o pr�ximo servi�o associado a esta senha
	 * 	 */
	public Servico proxServico() 
	{

		return servicosaVisitar.get(0);
	}

	/** faz o processamento do fim da consulta por um dado servi�o
	 */
	public void terminaConsulta(Servico servico) {
		servicosaVisitar.remove(servico);
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
		return DataeHoradeEntrada;
	}
	
	public String getIdSenha() {
		return idSenha;
	}


	public void setIdSenha(String idSenha) {
		this.idSenha = idSenha;
	}


	public void setHoraEntrada(LocalDateTime Data) {
		this.DataeHoradeEntrada = Data;
	}
	
	public void addServicosVistar(Servico servico)
	{
		if (servicosaVisitar.contains(servico))
		{
			System.out.println("ja existe");
			return;
		}
		
		servicosaVisitar.add(servico);;   	
	}
	
	public void removeServicosVistar(Servico servico)
	{
		servicosaVisitar.remove(servico);
	}
	
	public List<Servico> getConsultasMarcadasServico() {
		return Collections.unmodifiableList(servicosaVisitar);
	}

	@Override
	public String toString() {
		return "idSenha=" + idSenha + ", DataeHoradeEntrada="
				+ DataeHoradeEntrada + "]";
	}
	
}
