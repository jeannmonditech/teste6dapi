package com.monditech.classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Util {

	public String Prazo(String tipo, int valor) {
		
		Date dataAtual = new Date();

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(dataAtual);
		
		// Alteramos a data para o valor solicitado
		if(tipo.equals("dia") || tipo.equals("semana")){
			
			calendario.add(Calendar.DATE, valor);
		}
		else if(tipo.equals("mes")){
			
			calendario.add(Calendar.MONTH, valor);
		} else if (tipo.equals("padrao")){
			
			calendario.add(Calendar.HOUR, valor);
		}
		
		// Obtemos a data alterada
		dataAtual = calendario.getTime();
		
		System.out.println(dataAtual);
		
		// Obtemos os segundos da data alterada
		int hora = calendario.get(Calendar.HOUR_OF_DAY) * 3600;
		int minuto = calendario.get(Calendar.MINUTE) * 60;
		int segundo = calendario.get(Calendar.SECOND);
		
		int prazo = hora + minuto + segundo;
		
		// Data prazo formatada dd-MM-YYYY
		String prazoFormatado = new SimpleDateFormat("dd/MM/yyyy").format(dataAtual);
		
		String retorno = "{\"data\": \""+ prazoFormatado +"\", \"segundos\": \"" + prazo + "\" }";
		
		return retorno;
	}

}
