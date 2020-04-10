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

/** Classe responsável pelo arranque do sistema.
 * Tem um método para criar a configuração de teste
 */
public class Arranque {
	
	
	public static final LocalDate today = LocalDate.now();
	public static final LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
	public static final LocalDate yesterday = tomorrow.minusDays(2);

	/** método qeu cria a configuração inicial do sistema
	 * @return um GEstSaude já completamente configurado
	 */
	/**
	 * @return
	 */
	public static GEstSaude getGEstSaude() {
		GEstSaude gest = new GEstSaude();
		
//	deve ter os seguintes utentes, a informação é número, nome
//	120, Dália Ribeiro Sanches
	Utente Dalia = new Utente(120,"Dália Ribeiro Sanches");
	System.out.println(Dalia);
//	121, Raquel Marques Soares
	Utente Raquel = new Utente(121,"Raquel Marques Soares");
//	122, Daniel Mendes Rodrigues
	Utente Daniel = new Utente(122,"Daniel Mendes Rodrigues");
	Utente Zeferino = new Utente(123, "Zeferino Dias Torres");
//	123, Zeferino Dias Torres
	Utente Anabela = new Utente(124, "Anabela Dias Santos");
//	124, Anabela Dias Santos
	Utente Felizbetrto = new Utente(125, "Felizbetrto Desgraçado");
//	125, Felizbetrto Desgraçado
	Utente Antonina = new Utente(126, "Antonina Martins Pires");
//	126, Antonina Martins Pires
	Utente Camaleão = new Utente(127, "Camaleão das Neves Freitas");
//	127, Camaleão das Neves Freitas
	Utente João = new Utente(128, "João Pais Pereira");
//	128, João Pais Pwreira
	Utente Carlos = new Utente(129, "Carlos Freitas Lobo");
//	129, Carlos Freitas Lobo
	Utente DanielMendes = new Utente(130, "Daniel Mendes Rodrigues");
//	130, Daniel Mendes Rodrigues
//
//	deve ter os seguintes serviços que aceitam consultas (identificação, Nome do serviço)
	Servico PediatriaQuena = new Servico("Ped1", "Pediatria - Drª P. Quena");
//	Ped1, Pediatria - Drª P. Quena
	Servico PediatriaZinho = new Servico("Ped2", "Pediatria - Dr B. B. Zinho");
//	Ped2, Pediatria - Dr B. B. Zinho
	Servico OrtopediaOssos = new Servico("Orto1", "Ortopedia - Dr Ossos");
//	Orto1, Ortopedia - Dr Ossos
	Servico OrtopediaEntorse = new Servico("Orto2", "Ortopedia - Drª Entorse");
//	Orto2, Ortopedia - Drª Entorse
	Servico OtorrinoNarize = new Servico("Oto1", "Otorrino - Dr Narize");
//	Oto1, Otorrino - Dr Narize
	Servico OtorrinoOdete = new Servico("Oto2", "Otorrino - Drª Odete Otite");
//	Oto2, Otorrino - Drª Odete Otite
	Servico PneumologiaPaula = new Servico("Pneu1", "Pneumologia - Drª Paula Mão");
//	Pneu1, Pneumologia - Drª Paula Mão
	Servico DermatologiaRuga = new Servico("Derm1", "Dermatologia - Dr V. Ruga");
//	Derm1, Dermatologia - Dr V. Ruga
	Servico CardiologiaCard1 = new Servico("Card1", "Cardiologia - Dr Paul Sassão");
//	Card1, Cardiologia - Dr Paul Sassão
	Servico OftalmologiaOfta1 = new Servico("Ofta1", "Oftalmologia - Drª Íris Tapada");
//	Ofta1, Oftalmologia - Drª Íris Tapada
	Servico OftalmologiaAler = new Servico("Aler", "Alergologia - Dr E. S. Pirro");
//	Aler, Alergologia - Dr E. S. Pirro
	
	Servico servPed1 = new Servico("Ped1", "Pediatria - Drª P. Quena");
	System.out.println(servPed1);
//		
// deve ter os seguintes serviços que NÃO aceitam consultas (identificação, Nome do serviço)
	
//	Rad, Radiologia
//	Audio, Audiometria
//	Scopia, Endo/Colonoscopia
//	Enf, Enfermaria
//	NeuLab, EEG + Dopler
	
	
	

    /*
// Deve adicionar as seguntes consultas (data e hora, ide do serviço, id utente)
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
//	Amanhã 8h10, Ped1, 127
	Consulta consulta8 = new Consulta(129, "Ped1",tomorrow, LocalTime.of(8, 10, 00));
//	Amanhã 8h10, Ped1, 129
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
		
		// Criar o relógio simulado e definir o tempo por segundo
		RelogioSimulado relogio = RelogioSimulado.getRelogioSimulado();
		relogio.setTicksPorSegundo( 60 ); 
		
		// criar as máquina de entrada, neste caso irá ter duas
		MaquinaEntrada me1 = new MaquinaEntrada( new Point(10, 10), "Entrada 1", gs );
		me1.setVisible( true );
		MaquinaEntrada me2 = new MaquinaEntrada( new Point(10, 140), "Entrada 2", gs );
		me2.setVisible( true );
		
		// criar o menu da secretaria, neste caso irá ter apenas um
		MenuSecretaria lc = new MenuSecretaria( new Point( 230, 10 ), "Secretaria", gs);
		lc.setVisible( true );
		
		// criar todos os menus de serviço
		Collection<Servico> servs = gs.getServicos(); 
		int serIdx = 0;
		MenuServico menus[] = new MenuServico[0 /*servs.size()*/ ];
		/*
		 * for( Servico s : servs ) { menus[serIdx] = new MenuServico( new Point( 10 +
		 * (serIdx % 5)*200, 250 + (serIdx / 5 )*170 ), s, gs );
		 * menus[serIdx].setVisible( true ); serIdx++; }
		 */
		
		// criar um temporizador que vai atualizando as várias janelas
		// do menu de serviços, de 10 em 10 segundos (10000 milisegundos)
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
