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
	
	private LocalDate dataConulta;
	private LocalTime horaConsulta;
	private String servicoId;
	private String numeroSNSUtente;
	
	public Consulta(LocalDate dataConulta, LocalTime horaConsulta, String servicoId, String numeroSNSUtente) {
		this.dataConulta = dataConulta;
		this.horaConsulta = horaConsulta;
		this.servicoId = servicoId;
		this.numeroSNSUtente = numeroSNSUtente;
	}

	public LocalDate getDataConulta() {
		return dataConulta;
	}

	public void setDataConulta(LocalDate dataConulta) {
		this.dataConulta = dataConulta;
	}

	public LocalTime getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(LocalTime horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	public String getServicoId() {
		return servicoId;
	}

	public void setServicoId(String servicoId) {
		this.servicoId = servicoId;
	}

	public String getNumeroSNSUtente() {
		return numeroSNSUtente;
	}

	public void setNumeroSNSUtente(String numeroSNSUtente) {
		this.numeroSNSUtente = numeroSNSUtente;
	}


	public String toString() {
		return "Consulta - Data: " + dataConulta + ", Hora: " + horaConsulta + ", servicoId: (" + servicoId
				+ "), Utente (NºSNS): " + numeroSNSUtente;
	}
	


	
}
