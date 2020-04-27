package gestsaude.menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gestsaude.recurso.GEstSaude;
import gestsaude.recurso.Senha;
import gestsaude.recurso.Servico;

/** Janela de interação reservada a cada serviço
 *
 */
public class MenuServico extends JDialog {
	
	// dimensões dos botões
	private static final Dimension tamanhoBt = new Dimension( 170, 30);
	private static final int SEM_UTENTES = 0;

	private Servico servico;
	private Senha senha;
	private GEstSaude gest;

	// elementos gráficos usados na interface
	private static final long serialVersionUID = 1L;
	private JPanel menusPanel; 
	private CardLayout menusCard;
	private JButton proxBt;
	private JLabel senhaLbl, utenteLbl;
	

	/** Construtor da janela de Serviço
	 * @param pos posição onde por a janela
	 * @param s qual o serviço associado à janela
	 * @param gest o sistema
	 */
	public MenuServico( Point pos, Servico s, GEstSaude gest ) {
		setLocation( pos );
		servico = s;		
		setupAspeto();
		atualizarInfo();
		this.gest = gest;
	}
	
	/** método que chama o próximo utente 
	 * @return true se tem próximo utente
	 */
	private boolean proximoUtente() {
		// TODO ver se há senhas em espera e usar a próxima
		int idx = 0;
		senha = servico.getProximaSenha();
				
		if( senha == null)
			return false;
				
		while(!servico.equals(senha.proxServico()))
		{
			idx++;
			senha= servico.getProximaSenha(idx);
			System.out.println(servico);
			System.out.println(senha.proxServico());
			System.out.println(idx);
		}
						
		senhaLbl.setText( senha.getIdSenha());
		utenteLbl.setText( senha.getUtente().getNomeUtente());
		return true;
	}

	/** método chamado para rejeitar o utente */
	private void rejeitarUtente() 
	{
		servico.alteraPosiçaoSenha(senha);		
	}
		

	/** método chamado para confirmar a consulta */
	private void confirmarConsulta() 
	{
	
	}
	
	/** método chamado para finalizar a consulta */
	private void finalizarConsulta( ) 
	{			
		if(senha.getConsultasMarcadasServico().size() == 1) //caso seja o ultimo serviço da senha a visitar
		{
			servico.removeSenhaServico(senha);
			senha.terminaConsulta(servico);
			servico.removeConsultasServico(senha.getConsulta()); 
			gest.getSenhas().remove(senha.getIdSenha()); //apaga a senha do sistema
			gest.removeConsulta(senha.getConsulta()); // apaga a consulta do sistema
		}
		else
		{
			senha.terminaConsulta(servico);
			servico.removeSenhaServico(senha);
			servico.removeConsultasServico(senha.getConsulta());
		}

	}

	/** método chamado para encaminhar o utente para outros serviços */
	private void encaminhar() {
		Vector<String> serv = new Vector<String>();
		// ciclo para pedir os vários serviçpos (pode ser mais que um)
		do {
			// criar umalista visual, com todos os serviços introduzidos até agora
			JList<String> lista = new JList<String>(serv);
			// pedir ao utilizar o id do próximo serviço, apresentando os serviços já
			// introduzidos
			String res = JOptionPane.showInputDialog(this, lista, "Encaminhar para onde?", JOptionPane.PLAIN_MESSAGE);
			// se não introduziu nada sai do ciclo
			if (res == null || res.isEmpty())
				break;
			Servico s = gest.getServico(res);
			if (s == null)
				JOptionPane.showMessageDialog(this, "Esse serviço não existe!");
			else {
				serv.add(res);
				s.addSenhasAoServiço(senha);		
				senha.addServicosVistar(s);
				gest.getServico(s.getServicoId()).addConsultasServico(senha.getConsulta());
				atualizarInfo();
			}
		} while (true);
		finalizarConsulta();
	}

	/** método chamado para listar as senhas em espera neste serviço */
	private void listarSenhas() {
		// ver quais as senhas em espera por este serviço		
		List<Senha> senhas = new ArrayList<Senha>();
		for (Senha senhaServico : servico.getSenhasaAtender())
		{
			if (senhaServico.proxServico().equals(servico))
			{
				senhas.add(senhaServico);
			}
		}

		String infoSenhas[] = new String[senhas.size()];
		int i = 0;
		for (Senha s : senhas) {
			infoSenhas[i++] = s.getIdSenha() + ": " + gest.getUtente(s.getConsulta().getNumeroSNSUtente());
		}
		JList<String> list = new JList<String>(infoSenhas);
		JScrollPane scroll = new JScrollPane(list);
		JOptionPane.showMessageDialog(this, scroll, "Senhas no serviço", JOptionPane.PLAIN_MESSAGE);
	}

