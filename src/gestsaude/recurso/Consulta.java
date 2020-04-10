package gestsaude.recurso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import gestsaude.menu.DatePicker;

/** Representa uma Consulta 
 */
public class Consulta 
{

/*	Esta classe representa uma consulta. Deve ter a indicação do utente, do serviço e o dia e
	hora da consulta. Deve ter construtores, getters e setters relevantes. */
	
	private Utente utente;
	private Servico servico;
	private LocalDate dataConulta;
	private LocalTime horaConsulta;
	
	
	public Consulta(LocalDate dataConsulta, LocalTime horaConsulta, Servico servico, Utente utente) {
		this.dataConulta = dataConsulta;
		this.horaConsulta = horaConsulta;
		this.servico = servico;
		this.utente = utente;
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


	public LocalDate getDataConsulta() {
		return dataConulta;
	}


	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConulta = dataConsulta;
	}


	public LocalTime getHoraConsulta() {
		return horaConsulta;
	}


	public void setHoraConsulta(LocalTime horaConsulta) {
		this.horaConsulta = horaConsulta;
	}
	

	@Override
	public String toString() {
		return "Consulta: Data: " + dataConulta + ", Hora: " + horaConsulta +
				", Servico: " +	servico.getServicoNome() + "(" +servico.getServicoId() + ")" +
				", Utente: " + utente.getNomeUtente() + " - SnsNº(" + utente.getNumeroSNS() + ")";
	}
	
}
