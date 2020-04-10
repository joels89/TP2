package gestsaude.recurso;

import java.time.LocalDate;
import java.time.LocalTime;

/** Representa uma Senha
 */
public class Senha {

	private Utente utente;
	private Servico servico;
	private LocalDate dataEntrada;
	private LocalTime horaEntrada;
	
	
	public Senha (Utente utente, Servico servico, LocalDate dataEntrada, LocalTime horaEntrada) {
		this.servico = servico;
		this.utente = utente;
		this.dataEntrada = dataEntrada;
		this.horaEntrada = horaEntrada;	
	}
	


	/** retorna o pr�ximo servi�o associado a esta senha 
	 * @return o pr�ximo servi�o associado a esta senha
	 */
	public Servico proxServico() {
		return null;
	}

	/** faz o processamento do fim da consulta por um dado servi�o
	 */
	public void terminaConsulta() {
	}
	
	@Override
	public String toString() {
		return "Senha [utente=" + utente + ", servico=" + servico + "]";
	}


	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
	}


	public Servico getServico() {
		return servico;
	}


	public void setServico(Servico servico) {
		this.servico = servico;
	}


	public LocalDate getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}


	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

}
