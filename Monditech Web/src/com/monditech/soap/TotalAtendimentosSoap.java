package com.monditech.soap;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class TotalAtendimentosSoap {
	
	public String CriaRequestSoap(String login, String senha, String matricula, String dataIni, String dataFim) throws SOAPException, IOException {
		  //XML passado no SOAP
//	    String requestSoap = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:web='http://webservices.monditech.com/'>"
//					     + "<soapenv:Header/>"
//					     + " <soapenv:Body>"
//					         + "<web:ConsultaTotalAtendimentos>"
//					            + "<web:login>monditech</web:login>"
//					            + "<web:senha>m0nd1t3ch.2018</web:senha>"
//					            + "<web:matricula>123123</web:matricula>"
//					            + "<web:dataInicio>09-NOV-11</web:dataInicio>"
//					            + "<web:dataFim>25-NOV-11</web:dataFim>"
//					         + "</web:ConsultaTotalAtendimentos>"
//					      + "</soapenv:Body>"
//					   + "</soapenv:Envelope>";
	    String requestSoap = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:web='http://webservices.monditech.com/'>"
			     + "<soapenv:Header/>"
			     + " <soapenv:Body>"
			         + "<web:ConsultaTotalAtendimentos>"
			            + "<web:login>" + login + "</web:login>"
			            + "<web:senha>" + senha + "</web:senha>"
			            + "<web:matricula>" + matricula + "</web:matricula>"
			            + "<web:dataInicio>" + dataIni + "</web:dataInicio>"
			            + "<web:dataFim>" + dataFim + "</web:dataFim>"
			         + "</web:ConsultaTotalAtendimentos>"
			      + "</soapenv:Body>"
			   + "</soapenv:Envelope>";
	   // System.out.println(requestSoap);
	    SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	    SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	    String url = "http://remoto.dapi.com.br/SOAP%20Consulta%20VIEW%20Homologacao/Consultas.asmx?wsdl";
	    MimeHeaders headers = new MimeHeaders();
	    headers.addHeader("Content-Type", "text/xml");
	    //soapActiondo metodo contigo no WSDL e que sera usado
	    headers.addHeader("SOAPAction", "http://webservices.monditech.com/ConsultaTotalAtendimentos"); 
	    MessageFactory messageFactory = MessageFactory.newInstance();

	    SOAPMessage msg = messageFactory.createMessage(headers, (new ByteArrayInputStream(requestSoap.getBytes())));

	    SOAPMessage soapResponse = soapConnection.call(msg, url);
	    Document xmlRespostaARequisicao=soapResponse.getSOAPBody().getOwnerDocument();
	    //Retorno do WS
	    //System.out.println(XMLToString(xmlRespostaARequisicao,4));
	
		JSONObject jsonObj = XML.toJSONObject(XMLToString(xmlRespostaARequisicao,4));
		
		String json = jsonObj.toString(4);

		String retorno = json.split("\\[")[1];
		String retornoFormatado = retorno.substring(0,retorno.indexOf("]"));
		
		
	    return retornoFormatado.split(":")[1].replace("}","");
	}
	
	public  String XMLToString(Document xml, int espacosIdentacao){
	    try {

	        TransformerFactory transfac = TransformerFactory.newInstance();
	        transfac.setAttribute("indent-number", new Integer(espacosIdentacao));
	        Transformer trans = transfac.newTransformer();
	        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        trans.setOutputProperty(OutputKeys.INDENT, "yes");

	        //Cria string do xml
	        StringWriter sw = new StringWriter();
	        StreamResult result = new StreamResult(sw);
	        DOMSource source = new DOMSource(xml);
	        trans.transform(source, result);
	        String xmlString = sw.toString();
	        
	        return xmlString;
	    }
	    catch (TransformerException e) {
	        e.printStackTrace();
	        System.exit(0);
	    }
	    return null;
	}

}


