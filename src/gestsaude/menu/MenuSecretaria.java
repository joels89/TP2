package gestsaude.menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import gestsaude.recurso.*;
import gestsaude.util.Consultas;
import gestsaude.util.RelogioSimulado;

/** Interface reservada aos funcion�rios da secretaria.
 * Permite v�rias listagens das consultas, a cria��o, edi��o 
 * e remo��o de consultas e visualizar as senhas presentes no sistema 
 */
public class MenuSecretaria extends JFrame {

	private GEstSaude gest; // o sistema
	
	/** elementos gr�ficos usados na interface gr�fica */
	private static final long serialVersionUID = 1L;
	private java.util.List<Consulta> consultasListadas;
	private DefaultTableModel consultasModel;
	private JButton delBt = new JButton("x"); 
	private JButton editBt = new JButton("e");
	private ButtonGroup listagemGrp;
	private String titulo;
	
	/** Cria a janela com o menu da secretaria
	 * @param posicao onde colocar a janela
	 * @param titulo t�tulo da janela
	 * @param gest o sistema
	 */
	public MenuSecretaria( Point posicao, String titulo, GEstSaude gest ) {
		this.gest = gest;
		this.titulo = titulo;
		setLocation( posicao );
		setupAspeto();		
		listarTodas();
		setDefaultCloseOperation( JDialog.EXIT_ON_CLOSE );
		atualizarRelogio();
	}

	/** lista todas as consultas */
	private void listarTodas() 
	{
		listarConsultas(gest.getConsultas());
	}

	/** lista apenas as consultas de hoje */
	private void listarHoje()
	{
		listarConsultas(Consultas.getConsultasDoDia(gest.getConsultas(), LocalDate.now()));
	}
	
	/** lista todas as consultas de um utente */
	private void listarPorUtente() {
		// pedir o utente
		String numSns = JOptionPane.showInputDialog(this, "N�mero de SNS do utente?");
		if(gest.getUtente(numSns) !=null)
			listarConsultas(gest.getUtente(numSns).getPresentes());
		else
			JOptionPane.showMessageDialog( this, "Utente inv�lido" );			
	}
	
	/** lista todas as consultas de um servi�o */
	private void listarPorServico() {
		String numServico = JOptionPane.showInputDialog(this, "N�mero do servi�o?");
		if(gest.getServico(numServico) !=null)
			listarConsultas(gest.getServico(numServico).getConsultasMarcadasServico());
		else
			JOptionPane.showMessageDialog( this, "Servi�o inv�lido" );					
	}
	
	/** m�todo auxiliar que apresenta todas as consultas da lista
	 * @param consultas as consultas a apresentar
	 */
	private void listarConsultas( java.util.List<Consulta> consultas ) {
		consultasListadas = consultas;
		consultasModel.setRowCount( 0 );
		for( Consulta c : consultas ) {
			LocalDate d = c.getDataConsulta(); 
			LocalTime h = c.getHoraConsulta();
			String dStr = d.format( DateTimeFormatter.ofPattern("dd/MM/yyyy") );
			String hStr = h.format( DateTimeFormatter.ofPattern("hh:mm:ss") );
			addLinhaTabela( c.getNumeroSNSUtente() + " - " + gest.getUtente(c.getNumeroSNSUtente()),
					        c.getServicoId()  + " - " + gest.getServico(c.getServicoId()).getServicoNome(), dStr, hStr);
		}		
	}
	
	/** m�todo auxiliar para adicionar uma linha � tabela de consultas. 
	 * @param utente string com o sns e nome do mutente
	 * @param servico string com id do servi�o e descrio��o do servi�o
	 * @param data string que represnta a data da consulta
	 * @param hora string que representa a hora da consulta
	 */
	private void addLinhaTabela(String utente, String servico, String data, String hora ) {
		Object values[] = { utente, servico, data, hora, delBt, editBt };
		consultasModel.addRow( values );
	}


	/** m�todo chamado quando se carrega no bot�o de criar nova consulta */
	private void agendarNovaConsulta() {
		// apresentar o editor de consulta
		EditorConsulta ec = new EditorConsulta( getLocation(), gest );
		ec.setVisible( true );
		Consulta c = ec.getConsulta();
		// se existe uma consulta adicionar ao sistema
		if( c != null ) {
			gest.addConsulta(c);
			listarTodas();
		}		
	}

	/** m�todo chamado quando se carrega no bot�o de eliminar consulta */
	private void apagarConsulta( Consulta c ) {
		int opcao = JOptionPane.showConfirmDialog( this, "Deseja mesmo apagar esta Consulta?", "Confima��o", JOptionPane.YES_NO_OPTION );
		if( opcao == JOptionPane.NO_OPTION )
			return;
		gest.removeConsulta(c);
	}

	/** m�todo chamado quando se carrega no bot�o de editar consulta */
	private void editarConsulta( Consulta c ) {
		// apresentar o editor de consultas
		EditorConsulta ec = new EditorConsulta( getLocation(), gest, c );
		ec.setVisible( true );
		Consulta nova = ec.getConsulta();
		// se o utilizador editou a consulta, � preciso alterar
		if( nova != null ) {
			gest.addConsulta(nova);
			listarTodas();
		}
	}

