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
		return getSenhasaAtender().get(0); //para retornar a ultima senha		
	}

	/** processo para rejeitar a próxima senha, caso o utente seja muito atrasado
	 */
	public void rejeitaProximaSenha() 
	{
		senhasAtender.remove(getProximaSenha());	
	}

	/** processo de terminar a consulta associada à senha */ 
	public void terminaConsulta( Senha s ) 
	{
		if (senhasAtender.contains(s))
		{
			senhasAtender.remove(s);
		}		
		return;	
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
		Collections.sort(senhasAtender, new Comparator<Senha>() 
			{		
				public int compare(Senha senha, Senha senha1) 
				{
					if(senha.getConsulta().getDataConsulta().equals(senha1.getConsulta().getDataConsulta()))
					{
						return senha.getConsulta().getHoraConsulta().compareTo(senha1.getConsulta().getHoraConsulta());
					}
					return senha.getConsulta().getDataConsulta().compareTo(senha1.getConsulta().getDataConsulta());
				}
		});			
	}
	
	public void removeSenhaServico(Senha senha) {
		senhasAtender.remove(senha);
	}
	
	@Override
	public String toString() {
		return "Servico [servicoId=" + servicoId + ", servicoNome=" + servicoNome + "]";
	}
	
	
	
}

