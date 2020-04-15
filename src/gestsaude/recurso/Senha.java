package gestsaude.recurso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gestsaude.util.gestSaudeUtilitarios;

/** Representa uma Senha
 */
public class Senha {

	private Utente utente;
	private Consulta consulta;
	private String idSenha;
	private LocalDateTime DataeHoradeEntrada;
	public List<Servico> servicosaVisitar = new ArrayList<Servico>();
	
	
	
	/*
	 * No dia da consulta o utente dirige-se � clinica e retira a senha de consulta.
	 * O sistema verifica se o utente tem consulta nas pr�ximas 3 horas (ou nas
	 * anteriores 3, se o utente chegar atrasado). Se n�o tiver, deve rejeitar o
	 * pedido de senha. Quando tira a senha, o sistema marca a hora de entrada e
	 * gera um n�mero para a senha. A senha � enviada para o servi�o correspondente.
	 * A senha deve ser colocada por ordem cronol�gica, pela hora de consulta e n�o
	 * pela ordem de entrada. (por exemplo, se o utente que tem consulta �s 10h00
	 * chega antes do utente com consulta �s 9:30, n�o ser� atendido antes deste). A
	 * exce��o � o utente que chega atrasado: neste caso ser� usada a hora de
	 * entrada e n�o a hora da consulta. Cada servi�o ter� uma aplica��o que lhe
	 * permitir� chamar o pr�ximo utente. Se o utente comparecer, o servi�o dever�
	 * confirmar a consulta. Neste caso a aplica��o abrir� um novo menu com outras
	 * op��es (discutidas mais � frente). Caso o utente n�o compare�a, o servi�o
	 * pode voltar a chamar o utente, ou rejeitar o utente. Em caso de rejei��o o
	 * utente ser� colocado 3 lugares atr�s na fila de espera do servi�o. Trabalho
	 * Pratico n� 1 Programa��o II 2019/2020 2/4 Quando aceita a consulta o servi�o
	 * fica com duas op��es no sistema: d� a consulta por terminada ou encaminha o
	 * utente para outros servi�os. Ao terminar a consulta, esta � removida do
	 * sistema e a senha igualmente. Se for necess�rio encaminhar o utente para
	 * outro(s) servi�o(s) (por exemplo, tirar uma radiografia, fazer um
	 * eletrocardiograma, etc) � preciso indicar quais os servi�os, pela ordem em
	 * que estes devem ser visitados. Caso o servi�o queira voltar a ver o utente no
	 * final, deve incluir o seu servi�o no final da lista. De referir que, se o
	 * utente tiver reencaminhamentos, a finaliza��o da consulta s� elimina a mesma
	 * e a senha quando todos os servi�os tiverem terminado a consulta.
	 */
	
	public Senha (Utente utente, Consulta consulta, LocalDateTime DataeHoradeEntrada, String idSenha) {
		this.utente = utente;
		this.consulta = consulta;
		this.DataeHoradeEntrada = DataeHoradeEntrada;
		this.idSenha = idSenha;
	}
	
	/** retorna o pr�ximo servi�o associado a esta senha 
	 * @return o pr�ximo servi�o associado a esta senha
	 * 	 */
	public Servico proxServico() {
		return null;
	}

	/** faz o processamento do fim da consulta por um dado servi�o
	 */
	public void terminaConsulta() {
	}


	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
	}


	public Consulta getConsulta() {
		return consulta;
	}


	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}


	public LocalDateTime getHoraEntrada() {
		return DataeHoradeEntrada;
	}
	
	public String getIdSenha() {
		return idSenha;
	}


	public void setIdSenha(String idSenha) {
		this.idSenha = idSenha;
	}


	public void setHoraEntrada(LocalDateTime Data) {
		this.DataeHoradeEntrada = Data;
	}
	
	public void addServicosVistar(Servico servico)
	{
		servicosaVisitar.add(servico);   	
	}
	
	public void removeServicosVistar(Servico servico)
	{
		servicosaVisitar.remove(servico);
	}
	
	public List<Servico> getConsultasMarcadasServico() {
		return Collections.unmodifiableList(servicosaVisitar);
	}

	@Override
	public String toString() {
		return " \nSenha [utente=" + utente + ", consulta=" + consulta + ", HoradeEntrada=" + DataeHoradeEntrada + "]";
	}
	
	

}
