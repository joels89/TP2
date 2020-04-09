package gestsaude.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
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
//	121, Raquel Marques Soares
//	122, Daniel Mendes Rodrigues
//	123, Zeferino Dias Torres
//	124, Anabela Dias Santos
//	125, Felizbetrto Desgraçado
//	126, Antonina Martins Pires
//	127, Camaleão das Neves Freitas
//	128, João Pais Pwreira
//	129, Carlos Freitas Lobo
//	130, Daniel Mendes Rodrigues
//
//	deve ter os seguintes serviços que aceitam consultas (identificação, Nome do serviço)
	Servico Pediatria1 = new Servico("Ped1", "Pediatria - Drª P. Quena");
//	Ped1, Pediatria - Drª P. Quena
//	Ped2, Pediatria - Dr B. B. Zinho
//	Orto1, Ortopedia - Dr Ossos
//	Orto2, Ortopedia - Drª Entorse
//	Oto1, Otorrino - Dr Narize
//	Oto2, Otorrino - Drª Odete Otite
//	Pneu1, Pneumologia - Drª Paula Mão
//	Derm1, Dermatologia - Dr V. Ruga
//	Card1, Cardiologia - Dr Paul Sassão
//	Ofta1, Oftalmologia - Drª Íris Tapada
//	Aler, Alergologia - Dr E. S. Pirro
//		
// deve ter os seguintes serviços que NÃO aceitam consultas (identificação, Nome do serviço)
//	Rad, Radiologia
//	Audio, Audiometria
//	Scopia, Endo/Colonoscopia
//	Enf, Enfermaria
//	NeuLab, EEG + Dopler
		
// Deve adicionar as seguntes consultas (data e hora, ide do serviço, id utente)
	Consulta consulta = new Consulta(Dalia, Pediatria1,LocalDate.of(2020, 4, 06), LocalTime.of(8, 00, 00));
//	Hoje 8h10, Ped1, 120
//	Hoje 8h10, Ped2, 121
//	Hoje 8h10, Orto1, 122
//	Hoje 8h20, Derm1, 125
//	Hoje 8h30, Ped1, 126
//	Hoje 8h40, Ped1, 127
//	
//	Amanhã 8h10, Ped1, 127
//	Amanhã 8h10, Ped1, 129
//	Daqui a dois dias 8h40, Ped1, 123
				
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
