package gestsaude.recurso;

/** Representa um Serviço
 */
public class Servico {

	/** Retorna a próxima senha a ser atendida, ou null, caso não haja 
	 * @return a próxima senha a ser atendida, ou null, caso não haja
	 */
	public Senha getProximaSenha() {
		return null;
	}

	/** processo para rejeitar a próxima senha, caso o utente seja muito atrasado
	 */
	public void rejeitaProximaSenha() {
	}

	/** processo de terminar a consulta associada à senha */ 
	public void terminaConsulta( Senha s ) {
	}
}

