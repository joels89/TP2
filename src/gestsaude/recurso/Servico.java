package gestsaude.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import gestsaude.util.Consultas;
import gestsaude.util.gestSaudeUtilitarios;

/** Representa um Serviço
 */
public class Servico {
	
	/*Esta classe representa um serviço da clinica. Deve ter um identificador único, uma
	descrição e uma lista, ordenada cronologicamente, das consultas marcada. Deve também ter
	uma lista, ordenada pela ordem de atendimento, das senhas que irão ser atendidas.
	Deve ter construtores, getters e setters relevantes. */

	private String servicoId;
	private String servicoNome;
	private ArrayList <Consulta> consultadasMarcadasServico = new ArrayList<Consulta>();
	private ArrayList<Senha> senhasAtender = new ArrayList<Senha>();
	
	
	public Servico(String servicoId, String servicoNome) 
	{
		this.servicoId = servicoId;
		this.servicoNome = servicoNome;
	}
	
	

	/** Retorna a próxima senha a ser atendida, ou null, caso não haja 
	 * @return a próxima senha a ser atendida, ou null, caso não haja
	 */
	public Senha getProximaSenha() 
	{
		if (senhasAtender.size() == 0)
			return null;
		return senhasAtender.get(senhasAtender.size()-1); //para retornar a ultima senha		
	}

	/** processo para rejeitar a próxima senha, caso o utente seja muito atrasado
	 */
	public void rejeitaProximaSenha() 
	{
		senhasAtender.remove(senhasAtender.size() - 1);	
	}

	/** processo de terminar a consulta associada à senha */ 
	public void terminaConsulta( Senha s ) 
	{
		senhasAtender.remove(s);
	}
	
	
	public String getServicoId() {
		return servicoId;
	}
	public void setServicoId(String servicoId) {
		this.servicoId = servicoId;
	}

	public String getServicoNome() {
		return servicoNome;
	}
	public void setServicoNome(String servicoNome) {
		this.servicoNome = servicoNome;
	}
	
	public List<Consulta> getConsultasMarcadasServico() {
		return Collections.unmodifiableList(consultadasMarcadasServico);
	}
	
	public void addConsultasServico(Consulta consulta)
	{
		Consultas.addConsultaOrdemData(consultadasMarcadasServico, consulta); 	
	}
	
	public void removeConsultasServico(Consulta consulta) {
		consultadasMarcadasServico.remove(consulta);
	}
	
	public List<Senha> getSenhasaAtender() {
		return Collections.unmodifiableList(senhasAtender);
	}
	
	public void addSenhasServico(Senha senha)
	{
		senhasAtender.add(senha);	
	}
	
	public void removeConsultasServico(Senha senha) {
		senhasAtender.remove(senha);
	}
	
	

	@Override
	public String toString() {
		return "Servico: " + servicoNome + " (" + servicoId + ") \n Consultas Marcadas: \n"
				+ consultadasMarcadasServico + "\n Senhas Atender:" + senhasAtender + "\n";
		
	}
	
	
	
	
}

