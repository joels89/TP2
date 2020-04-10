package gestsaude.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import javax.swing.Timer;

import gestsaude.recurso.Consulta;
import gestsaude.recurso.GEstSaude;
import gestsaude.recurso.Servico;
import gestsaude.recurso.Utente;
import gestsaude.util.RelogioSimulado;

/** Classe respons�vel pelo arranque do sistema.
 * Tem um m�todo para criar a configura��o de teste
 */
public class Arranque {
	
	
	public static final LocalDate today = LocalDate.now();
	public static final LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
	public static final LocalDate yesterday = tomorrow.minusDays(2);

	/** m�todo qeu cria a configura��o inicial do sistema
	 * @return um GEstSaude j� completamente configurado
	 */
	/**
	 * @return
	 */
	public static GEstSaude getGEstSaude() {
		GEstSaude gest = new GEstSaude();
		
//	deve ter os seguintes utentes, a informa��o � n�mero, nome
//	120, D�lia Ribeiro Sanches
	Utente Dalia = new Utente(120,"D�lia Ribeiro Sanches");
	System.out.println(Dalia);
//	121, Raquel Marques Soares
	Utente Raquel = new Utente(121,"Raquel Marques Soares");
//	122, Daniel Mendes Rodrigues
	Utente Daniel = new Utente(122,"Daniel Mendes Rodrigues");
	Utente Zeferino = new Utente(123, "Zeferino Dias Torres");
//	123, Zeferino Dias Torres
	Utente Anabela = new Utente(124, "Anabela Dias Santos");
//	124, Anabela Dias Santos
	Utente Felizbetrto = new Utente(125, "Felizbetrto Desgra�ado");
//	125, Felizbetrto Desgra�ado
	Utente Antonina = new Utente(126, "Antonina Martins Pires");
//	126, Antonina Martins Pires
	Utente Camale�o = new Utente(127, "Camale�o das Neves Freitas");
//	127, Camale�o das Neves Freitas
	Utente Jo�o = new Utente(128, "Jo�o Pais Pereira");
//	128, Jo�o Pais Pwreira
	Utente Carlos = new Utente(129, "Carlos Freitas Lobo");
//	129, Carlos Freitas Lobo
	Utente DanielMendes = new Utente(130, "Daniel Mendes Rodrigues");
//	130, Daniel Mendes Rodrigues
//
//	deve ter os seguintes servi�os que aceitam consultas (identifica��o, Nome do servi�o)
	Servico PediatriaQuena = new Servico("Ped1", "Pediatria - Dr� P. Quena");
//	Ped1, Pediatria - Dr� P. Quena
	Servico PediatriaZinho = new Servico("Ped2", "Pediatria - Dr B. B. Zinho");
//	Ped2, Pediatria - Dr B. B. Zinho
	Servico OrtopediaOssos = new Servico("Orto1", "Ortopedia - Dr Ossos");
//	Orto1, Ortopedia - Dr Ossos
	Servico OrtopediaEntorse = new Servico("Orto2", "Ortopedia - Dr� Entorse");
//	Orto2, Ortopedia - Dr� Entorse
	Servico OtorrinoNarize = new Servico("Oto1", "Otorrino - Dr Narize");
//	Oto1, Otorrino - Dr Narize
	Servico OtorrinoOdete = new Servico("Oto2", "Otorrino - Dr� Odete Otite");
//	Oto2, Otorrino - Dr� Odete Otite
	Servico PneumologiaPaula = new Servico("Pneu1", "Pneumologia - Dr� Paula M�o");
//	Pneu1, Pneumologia - Dr� Paula M�o
	Servico DermatologiaRuga = new Servico("Derm1", "Dermatologia - Dr V. Ruga");
//	Derm1, Dermatologia - Dr V. Ruga
	Servico CardiologiaCard1 = new Servico("Card1", "Cardiologia - Dr Paul Sass�o");
//	Card1, Cardiologia - Dr Paul Sass�o
	Servico OftalmologiaOfta1 = new Servico("Ofta1", "Oftalmologia - Dr� �ris Tapada");
//	Ofta1, Oftalmologia - Dr� �ris Tapada
	Servico OftalmologiaAler = new Servico("Aler", "Alergologia - Dr E. S. Pirro");
//	Aler, Alergologia - Dr E. S. Pirro
	
	Servico servPed1 = new Servico("Ped1", "Pediatria - Dr� P. Quena");
	System.out.println(servPed1);
//		
// deve ter os seguintes servi�os que N�O aceitam consultas (identifica��o, Nome do servi�o)
	
//	Rad, Radiologia
//	Audio, Audiometria
//	Scopia, Endo/Colonoscopia
//	Enf, Enfermaria
//	NeuLab, EEG + Dopler
	
	
	

    /*
// Deve adicionar as seguntes consultas (data e hora, ide do servi�o, id utente)
	Consulta consulta1 = new Consulta(121, "Ped1",today, LocalTime.of(8, 10, 00));
//	Hoje 8h10, Ped1, 120
	Consulta consulta2 = new Consulta(121, "Ped2",today, LocalTime.of(8, 10, 00));
//	Hoje 8h10, Ped2, 121
	Consulta consulta3 = new Consulta(122, "Orto1",today, LocalTime.of(8, 10, 00));
//	Hoje 8h10, Orto1, 122
	Consulta consulta4 = new Consulta(125, "Derm1",today, LocalTime.of(8, 20, 00));
//	Hoje 8h20, Derm1, 125
	Consulta consulta5 = new Consulta(126, "Ped1",today, LocalTime.of(8, 30, 00));
//	Hoje 8h30, Ped1, 126
	Consulta consulta6 = new Consulta(127, "Ped1",today, LocalTime.of(8, 40, 00));
//	Hoje 8h40, Ped1, 127
//	
	Consulta consulta7 = new Consulta(127, "Ped1",tomorrow, LocalTime.of(8, 10, 00));
//	Amanh� 8h10, Ped1, 127
	Consulta consulta8 = new Consulta(129, "Ped1",tomorrow, LocalTime.of(8, 10, 00));
//	Amanh� 8h10, Ped1, 129
	Consulta consulta9 = new Consulta(129, "Ped1",today.plus(2, ChronoUnit.DAYS), LocalTime.of(8, 10, 00));
//	Daqui a dois dias 8h40, Ped1, 123*/
	Consulta consulta1 = new Consulta(LocalDate.now(), LocalTime.now(), servPed1, Dalia);
	Consulta consulta2 = new Consulta(LocalDate.of(2020, 5, 20), LocalTime.of(8, 10), servPed1, Raquel);
	System.out.println(consulta1);	
	
	gest.addConsulta(consulta1);
	System.out.println(servPed1);
	System.out.println(Dalia);
		return gest;
	}

