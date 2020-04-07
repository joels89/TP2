package gestsaude.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/** Representa um Utente
 */
public class Utente 
{
	private int snsnumber;
	private String nomeDoUtente;
	private ArrayList<Consulta> consultasMarcadas = new ArrayList<Consulta>();
	
	
	public Utente(int sns, String nomeDoUtente) 
	{		
		this.snsnumber = sns;
		this.nomeDoUtente = nomeDoUtente;
	}


	public int getSnsnumber() {
		return snsnumber;
	}


	public void setSnsnumber(int snsnumber) {
		this.snsnumber = snsnumber;
	}


	public String getNomeDoUtente() {
		return nomeDoUtente;
	}


	public void setNomeDoUtente(String nomeDoUtente) {
		this.nomeDoUtente = nomeDoUtente;
	}
	
	public void addConsulta(Consulta consulta)
	{
		consultasMarcadas.add(consulta);
	}
	
	public void eliminarConsulta(Consulta consulta) 
	{
		consultasMarcadas.remove(consulta);
	}
	
	public List <Consulta> getPresentes()
	{
		return Collections.unmodifiableList(consultasMarcadas);
	}


	@Override
	public String toString() {
		return "Utente [Numero de Utente=" + snsnumber + ", nomeDoUtente=" + nomeDoUtente + "]";
	}
	
}
