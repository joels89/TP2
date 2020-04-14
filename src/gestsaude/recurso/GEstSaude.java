package gestsaude.recurso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import gestsaude.util.Consultas;
import gestsaude.util.gestSaudeUtilitarios;

/** Representa o sistema
 */
public class GEstSaude {

	// Constantes para os erros possíveis nos métodos de aceitar chamadas
	public static final int CONSULTA_ACEITE = 0;       // indica que a consulta é aceite
	public static final int UTENTE_TEM_CONSULTA = 1;   // indica que o utente já tem consulta
	public static final int SERVICO_TEM_CONSULTA = 2;  // indica que o serviço já tem consulta
	public static final int DATA_JA_PASSOU = 3;        // indica que a data já passou  
	public static final int ALTERACAO_INVALIDA = 4;    // indica que a alteração é inválida
	
	public static final int MINS_POR_HORA = 60;
	public static final int TRES_HORAS = 180;
	public static final LocalTime HORARIO_INICIO = LocalTime.of(8, 10);
	public static final LocalTime HORARIO_FIM = LocalTime.of(19, 50);	
	
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
		Collection<Senha> senhas = getSenhas().values();
		for (Consulta consulta : consultas)
		{
			for (Senha s: senhas)
			{
				if(consulta.equals(s.getConsulta()))
				{
					return s;
				}
			}	
		}
		
		Senha senha = new Senha(utentes.get(c.getNumeroSNSUtente()), c, t);
		addSenha(new Senha(utentes.get(c.getNumeroSNSUtente()), c, t));
		return senha;
	}
	
	public Utente getUtente(String numeroSNSUtente) {
		return utentes.get(numeroSNSUtente);
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
	
	public void addSenha(Senha senha)
	{
		senhas.put(getProximoIdSenha(), senha);
		senha.addServicosVistar(servicos.get(senha.getConsulta().getServicoId()));
		servicos.get(senha.getConsulta().getServicoId()).addSenhasServico(senha);		
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
	
	public int localTime2Min( LocalTime hora ) {//---------------------------------------------------------------------------------------TODO ver onde por o método
		return  hora.getHour() * MINS_POR_HORA + hora.getMinute();
	}
	 
	
	public boolean temConsultaProxima(Utente u, LocalTime horaSenha) {//------------------------------------------------------------------------------------TODO ver onde por o método
		if ( u.getPresentes()!= null) 
		{
			return (Math.abs(localTime2Min(u.getPresentes().get(0).getHoraConsulta()) - localTime2Min(horaSenha)) < TRES_HORAS);//indice zero pois e sempre a 1 consulta qd uma termina e removida ...
		}
		return false;
	}
	
	public boolean validaConsulta( Consulta c ) {//---------------------------------------------------------------------------------------TODO ver onde por o método
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e enunciado)
		/*
	    - O utente tem de estar identificado; - DONE - getNumeroSNSUtente
		- O serviço tem de estar identificado; - DONE - getServicoId
		- O horário deve estar entre as 8h10 e as 19:50; - DONE
		- O intervalo entre consultas no mesmo serviço, deve ser de 10 minutos; ------------------------ Testado no método podeAceitarConsulta (TODO ate ter senha)
		- O utente não pode duas consultas, no mesmo dia, com diferença inferior a 3 horas;  - DONE
		*/
		if (servicos.get(c.getServicoId()) == null) {
			System.out.println("        **** ATENCAO CONSULTA INVALIDA ****  \n Servico nao existe na Lista de Servicos  \n ");
			return false;	
		}
		if (utentes.get(c.getNumeroSNSUtente()) == null) {
			System.out.println("        **** ATENCAO CONSULTA INVALIDA ***  \n Utente nao existe na Lista de Utentes \n");
			return false;	
		}
		if ((c.getHoraConsulta().compareTo(HORARIO_INICIO) < 0) || (c.getHoraConsulta().compareTo(HORARIO_FIM) > 0)) {
			System.out.println("        **** ATENCAO CONSULTA INVALIDA *** \n Consulta fora do Horario de Funcionamento. Volte a remarcar entre as 8:10 e as 19:50 \n");
			return false;
		}

		for (Consulta consultaListada : consultas){
			if ((c.getNumeroSNSUtente() == consultaListada.getNumeroSNSUtente()) &&
					((c.getDataConsulta() == consultaListada.getDataConsulta()) && 
					(Math.abs(localTime2Min(c.getHoraConsulta()) - localTime2Min(consultaListada.getHoraConsulta())) < TRES_HORAS))){
				System.out.println("        **** ATENCAO CONSULTA INVALIDA *** \n A segunda consulta no mesmo dia deve iniciar no mínimo após 3 horas do inicio da primeira \n");
				return false;	
			}}
		return true;
	}

	
	public int addConsulta( Consulta c ) {
		if (!validaConsulta(c)){
			return -1;
		}else
			Consultas.addConsultaOrdemData(consultas, c);
			System.out.println("------Consultas adicionadas a lista -------------" + servicos.get(c.getServicoId()));//--------------------controlo de consultas TODO retirar no final
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
		return Collections.unmodifiableCollection(servicos.values());
	}
	
	public List<Consulta> getConsultas() {
		return Collections.unmodifiableList(consultas);
	}
	
	public Servico getServico(String servicoId) {
		return servicos.get(servicoId);
	}

	public HashMap<String, Senha> getSenhas() {
		return senhas;
	}
	
	
	

	


}
