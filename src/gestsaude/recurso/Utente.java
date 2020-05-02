package gestsaude.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import gestsaude.util.Consultas;

/**
 * Representa um Utente
 */
public class Utente {

	private String numeroSNS;
	private String nomeUtente;
	private ArrayList<Consulta> consultasMarcadas = new ArrayList<Consulta>();

	public Utente(String numeroSNS, String nomeUtente) {
		setNumeroSNS(numeroSNS);
		setNomeUtente(nomeUtente);
	}

	public String getNumeroSNS() {
		return numeroSNS;
	}

	public void setNumeroSNS(String numeroSNS) {
		if (Integer.parseInt(numeroSNS) <= 0 ) // Verifica se o número de SNS é maior que zero
			return;
		this.numeroSNS = numeroSNS;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public void addConsulta(Consulta consulta) {
		if (consultasMarcadas.contains(consulta)) { // Se a consulta existe na lista não pode ser adicionada novamente
			return;
		}
		Consultas.addConsultaOrdemData(consultasMarcadas, consulta);
	}

	public void removeConsulta(Consulta consulta) {
		if (!consultasMarcadas.contains(consulta)) { // Se a consulta não existe na lista não pode ser removida
			return;
		}
		consultasMarcadas.remove(consulta);
	}

	public List<Consulta> getConsultasMarcadas() {
		return Collections.unmodifiableList(consultasMarcadas);
	}

	@Override
	public String toString() {
		return "Utente: " + nomeUtente + " - SnsNº(" + numeroSNS + ")";
	}

}