	/** Atualiza título, indicando quantos utentes estão em fila de espera */
	public void atualizarInfo() {
		
		int nUtentes = SEM_UTENTES;
		for (Senha senhaServico : servico.getSenhasaAtender()) //listar apenas os utentes que estão disponiveis no serviço
		{
			if (senhaServico.proxServico().equals(servico))// caso a proximo serviço associado a senha não seja igual ao nosso serviço, o utente nao é listado.
			{
				nUtentes++;
			}
		}	
		setTitle( servico.getServicoId() + " utentes: " + nUtentes );
		proxBt.setEnabled( nUtentes > SEM_UTENTES ); // se houver em espera pode-se ativar o botão de próximo
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
		setLayout( new BorderLayout() );
		setupInfoPanel();
		
		JPanel menuChamada = setupMenuChamada();
		JPanel menuConsulta = setupMenuConsulta();
		menusCard = new CardLayout();
		menusPanel = new JPanel( menusCard );
		menusPanel.add( menuChamada );
		menusPanel.add( menuConsulta );
		
		add( menusPanel, BorderLayout.CENTER );
		pack();
	}
	
	private void setupInfoPanel() {
		JPanel info = new JPanel( );
		info.setLayout( new BoxLayout( info, BoxLayout.Y_AXIS) );
		senhaLbl = new JLabel( "---", JLabel.CENTER );
		senhaLbl.setFont( new Font("Roman", Font.BOLD, 15) );
		senhaLbl.setAlignmentX( JLabel.CENTER_ALIGNMENT);
		info.add( senhaLbl );

		utenteLbl = new JLabel( "---", JLabel.CENTER );
		utenteLbl.setFont( new Font("Roman", Font.BOLD, 10) );
		utenteLbl.setAlignmentX( JLabel.CENTER_ALIGNMENT);
		info.add( utenteLbl );

		JButton senhasBt = new JButton("Senhas");
		senhasBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listarSenhas();
			}
		});
		senhasBt.setAlignmentX( JLabel.CENTER_ALIGNMENT);
		senhasBt.setMargin( new Insets(0,0,0,0));
		info.add( senhasBt );
		
		add( info, BorderLayout.NORTH );
	}
	

	private JPanel setupMenuChamada() {
		JPanel menuChamada = new JPanel( new GridLayout(0,1) );
		proxBt = new JButton("Chamar Utente");
		JButton rejeitarBt = new JButton("Rejeitar Utente");
		JButton consultaBt = new JButton("Confirmar consulta");
		
		proxBt.setMinimumSize( tamanhoBt );
		proxBt.setPreferredSize( tamanhoBt );
		proxBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( proximoUtente() ) {
					atualizarInfo();
					rejeitarBt.setEnabled( true );
					consultaBt.setEnabled( true );
				}
			}
		});
		menuChamada.add( proxBt );
				
		rejeitarBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				senhaLbl.setText( "---" );
				utenteLbl.setText( "---" );
				rejeitarUtente();
			}
		});
		rejeitarBt.setEnabled( false );
		menuChamada.add( rejeitarBt );
		
		consultaBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmarConsulta();
				rejeitarBt.setEnabled( false );
				consultaBt.setEnabled( false );
				menusCard.next( menusPanel );
			}
		});
		consultaBt.setEnabled( false );
		menuChamada.add( consultaBt );
		
		return menuChamada;		
	}
	
	private JPanel setupMenuConsulta() {
		JPanel menuConsulta = new JPanel( new GridLayout(0,1) );
		JButton finalizarBt = new JButton("Finalizar Consulta");
		finalizarBt.setMinimumSize( tamanhoBt );
		finalizarBt.setPreferredSize( tamanhoBt );
		finalizarBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finalizarConsulta( );
				limpaInfo();
			}

		});
		menuConsulta.add( finalizarBt );

		JButton encaminharBt = new JButton("Encaminhar");
		encaminharBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				encaminhar( );
				limpaInfo();
			}
		});
		menuConsulta.add( encaminharBt );
		
		return menuConsulta;
	}

	private void limpaInfo() {
		senhaLbl.setText( "---" );
		utenteLbl.setText( "---" );
		atualizarInfo();
		menusCard.next( menusPanel );
	}
}
