package gestsaude.recurso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/** Representa o sistema
 */
public class GEstSaude {

	// Constantes para os erros poss�veis nos m�todos de aceitar chamadas
	public static final int CONSULTA_ACEITE = 0;       // indica que a consulta � aceite
	public static final int UTENTE_TEM_CONSULTA = 1;   // indica que o utente j� tem consulta
	public static final int SERVICO_TEM_CONSULTA = 2;  // indica que o servi�o j� tem consulta
	public static final int DATA_JA_PASSOU = 3;        // indica que a data j� passou  
	public static final int ALTERACAO_INVALIDA = 4;    // indica que a altera��o � inv�lida
	
	// mapas com as listas de todas as informa��es
	private HashMap<String,Utente> utentes = new HashMap<String,Utente>();
	private HashMap<String,Servico> servicos = new HashMap<String,Servico>();
	private HashMap<String,Senha> senhas = new HashMap<String,Senha>();
	private ArrayList<Consulta> consultas = new ArrayList<Consulta>();
	
	// defini��o da pr�xima senha
	private int proxSenha = 1;
	
	public GEstSaude() {
	}
	
	public Senha emiteSenha( Consulta c, LocalDateTime t ) {
		// TODO testar se a consulta j� est� validada, se estiver retornar a senha j� emitida	
		// TODO sen�o criar e retornar a nova senha
		return null;
	}
	
	public void resetSenhas() {
		proxSenha = 1;
	}
	
	private String getProximoIdSenha() {
		String id = "A" + proxSenha;
		proxSenha++;
		return id;
	}
	
	public int podeAceitarConsulta( Consulta c ) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e enunciado)
		return CONSULTA_ACEITE;
	}
	
	public int addConsulta( Consulta c ) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e enunciado)
		c.getServico().addConsultasServico(c);
		c.getUtente().addConsulta(c);
		return CONSULTA_ACEITE;
	}
	
	public int podeAlterarConsulta( Consulta antiga, Consulta nova ) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e enunciado)
		return CONSULTA_ACEITE;
	}
	
	public int alteraConsulta( Consulta antiga, Consulta nova ) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e enunciado)
		return CONSULTA_ACEITE;
	}

	public Collection<Servico> getServicos() {
		// TODO acabar este m�todo
		return null;
	}
}
