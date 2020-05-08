package gestsaude.recurso;

import java.time.LocalDate;
import java.time.LocalTime;

/** Representa uma Consulta 
 */
public class Consulta {

/*	Esta classe representa uma consulta. Deve ter a indicação do utente, do serviço e o dia e
	hora da consulta. Deve ter construtores, getters e setters relevantes. */
	
	private LocalDate dataConsulta;
	private LocalTime horaConsulta;
	private String servicoId;
	private String numeroSNSUtente;
	private Senha senha;
	
	public Consulta(LocalDate dataConsulta, LocalTime horaConsulta, String servicoId, String numeroSNSUtente) {
		setDataConulta(dataConsulta);
		setHoraConsulta(horaConsulta);
		setServicoId(servicoId);
		setNumeroSNSUtente(numeroSNSUtente);
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

	public void setNumeroSNSUtente(String numeroSNSUtente) { 
		if (Integer.parseInt(numeroSNSUtente) <= 0)
			return;
		this.numeroSNSUtente = numeroSNSUtente;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public String toString() {
		return "\n Consulta - Data: " + dataConsulta + ", Hora: " + horaConsulta + ", servicoId: (" + servicoId
				+ "), Utente (NºSNS): " + numeroSNSUtente;
	}
	
}
