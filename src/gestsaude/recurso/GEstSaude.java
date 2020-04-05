package gestsaude.recurso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/** Representa o sistema
 */
public class GEstSaude {

	// Constantes para os erros possíveis nos métodos de aceitar chamadas
	public static final int CONSULTA_ACEITE = 0;       // indica que a consulta é aceite
	public static final int UTENTE_TEM_CONSULTA = 1;   // indica que o utente já tem consulta
	public static final int SERVICO_TEM_CONSULTA = 2;  // indica que o serviço já tem consulta
	public static final int DATA_JA_PASSOU = 3;        // indica que a data já passou  
	public static final int ALTERACAO_INVALIDA = 4;    // indica que a alteração é inválida
	
	// mapas com as listas de todas as informações
	private HashMap<String,Utente> utentes = new HashMap<String,Utente>();
	private HashMap<String,Servico> servicos = new HashMap<String,Servico>();
	private HashMap<String,Senha> senhas = new HashMap<String,Senha>();
	private ArrayList<Consulta> consultas = new ArrayList<Consulta>();
	
	// definição da próxima senha
	private int proxSenha = 1;
	
	public GEstSaude() {
	}
	
	public Senha emiteSenha( Consulta c, LocalDateTime t ) {
		// TODO testar se a consulta já está validada, se estiver retornar a senha já emitida	
		// TODO senão criar e retornar a nova senha
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
		// TODO acabar este método
		return null;
	}
}
