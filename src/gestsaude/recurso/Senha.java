package gestsaude.recurso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import gestsaude.recurso.Servico;

/**
 * Representa uma Senha
 */
public class Senha {

	private Utente utente;
	private Consulta consulta;
	private String idSenha;
	private LocalDateTime dataHoraEntrada;
	public List<Servico> servicosVisitar = new ArrayList<Servico>();

	public Senha(Utente utente, Consulta consulta, LocalDateTime dataHoraEntrada, String idSenha) {
		setUtente(utente);
		setConsulta(consulta);
		setDataHoraEntrada(dataHoraEntrada);
		setIdSenha(idSenha);
	}

	/**
	 * retorna o próximo serviço associado a esta senha
	 * 
	 * @return o próximo serviço associado a esta senha
	 */
	public Servico proxServico() {
		return servicosVisitar.get(Servico.LISTA_INDICE_ZERO);
	}

	/**
	 * faz o processamento do fim da consulta por um dado serviço
	 */
	public void terminaConsulta(Servico servico) {
		servicosVisitar.remove(servico);
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

	public LocalDateTime getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}

	public String getIdSenha() {
		return idSenha;
	}

	public void setIdSenha(String idSenha) {
		this.idSenha = idSenha;
	}

	public void addServicosVistar(Servico servico) {
		if (servicosVisitar.contains(servico)) {
			return;
		}
		servicosVisitar.add(servico);
	}

	public void removeServicosVistar(Servico servico) {
		if (!servicosVisitar.contains(servico)) {
			return;
		}
		servicosVisitar.remove(servico);
	}

	public List<Servico> getServicosVisitar() {
		return Collections.unmodifiableList(servicosVisitar);
	}

	@Override
	public String toString() {
		return "idSenha=" + idSenha + ", DataeHoradeEntrada=" + dataHoraEntrada + "]";
	}

}