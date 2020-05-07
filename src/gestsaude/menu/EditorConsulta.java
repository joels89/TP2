package gestsaude.menu;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import gestsaude.recurso.*;

/** Classe usada para apresentar a janela
 * de criação/edição de uma consulta
 */
public class EditorConsulta extends JDialog {
	// Constantes para a definição do período horário
	private static final int PRIMEIRA_HORA = 8;
	private static final int ULTIMA_HORA = 19;
	private static final int TEMPO_CONSULTA = 10;

	// as variáveis para a consulta
	private GEstSaude gest;
	private Consulta consulta;
	private Utente utente;
	private Servico servico;
	private LocalDate data;
	private LocalTime hora;
	private boolean estaAlterarConsulta = false;
	
	// elementos gráficos para a interface com o utilizador
	private static final long serialVersionUID = 1L;
	private JLabel avisosLbl;
	private JTextField snsUtente, nomeUtente; 
	private JComboBox<String> idServico;
	private JTextField nomeServico;
	private JTextField dataText;
	private JComboBox<String> horaBox, minsBox;
	private Color corOk = new Color( 0, 150, 0 );
	private JButton okBt;
	
	// consulta que vai resultar da edição ou criação
	private Consulta consultaRes;

	/** cria a janela de edição para a criação de uma nova consulta
	 * @param posicao ondo colocar a janela
	 * @param g o sistema
	 */
	public EditorConsulta( Point posicao, GEstSaude g  ) {
		this( posicao, g, null, "Criar Nova Consulta" );
		estaAlterarConsulta = false;
	}
	
	/** Cria a janela de edição para a edição de uma consulta
	 * @param posicao onde colocar a janela
	 * @param g o sistema
	 * @param consulta a consulta a editar
	 */
	public EditorConsulta( Point posicao, GEstSaude g, Consulta consulta ) {
		this( posicao, g, consulta, "Editar Consulta" );
		estaAlterarConsulta = true;
		snsUtente.setFocusable( false );
		idServico.setFocusable( false );
	}

	/** Construtor que coloca o titulo na jela sendo chamado pelos outros construtores
	 * @param posicao onde colocar a janela
	 * @param g o sistema 
	 * @param consulta a consulta a editar (se for null é para criar uma nova)
	 * @param titulo o título a usar na janela
	 */
	private EditorConsulta( Point posicao, GEstSaude g, Consulta consulta, String titulo ) {
		setLocation( posicao );
		setTitle( titulo );
		this.consulta = consulta;
		this.gest = g;
		
		setupAspeto();

		// se for uma consulta existente é preciso carregar os dados desta
		if( consulta != null ) {
			
			snsUtente.setText(consulta.getNumeroSNSUtente()); 
			testaIdUtente();
			
			idServico.setSelectedItem(consulta.getServicoId());		
			testaIdServico();

			data = consulta.getDataConsulta();
			escreverData();

			hora = consulta.getHoraConsulta();	
			escreverHora();
		}
		testaTudoOk();
	}

	/** retorna a consulta resultante 
	 * @return  a consulta resultante, null se a operação tiver sido cancelada
	 */
	public Consulta getConsulta() {
		return consultaRes;
	}
	
	/** método chamado pela interface para saber se o sns utente é válido.
	 * Deve dar indicações de erro.
	 */
	protected void testaIdUtente() {
		String idUtente = snsUtente.getText(); // vai buscar o id introduzido pelo utilizador
		utente = gest.getUtente(idUtente);
		if( utente == null ) {
			apresentarMensagem( "Id do utente é inválido!", false );
			nomeUtente.setText( "" );
		}
		else {
			nomeUtente.setText( gest.getUtente(idUtente).getNomeUtente() );
			testaTudoOk();
		}
	}
	
	/** método chamado pela interface para saber se o id do serviço é válido.
	 * Deve dar indicações de erro.
	 */
	protected void testaIdServico() {
		String id = (String) idServico.getSelectedItem();
		// vai buscar o id introduzido pelo utilizador
		servico = gest.getServico(id);
		if( servico == null ) {
			apresentarMensagem( "Serviço não reconhecido!", false );
			nomeServico.setText("");
		}
		else {
			// TODO colocar aqui o nome do serviço
			nomeServico.setText( servico.getServicoNome() );
			testaTudoOk();
		}
	}

