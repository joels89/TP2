package gestsaude.recurso;

import java.time.LocalDate;
import java.time.LocalTime;

/** Representa uma Consulta 
 */
public class Consulta 
{

/*	Esta classe representa uma consulta. Deve ter a indica��o do utente, do servi�o e o dia e
	hora da consulta. Deve ter construtores, getters e setters relevantes. */
	
	private LocalDate dataConsulta;
	private LocalTime horaConsulta;
	private String servicoId;
	private String numeroSNSUtente;
	
	public Consulta(LocalDate dataConsulta, LocalTime horaConsulta, String servicoId, String numeroSNSUtente) {
		setDataConulta(dataConsulta);
		setHoraConsulta(horaConsulta);
		setServicoId(servicoId);
		this.numeroSNSUtente = numeroSNSUtente;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
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

	public void setNumeroSNSUtente(String numeroSNSUtente) { ////////// TODo eu removia este ou punha privado piois este valor nao deveria por se r alterado
		this.numeroSNSUtente = numeroSNSUtente;
	}

	public String toString() {
		return "\n Consulta - Data: " + dataConsulta + ", Hora: " + horaConsulta + ", servicoId: (" + servicoId
				+ "), Utente (N�SNS): " + numeroSNSUtente;
	}
	
}
