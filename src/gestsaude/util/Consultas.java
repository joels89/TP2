package gestsaude.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


import gestsaude.recurso.Consulta;
import gestsaude.recurso.Utente;

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


	public static List<Consulta> getConsultaEntreDatas( List<Consulta> cs, LocalDateTime ini, LocalDateTime fim )
	{
		List<Consulta> listaConsultasEntreDatas = new ArrayList<Consulta>();		
		for (Consulta consulta : cs)
		{
			LocalDateTime dataHoraConsulta = consulta.getDataConsulta().atTime(consulta.getHoraConsulta());  //LocalDate + LocalTime = LocalDateTime
			
			if ((dataHoraConsulta.isBefore(fim)) && (dataHoraConsulta.isAfter(ini)))
				listaConsultasEntreDatas.add(consulta);
		}
		return Collections.unmodifiableList(listaConsultasEntreDatas); //needs to be tested
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

		List<Consulta> listaConsultasApos = new ArrayList<Consulta>();		
		for (Consulta consulta : cs)
		{
			LocalDateTime dataHoraConsulta = consulta.getDataConsulta().atTime(consulta.getHoraConsulta());  //LocalDate + LocalTime = LocalDateTime
			
			if ((dataHoraConsulta.isAfter(t)))
				listaConsultasApos.add(consulta);
		}
		return Collections.unmodifiableList(listaConsultasApos); //needs to be tested

	}
	
	public static boolean temConsultaProxima(Utente u, LocalTime horaSenha) {// -------------------------TODO -ver onde por o método
		if (u.getPresentes() != null) {
			return (Math.abs(
					RelogioSimulado.localTime2Min(u.getPresentes().get(0).getHoraConsulta()) - RelogioSimulado.localTime2Min(horaSenha)) < RelogioSimulado.TRES_HORAS);
			// indice zero verifica em relacao a primeira. se passarem tres horas a consulta deve ser removida confirmar.
		}
		return false;
	}

}