	/** método chamado pela interface para saber se todos os parametros
	 * estão corretos e se a consulta é aceitável. Deve dar indicações de erro.https://www.google.com/search?q=garmin+935+xt&source=lmns&tbm=shop&bih=1041&biw=2133&hl=pt-PT&ved=2ahUKEwiqvND-j_roAhULKhoKHWNlACYQ_AUoAnoECAEQAg
	 */
	private void testaTudoOk() {
		int h = Integer.parseInt((String) horaBox.getSelectedItem());
		int m = Integer.parseInt((String) minsBox.getSelectedItem());
		if (utente == null)
			apresentarMensagem("Falta definir o utente!", false);
		else if (servico == null)
			apresentarMensagem("Falta definir o serviço!", false);
		else if (data == null)
			apresentarMensagem("Falta definir a data!", false);
		else {
			// TODO criar aqui uma consulta com todos os dados introduzidos
			Consulta c = new Consulta(data, LocalTime.of(h, m), (String) idServico.getSelectedItem(), snsUtente.getText());
			int res = 0;
			if (estaAlterarConsulta) {
				res = gest.alteraConsulta(consulta, c);
			} else
				res = gest.podeAceitarConsulta(c);
			switch (res) {
			case GEstSaude.CONSULTA_ACEITE:
				apresentarMensagem("Está tudo OK!", true);
				break;
			case GEstSaude.UTENTE_TEM_CONSULTA:
				apresentarMensagem("Utente já tem consulta!", false);
				break;
			case GEstSaude.SERVICO_TEM_CONSULTA:
				apresentarMensagem("Serviço já tem consulta!", false);
				break;
			case GEstSaude.DATA_JA_PASSOU:
				apresentarMensagem("Fora de prazo!", false);
				break;
			// esta nunca deve aparecer, mas vamos testar na mesma
			case GEstSaude.ALTERACAO_INVALIDA:
				apresentarMensagem("Alteração não permitida!", false);
				break;
			}
		}
	}

	/** método que vai colocar os identificadores dos serviços, que aceitam consultas, 
	 * numa combo box para apresentar ao utilizador
	 * @param box onde adicionar os serviços
	 */
	private void prencheServicos( JComboBox box ) 
	{
		for( Servico s : gest.getServicos() )
			if(gest.aceitaConsulta(s))
			{
				box.addItem(s.getServicoId());
			}
	}
	


	/** método chamado quando o botão ok é premido
	 * fazer aqui as considerações finais, se necessário
	 */
	protected void okPremido() {
		// crar um objeto LocalDateTime com os dados da data e hora 
		int h = Integer.parseInt( (String)horaBox.getSelectedItem() );
		int m = Integer.parseInt( (String)minsBox.getSelectedItem() );
		LocalDateTime quando = LocalDateTime.of( data,LocalTime.of( h, m));
		if (!estaAlterarConsulta)
		{
			consultaRes = new Consulta (quando.toLocalDate(), quando.toLocalTime(), (String) idServico.getSelectedItem(), snsUtente.getText());	
		}
		else
		{
			Consulta c = new Consulta (quando.toLocalDate(), quando.toLocalTime(), (String) idServico.getSelectedItem(), snsUtente.getText());
			if(gest.alteraConsulta(consulta, c) == 0)
			{
				consultaRes = new Consulta (quando.toLocalDate(), quando.toLocalTime(), (String) idServico.getSelectedItem(), snsUtente.getText());				
			}	
		}
			
		setVisible( false );
	}

	/** método chamado quando o botão cancel é premido
	 * fazer aqui as considerações finais, se necessário
	 */
	protected void cancelPremido() {
		if(estaAlterarConsulta)
		{
			if(!gest.getConsultas().contains(consulta))
			{
				gest.addConsulta(consulta);			
			}
		}	
		setVisible( false );
	}

	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	// métodos relacionados com a interface gráfica. Não deve ser necessário alterar nada nestes métodos
	
	/** método que devolve a hora conforme definida pelo utilizador
	 * @return a hora, em LocalTime,  conforme definida pelo utilizador
	 */
	private LocalTime getHora() {
		int h = Integer.parseInt( (String) horaBox.getSelectedItem() );
		int m = Integer.parseInt( (String) minsBox.getSelectedItem() );
		return LocalTime.of( h, m);
	}

	/** método auxiliar que escreve uma mensagem no painel de informações
	 * @param texto texto a escrever no painel
	 * @param tudoOk se for true, texto aparece a verde, senão a vermelho
	 */
	private void apresentarMensagem( String texto, boolean tudoOk ) {
		if( tudoOk )
			avisosLbl.setForeground( corOk );
		else
			avisosLbl.setForeground( Color.red );
		okBt.setEnabled( tudoOk );
		avisosLbl.setText( texto );
	}

	/** método auxiliar que transforma uma LocalDate numa string com a formatação portuguesa 
	 */
	private void escreverData() {
		dataText.setText( data.format( DateTimeFormatter.ofPattern("dd/MM/yyyy") ) );
	}
	
	/** método auxiliar que representa uma LocalHour nas combobox da interface  
	 */
	private void escreverHora() {
		int idx = hora.getHour() - PRIMEIRA_HORA;
		horaBox.setSelectedIndex( idx );
		
		idx = hora.getMinute() / TEMPO_CONSULTA;
		minsBox.setSelectedIndex( idx );
	}

