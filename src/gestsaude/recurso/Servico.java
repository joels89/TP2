package gestsaude.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/** Representa um Serviço
 */
public class Servico {
	
	/*Esta classe representa um serviço da clinica. Deve ter um identificador único, uma
	descrição e uma lista, ordenada cronologicamente, das consultas marcada. Deve também ter
	uma lista, ordenada pela ordem de atendimento, das senhas que irão ser atendidas.
	Deve ter construtores, getters e setters relevantes. */

	private String id;
	private String descricao;
	private ArrayList <Consulta> consultadasMarcadas = new ArrayList<Consulta>();
	private ArrayList<Senha> senhasTiradas = new ArrayList<Senha>();
	
	
	public Servico(String id, String descricao) 
	{
		this.id = id;
		this.descricao = descricao;
	}
	
	

	/** Retorna a próxima senha a ser atendida, ou null, caso não haja 
	 * @return a próxima senha a ser atendida, ou null, caso não haja
	 */
	public Senha getProximaSenha() 
	{
		if (senhasTiradas.size() == 0)
			return null;
		return senhasTiradas.get(senhasTiradas.size()-1); //para retornar a ultima senha		
	}

	/** processo para rejeitar a próxima senha, caso o utente seja muito atrasado
	 */
	public void rejeitaProximaSenha() 
	{
		senhasTiradas.remove(senhasTiradas.size() - 1);	
	}

	/** processo de terminar a consulta associada à senha */ 
	public void terminaConsulta( Senha s ) 
	{
		senhasTiradas.remove(s);
	}
	
	public void addConsultas(Consulta consulta)
	{
		consultadasMarcadas.add(consulta);
		
		Collections.sort(consultadasMarcadas, new Comparator<Consulta>() 
		{		
			public int compare(Consulta consulta, Consulta consulta2) 
			{
				if(consulta.getData().equals(consulta2.getData()))
				{
					return consulta.getHora().compareTo(consulta2.getHora());
				}
				return consulta.getData().compareTo(consulta2.getData());
			}
		});			    	
	}
	

	@Override
	public String toString() {
		return "Servico [id=" + id + ", descricao=" + descricao + "]";
	}
	
	
	
	
}

