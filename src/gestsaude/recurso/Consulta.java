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
	private LocalDate data;
	private LocalTime hora;
	
	
	public Consulta(Utente utente, Servico servico, LocalDate data, LocalTime hora) {
		this.utente = utente;
		this.servico = servico;
		this.data = data;
		this.hora = hora;
	}
	
	
	

	

}
