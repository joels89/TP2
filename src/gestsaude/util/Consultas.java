package gestsaude.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gestsaude.menu.Arranque;
import gestsaude.recurso.Consulta;

/** Class utilit�ria que define um conjunto base de opera��es com listas de consultas
 */
public class Consultas {

	/** adiciona a cosulta na lista usando a ordem cronol�gica
	 * @param cs lista onde colcoar a consulta
	 * @param c a cosulta a adicionar
	 */
	public static void addConsultaOrdemData( List<Consulta> cs, Consulta c  ) {
		// TODO implementar este m�todo
	}

	/** retorna uma lista apenas com as consultas que est�o entre as datas definidas
	 * @param cs lista de consulta a filtrar
	 * @param ini data inicial a considerar (inclusive)
	 * @param fim data final a considerar (inclusive)
	 * @return uma lista com as consultas que est�o entre as datas definidas
	 */
	public static List<Consulta> getConsultaEntreDatas( List<Consulta> cs, LocalDateTime ini, LocalDateTime fim ){
		// TODO implementar este m�todo
		return null;
	}
	
	/** Retorna uma lista apenas com as consultas marcadas num dado dia
	 * @param cs lista de consulta a filtrar
	 * @param dia dia a usar
	 * @return uma lista com as consultas marcadas para dia
	 */
	public static List<Consulta> getConsultasDoDia( List<Consulta> cs, LocalDate dia ) 
	{
		List<Consulta> listaConsultasDia = new ArrayList<Consulta>();		
		for (Consulta consulta : cs)
			if(consulta.getDataConsulta().equals(dia))
				listaConsultasDia.add(consulta);
		return Collections.unmodifiableList(listaConsultasDia);
	}
	
	/** Retorna uma lista com as consultas ap�s uma dada data
	 * @param cs  lista de consulta a filtrar
	 * @param t tempo a aptire do qual se deve considerar as consultas (inclusive)
	 * @return  uma lista com as consultas ap�s a data t
	 */
	public static List<Consulta> getConsultasApos( List<Consulta> cs, LocalDateTime t ) {
		// TODO implementar este m�todo
		return null;
	}
}
