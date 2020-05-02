package gestsaude.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import gestsaude.util.Consultas;

/** Representa um Serviço
 */
public class Servico {
	
	private static final int ATRASA_UTENTE = 4;
	private String servicoId;
	private String servicoNome;
	private ArrayList <Consulta> consultadasMarcadasServico = new ArrayList<Consulta>();
	private ArrayList<Senha> senhasAtender = new ArrayList<Senha>();
	
	public Servico(String servicoId, String servicoNome) {
		setServicoId(servicoId);
		setServicoNome(servicoNome);
	}
	
	/** Retorna a próxima senha a ser atendida, ou null, caso não haja 
	 * @return a próxima senha a ser atendida, ou null, caso não haja
	 */
	public Senha getProximaSenha() {
		if (senhasAtender.size() == 0)
			return null;
		return getSenhasaAtender().get(0);		
	}
	
	public Senha getProximaSenha(int idx) {
		if (senhasAtender.size() == 0)
			return null;
		return getSenhasaAtender().get(idx);	
	}

	/** processo para rejeitar a próxima senha, caso o utente seja muito atrasado
	 */
	public void rejeitaProximaSenha() {
		senhasAtender.remove(getProximaSenha());	//***TODO*** nao esta a ser utillizado
	}

	/** processo de terminar a consulta associada à senha */ 
	public void terminaConsulta( Senha s ) {
		if (senhasAtender.contains(s)) {
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
	
	public void addConsultasServico(Consulta consulta) {
		if (consultadasMarcadasServico.contains(consulta)) {
			return;	
		}
		Consultas.addConsultaOrdemData(consultadasMarcadasServico, consulta); 	
	}
	
	public void removeConsultasServico(Consulta consulta) {
		if (!consultadasMarcadasServico.contains(consulta)) // Se a consulta não existe na lista não pode ser removida
			return;
		consultadasMarcadasServico.remove(consulta);
	}
	
	public List<Senha> getSenhasaAtender() {
		return Collections.unmodifiableList(senhasAtender);
	}
	
	public void addSenhasAoServiço(Senha senha)	{
		if(senhasAtender.contains(senha)) {	 // Se a Senha já existe na lista não pode ser adicionada novamente
			return;
		}
		
		senhasAtender.add(senha);
		Collections.sort(senhasAtender, new Comparator<Senha>()	{	// TODO	verificar se devia ser com SORT ver FAQs do prof e este codigo por num metodo
				public int compare(Senha senha, Senha senha1) {
					if(senha.getConsulta().getHoraConsulta().compareTo(senha.getHoraEntrada().toLocalTime()) < 0) {
						return senha.getHoraEntrada().compareTo(senha1.getHoraEntrada());
					}
					else {
						if(senha.getConsulta().getDataConsulta().equals(senha1.getConsulta().getDataConsulta())) {
							return senha.getConsulta().getHoraConsulta().compareTo(senha1.getConsulta().getHoraConsulta());
						}
						return senha.getConsulta().getDataConsulta().compareTo(senha1.getConsulta().getDataConsulta());
					}
				}
		});			
	}
	
	public void alteraPosiçaoSenha(Senha senha) {
		if(senhasAtender.size()<ATRASA_UTENTE) {
			senhasAtender.add(senhasAtender.size(), senha);
			senhasAtender.remove(0);
		}
		else {
			senhasAtender.add(ATRASA_UTENTE, senha);
			senhasAtender.remove(0);
		}
	}
	
	public void removeSenhaServico(Senha senha) {
		if (!senhasAtender.contains(senha)) //  Se a senha não existe na lista não pode ser removida
			return;
		senhasAtender.remove(senha);
	}
	
	@Override
	public String toString() {
		return "Servico [servicoId=" + servicoId + ", servicoNome=" + servicoNome + "]";
	}

}
