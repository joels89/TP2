package gestsaude.recurso;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/** Representa o sistema
 */
public class GEstSaude {

	// Constantes para os erros poss�veis nos m�todos de aceitar chamadas
	public static final int CONSULTA_ACEITE = 0;       // indica que a consulta � aceite
	public static final int UTENTE_TEM_CONSULTA = 1;   // indica que o utente j� tem consulta
	public static final int SERVICO_TEM_CONSULTA = 2;  // indica que o servi�o j� tem consulta
	public static final int DATA_JA_PASSOU = 3;        // indica que a data j� passou  
	public static final int ALTERACAO_INVALIDA = 4;    // indica que a altera��o � inv�lida
	
	public static final int MINS_POR_HORA = 60;
	public static final int TRES_HORAS = 180;
	public static final LocalTime HORARIO_INICIO = LocalTime.of(8, 10);
	public static final LocalTime HORARIO_FIM = LocalTime.of(19, 50);	
	
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
		
		for (Consulta consulta : consultas)
		{
			if (!consulta.equals(c))
			{
				System.out.println("        **** ATENCAO CONSULTA INVALIDA ****  \n Consulta nao existe na Lista de consultas  \n ");
				return null;
			}
			else
			{
				if(Math.abs(t.getHour() - localTime2Min(consulta.getHoraConsulta())) <= TRES_HORAS)
				{
					Senha senha = new Senha(utentes.get(c.getNumeroSNSUtente()), c, t);
					addSenha(senha);
					return senha;
				}
				else
				{
					System.out.println("        **** ATENCAO SENHA INVALIDA ****  \n Volte no horario mais proximo a sua consulta.  \n ");
					return null;
				}
				
			}
				
		}
		
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
	
	public int localTime2Min( LocalTime hora ) {//----------------------------------TODO ver onde por o m�todo
		return  hora.getHour() * MINS_POR_HORA + hora.getMinute();
	}
	
	public boolean validaConsulta( Consulta c ) {//---------------------------------TODO ver onde por o m�todo
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e enunciado)
		/*
	    - O utente tem de estar identificado; - DONE - getNumeroSNSUtente
		- O servi�o tem de estar identificado; - DONE - getServicoId
		- O hor�rio deve estar entre as 8h10 e as 19:50; - DONE
		- O intervalo entre consultas no mesmo servi�o, deve ser de 10 minutos; ------------------------ Testado no m�todo podeAceitarConsulta (TODO ate ter senha)
		- O utente n�o pode duas consultas, no mesmo dia, com diferen�a inferior a 3 horas;  - DONE
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
				System.out.println("        **** ATENCAO CONSULTA INVALIDA *** \n A segunda consulta no mesmo dia deve iniciar no m�nimo ap�s 3 horas do inicio da primeira \n");
				return false;	
			}}
		return true;
	}

	
	public int addConsulta( Consulta c ) {
		if (!validaConsulta(c)){
			return -1;
		}else
			consultas.add(c);
			System.out.println(servicos.get(c.getServicoId()));//--------------------controlo de consultas TODO retirar no final
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


}
