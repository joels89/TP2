package testes;

import java.time.LocalDate;
import java.time.LocalTime;

import gestsaude.recurso.Consulta;
import gestsaude.recurso.Servico;
import gestsaude.recurso.Utente;

public class TesteClasses {

	public static void main(String[] args) {
		
		
		
		Utente Dalia = new Utente(120,"Dália Ribeiro Sanches");
		Servico Pediatria1 = new Servico("Ped1", "Pediatria - Drª P. Quena");
		
		/*
		
		Consulta consulta3 = new Consulta(Dalia,Pediatria1,LocalDate.of(2020, 01, 01),LocalTime.of(9,00));
		Consulta consulta1 = new Consulta(Dalia,Pediatria1,LocalDate.now(),LocalTime.of(8,00));
		Consulta consulta = new Consulta(Dalia,Pediatria1,LocalDate.now(),LocalTime.of(9,00));
		Consulta consulta2 = new Consulta(Dalia,Pediatria1,LocalDate.of(2020, 05, 01),LocalTime.of(9,00));
	
		Dalia.addConsulta(consulta2);
		Dalia.addConsulta(consulta);
		Dalia.addConsulta(consulta1);
		Dalia.addConsulta(consulta3);
		
		
		
		
		
		// TODO Auto-generated method stub
		
		
		System.out.println(Dalia.getPresentes());	*/

	}
	

}
