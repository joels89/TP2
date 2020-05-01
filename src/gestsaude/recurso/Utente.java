package gestsaude.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import gestsaude.util.Consultas;

/** Representa um Utente
 */
public class Utente {
	
	private String numeroSNS;
	private String nomeUtente;
	private ArrayList<Consulta> consultasMarcadas = new ArrayList<Consulta>();
	
	public Utente(String numeroSNS, String nomeUtente) {		
		this.numeroSNS = numeroSNS;
		setNomeUtente(nomeUtente);
	}

	public String getNumeroSNS() {
		return numeroSNS;
	}

	public void setNumeroSNS(String numeroSNS) { //////// TODo eu removia este ou punha privado piois este valor nao deveria por se r alterado
		this.numeroSNS = numeroSNS;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	
	public void addConsulta(Consulta consulta) {
		Consultas.addConsultaOrdemData(consultasMarcadas, consulta);	    	
	}
	
	public void removeConsulta(Consulta consulta) {
		consultasMarcadas.remove(consulta);
	}
	
	public List <Consulta> getConsultasMarcadas() {		
		return Collections.unmodifiableList(consultasMarcadas);
	}
	
	@Override
	public String toString() {
		return "Utente: " + nomeUtente + " - SnsNº(" + numeroSNS + ")";
	}
	
}
