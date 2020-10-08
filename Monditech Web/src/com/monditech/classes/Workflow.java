package com.monditech.classes;

import java.net.URL;
import java.util.ArrayList;

import com.monditech.fluig.Parametros;
import com.monditech.fluig.webservices.ECMWorkflowEngineServiceService;
import com.monditech.fluig.webservices.ProcessAttachmentDtoArray;
import com.monditech.fluig.webservices.ProcessTaskAppointmentDtoArray;
import com.monditech.fluig.webservices.StringArray;
import com.monditech.fluig.webservices.StringArrayArray;
import com.monditech.fluig.webservices.WorkflowEngineService;


public class Workflow {
	
	private ArrayList<StringArray> listaCampos = new ArrayList<StringArray>();
	
	
	public Workflow() throws Exception {

	}
	
	public String Start(String processo, Integer etapa, String[] colleagueIds, String comentario, String solicitante, boolean completeTask, boolean managerMode) throws Exception {
		
		URL url = new URL("http://liga-dev.fluigcloud.com.br/webdesk/ECMWorkflowEngineService?wsdl");

		ECMWorkflowEngineServiceService ecmWorkflowEngineService = new ECMWorkflowEngineServiceService(url);
		WorkflowEngineService service = ecmWorkflowEngineService.getWorkflowEngineServicePort();
				
		StringArray responsaveis = new StringArray();
		ProcessTaskAppointmentDtoArray appointment = new ProcessTaskAppointmentDtoArray();
		StringArrayArray cardData = new StringArrayArray();
		ProcessAttachmentDtoArray anexos = new ProcessAttachmentDtoArray();
		
		for (int i = 0; i < colleagueIds.length; i++) {
			
			responsaveis.getItem().add(colleagueIds[i]);
			
		}
						
		for (int i = 0; i < listaCampos.size(); i++) {
			
			cardData.getItem().add(listaCampos.get(i));
			
		}

		Parametros parametros = new Parametros();
		
		StringArrayArray retorno = service.startProcess(parametros.GetParametro("matriculaFluig"), parametros.GetParametro("senhaFluig"), 1, processo, etapa, responsaveis, comentario, solicitante, completeTask, anexos, cardData, appointment, managerMode);
						
		for (StringArray stringArray : retorno.getItem()) {
			
			if (stringArray.getItem().get(0).equals("ERROR")) {
				
				throw new Exception(stringArray.getItem().get(1));
								
			}
			else if (stringArray.getItem().get(0).equals("iProcess")) {
				
				return stringArray.getItem().get(1);
				
			}
			
		}
		
		throw new Exception("Erro na criação de uma nova solicitação!");
				
	}
	
	public void AddData(String nome, String valor) throws Exception {
		
		valor.replace("'","");
				
		StringArray campo = new StringArray();
		campo.getItem().add(0, nome);
		campo.getItem().add(1, valor);
				
		listaCampos.add(campo);
		
	}
	
	public String toString() {
		
		String retorno = "";
		
		for (int i = 0; i < listaCampos.size(); i++) {
			
			retorno += listaCampos.get(i).getItem();
			
		}
		
		return retorno;
		
	}
	
}

