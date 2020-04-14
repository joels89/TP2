package gestsaude.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import gestsaude.menu.Arranque;
import gestsaude.recurso.Consulta;

/** Class utilitária que define um conjunto base de operações com listas de consultas
 */
public class Consultas {

	/** adiciona a cosulta na lista usando a ordem cronológica
	 * @param cs lista onde colcoar a consulta
	 * @param c a cosulta a adicionar
	 */
	public static void addConsultaOrdemData( List<Consulta> cs, Consulta c  ) 
	{
		cs.add(c);
		Collections.sort(cs, new Comparator<Consulta>() 
			{		
				public int compare(Consulta consulta, Consulta consulta2) 
				{
					if(consulta.getDataConsulta().equals(consulta2.getDataConsulta()))
					{
						return consulta.getHoraConsulta().compareTo(consulta2.getHoraConsulta());
					}
					return consulta.getDataConsulta().compareTo(consulta2.getDataConsulta());
				}
		});									
	}


	public static List<Consulta> getConsultaEntreDatas( List<Consulta> cs, LocalDateTime ini, LocalDateTime fim ){
		// TODO implementar este método
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
	
	/** Retorna uma lista com as consultas após uma dada data
	 * @param cs  lista de consulta a filtrar
	 * @param t tempo a aptire do qual se deve considerar as consultas (inclusive)
	 * @return  uma lista com as consultas após a data t
	 */
	public static List<Consulta> getConsultasApos( List<Consulta> cs, LocalDateTime t ) {
		// TODO implementar este método
		return null;
	}
}
