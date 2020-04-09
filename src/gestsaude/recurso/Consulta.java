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

/*	Esta classe representa uma consulta. Deve ter a indica��o do utente, do servi�o e o dia e
	hora da consulta. Deve ter construtores, getters e setters relevantes. */
	
	private int utente;
	private String servico;
	private LocalDate data;
	private LocalTime hora;
	
	
	public Consulta(int utente, String servico, LocalDate data, LocalTime hora) {
		this.utente = utente;
		this.servico = servico;
		this.data = data;
		this.hora = hora;
	}


	public int getUtente() {
		return utente;
	}


	public void setUtente(int utente) {
		this.utente = utente;
	}


	public String getServico() {
		return servico;
	}


	public void setServico(String servico) {
		this.servico = servico;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public LocalTime getHora() {
		return hora;
	}


	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	

	@Override
	public String toString() {
		return "Consulta [utente=" + utente + ", servico=" + servico + ", data=" + data + ", hora=" + hora + "]";
	}
	
}
