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
	 * No dia da consulta o utente dirige-se à clinica e retira a senha de consulta.
	 * O sistema verifica se o utente tem consulta nas próximas 3 horas (ou nas
	 * anteriores 3, se o utente chegar atrasado). Se não tiver, deve rejeitar o
	 * pedido de senha. Quando tira a senha, o sistema marca a hora de entrada e
	 * gera um número para a senha. A senha é enviada para o serviço correspondente.
	 * A senha deve ser colocada por ordem cronológica, pela hora de consulta e não
	 * pela ordem de entrada. (por exemplo, se o utente que tem consulta às 10h00
	 * chega antes do utente com consulta às 9:30, não será atendido antes deste). A
	 * exceção é o utente que chega atrasado: neste caso será usada a hora de
	 * entrada e não a hora da consulta. Cada serviço terá uma aplicação que lhe
	 * permitirá chamar o próximo utente. Se o utente comparecer, o serviço deverá
	 * confirmar a consulta. Neste caso a aplicação abrirá um novo menu com outras
	 * opções (discutidas mais à frente). Caso o utente não compareça, o serviço
	 * pode voltar a chamar o utente, ou rejeitar o utente. Em caso de rejeição o
	 * utente será colocado 3 lugares atrás na fila de espera do serviço. Trabalho
	 * Pratico nº 1 Programação II 2019/2020 2/4 Quando aceita a consulta o serviço
	 * fica com duas opções no sistema: dá a consulta por terminada ou encaminha o
	 * utente para outros serviços. Ao terminar a consulta, esta é removida do
	 * sistema e a senha igualmente. Se for necessário encaminhar o utente para
	 * outro(s) serviço(s) (por exemplo, tirar uma radiografia, fazer um
	 * eletrocardiograma, etc) é preciso indicar quais os serviços, pela ordem em
	 * que estes devem ser visitados. Caso o serviço queira voltar a ver o utente no
	 * final, deve incluir o seu serviço no final da lista. De referir que, se o
	 * utente tiver reencaminhamentos, a finalização da consulta só elimina a mesma
	 * e a senha quando todos os serviços tiverem terminado a consulta.
	 */
	
	public Senha (Utente utente, Consulta consulta, LocalDateTime DataeHoradeEntrada, String idSenha) {
		this.utente = utente;
		this.consulta = consulta;
		this.DataeHoradeEntrada = DataeHoradeEntrada;
		this.idSenha = idSenha;
	}
	
	/** retorna o prï¿½ximo serviï¿½o associado a esta senha 
	 * @return o prï¿½ximo serviï¿½o associado a esta senha
	 * 	 */
	public Servico proxServico() {
		return null;
	}

	/** faz o processamento do fim da consulta por um dado serviï¿½o
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
