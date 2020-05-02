package gestsaude.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import javax.swing.Timer;

import gestsaude.recurso.Consulta;
import gestsaude.recurso.GEstSaude;
import gestsaude.recurso.Senha;
import gestsaude.recurso.Servico;
import gestsaude.recurso.Utente;
import gestsaude.util.RelogioSimulado;

/** Classe responsável pelo arranque do sistema.
 * Tem um método para criar a configuração de teste
 */
public class Arranque {


	public static final LocalDate HOJE = LocalDate.now();
	public static final LocalDate AMANHA = HOJE.plus(1, ChronoUnit.DAYS);
	public static final LocalDate ONTEM = HOJE.minusDays(1);

	/** método qeu cria a configuração inicial do sistema
	 * @return um GEstSaude já completamente configurado
	 */
	/**
	 * @return
	 */
	
///////// Tarefas////////////

	//Classe Utente - OK
	//Classe Consulta - OK
	
	//Classe GestSaude - OK tirar so sysout no final
	//Classe Servico	
		//rejeitaProximaSenha()  ver onde este metodo sera usado
		//// TODO	verificar se devia ser com SORT ver FAQs do prof
	
	
	public static GEstSaude getGEstSaude() {
		GEstSaude gest = new GEstSaude();

		Utente utente120 = new Utente("120","Dália Ribeiro Sanches");
		Utente utente121 = new Utente("121","Raquel Marques Soares");
		Utente utente122 = new Utente("122","Daniel Mendes Rodrigues");
		Utente utente123 = new Utente("123", "Zeferino Dias Torres");
		Utente utente124 = new Utente("124", "Anabela Dias Santos");
		Utente utente125 = new Utente("125", "Felizbetrto Desgraçado");
		Utente utente126 = new Utente("126", "Antonina Martins Pires");
		Utente utente127 = new Utente("127", "Camaleão das Neves Freitas");
		Utente utente128 = new Utente("128", "João Pais Pereira");
		Utente utente129 = new Utente("129", "Carlos Freitas Lobo");
		Utente utente130 = new Utente("130", "Daniel Mendes Rodrigues");





		Servico ped1 = new Servico("Ped1", "Pediatria - Drª P. Quena");
		Servico ped2 = new Servico("Ped2", "Pediatria - Dr B. B. Zinho");
		Servico orto1 = new Servico("Orto1", "Ortopedia - Dr Ossos");
		Servico orto2 = new Servico("Orto2", "Ortopedia - Drª Entorse");
		Servico oto1 = new Servico("Oto1", "Otorrino - Dr Narize");
		Servico oto2 = new Servico("Oto2", "Otorrino - Drª Odete Otite");
		Servico pneu1 = new Servico("Pneu1", "Pneumologia - Drª Paula Mão");
		Servico derm1 = new Servico("Derm1", "Dermatologia - Dr V. Ruga");
		Servico card1 = new Servico("Card1", "Cardiologia - Dr Paul Sassão");
		Servico ofta1 = new Servico("Ofta1", "Oftalmologia - Drª Íris Tapada");
		Servico aler = new Servico("Aler", "Alergologia - Dr E. S. Pirro");
		


		Servico Rad = new Servico ("Rad","Radiologia");
		Servico Audio = new Servico ("Audio","Audiometria");
		Servico Scopia = new Servico ("Scopia","Endo/Colonoscopia");
		Servico Enf = new Servico ("Enf","Enfermaria");
		Servico NeuLab = new Servico ("Enf","EEG + Dopler");


		// Deve adicionar as seguntes consultas (data e hora, ide do serviço, id utente)
		Consulta consulta1 = new Consulta(HOJE, LocalTime.of(8,10), Enf.getServicoId(), utente120.getNumeroSNS());
		Consulta consulta2 = new Consulta(HOJE, LocalTime.of(8,10), ped2.getServicoId(), utente121.getNumeroSNS());
		Consulta consulta3 = new Consulta(HOJE, LocalTime.of(8,10), orto1.getServicoId(), utente122.getNumeroSNS());
		Consulta consulta4 = new Consulta(HOJE, LocalTime.of(8,20), derm1.getServicoId(), utente125.getNumeroSNS());
		Consulta consulta5 = new Consulta(HOJE, LocalTime.of(8,30), ped1.getServicoId(), utente126.getNumeroSNS());
		Consulta consulta6 = new Consulta(HOJE, LocalTime.of(8,40), ped1.getServicoId(), utente127.getNumeroSNS());
		Consulta consulta7 = new Consulta(AMANHA, LocalTime.of(8,10), ped1.getServicoId(), utente127.getNumeroSNS());
		Consulta consulta8 = new Consulta(AMANHA, LocalTime.of(8,10), ped1.getServicoId(), utente129.getNumeroSNS());
		Consulta consulta9 = new Consulta(HOJE.plusDays(2), LocalTime.of(8,40), ped1.getServicoId(), utente123.getNumeroSNS());



		gest.addUtente(utente120);
		gest.addUtente(utente121);
		gest.addUtente(utente122);
		gest.addUtente(utente123);
		gest.addUtente(utente124);
		gest.addUtente(utente125);
		gest.addUtente(utente126);
		gest.addUtente(utente127);
		gest.addUtente(utente128);
		gest.addUtente(utente129);
		gest.addUtente(utente130);

		gest.addServico(ped1);
		gest.addServico(ped2);
		gest.addServico(orto1);
		gest.addServico(orto2);
		gest.addServico(oto1);
		gest.addServico(oto2);		
		gest.addServico(pneu1);
		gest.addServico(ped2);
		gest.addServico(derm1);
		gest.addServico(card1);
		gest.addServico(ofta1);
		gest.addServico(aler);
		gest.addServico(Rad);
		gest.addServico(Audio);
		gest.addServico(Scopia);
		gest.addServico(Enf);
		gest.addServico(NeuLab);



		gest.addConsulta(consulta1);
		gest.addConsulta(consulta2);
		gest.addConsulta(consulta3);
		gest.addConsulta(consulta4);
		gest.addConsulta(consulta5);
		gest.addConsulta(consulta6);
		gest.addConsulta(consulta7);
		gest.addConsulta(consulta8);
		gest.addConsulta(consulta9);


		return gest;
	}

	/** arranque do sistema
	 */
	public static void main(String[] args) {
		// criar o GEstSaude
		GEstSaude gs = getGEstSaude();

		// Criar o relógio simulado e definir o tempo por segundo
		RelogioSimulado relogio = RelogioSimulado.getRelogioSimulado();
		relogio.setTicksPorSegundo( 1); 

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
		MenuServico menus[] = new MenuServico[servs.size()];

		for( Servico s : servs ) { menus[serIdx] = new MenuServico( new Point( 10 +
				(serIdx % 5)*200, 250 + (serIdx / 5 )*170 ), s, gs );
		menus[serIdx].setVisible( true ); serIdx++; }


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
