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
	
	
	public static final LocalDate HOJE = LocalDate.now();
	public static final LocalDate AMANHA = HOJE.plus(1, ChronoUnit.DAYS);
	public static final LocalDate ONTEM = AMANHA.minusDays(2);

	/** m�todo qeu cria a configura��o inicial do sistema
	 * @return um GEstSaude j� completamente configurado
	 */
	/**
	 * @return
	 */
	public static GEstSaude getGEstSaude() {
		GEstSaude gest = new GEstSaude();
		
		
		
		
//		deve ter os seguintes utentes, a informa��o � n�mero, nome
//		120, D�lia Ribeiro Sanches
//		121, Raquel Marques Soares
//		122, Daniel Mendes Rodrigues
//		123, Zeferino Dias Torres
//		124, Anabela Dias Santos
//		125, Felizbetrto Desgra�ado
//		126, Antonina Martins Pires
//		127, Camale�o das Neves Freitas
//		128, Jo�o Pais Pwreira
//		129, Carlos Freitas Lobo
//		130, Daniel Mendes Rodrigues
		
		
	Utente utente120 = new Utente("120","D�lia Ribeiro Sanches");
	Utente utente121 = new Utente("121","Raquel Marques Soares");
	Utente utente122 = new Utente("122","Daniel Mendes Rodrigues");
	Utente utente123 = new Utente("123", "Zeferino Dias Torres");
	Utente utente124 = new Utente("124", "Anabela Dias Santos");
	Utente utente125 = new Utente("125", "Felizbetrto Desgra�ado");
	Utente utente126 = new Utente("126", "Antonina Martins Pires");
	Utente utente127 = new Utente("127", "Camale�o das Neves Freitas");
	Utente utente128 = new Utente("128", "Jo�o Pais Pereira");
	Utente utente129 = new Utente("129", "Carlos Freitas Lobo");
	Utente utente130 = new Utente("130", "Daniel Mendes Rodrigues");
	
	
	

//	deve ter os seguintes servi�os que aceitam consultas (identifica��o, Nome do servi�o)
	Servico ped1 = new Servico("Ped1", "Pediatria - Dr� P. Quena");
//	Ped1, Pediatria - Dr� P. Quena
	Servico ped2 = new Servico("Ped2", "Pediatria - Dr B. B. Zinho");
//	Ped2, Pediatria - Dr B. B. Zinho
	Servico orto1 = new Servico("Orto1", "Ortopedia - Dr Ossos");
//	Orto1, Ortopedia - Dr Ossos
	Servico orto2 = new Servico("Orto2", "Ortopedia - Dr� Entorse");
//	Orto2, Ortopedia - Dr� Entorse
	Servico oto1 = new Servico("Oto1", "Otorrino - Dr Narize");
//	Oto1, Otorrino - Dr Narize
	Servico oto2 = new Servico("Oto2", "Otorrino - Dr� Odete Otite");
//	Oto2, Otorrino - Dr� Odete Otite
	Servico pneu1 = new Servico("Pneu1", "Pneumologia - Dr� Paula M�o");
//	Pneu1, Pneumologia - Dr� Paula M�o
	Servico derm1 = new Servico("Derm1", "Dermatologia - Dr V. Ruga");
//	Derm1, Dermatologia - Dr V. Ruga
	Servico card1 = new Servico("Card1", "Cardiologia - Dr Paul Sass�o");
//	Card1, Cardiologia - Dr Paul Sass�o
	Servico ofta1 = new Servico("Ofta1", "Oftalmologia - Dr� �ris Tapada");
//	Ofta1, Oftalmologia - Dr� �ris Tapada
	Servico aler = new Servico("Aler", "Alergologia - Dr E. S. Pirro");
//	Aler, Alergologia - Dr E. S. Pirro
	
	System.out.println(ped1);
//		
// deve ter os seguintes servi�os que N�O aceitam consultas (identifica��o, Nome do servi�o)
	
//	Rad, Radiologia
//	Audio, Audiometria
//	Scopia, Endo/Colonoscopia
//	Enf, Enfermaria
//	NeuLab, EEG + Dopler
	
	
	

 
// Deve adicionar as seguntes consultas (data e hora, ide do servi�o, id utente)
	Consulta consulta1 = new Consulta(HOJE, LocalTime.of(8,10), ped1.getServicoId(), utente120.getNumeroSNS());
	Consulta consulta2 = new Consulta(HOJE, LocalTime.of(8,10), ped2.getServicoId(), utente121.getNumeroSNS());
	Consulta consulta3 = new Consulta(HOJE, LocalTime.of(8,10), orto1.getServicoId(), utente122.getNumeroSNS());
	Consulta consulta4 = new Consulta(HOJE, LocalTime.of(8,20), derm1.getServicoId(), utente125.getNumeroSNS());
	Consulta consulta5 = new Consulta(HOJE, LocalTime.of(8,30), ped1.getServicoId(), utente126.getNumeroSNS());
	Consulta consulta6 = new Consulta(HOJE, LocalTime.of(8,40), ped1.getServicoId(), utente127.getNumeroSNS());
	Consulta consulta7 = new Consulta(AMANHA, LocalTime.of(8,10), ped1.getServicoId(), utente127.getNumeroSNS());
	Consulta consulta8 = new Consulta(AMANHA, LocalTime.of(8,10), ped1.getServicoId(), utente129.getNumeroSNS());
	Consulta consulta9 = new Consulta(HOJE.plusDays(2), LocalTime.of(8,40), ped1.getServicoId(), utente123.getNumeroSNS());
//	Hoje 8h10, Ped1, 120
//	Hoje 8h10, Ped2, 121
//	Hoje 8h10, Orto1, 122
//	Hoje 8h20, Derm1, 125
//	Hoje 8h30, Ped1, 126
//	Hoje 8h40, Ped1, 127
//	Amanh� 8h10, Ped1, 127
//	Amanh� 8h10, Ped1, 129
//	Daqui a dois dias 8h40, Ped1, 123*/

	
	System.out.println(consulta1);	
	System.out.println(consulta2);
	gest.addUtente(utente120);
	gest.addUtente(utente121);
	gest.addUtente(utente122);
	gest.addServico(ped1);

	
	
	//gest.addConsulta(consulta1);
	gest.addConsulta(consulta2);
	System.out.println(ped1);
	System.out.println(utente120);
	
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