	/** Define o aspeto deste componente */
	private void setupAspeto() {
		setModal( true );
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weighty = 0.2;
		gbc.weightx = 1;
		setLayout( new GridBagLayout( ) );
		JPanel utentePanel = new JPanel( );
		utentePanel.setBorder( BorderFactory.createTitledBorder( "Utente") );
		setupAreaCliente( utentePanel );
		add( utentePanel, gbc );
		
		JPanel servicoPanel = new JPanel( );
		servicoPanel.setBorder( BorderFactory.createTitledBorder( "Serviço") );
		setupAreaServico(servicoPanel);
		add( servicoPanel, gbc );
	
		JPanel dataPanel = new JPanel( );
		dataPanel.setBorder( BorderFactory.createTitledBorder( "Data e hora" ) );
		setupAreaTempo(dataPanel);
		add( dataPanel, gbc );

		avisosLbl = new JLabel();
		avisosLbl.setBorder( BorderFactory.createTitledBorder( "Mensagens" ) );
		avisosLbl.setPreferredSize( new Dimension(100,80) );
		gbc.weighty = 0.8;
		add( avisosLbl, gbc );
		
		JPanel okPanel = new JPanel( );
		okPanel.setBorder( BorderFactory.createLoweredBevelBorder() );
		setupOkCancel(okPanel);
		gbc.weighty = 0;
		add( okPanel, gbc );
		pack();
	}

	private void setupOkCancel(JPanel okPanel) {
		okBt = new JButton( "Ok" );
		okBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				okPremido();
			}
		});
		okPanel.add( okBt );

		JButton cancelBt = new JButton( "Cancelar" );
		cancelBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelPremido();
			}
		});
		okPanel.add( cancelBt );
	}

	private void setupAreaCliente(JPanel utentePanel) {
		GridBagLayout gl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		utentePanel.setLayout( gl );
		
		utentePanel.add( new JLabel("SNS:"), gbc );
		
		snsUtente = new JTextField( 8 );
		snsUtente.addFocusListener( new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				testaIdUtente( );
			}
		});
		snsUtente.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				testaIdUtente( );
				transferFocus();
			}
		});
		
		gbc.weightx = 0.1;
		utentePanel.add( snsUtente, gbc );
		
		gbc.weightx = 0;
		utentePanel.add( new JLabel("Nome:"), gbc );
		
		nomeUtente = new JTextField( 40 );
		nomeUtente.setFocusable( false );
		gbc.weightx = 0.9;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		utentePanel.add( nomeUtente, gbc );
	}

	private void setupAreaServico(JPanel servicoPanel) {
		GridBagLayout gl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		servicoPanel.setLayout( gl );
		
		servicoPanel.add( new JLabel("ID: "), gbc );
		
		idServico = new JComboBox<String>( );
		prencheServicos( idServico );
		idServico.addFocusListener( new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				testaIdServico( );
			}
		});
		idServico.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				testaIdServico( );
				transferFocus();
			}
		});

		gbc.weightx = 0.1;
		servicoPanel.add( idServico, gbc );

		gbc.weightx = 0;
		servicoPanel.add( new JLabel("Serviço:"), gbc );
		
		nomeServico = new JTextField( 40 );
		nomeServico.setFocusable( false );
		gbc.weightx = 0.9;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		servicoPanel.add( nomeServico, gbc );
	}

	private void setupAreaTempo(JPanel dataPanel) {
		dataPanel.add( new JLabel("Data: ") );
		dataText = new JTextField( 8 );
		dataText.setFocusable( false );
		dataPanel.add( dataText );
		
		JButton pickBt = new JButton("...");
		pickBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(EditorConsulta.this, LocalDate.now(), LocalDate.now().plusDays( 365 ) );
				dp.setVisible( true );
				data = dp.getPickedDate();
				if( data != null ) {
					escreverData();
					testaTudoOk();
				}
			}
		});
		dataPanel.add( pickBt );
		
		dataPanel.add( new JLabel("   Hora: ") );
		String []horas = new String[ULTIMA_HORA - PRIMEIRA_HORA + 1];
		for( int i = 0; i < horas.length; i++)
			horas[i] = "" + (i+PRIMEIRA_HORA );
		
		String [] minutos = new String[ 60 / TEMPO_CONSULTA ];
		for( int i = 0; i < minutos.length; i++)
			minutos[i] = "" + (i*TEMPO_CONSULTA);
		
		horaBox = new JComboBox<String>( horas ) ;
		horaBox.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				testaTudoOk();				
			}
		});
		minsBox = new JComboBox<String>( minutos ) ;
		minsBox.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				testaTudoOk();				
			}
		});

		dataPanel.add( horaBox );
		dataPanel.add( minsBox );
	}

}
