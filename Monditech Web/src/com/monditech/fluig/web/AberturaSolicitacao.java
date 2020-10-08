package com.monditech.fluig.web;

import javax.ejb.LocalBean;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.monditech.classes.Retorno;
import com.monditech.classes.SolicitacaoExternaOuvidoriaInterna;
import com.monditech.fluig.Parametros;
import com.monditech.fluig.Workflow;

@LocalBean
@Path("/AberturaSolicitacao")
public class AberturaSolicitacao {
	
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
    @Path("/StartProcessOuvidoriaInterna")
	public Response StartProcessOuvidoriaInterna(SolicitacaoExternaOuvidoriaInterna solicitacao){
		
		try {

			Workflow workflow = new Workflow();				
			
			workflow.AddData("unidade", solicitacao.getUnidade());
			workflow.AddData("setor", solicitacao.getSetor());
			workflow.AddData("turno", solicitacao.getTurno());
			workflow.AddData("solicitante", solicitacao.getSolicitante());
			workflow.AddData("manifestacao", solicitacao.getTratamento());
			workflow.AddData("assunto", solicitacao.getAssunto());
			workflow.AddData("outroAssunto", solicitacao.getOutroAssunto());
			workflow.AddData("comentarios", solicitacao.getComentarios());		

			Parametros parametros = new Parametros();
			
			return Retorno.Sucesso(workflow.Start("ouvidoriaInterna", 5, new String[]{ null }, "Solicitação iniciada!", parametros.GetParametro("matriculaFluig"), true, false));
			
		} catch (Exception ex) {
			return Retorno.Erro(ex);
		}		
	}
}
