package com.monditech.fluig.web.teste;

import java.net.URL;

import com.monditech.webservices.Consultas;
import com.monditech.webservices.ConsultasSoap;

public class Teste {

	public static void main(String[] args) throws Exception {
		
		//HashMap<String, String> teste = new HashMap<String, String>();
		
		URL url = new URL("http://remoto.dapi.com.br:8091/SOAP%20Consulta%20VIEW%20Homologacao/Consultas.asmx?WSDL");
		
		Consultas ws = new Consultas(url);
		ConsultasSoap wss = ws.getConsultasSoap();
		
		System.out.println(wss.consultarUsuariosOnline("monditech", "m0nd1t3ch.2018"));

		//System.out.println(wss.zoomCountConsultarExamesPaciente("digital", "dapi", "70"));
		
	}
}