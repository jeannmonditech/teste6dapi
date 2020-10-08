package com.monditech.fluig.web;

import java.sql.ResultSet;

import javax.ejb.LocalBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.monditech.classes.Retorno;
import com.monditech.fluig.Parametros;
import com.monditech.fluig.SQL;
import com.monditech.fluig.Util;

@LocalBean
@Path("/SolicitacaoRetorno")
public class SolicitacaoRetorno {

	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetSolicitacoesRetorno")
	public Response GetSolicitacoesRetorno(@QueryParam("inicioPeriodo") String inicioPeriodo, @QueryParam("fimPeriodo") String fimPeriodo,
			@QueryParam("tipoSolicitacao") String tipoSolicitacao, @QueryParam("tipoAtendimento") String tipoAtendimento) {
		
		try {
			
			SQL sql = new SQL("DatabaseFluig");
			try{
				
			
			Parametros parametros = new Parametros();
			
			String query = "";
			
			query += "SELECT " +
								"pw.NUM_PROCES AS fluig, " +
								"ml.valueTipoSolicitacao, " +
								"ml.valueTipoAtendimento, " +
								"hp.NUM_SEQ_ESTADO AS etapaAtual, " +
							    "hp.LOG_ATIV AS statusAtivo, " +
								"pw.START_DATE AS dataHoraAbertura, " +
								"pw.END_DATE AS dataEncerramento, " +
								"(select fdn_user.FULL_NAME from fdn_usertenant inner join fdn_user on fdn_usertenant.USER_TENANT_ID = fdn_user.USER_ID where fdn_usertenant.USER_CODE = pw.COD_MATR_REQUISIT) AS solicitante, " +
								"(select user.FULL_NAME from tar_proces as tar inner join fdn_usertenant as usertenant on tar.COD_MATR_ESCOLHID = usertenant.USER_CODE inner join fdn_user as user on usertenant.USER_TENANT_ID = user.USER_ID where NUM_PROCES = ap.NUM_PROCES and NUM_SEQ_MOVTO = 2 and NUM_SEQ_ESCOLHID = 5 order by NUM_SEQ_TRANSF desc limit 1) AS atendente, " +	
								"ml.nomeProfissional, " +	
								"ml.inscricaoCRO, " +
								"ml.inscricaoCRM, " +
								"ml.descricao AS descricaoSolicitante, " +
								"ml.descricaoCanalMedico " +      	    
							    
							"FROM proces_workflow AS pw " +
							
							"INNER JOIN anexo_proces AS ap " +
								"ON ap.NUM_PROCES = pw.NUM_PROCES " +
			
							"INNER JOIN " + parametros.GetParametro("mlRelatorioSoliciRetorno") + " AS ml " +
								"ON ap.NR_DOCUMENTO = ml.documentid " +
							    "AND ap.NR_VERSAO = ml.version " +
							    
							"LEFT JOIN histor_proces AS hp " +
								"ON ap.NUM_PROCES = hp.NUM_PROCES " +
							    "AND hp.NUM_SEQ_MOVTO = (SELECT COUNT(*) FROM histor_proces WHERE NUM_PROCES = ap.NUM_PROCES) " +				 
								
							"WHERE 1 = 1 " +
							
							((!(inicioPeriodo.isEmpty()) && !(fimPeriodo.isEmpty())) ? "AND (DATE(pw.START_DATE) >= '" + inicioPeriodo + "' AND DATE(pw.START_DATE) <= '" + fimPeriodo + "') " : "");														
										
							String filtrosSolicitacao[] = tipoSolicitacao.split(";");
							
							if (filtrosSolicitacao.length >= 1) {
								
								query += "AND (";
								
								for (int i = 0; i < filtrosSolicitacao.length; i++) {
									
									query += (i > 0 ? "OR" : "") + " valueTipoSolicitacao = '" + filtrosSolicitacao[i] + "' ";
									
								}
								
								query += ") ";
								
							}								
														
							String filtrosAtendimento[] = tipoAtendimento.split(";");	
							
							if (filtrosAtendimento.length >= 1) {
								
								query += "AND (";
								
								for (int j = 0; j < filtrosAtendimento.length; j++) {
									
									if (filtrosAtendimento[j].equals("naoClassificadas")) {
										
										query += ((j > 0 ? "OR" : "")) + " valueTipoAtendimento IS NULL OR valueTipoAtendimento = '' ";
										
									} else {
										
										query += ((j > 0 ? "OR" : "")) + " valueTipoAtendimento = '" + filtrosAtendimento[j] + "' ";
										
									}																		
									
								}
								
								query += ") ";
								
							}
							
							query += "ORDER BY fluig ";
							
			System.out.println("RYEO-000");
			System.out.println(query);
			
			ResultSet resultSet = sql.Select(query);
			Util util = new Util();
			try{
				return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));	
			}finally{
				resultSet.close();
			}
			
			
			
			}finally{
				sql.Close();
			}
					
			
		} catch (Exception ex) {
			
			return Retorno.Erro(ex);
		}		
	
	}
	
}
