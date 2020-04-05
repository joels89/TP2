package gestsaude.menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gestsaude.recurso.*;

/** Representa uma máquina de entrada, onde os clientes retiram as senhas
 * Neste caso, apenas permite retirar as senhas para confirmar consulta 
 */
public class MaquinaEntrada extends javax.swing.JDialog {
	
	private static final long serialVersionUID = 1L;
	private static final Dimension tamanhoBt = new Dimension( 170, 50); // tamanho dos botões
	
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
		// TODO associar o id ao utente
		Utente u = null;
		if( u != null ) {
			String nome = "NOME UTENTE"; // TODO inicializar nome do utente
			// TODO verificar se o utente tem consulta hoje 
			if( false ) {
				JOptionPane.showMessageDialog( this, nome + ", não tem consultas hoje!" );
				return;
			}
			// TODO testar se o cliente tem uma consulta nas 3 horas anteriores e próximas
			if( false ){
				JOptionPane.showMessageDialog( this, nome + ", não tem consultas nas próximas 3 horas!" );
				return;
			}
			// TODO emitir a senha 
			JOptionPane.showMessageDialog( this, nome + ", a sua senha é " + "NUM SENHA" ); // TODO colocar aqui o número da senha			
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
