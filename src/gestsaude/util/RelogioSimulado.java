package gestsaude.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Classe que representa um simulador de um relógio. Este relógio é sempre
 * inicializado às 8h00 do dia atual.<br>
 * Em todo o sistema existe apenas um relógio, dai, para aceder ao relógio é
 * necessário usar o método <code><b>getRelogioSistema</b></code>, ou em
 * alternativa, para aceder ao tempo atual, ao método
 * <code><b>getTempoAtual</b></code>.<br>
 * Para definir a velocidade do relógio, usar o método
 * <code><b>setTicksPorSegundo</b></code>.
 * 
 * <br>
 * <b>NÃO ALTERAR ESTE CLASSE<br>
 * NÃO ALTERAR ESTE CLASSE<br>
 * NÃO ALTERAR ESTE CLASSE</b><br>
 */
public class RelogioSimulado {

	private static RelogioSimulado relogio;
	private LocalDateTime horaAbertura;
	private long arranque;
	private int ticksPorSegundo = 1;

	/**
	 * Construtor privado. Para ter um relógio devem usar o método
	 * getRelogioSimulado
	 */
	private RelogioSimulado() {
		arranque = System.currentTimeMillis();
		horaAbertura = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0, 0));
	}

	/**
	 * Retorna o relógio simulado
	 * 
	 * @return o relógio simulado
	 */
	public static RelogioSimulado getRelogioSimulado() {
		if (relogio == null) {
			relogio = new RelogioSimulado();
		}
		return relogio;
	}

	/**
	 * Método de conveniência para retornar o tempo atual, sem ser necessário aceder
	 * ao relógio diretamente
	 * 
	 * @return o tempo atual no relógio simulado
	 */
	public static LocalDateTime getTempoAtual() {
		return getRelogioSimulado().tempoAtual();
	}

	/**
	 * Método que retorna o tempo atual no relógio de sistema simulado
	 * 
	 * @return o tempo atual no relógio simulado
	 */
	public LocalDateTime tempoAtual() {
		long difSecs = (System.currentTimeMillis() - arranque) / 1000;
		long simulado = difSecs * ticksPorSegundo;
		if (simulado > 0)
			return horaAbertura.plusSeconds(simulado);
		else
			return horaAbertura.minusSeconds(simulado);
	}

	/**
	 * Define a "velocidade" do relógio, isto é, cada segundo no relógio real
	 * corresponderá a <b>ticksPorSegundo</b> segundos no relógio simulado.
	 * Inicialmente o relógio tem 1 tick por segundo, isto é, 1 segundo real
	 * corresponde a 1 segundo simulado.
	 * 
	 * @param ticksPorSegundo o número de segundos que decorrem em cada segundo do
	 *                        relógio real
	 */
	public void setTicksPorSegundo(int ticksPorSegundo) {
		if (ticksPorSegundo > 1)
			this.ticksPorSegundo = ticksPorSegundo;
	}

}
