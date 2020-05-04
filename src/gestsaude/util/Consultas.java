package gestsaude.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import gestsaude.recurso.Consulta;
import gestsaude.recurso.Senha;

/**
 * Class utilitária que define um conjunto base de operações com listas de
 * consultas
 */
public class Consultas {

	/**
	 * adiciona a cosulta na lista usando a ordem cronológica
	 * 
	 * @param cs lista onde colcoar a consulta
	 * @param c  a cosulta a adicionar
	 */
	public static void addConsultaOrdemData(List<Consulta> cs, Consulta c) {
		int idx = 0;
		if(cs.isEmpty())
			cs.add(c);
		else{
			for (Consulta consulta : cs) {
				if (c.getDataConsulta().atTime(c.getHoraConsulta()).isAfter(consulta.getDataConsulta().atTime(consulta.getHoraConsulta())))
					idx++;
			}
			cs.add(idx, c);
		}
	}

	public static List<Consulta> getConsultaEntreDatas( List<Consulta> cs, LocalDateTime ini, LocalDateTime fim ) {
		List<Consulta> listaConsultasEntreDatas = new ArrayList<Consulta>();		
		for (Consulta consulta : cs) {
			LocalDateTime dataHoraConsulta = consulta.getDataConsulta().atTime(consulta.getHoraConsulta());
			if ((dataHoraConsulta.isBefore(fim)) && (dataHoraConsulta.isAfter(ini)))
				listaConsultasEntreDatas.add(consulta);
		}
		return Collections.unmodifiableList(listaConsultasEntreDatas);
	}

	/** Retorna uma lista apenas com as consultas marcadas num dado dia
	 * @param cs lista de consulta a filtrar
	 * @param dia dia a usar
	 * @return uma lista com as consultas marcadas para dia
	 */
	public static List<Consulta> getConsultasDoDia( List<Consulta> cs, LocalDate dia ) {
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
	public static List<Consulta> getConsultasApos( List<Consulta> cs, LocalDateTime t ) { // TODO nao eta a ser utilzado
		List<Consulta> listaConsultasApos = new ArrayList<Consulta>();		
		for (Consulta consulta : cs) {
			LocalDateTime dataHoraConsulta = consulta.getDataConsulta().atTime(consulta.getHoraConsulta());  //LocalDate + LocalTime = LocalDateTime	
			if ((dataHoraConsulta.isAfter(t)))
				listaConsultasApos.add(consulta);
		}
		return Collections.unmodifiableList(listaConsultasApos); // TODO needs to be tested
	}

}
