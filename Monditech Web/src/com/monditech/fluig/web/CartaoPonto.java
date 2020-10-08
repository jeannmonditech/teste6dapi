package com.monditech.fluig.web;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.monditech.classes.Retorno;
import com.monditech.fluig.Parametros;
import com.monditech.fluig.SQL;
import com.monditech.fluig.Workflow;
import com.monditech.webservices.Consultas;
import com.monditech.webservices.ConsultasSoap;

@LocalBean
@Path("/CartaoPonto")
public class CartaoPonto {

	@GET
    @Path("/GetTeste")
	public Response GetTeste() {
		
		return Retorno.Sucesso("Cartão Ponto OK!");
	
	}
	
	@GET
    @Path("/GetPonto")
	public Response GetPonto(@QueryParam("matricula") String matricula, @QueryParam("dataInicio") String dataInicio, @QueryParam("dataFim") String dataFim) throws Exception {
		
		Parametros parametros = new Parametros();
		
		URL url = new URL(parametros.GetParametro("webserviceMonditech"));
		
		Consultas consultas = new Consultas(url);
		ConsultasSoap consultasSoap = consultas.getConsultasSoap();
		
		return Retorno.Sucesso(consultasSoap.consultarCartaoPonto(parametros.GetParametro("loginFluig"), parametros.GetParametro("senhaFluig"), matricula, dataInicio, dataFim));

	}
	
	@GET
    @Path("/AbreRegistroPonto")
	public Response GetPonto(	@QueryParam("tipoOcorrencia") String tipoOcorrencia,
								@QueryParam("matricula") String matricula,
								@QueryParam("nomeColaborador") String nomeColaborador,
								@QueryParam("dataOcorrencia") String dataOcorrencia,
								@QueryParam("horarioOcorrencia") String horarioOcorrencia,
								@QueryParam("horaInicialOcorrencia") String horaInicialOcorrencia,
								@QueryParam("horaFinalOcorrencia") String horaFinalOcorrencia,
								@QueryParam("justificativaColaborador") String justificativaColaborador,
								@QueryParam("gestor") String gestor,
								@QueryParam("ehPapelRh") String ehPapelRh) throws Exception {
		
		Workflow workflow = new Workflow();
		workflow.AddData("tipOcorrencia", tipoOcorrencia);
		workflow.AddData("matriculaColaborador", matricula);
		workflow.AddData("nomeColaborador", nomeColaborador);
		workflow.AddData("datOcorrencia", dataOcorrencia);
		workflow.AddData("horarioOcorrencia", horarioOcorrencia);
		workflow.AddData("horInicialOcorrencia", horaInicialOcorrencia);
		workflow.AddData("horFinalOcorrencia", horaFinalOcorrencia);
		workflow.AddData("justificativaColaborador", justificativaColaborador);
		workflow.AddData("aprovadorWidgetPonto", gestor);
		workflow.AddData("ehPapelRh", ehPapelRh);
		
		String[] colleagueIds = new String[1];
		colleagueIds[0] = new String();
		colleagueIds[0] = "System:Auto";
				
		try {
			
			String numeroFluig = workflow.Start("RH001", 16, colleagueIds, "Solicitação iniciada pela página de cartão ponto!", matricula, true, false);
			
			return Retorno.Sucesso(numeroFluig);
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}
	
	}
	
	@GET
    @Path("/GetSolicitacaoCriada")
	public Response GetSolicitacaoCriada(@QueryParam("matricula") String matricula, @QueryParam("dataOcorrencia") String dataOcorrencia) throws Exception {

		
			
		
		
		
		SQL sql = new SQL("DatabaseCustom");
		try{
		ResultSet resultSet = sql.Select("select distinct fluig from ponto_solicitacoes_correcao where matricula = '" + matricula + "' and data = '" + dataOcorrencia + "'");
			try{
				ArrayList<String> lista = new ArrayList<String>();
				
				while (resultSet.next()) {
						
					SQL sqlFluig = new SQL("DatabaseFluig");
					
					ResultSet resultSetFluig = sqlFluig.Select(	"select ESTADO_PROCES.NOM_ESTADO from HISTOR_PROCES " +
																"inner join PROCES_WORKFLOW on PROCES_WORKFLOW.NUM_PROCES = HISTOR_PROCES.NUM_PROCES " +
																"inner join ESTADO_PROCES on ESTADO_PROCES.COD_DEF_PROCES = 'RH001' " +
																"and ESTADO_PROCES.NUM_SEQ = HISTOR_PROCES.NUM_SEQ_ESTADO " +
																"and ESTADO_PROCES.NUM_VERS = PROCES_WORKFLOW.NUM_VERS " +
																"where HISTOR_PROCES.NUM_PROCES = " + resultSet.getString("fluig") + " " +
																"and PROCES_WORKFLOW.STATUS <> 1 " +
																"and PROCES_WORKFLOW.COD_MATR_REQUISIT = '" + matricula + "' " +
																"and HISTOR_PROCES.NUM_SEQ_MOVTO = (select max(NUM_SEQ_MOVTO) from HISTOR_PROCES where HISTOR_PROCES.NUM_PROCES = " + resultSet.getString("fluig") + ")");
					
					try{
						if (resultSetFluig.next()) {
							
							lista.add(resultSet.getString("fluig") + ";" + resultSetFluig.getString("NOM_ESTADO"));
							
						}
					}finally{
						resultSetFluig.close();
						sqlFluig.Close();
					}
					
					
					
				}
						
				return Retorno.Sucesso(lista.toString());
			}finally{
				resultSet.close();
			}
		}finally {
			
			sql.Close();
			
		}
					
	}
	
	@GET
    @Path("/GetUltimaAtualizacao")
	public Response GetUltimaAtualizacao(@QueryParam("matricula") String matricula) throws Exception {

		Parametros parametros = new Parametros();
		
		URL url = new URL(parametros.GetParametro("webserviceMonditech"));
		
		Consultas consultas = new Consultas(url);
		ConsultasSoap consultasSoap = consultas.getConsultasSoap();

		return Retorno.Sucesso(consultasSoap.ultimaAtualizacaoBanco(parametros.GetParametro("loginFluig"), parametros.GetParametro("senhaFluig"), matricula));
		
	}
	
	@GET
    @Path("/GetDadosFuncionario")
	public Response GetDadosFuncionario(@QueryParam("matricula") String matricula) throws Exception {

		Parametros parametros = new Parametros();
		
		URL url = new URL(parametros.GetParametro("webserviceMonditech"));
		
		Consultas consultas = new Consultas(url);
		ConsultasSoap consultasSoap = consultas.getConsultasSoap();

		return Retorno.Sucesso(consultasSoap.getDadosFuncionario(parametros.GetParametro("loginFluig"), parametros.GetParametro("senhaFluig"), matricula));
	
	}
	
}
