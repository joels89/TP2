package gestsaude.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import gestsaude.recurso.Consulta;

public class gestSaudeUtilitarios {
	
	
		
	public static void ordenarListaCronologica (ArrayList<Consulta> consultasMarcadas)
	{
		Collections.sort(consultasMarcadas, new Comparator<Consulta>() 
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
	
	

}