	/** arranque do sistema
	 */
	public static void main(String[] args) {
		// criar o GEstSaude
		GEstSaude gs = getGEstSaude();
		
		// Criar o rel�gio simulado e definir o tempo por segundo
		RelogioSimulado relogio = RelogioSimulado.getRelogioSimulado();
		relogio.setTicksPorSegundo( 60 ); 
		
		// criar as m�quina de entrada, neste caso ir� ter duas
		MaquinaEntrada me1 = new MaquinaEntrada( new Point(10, 10), "Entrada 1", gs );
		me1.setVisible( true );
		MaquinaEntrada me2 = new MaquinaEntrada( new Point(10, 140), "Entrada 2", gs );
		me2.setVisible( true );
		
		// criar o menu da secretaria, neste caso ir� ter apenas um
		MenuSecretaria lc = new MenuSecretaria( new Point( 230, 10 ), "Secretaria", gs);
		lc.setVisible( true );
		
		// criar todos os menus de servi�o
		Collection<Servico> servs = gs.getServicos(); 
		int serIdx = 0;
		MenuServico menus[] = new MenuServico[0 /*servs.size()*/ ];
		/*
		 * for( Servico s : servs ) { menus[serIdx] = new MenuServico( new Point( 10 +
		 * (serIdx % 5)*200, 250 + (serIdx / 5 )*170 ), s, gs );
		 * menus[serIdx].setVisible( true ); serIdx++; }
		 */
		
		// criar um temporizador que vai atualizando as v�rias janelas
		// do menu de servi�os, de 10 em 10 segundos (10000 milisegundos)
		Timer t = new Timer( 10000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for( MenuServico ms : menus )
					ms.atualizarInfo();
				 lc.atualizarRelogio();
			}
		});
		t.start();
	}	
}
