package gestsaude.recurso;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
	
	public void addUtente(Utente utente) {
		utentes.put(utente.getNumeroSNS(), utente);
	}
	public void removeUtente( String numeroSNSUtente ){
		utentes.remove( numeroSNSUtente );
	}
	
	public void addServico(Servico servico) {
		servicos.put(servico.getServicoId(), servico);
	}
	public void removeServico( String servicoId ){
		servicos.remove( servicoId );
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
		/*
	    - O utente tem de estar identificado; - DONE - getNumeroSNSUtente
		- O serviço tem de estar identificado; - DONE - getServicoId
		- O horário deve estar entre as 8h10 e as 19:50; - DONE - getServicoId
		- O intervalo entre consultas no mesmo serviço, deve ser de 10 minutos; ------------------------ TODO
		- O utente não pode duas consultas, no mesmo dia, com diferença inferior a 3 horas; ------------- TODO
		*/
		if ((c.getHoraConsulta().compareTo(LocalTime.of(8, 10)) < 0) || (c.getHoraConsulta().compareTo(LocalTime.of(19, 50)) > 0)) {
			System.out.println("******************************************* NAO pode Aceitar consulta - ver condicoes - TODO - Apagar no fim ************");
			return -1;
		}
		System.out.println(" ******************************************* Consulta ACEITE - TODO - Apagar no fim ************");
		return CONSULTA_ACEITE;
	}
	
	public int addConsulta( Consulta c ) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e enunciado)
		if (podeAceitarConsulta(c) != CONSULTA_ACEITE) {
			return -1;
		}	
		consultas.add(c);
		System.out.println("consultas ****************" + consultas);
		servicos.get(c.getServicoId()).addConsultasServico(c);
		utentes.get(c.getNumeroSNSUtente()).addConsulta(c);
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
