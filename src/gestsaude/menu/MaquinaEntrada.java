package gestsaude.menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import gestsaude.recurso.*;
import gestsaude.util.Consultas;
import gestsaude.util.RelogioSimulado;

/** Representa uma máquina de entrada, onde os clientes retiram as senhas
 * Neste caso, apenas permite retirar as senhas para confirmar consulta 
 */
public class MaquinaEntrada extends javax.swing.JDialog {
	
	private static final long serialVersionUID = 1L;
	private static final Dimension tamanhoBt = new Dimension( 170, 50); // tamanho dos botões
	public static final int TRES = 3;
	
	private GEstSaude gest;

	/** Cria uma janela para uma máquina de entrada
	 * @param posicao onde fica a janela
	 * @param nome Título da janela
	 * @param gs O sistema
	 */
	public MaquinaEntrada( Point posicao, String nome, GEstSaude gs ) {
		setTitle( "Máquina - " + nome );
		setupAspeto();
		setLocation( posicao );
		gest = gs;
	}

	/** método chamado quando se pressiona o botão "Validar Consulta"*/
	private void validarConsulta() {
		String numSns = JOptionPane.showInputDialog(this, "Qual o seu número de SNS?");
		Utente u = null;
		u = gest.getUtente(numSns);
		//Utente u = null;
		if( u != null ) {
			String nome = u.getNomeUtente();
			
			if (RelogioSimulado.getTempoAtual().toLocalTime().compareTo(LocalTime.of(8, 00)) < 0 ||
					RelogioSimulado.getTempoAtual().toLocalTime().compareTo(LocalTime.of(20, 00)) > 0)
			{
				JOptionPane.showMessageDialog( this, nome + ", O serviço de senhas só funciona entre as 8 e as 20 horas");
				return;
			}
			
			if((Consultas.getConsultasDoDia(u.getConsultasMarcadas(), RelogioSimulado.getTempoAtual().toLocalDate())).size() == 0) {
				JOptionPane.showMessageDialog( this, nome + ", não tem consultas hoje!" );
				return;
			}

			// TODO testar se o cliente tem uma consulta nas 3 horas anteriores e próximas  LocalTime.of(8, 30) por este no FINAL LocalTime.now() ---------TODO	
			if(Consultas.getConsultaEntreDatas(u.getConsultasMarcadas(), RelogioSimulado.getTempoAtual().minusHours(TRES), RelogioSimulado.getTempoAtual().plusHours(TRES)).isEmpty()) {
				JOptionPane.showMessageDialog( this, nome + ", não tem consultas nas próximas 3 horas!" );
				return;
			}
			Senha senha = gest.emiteSenha(u.getConsultasMarcadas().get(0), RelogioSimulado.getTempoAtual());
			
			JOptionPane.showMessageDialog( this, nome + ", a sua senha é " + senha.getIdSenha());			
		} else {
			JOptionPane.showMessageDialog( this, "Número inválido" );
		}
	}

	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	
	/** método que define o aspeto desta janela
	 */
	private void setupAspeto() {
		setLayout( new GridLayout(0,1) );
		JButton validarBt = new JButton("Validar Consulta");
		validarBt.setMinimumSize( tamanhoBt );
		validarBt.setPreferredSize( tamanhoBt );
		validarBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validarConsulta();
			}
		});
		add( validarBt );
		pack();
	}
}
