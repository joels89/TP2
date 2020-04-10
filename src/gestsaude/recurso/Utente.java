package gestsaude.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



/** Representa um Utente
 */
public class Utente 
{
	private int numeroSNS;
	private String nomeUtente;
	private ArrayList<Consulta> consultasMarcadas = new ArrayList<Consulta>();
	
	
	public Utente(int numeroSNS, String nomeUtente) 
	{		
		this.numeroSNS = numeroSNS;
		this.nomeUtente = nomeUtente;
	}


	public int getNumeroSNS() {
		return numeroSNS;
	}


	public void setNumeroSNS(int numeroSNS) {
		this.numeroSNS = numeroSNS;
	}


	public String getNomeUtente() {
		return nomeUtente;
	}


	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	
	public void addConsulta(Consulta consulta)
	{
		consultasMarcadas.add(consulta);
		Collections.sort(consultasMarcadas, new Comparator<Consulta>() 
		{		
			public int compare(Consulta consulta, Consulta consulta2) 
			{
				if(consulta.getDataConsulta().equals(consulta2.getDataConsulta()))
				{
					System.out.println("Datas Iguais");
					return consulta.getHoraConsulta().compareTo(consulta2.getHoraConsulta());
				}
				return consulta.getDataConsulta().compareTo(consulta2.getDataConsulta());
			}
		});			    	
	}
	
	public void removeConsulta(Consulta consulta) 
	{
		consultasMarcadas.remove(consulta);
	}
	
	public List <Consulta> getPresentes()
	{		
		return Collections.unmodifiableList(consultasMarcadas);
	}
	
	

	@Override
	public String toString() {
		return "Utente: " + nomeUtente + " - SnsNº(" + numeroSNS + "), Consultas Marcadas:" + consultasMarcadas;
	}
	
}