	/** m�todo chamado quando se pretende listar toda sas senhas */
	private void listarSenhas() {
		Collection<Senha> senhas = gest.getSenhas().values();
		String infoSenhas[] = new String[senhas.size()];
		int i=0;
		for( Senha s : senhas) {			
			infoSenhas[i++] = "N�mero Senha " + s.getIdSenha() + " ID do pr�ximo servi�o da senha " + s.getConsulta().getServicoId() ; 
		}
		// apresentar a lista numa janela
		JList<String> list = new JList<String>( infoSenhas );
		JScrollPane scroll = new JScrollPane( list );
		JOptionPane.showMessageDialog( this, scroll, "Senhas no sistema", JOptionPane.PLAIN_MESSAGE );
	}
	

	// m�todos relacionados com a interface gr�fica. N�o deve ser necess�rio alterar nada nestes m�todos
	// m�todos relacionados com a interface gr�fica. N�o deve ser necess�rio alterar nada nestes m�todos
	// m�todos relacionados com a interface gr�fica. N�o deve ser necess�rio alterar nada nestes m�todos
	// m�todos relacionados com a interface gr�fica. N�o deve ser necess�rio alterar nada nestes m�todos
	// m�todos relacionados com a interface gr�fica. N�o deve ser necess�rio alterar nada nestes m�todos
	// m�todos relacionados com a interface gr�fica. N�o deve ser necess�rio alterar nada nestes m�todos
	/** m�todo que vai atualizar o relogio no t�tulo da janela
	 */
	public void atualizarRelogio() {
		String tempo = RelogioSimulado.getTempoAtual().toLocalTime().format( DateTimeFormatter.ofPattern("hh:mm") );
		setTitle( titulo + "     " + tempo );		
	}

	
	/** m�todo chamado quando � selecionada uma c�lula da tabela
	 */
	private void selecionadaCelula(JTable table) {
		int col = table.getSelectedColumn();
		int row = table.getSelectedRow();
		if( col < 4 )
			return;
		if( row < 0 )
			return;
		if( col == 4 )
			apagarConsulta( consultasListadas.get( row ) );
		else
			editarConsulta( consultasListadas.get( row ) );
	}
	
	private void setupAspeto() {
		setLayout( new BorderLayout() );	
		
		JPanel top = new JPanel( new FlowLayout( FlowLayout.LEFT, 0, 0) );
		setupAspetoSuperior( top );
		add( top, BorderLayout.NORTH );
		
		String colunas[] = { "Utente", "Servi�o", "Data", "Hora", "", "" };
		consultasModel = new DefaultTableModel( colunas, 0 );
		

		delBt.setMargin( new Insets(0, 0, 0, 0) );
		editBt.setMargin( new Insets(0, 0, 0, 0) );
		@SuppressWarnings("serial")
		JTable table = new JTable( consultasModel ) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener( new MouseAdapter() {
		@Override
			public void mouseClicked(MouseEvent e) {
				selecionadaCelula(table);			
			}			
		});
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		table.getColumnModel().getColumn(0).setMinWidth(200);
		table.getColumnModel().getColumn(1).setMinWidth(180);
		table.getColumnModel().getColumn(2).setMinWidth(90);
		table.getColumnModel().getColumn(3).setMinWidth(80);
		table.getColumnModel().getColumn(4).setMaxWidth(50);
		table.getColumnModel().getColumn(5).setMaxWidth(50);		
		table.getColumnModel().getColumn(4).setCellRenderer( new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				return (JButton)value;
			}
		});
		table.getColumnModel().getColumn(5).setCellRenderer( new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				return (JButton)value;
			}
		});

		JScrollPane scroll = new JScrollPane( table );
		scroll.setPreferredSize( new Dimension(600, 200) );
		add( scroll, BorderLayout.CENTER );
		pack();
	}

	private void setupAspetoSuperior(JPanel top) {
		top.add( new JLabel("Listagem: ") );
		
		listagemGrp = new ButtonGroup();		
		JToggleButton todasBt = new JToggleButton("Todas");
		todasBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listarTodas();
			}
		});
		todasBt.setSelected( true );
		listagemGrp.add( todasBt );
		top.add( todasBt );

		JToggleButton hojeBt = new JToggleButton("Hoje");
		hojeBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listarHoje();
			}
		});
		listagemGrp.add( hojeBt );
		top.add( hojeBt );
		
		JToggleButton utenteBt = new JToggleButton("Utente");
		utenteBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listarPorUtente();
			}
		});
		listagemGrp.add( utenteBt );
		top.add( utenteBt );
		
		JToggleButton servicoBt = new JToggleButton("Servi�o");
		servicoBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listarPorServico();
			}
		});
		listagemGrp.add( servicoBt );
		top.add( servicoBt );				
		
		top.add( new JLabel("   opera��es: ") );
		
		JButton novaBt = new JButton("Nova Consulta");
		novaBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agendarNovaConsulta();
			}

		});
		top.add( novaBt );
		
		JButton senhasBt = new JButton("Ver Senhas");
		senhasBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listarSenhas();
			}
		});
		top.add( senhasBt );
	}
}
