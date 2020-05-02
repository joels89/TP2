package gestsaude.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** Classe que representa um simulador de um rel�gio.
 * Este rel�gio � sempre inicializado �s 8h00 do dia atual.<br>
 * Em todo o sistema existe apenas um rel�gio, dai, para aceder 
 * ao rel�gio � necess�rio usar o m�todo <code><b>getRelogioSistema</b></code>, ou em alternativa,
 * para aceder ao tempo atual, ao m�todo <code><b>getTempoAtual</b></code>.<br>
 * Para definir a velocidade do rel�gio, usar o m�todo <code><b>setTicksPorSegundo</b></code>.
 * 
 * <br><b>N�O ALTERAR ESTE CLASSE<br>
 * N�O ALTERAR ESTE CLASSE<br>
 * N�O ALTERAR ESTE CLASSE</b><br>
 */
public class RelogioSimulado {

	private static RelogioSimulado relogio;
	private LocalDateTime horaAbertura; 
	private long arranque;
	private int ticksPorSegundo = 1;

	/** Construtor privado. Para ter um rel�gio devem usar o m�todo
	 * getRelogioSimulado
	 */
	private RelogioSimulado() {		
		arranque = System.currentTimeMillis();
		horaAbertura = LocalDateTime.of( LocalDate.now(), LocalTime.of( 8, 0, 0) );	
	}
	
	/** Retorna o rel�gio simulado
	 * @return o rel�gio simulado
	 */
	public static RelogioSimulado getRelogioSimulado( ) {
		if( relogio == null ) {
			relogio = new RelogioSimulado();
		}
		return relogio;
	}
	
	/** M�todo de conveni�ncia para retornar o tempo atual,
	 * sem ser necess�rio aceder ao rel�gio diretamente 
	 * @return o tempo atual no rel�gio simulado
	 */
	public static LocalDateTime getTempoAtual() {
		return getRelogioSimulado().tempoAtual();
	}
	
	/** M�todo que retorna o tempo atual no rel�gio de sistema simulado
	 * @return o tempo atual no rel�gio simulado
	 */
	public LocalDateTime tempoAtual() {
		long difSecs = (System.currentTimeMillis() - arranque)/1000;
		long simulado = difSecs*ticksPorSegundo;
		if( simulado > 0)
			return horaAbertura.plusSeconds( simulado );
		else
			return horaAbertura.minusSeconds( simulado );
	}
	
	/** Define a "velocidade" do rel�gio, isto �, cada segundo no rel�gio real
	 * corresponder� a <b>ticksPorSegundo</b> segundos no rel�gio simulado. Inicialmente o rel�gio tem 
	 * 1 tick por segundo, isto �, 1 segundo real corresponde a 1 segundo simulado.
	 * @param ticksPorSegundo o n�mero de segundos que decorrem em cada segundo do rel�gio real
	 */
	public void setTicksPorSegundo(int ticksPorSegundo) {
		if( ticksPorSegundo > 1 )
			this.ticksPorSegundo = ticksPorSegundo;
	}
	
}
