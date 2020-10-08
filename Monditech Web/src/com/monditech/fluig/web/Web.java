package com.monditech.fluig.web;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.soap.SOAPException;

import com.google.gson.Gson;
import com.monditech.classes.Registro;
import com.monditech.classes.Retorno;
import com.monditech.fluig.Consulta;
import com.monditech.fluig.Parametros;
import com.monditech.fluig.SQL;
import com.monditech.fluig.Tempo;
import com.monditech.fluig.Util;
import com.monditech.model.RegistroModel;
import com.monditech.soap.SoapFolderService;
import com.monditech.soap.TotalAtendimentosSoap;
import com.monditech.webservices.Consultas;
import com.monditech.webservices.ConsultasSoap;
import com.monditech.classes.TotalAtendimentos;

@LocalBean
@Path("/")
public class Web {
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetTeste")
	public Response GetTeste() {
		return Retorno.Sucesso("WEB OK! 10-08-2020 17:47");
	
	}
	

	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetUsuariosOnline")
	public Response GetUsuariosOnline() {
				
		try {
			
			Parametros parametros = new Parametros();
			
			URL url = new URL(parametros.GetParametro("webserviceMonditech"));
			
			Consultas ws = new Consultas(url);
			ConsultasSoap wss = ws.getConsultasSoap();
			
			return Retorno.Sucesso(wss.consultarUsuariosOnline(parametros.GetParametro("loginFluig"), parametros.GetParametro("senhaFluig")));
			
		} catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}		
	}
	
	@GET
    @Path("/GetPrazoOcorrenciaRegistroPonto")
	public Response GetPrazoOcorrenciaRegistroPonto(@QueryParam("tipo") String tipo, @QueryParam("valor") int valor, @QueryParam("data") Date ocorrencia, @QueryParam("dataAtual") Date dataAtual){
		try{
			Tempo tempo = new Tempo();
			String retorno = tempo.PrazoOcorrenciaRegistroPonto(tipo, valor, ocorrencia, dataAtual);
			return Retorno.Sucesso(retorno);
		} catch(Exception ex){
			return Retorno.Erro(ex);
		}
		
		/*System.out.println("aaaaaa-9999");
		
		try {
			
			Tempo tempo = new Tempo();
			
			//Date dataAtual = new Date();
			Boolean passouPrazo = false;
			
			Calendar atualData = Calendar.getInstance();
			atualData.setTime(dataAtual);
			
			Calendar prazoFinal = Calendar.getInstance();
			prazoFinal.setTime(ocorrencia);
			
			Calendar dataOcorrencia = Calendar.getInstance();
			dataOcorrencia.setTime(ocorrencia);
			
			SQL sql = new SQL("DatabaseFluig");
			
			if(tipo.equals("ocorrencia")){

				Integer diasNaoUteis = 0;
				
				for(int i = 1; i <= valor; i++){
					
					dataOcorrencia.add(Calendar.DATE, i);
					
					Date dataAux = new Date();
					
					dataAux = dataOcorrencia.getTime();
					
					while(!tempo.EhDiaUtil(dataAux, sql)){
						
						dataOcorrencia.add(Calendar.DATE, i);
						dataAux = dataOcorrencia.getTime();
						
						diasNaoUteis++;
					}
				}
				
				System.out.println("dias nao uteis aaaaaa");
				
				System.out.println(diasNaoUteis);
				System.out.println(valor);
				
				valor += diasNaoUteis;
				
				System.out.println(valor);
				
				prazoFinal.add(Calendar.DATE, valor);
				prazoFinal.set(Calendar.HOUR_OF_DAY, 23);
				prazoFinal.set(Calendar.MINUTE, 59);
				prazoFinal.set(Calendar.SECOND, 59);
							
			}
			
			Date dataFinal = prazoFinal.getTime();
			int segundosPrazo = tempo.ConvertDateToSeconds(dataFinal);
			
			if(dataFinal.before(dataAtual)){
				passouPrazo = true;
			}		
			
			// Data prazo formatada dd-MM-YYYY
			String prazoFormatado = new SimpleDateFormat("dd/MM/yyyy").format(dataFinal);
			
			String retorno = "{\"data\": \""+ prazoFormatado +"\", \"segundos\": \"" + segundosPrazo + "\", \"passouPrazo\": \"" + passouPrazo + "\" }";
			
			System.out.println(retorno);
			
			return Retorno.Sucesso(retorno);
			
		} catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}*/
	}
	
	// Retorna somente as tarefas com thread 0
	@GET
    @Path("/GetTarefaAtrasada")
	public Response GetTarefasAtrasadas(@QueryParam("numeroSolicitacao") String numeroSolicitacao){
		
		try{
			Tempo tempo = new Tempo();
			Boolean retorno = tempo.GetTarefaAtrasada(numeroSolicitacao);
			return Retorno.Sucesso(retorno.toString());
		} catch (Exception ex) {
			return Retorno.Erro(ex);
		}
	}
	
	@GET
    @Path("/SetPrazo")
	public String SetPrazo(@QueryParam("tipo") String tipo, @QueryParam("valor") int valor) throws Exception{
		
		com.monditech.classes.Util util = new com.monditech.classes.Util();
		 
		String retorno = util.Prazo(tipo, valor);
		
		return retorno;
	}
		
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetParametros")
	public Response GetParametros(@QueryParam("parametro") String parametro){
		
		try {
			Parametros parametros = new Parametros();
			String retorno = parametros.GetParametros(parametro);
			return Retorno.Sucesso(retorno);
		} catch (Exception ex) {
			return Retorno.Erro(ex);
		}		
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/DataInicioFluig")
	public Response DataInicioFluig(@QueryParam("fluig") String fluig) {
		
		try {
			Date retorno = Consulta.DataInicioFluig(fluig);
			return Retorno.Sucesso(retorno);
		}
		catch (Exception ex) {
			return Retorno.Erro(ex);
		}			
	}
	
	@GET
    @Path("/GetGrupoDeTrabalho")
	public Response GetGrupoDeTrabalho(@QueryParam("usuario") String usuario) {

		try{
			
			SQL sql = new SQL("DatabaseFluig");

			try{
				
				String query = 	"SELECT " + 
									"fdn_groupdata2.data_value AS setor " +
								"FROM fdn_userdata " +
									"INNER JOIN fdn_usertenant on fdn_usertenant.user_tenant_id = fdn_userdata.USER_TENANT_ID " +
								    "INNER JOIN fdn_group on fdn_group.GROUP_CODE = TRIM(fdn_userdata.DATA_value)" +
									"LEFT JOIN fdn_groupdata on fdn_groupdata.group_id = fdn_group.GROUP_ID " +
										"AND (TRIM(fdn_groupdata.DATA_KEY) = 'unidade' OR TRIM(fdn_groupdata.DATA_KEY) = 'Unidade') " +
									"LEFT JOIN fdn_groupdata fdn_groupdata2 on fdn_groupdata2.group_id = fdn_group.group_id " +
										"AND (TRIM(fdn_groupdata2.DATA_KEY) = 'setor' OR TRIM(fdn_groupdata2.DATA_KEY) = 'Setor') " +
								"WHERE fdn_usertenant.user_state = 1 " +
								"AND fdn_userdata.DATA_KEY = 'UserWorkflowGroup' " +
								"AND fdn_usertenant.login = '" + usuario + "' ";
				
				ResultSet resultSet = sql.Select(query);
				
				try{
					if (resultSet.next()) {
						
						return Retorno.Sucesso(resultSet.getString("setor"));
					}
					else {

						return Retorno.Sucesso(null);
						
					}
					
				}
				finally {
					
					resultSet.close();
					
				}
				
			}
			finally {
				
				sql.Close();
				
			}
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
	}

	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetGrupos")
	public Response GetGrupos() {
		
		SQL sql = null;
		String query = "";
		
		try {
			sql = new SQL("DatabaseFluig");
			query = "SELECT GROUP_CODE AS codigo, DESCRIPTION AS descricao FROM FDN_GROUP WHERE GROUP_TYPE = 'user'";
			try (ResultSet resultSet = sql.Select(query)) {
				return Retorno.Sucesso(new Util().ResultSetToArrayList(resultSet));
			}
		}
		catch (Exception ex) {
			System.err.println("####ERRO AO BUSCAR GRUPOS####");
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return Retorno.Erro(ex);
		}
		finally {
			if (sql != null) {
				try { sql.Close(); } catch (Exception ex) { ; }
				sql = null;
			}
		}
	}
 
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetUsuarioMatricula")
	public Response GetUsuarioMatricula(@QueryParam("matricula") String matricula) {
		
		String query = "";
		HashMap<String, String> usuario = new HashMap<String, String>();
		
		try {
			
			SQL sql = null;
						 
			try {
				
				sql = new SQL("DatabaseFluig");
	
				query = "SELECT fdn_usertenant.USER_CODE AS matricula, fdn_usertenant.LOGIN AS login, fdn_user.FULL_NAME AS nome FROM fdn_user \n" + 
						"JOIN fdn_usertenant ON fdn_user.USER_ID = fdn_usertenant.USER_ID \n" + 
						"WHERE UPPER(fdn_usertenant.USER_CODE) = UPPER('" + matricula + "')";
				
				try (ResultSet resultSet = sql.Select(query)) {
					
					ResultSetMetaData metadata = resultSet.getMetaData();
				    int columns = metadata.getColumnCount();
					
					if (resultSet.next()) {
						
						for(int i = 1; i <= columns; ++i) {          
					    	 
							usuario.put(metadata.getColumnLabel(i), resultSet.getString(i));
					      	
					     }
												
					}					
					
				}

				return Retorno.Sucesso(usuario);
				
			}
			finally {
				
				if (sql != null) {
					
					try { sql.Close(); } catch(Exception ex) { ; }
					sql = null;
					
				}
				
			}
			
		}
		catch (Exception ex) {
			
			System.err.println("####ERRO WEB####");
			System.err.println("####ERRO BUSCAR USUÃ�RIO POR MATRÃ�CULA: " + ex.getMessage());
			System.err.println("####QUERY: " + query);
			ex.printStackTrace();
			return Retorno.Erro(ex);
			
		}
		
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/ZoomUsuarios")
	public Response ZoomUsuarios(@QueryParam("search") String search,
							     @QueryParam("pagina") int pagina,
								 @QueryParam("qtdPagina") int qtdPagina) {
		
		SQL sql = null;
		String query = "";
		
		try {
			
			Parametros parametros = new Parametros();
			query = "SELECT fdn_usertenant.USER_CODE AS matricula, fdn_usertenant.LOGIN AS login, fdn_user.FULL_NAME AS nome\r\n" +
					"FROM " + parametros.GetParametro("nomeDatabaseFluig") + ".fdn_user\r\n" + 
					"JOIN " + parametros.GetParametro("nomeDatabaseFluig") + ".fdn_usertenant ON fdn_usertenant.USER_ID = fdn_user.USER_ID\r\n" + 
					"WHERE fdn_usertenant.USER_STATE = 1 AND fdn_usertenant.USER_ID > 4\r\n" +
					(search != null && !search.isEmpty() ? 
							"AND UPPER(fdn_usertenant.USER_CODE) LIKE '%" + search + "%' OR UPPER(fdn_usertenant.LOGIN) LIKE '%" + search + "%' OR\r\n" +
							"UPPER(fdn_user.FULL_NAME) LIKE '%" + search + "%'\r\n" : "") +
					"ORDER BY fdn_user.FULL_NAME\r\n" +
					"LIMIT " +
					(pagina == 1 ? qtdPagina : 
					   			   (((pagina * qtdPagina) - qtdPagina)) + "," + qtdPagina);
														
			sql = new SQL("DatabaseFluig");
			List<Object> lista;
			
			try (ResultSet resultSet = sql.Select(query)) {					
				lista = new Util().ResultSetToArrayList(resultSet);
			} 
							
			return Retorno.Sucesso(lista);
			
		} catch (Exception ex) {
			
			System.err.println("####ERRO WEB ZOOM USUARIO####");
			System.err.println("####QUERY: " + query);
			System.err.println("####ERRO: " + ex.getMessage());
			ex.printStackTrace();
			return Retorno.Erro(ex);
			
		} finally {
			if (sql != null) {
				try { sql.Close(); } catch (Exception ex) { ; }
				sql = null;
			}			
		}
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/ZoomGrupos")
	public Response ZoomGrupos(@QueryParam("search") String search,
							   @QueryParam("pagina") int pagina,
							   @QueryParam("qtdPagina") int qtdPagina) {
		
		SQL sql = null;
		String query = "";
		
		try {
			
			Parametros parametros = new Parametros();
			query = "SELECT fdn_group.group_code AS codigo, fdn_group.description AS descricao\r\n" + 
					"FROM " + parametros.GetParametro("nomeDatabaseFluig") + ".fdn_group\r\n" +
					(search != null && !search.isEmpty() ? 
							"WHERE (UPPER(fdn_group.group_code) LIKE '%" + search.toUpperCase() + "%' OR UPPER(fdn_group.description) LIKE '%" + search.toUpperCase() + "%')\r\n" : "") +
					"ORDER BY descricao\r\n" +
					"LIMIT " +
					(pagina == 1 ? qtdPagina : 
					   			   (((pagina * qtdPagina) - qtdPagina)) + "," + qtdPagina);
														
			sql = new SQL("DatabaseFluig");
			List<Object> lista;
			
			try (ResultSet resultSet = sql.Select(query)) {					
				
				lista = new Util().ResultSetToArrayList(resultSet);
				
			} 
							
			return Retorno.Sucesso(lista);
			
		}
		catch (Exception ex) {
			
			System.err.println("####ERRO WEB ZOOM GRUPOS####");
			System.err.println("####QUERY: " + query);
			System.err.println("####ERRO: " + ex.getMessage());
			ex.printStackTrace();
			return Retorno.Erro(ex);
			
		}
		finally {
			
			if (sql != null) {
				
				try { sql.Close(); } catch (Exception ex) { ; }
				sql = null;
				
			}			
			
		}
		
	}

	/*@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/GetTotalAtendimentos")
	public Response totalAtendimentos() throws Exception {
		
		SQL sql = new SQL("DatabaseCustom");
		
		try{

			String query = "SELECT " +
								"nota_colaborador, " +
							    "total_atendimentos, " +
							    "total_divergencias " +
						   "FROM "+
						   		"cep_registro_atendimento;"; 
			
			
			ResultSet resultSet = sql.Select(query);
				
			try {
			
				Util util = new Util();
				
				return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				
			}
			finally {
				
				resultSet.close();
				
			}	
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
		finally {
			
			sql.Close();	
			
		}
	 }*/
	@Deprecated
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetRelatorioAnalitico")
	public Response RelatorioAnalitico(@QueryParam("matricula")String matricula,@QueryParam("usuario")String usuario, @QueryParam("setor")String setor, 
									   @QueryParam("codErro")String codErro, @QueryParam("equipe")String equipe, @QueryParam("dataInicio")String dataInicio, 
									   @QueryParam("dataFim")String dataFim) throws Exception {
		
		
		
		SQL sql = new SQL("DatabaseCustom");
		System.out.println("getRelatorioAnalitico");
		
		try {
			System.out.println("inside try");
			
			String query ="SELECT " +
								"registroerro.data_erro as dataErro, " +
							    "setor.descricao as setor, " +
								"registroerro.descricao_erro as erro, " +
							    "registroerro.user_code as usuario, " +
							    "registroerro.matricula_colaborador as codusuario, " +
							    "equipe.descricao as equipe, " +
							    "fluigdatabase.unidade as unidade, " +
							    "fluigdatabase.nome as nome, " +
							    "registroerro.codigo_erro as codErro, " +
							    "registroerro.numero_paciente paciente, " +
							    "erro.descricao descricaoErro," +
							    "CASE " +
									"WHEN registroerro.houve_erro = 'Sim' THEN COUNT(registroerro.codigo_erro) " +
						        "ELSE 0 " +
						        "END houveErro " +
							 "FROM " +
								"cep_registros_erros as registroerro " +
							    "LEFT JOIN cep_setor_equipe as strequipe ON strequipe.setor = registroerro.codigo_setor " +
							    "LEFT JOIN cep_equipe as equipe ON strequipe.equipe = equipe.id " +
							    "LEFT JOIN cep_setores as setor ON strequipe.setor = setor.id " +
							    "LEFT JOIN cep_erro as erro ON registroerro.codigo_erro = erro.id " + 
							    "LEFT JOIN " +
							    	"(SELECT DISTINCT fdnuser.full_name AS nome, "
							    		+ " usut.user_code AS matricula, "
							    		+ " grpdt.data_value AS setor, "
							    		+ "grpdt.data_value AS unidade " +
							    	 "FROM " + sql.parametros.GetParametro("nomeDatabaseFluig") + ".fdn_userdata AS usud "
								      + "INNER JOIN " + sql.parametros.GetParametro("nomeDatabaseFluig") + ".fdn_usertenant AS usut on usut.user_tenant_id = usud.USER_TENANT_ID " +
								      	"INNER JOIN " + sql.parametros.GetParametro("nomeDatabaseFluig") + ".fdn_user AS fdnuser on fdnuser.USER_ID = usut.USER_ID " +
									    "INNER JOIN " + sql.parametros.GetParametro("nomeDatabaseFluig") + ".fdn_group AS fdngrp on fdngrp.GROUP_CODE = TRIM(usud.DATA_value) " +
									    "LEFT JOIN " + sql.parametros.GetParametro("nomeDatabaseFluig") + ".fdn_groupdata AS grpdt on grpdt.group_id = fdngrp.GROUP_ID "
								  + "WHERE "
								  	+ "grpdt.data_key = 'unidade') as fluigdatabase " +
								"ON registroerro.matricula_colaborador = fluigdatabase.matricula " +
							"WHERE 1=1 " +
								//"and status = 2 " + 
							    (matricula.equals("") || matricula.equals("'" + "'") || (matricula == null) || matricula.equals("undefined") ? " " : " and registroerro.matricula_colaborador = '" + matricula + "' " )+" "+
							    (usuario.equals("") || usuario.equals("'" + "'") || (usuario == null) || usuario.equals("undefined") ?" " : " and registroerro.user_code = '"  + usuario  + "' " )+" "+
							    (setor.equals("")  ||  setor.equals("'" + "'") || (setor == null) || setor.equals("undefined") ? " "  : " and registroerro.codigo_setor = '" + setor + "' " )+" "+
							    (codErro.equals("") || codErro.equals("'" + "'") || (codErro == null) || codErro.equals("undefined") ?  " "  : "and registroerro.codigo_erro = '" + codErro + "' " )+" "+
							    (equipe.equals("")  || equipe.equals("'" + "'") || (equipe == null) || equipe.equals("undefined") ? " " : " and strequipe.equipe = '" + equipe + "'  " )+" "+
							    (dataInicio.equals("") || dataInicio.equals("'" + "'") || (dataInicio == null) || dataInicio.equals("undefined") ? " " : "and (registroerro.data_exame = '" + dataInicio+ "' or registroerro.data_exame > '" + dataInicio + "') " )+" "+
								(dataFim.equals("")  || dataFim.equals("'" + "'") || (dataFim == null) || dataFim.equals("undefined") ? " " : "and (registroerro.data_exame = '" + dataFim + "' or registroerro.data_exame < '" + dataFim + "') " )+" " +
							 "GROUP BY " +
								"registroerro.data_erro, " + 
								"setor.descricao, " +
								"registroerro.descricao_erro, "+
								"registroerro.user_code, "+
								"registroerro.matricula_colaborador, "+ 
								"equipe.descricao, " +
								"fluigdatabase.unidade, " + 
								"fluigdatabase.nome, " +
						        "registroerro.houve_erro, " + 
						        "registroerro.codigo_erro," +
						        "erro.descricao, " + 
						        "numero_paciente;";
	
			
			System.out.println("QUERYRELANALITICO " + query);
   			System.out.println("MATRICULA " + matricula + " USUARIO " + usuario + " SETOR " + setor + " CODERRO " + codErro + " EQUIPE " + equipe + " DATAINICIO " + dataInicio + " DATAFIM " + dataFim);	
			
			
			
			ResultSet resultSet = sql.Select(query);
			
			
			
			try {
			
				Util util = new Util();
				
				return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				
			}
			finally {
				
				resultSet.close();
				
			}	
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
		finally {
			
			sql.Close();	
			
		}
	
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetCodProcesso")
	public Response GetCodProcesso(@QueryParam("codColaborador") String codColaborador, @QueryParam("cdPaciente") String cdPaciente) throws Exception {
		
		SQL sql = new SQL("DatabaseFluig");
	
		try {
	String query =	"SELECT "
						+ "documentid, "
						+ "codtipoErroPeso, "
						+ "desctipoErroPeso, "
						+ "cdPaciente, "
						+ "nomePaciente, "
						+ "descricaoErro, "
						+ "codColaborador, "
						+ "nomeColaborador, "
						+ "dataErro, "
						+ "setorErro, "
						+ "setorColaborador, "
						+ "unidadeColaborador, "
						+ "matriculaColaborador "
					+ "FROM " +
						sql.parametros.GetParametro("mlValidacaoCEP") + 
					 "where "
						+ "codColaborador = " + codColaborador + " " 
						+ "and cdPaciente = " +  cdPaciente + "; ";
		
											
			ResultSet resultSet = sql.Select(query);
			System.out.println(" QUERY CODPROCESSO  " + query);
			
			try {
				
				Util util = new Util();
				
				return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				
			}
			finally {
				
				resultSet.close();
				
			}	
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
		finally {
			
			sql.Close();	
			
		}
	
	}
 
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("GetSetorEquipe")
	public Response getSetorEquipe(@QueryParam("matricula")String matricula,
			@QueryParam("setor")String setor,
			@QueryParam("codErro")String codErro,
			@QueryParam("equipe")String equipe,
			@QueryParam("dataInicio")String dataInicio, 
			@QueryParam("dataFim")String dataFim) throws Exception{
		System.out.println("matricula: "+matricula+" setor: "
			+setor+" codErro: "+codErro+" equipe: "+equipe+" dataInicio: "+dataInicio+" dataFim: "+dataFim);
		
		SQL sql = null;
		try {
			sql =  new SQL("DatabaseCustom");
			try {
				String query =  "SELECT setores.descricao AS setor," 
								+"equipes.descricao AS equipe,"
								+"COUNT(col.equipe) AS errosDaEquipe," 
								+"cc.totalSetor AS errosDoSetor,"
								+"(SELECT COUNT(*) FROM cep_registros_erros  WHERE 1=1 AND  cep_registros_erros.houve_erro='sim' ";
								if(!dataInicio.equals("") && !dataFim.equals("")){
									query=query+" AND cep_registros_erros.data_erro BETWEEN '"+dataInicio+"' AND '"+dataFim+"' ";
								}
								query =  query+ ") AS errosTotais,"
								+"CONCAT(TRUNCATE((COUNT(col.equipe)*100)/cc.totalSetor,3 )  ,' %') AS percentualRelSetor,"
								+"CONCAT(TRUNCATE((COUNT(col.equipe)*100)/(SELECT COUNT(*) FROM cep_registros_erros WHERE 1=1 AND  cep_registros_erros.houve_erro='sim' ";
								if(!dataInicio.equals("") && !dataFim.equals("")){
									query=query+" AND cep_registros_erros.data_erro BETWEEN '"+dataInicio+"' AND '"+dataFim+"' ";
								}
								query =  query+ "),3),' %') AS percentualReltotal,"
								+"CONCAT(TRUNCATE((cc.totalSetor*100)/(SELECT COUNT(*) FROM cep_registros_erros WHERE 1=1 AND  cep_registros_erros.houve_erro='sim' ";
								if(!dataInicio.equals("") && !dataFim.equals("")){
									query=query+" AND cep_registros_erros.data_erro BETWEEN '"+dataInicio+"' AND '"+dataFim+"' ";
								}	 
								query =  query+  "),3),' %') AS percentualRelSetorTotal,"
								+"col.equipe AS codEquipe, "
								+"cc.setor AS codSetor  "
								+"FROM cep_colaborador_setor_equipe AS col "
								+"INNER JOIN cep_setores AS setores ON "
								+"setores.id = col.setor "
								+"INNER JOIN cep_equipe AS equipes ON "
								+"equipes.id=col.equipe "
								+"JOIN cep_registros_erros cep ON "
								+"cep.matricula_colaborador= col.user_code " 
								+"LEFT JOIN(SELECT col.setor AS setor, COUNT(col.setor) AS totalSetor  from cep_colaborador_setor_equipe AS col "
								+"LEFT JOIN cep_registros_erros cep ON "
								+"cep.matricula_colaborador= col.user_code WHERE 1=1 AND  cep.houve_erro='sim' ";
								if(!dataInicio.equals("") && !dataFim.equals("")){
									query=query+" AND cep.data_erro BETWEEN '"+dataInicio+"' AND '"+dataFim+"' ";
								}								
								query = query+"GROUP BY col.setor) AS cc "
								+"ON cc.setor = col.setor "
								+"WHERE 1=1 "
								+"AND cep.houve_erro='sim' " ;
								if(!dataInicio.equals("") && !dataFim.equals("")){
									query=query+"AND cep.data_erro BETWEEN '"+dataInicio+"' AND '"+dataFim+"' ";
								}
								if(!matricula.equals("")){
									query = query+"AND cep.matricula_colaborador = '"+matricula+"' ";
								}
								if(!setor.equals("")){
									query = query+"AND cep.codigo_setor="+setor+" ";
								}
								if(!equipe.equals("")){
									query = query+"AND col.equipe="+equipe+" ";
								}
								if(!codErro.equals("")){
									query = query+"AND cep.codigo_erro="+codErro+" ";
								}
								query=query+"GROUP BY col.equipe, cc.setor,setor ;";
								
								
				ResultSet resultSet = sql.Select(query);
				System.out.println(" QUERY getSetorEquipe  " + query);
				try {
					Util util = new Util(); 
					return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				} catch (Exception e) {
					System.out.println("getSetorEquipe ResultSet ERRO: "+e); 
					return Retorno.Erro(e);
				}finally{
					resultSet.close();
				}
			} catch (Exception e) {
				System.out.println("getSetorEquipe SQL ERRO: "+e);
				return Retorno.Erro(e);
			}finally{
				sql.Close();
				
			}
		} catch (Exception e) {
			 System.out.println("ERRO ao instanciar a classe SQL"+e);
			 return Retorno.Erro(e);
		} finally{
			if(sql.getConexao().isClosed()==false){
				sql.Close();
			}
		}
		 
	 	
	}
	@Deprecated
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetErrosRegistros")
	public Response GetErrosRegistros(@QueryParam("matricula")String matricula,@QueryParam("usuario")String usuario, @QueryParam("setor")String setor, 
									   @QueryParam("codErro")String codErro, @QueryParam("equipe")String equipe, @QueryParam("dataInicio")String dataInicio, 
									   @QueryParam("dataFim")String dataFim) throws Exception {
		
		SQL sql = new SQL("DatabaseCustom");
	
		try {
		String query =	"SELECT " + 
							"concat(registroerro.codigo_setor, ' - ', setor.descricao) descSet, " +
						    "equipe.descricao equipe, " +
						    "COUNT(equipe.id) errosEquipe, " +
						    "COUNT(setor.id) errosSetor, " + 
							"COUNT(registroerro.codigo_erro) totalErros, "+ //Total de Divergências
							"CONCAT(registroerro.codigo_erro, ' - ', erro.descricao) tipoErro, " +
							"CONCAT(TRUNCATE((100 * COUNT(equipe.id)) / COUNT(setor.id), 0), '%') calculoPercentualEquipeSetor, " +
							"CONCAT(TRUNCATE((100 * COUNT(equipe.id)) / COUNT(codigo_erro), 0), '%') calculoPercentualEquipeTotal, " +
							"CONCAT(TRUNCATE((100 * COUNT(setor.id)) / COUNT(codigo_erro), 0), '%') calculoPercentualSetorTotal " +
						"FROM " + 
							"dapi_homologacao.cep_registros_erros AS registroerro " + 
						    "LEFT JOIN cep_setor_equipe AS strequipe ON strequipe.setor = registroerro.codigo_setor " + 
						    "LEFT JOIN cep_equipe AS equipe ON strequipe.equipe = equipe.id " +
						    "LEFT JOIN cep_setores AS setor ON strequipe.setor = setor.id " +
						    "LEFT JOIN cep_erro AS erro ON registroerro.codigo_erro = erro.id " +
						    "WHERE 1=1 " +
							"and status = 2 " + 
						    (matricula.equals("") || matricula.equals("'" + "'") || (matricula == null) || matricula.equals("undefined") ? " " : " and registroerro.matricula_colaborador = '" + matricula + "' " )+" "+
						    (usuario.equals("") || usuario.equals("'" + "'") || (usuario == null) || usuario.equals("undefined") ?" " : " and registroerro.user_code = '"  + usuario  + "' " )+" "+
						    (setor.equals("")  ||  setor.equals("'" + "'") || (setor == null) || setor.equals("undefined") ? " "  : " and registroerro.codigo_setor = '" + setor + "' " )+" "+
						    (codErro.equals("") || codErro.equals("'" + "'") || (codErro == null) || codErro.equals("undefined") ?  " "  : "and registroerro.codigo_erro = '" + codErro + "' " )+" "+
						    (equipe.equals("")  || equipe.equals("'" + "'") || (equipe == null) || equipe.equals("undefined") ? " " : " and strequipe.equipe = '" + equipe + "'  " )+" "+
						    (dataInicio.equals("") || dataInicio.equals("'" + "'") || (dataInicio == null) || dataInicio.equals("undefined") ? " " : "and (registroerro.data_exame = '" + dataInicio+ "' or registroerro.data_exame > '" + dataInicio + "') " )+" "+
							(dataFim.equals("")  || dataFim.equals("'" + "'") || (dataFim == null) || dataFim.equals("undefined") ? " " : "and (registroerro.data_exame = '" + dataFim + "' or registroerro.data_exame < '" + dataFim + "') " )+" " +
						"GROUP BY " +
							"equipe.descricao, " +
						    "codigo_erro, " +
						    "codigo_setor, " +
						    "erro.descricao, " +
						    "registroerro.houve_erro "+
						 "HAVING " +
						 	"registroerro.houve_erro = 'sim';";
		
											
			ResultSet resultSet = sql.Select(query);
			System.out.println(" QUERY errosregistros  " + query);
			
			try {
				
				Util util = new Util();
				
				return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				
			}
			finally {
				
				resultSet.close();
				
			}	
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
		finally {
			
			sql.Close();	
			
		}
	
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/IsOrienting")
	public Response IsOrienting(@QueryParam("matricula") String matricula) throws Exception {
		
		SQL sql = new SQL("DatabaseFluig");
		
		try {
						
			String query = "SELECT userdata.DATA_VALUE AS EstaOrientando FROM fdn_userdata AS userdata " +
							"INNER JOIN fdn_usertenant AS usertenant " +
								"ON userdata.USER_TENANT_ID = usertenant.USER_TENANT_ID " +
							"AND usertenant.USER_CODE = '" + matricula + "' " +
							"WHERE DATA_KEY = 'EstaOrientando' ";
			
			ResultSet resultSet = sql.Select(query);
			
			try {
			
				Util util = new Util();
				
				return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				
			}
			finally {
				
				resultSet.close();
				
			}	
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
		finally {
			
			sql.Close();	
			
		}
	
	}
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetUnidades")
	public Response GetUnidades() throws Exception {
		
		SQL sql = new SQL("DatabaseFluig");
		
		try {
						
			String query = " select * from fdn_groupdata where fdn_groupdata.data_key = 'unidade' ";
			
			ResultSet resultSet = sql.Select(query);
			
			try {
			
				Util util = new Util();
				
				return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				
			}
			finally {
				
				resultSet.close();
				
			}	
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
		finally {
			
			sql.Close();	
			
		}
	
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetTotalAtendimentos")
	public Response totalAtendimentos(@QueryParam("matricula") String matricula) throws Exception {
		
			TotalAtendimentos totalAtendimentos = new TotalAtendimentos();
			totalAtendimentos.setNomeColaborador("Marley");
			totalAtendimentos.setTotalAtendimentos(25);
			totalAtendimentos.setDataAtendimento("2019-05-20");
			totalAtendimentos.setMatriculaColaborador("1854984894");
			TotalAtendimentos totalAtendimentos2 = new TotalAtendimentos();
			totalAtendimentos2.setNomeColaborador("Marcos");
			totalAtendimentos2.setTotalAtendimentos(2);
			totalAtendimentos2.setDataAtendimento("2019-05-20");
			totalAtendimentos2.setMatriculaColaborador("15");
			
			
			try {
				
				return Retorno.Sucesso(totalAtendimentos);	
					
			} catch(Exception ex){
				ex.printStackTrace();
				return Retorno.Erro(ex);
			}
	}
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/getGraficoQualitativo")
	public Response getGraficoQualitativo(
			@QueryParam("matricula")String matricula,
			@QueryParam("usuario")String usuario, 
			@QueryParam("setor")String setor, 
			@QueryParam("codErro")String codErro, 
			@QueryParam("equipe")String equipe, 
			@QueryParam("dataInicio")String dataInicio, 
			@QueryParam("dataFim")String dataFim
			) throws Exception{
		RegistroModel registroModel =  new RegistroModel();
		return registroModel.getGraficoQualitativo(matricula,usuario,setor,codErro,equipe,dataInicio,dataFim);
		
	}
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetEvolucaoDoUsuario")
	public Response GetEvolucaoDoUsuario(
			@QueryParam("matricula")String matricula,
			@QueryParam("usuario")String usuario, 
			@QueryParam("setor")String setor, 
			@QueryParam("codErro")String codErro, 
			@QueryParam("equipe")String equipe, 
			@QueryParam("dataInicio")String dataInicio, 
			@QueryParam("dataFim")String dataFim
			) throws Exception{ 
		RegistroModel registroModel =  new RegistroModel();
		return registroModel.GetEvolucaoDoUsuario(matricula,usuario,setor,codErro,equipe,dataInicio,dataFim);
				 
		
	}
	//Grafico de Evolução do Colaborador_INI
	@Deprecated
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetRegistrosEvolucao")
	public Response GetRegistrosEvolucao(
			@QueryParam("matricula")String matricula,
			@QueryParam("usuario")String usuario, 
			@QueryParam("setor")String setor, 
			@QueryParam("codErro")String codErro, 
			@QueryParam("equipe")String equipe, 
			@QueryParam("dataInicio")String dataInicio, 
			@QueryParam("dataFim")String dataFim
			) throws Exception {
		
		SQL sql = new SQL("DatabaseCustom");
	
		try {
		String query =	"SELECT * " + 
						"FROM " + 
							"dapi_homologacao.cep_registros_erros AS registroerro " + 
						    "LEFT JOIN cep_setor_equipe AS strequipe ON strequipe.setor = registroerro.codigo_setor " + 
						    "LEFT JOIN cep_equipe AS equipe ON strequipe.equipe = equipe.id " +
						    "LEFT JOIN cep_setores AS setor ON strequipe.setor = setor.id " +
						    "LEFT JOIN cep_erro AS erro ON registroerro.codigo_erro = erro.id " +
						    "WHERE 1=1 " +
							"and status = 2 " + 
						    (matricula.equals("") || matricula.equals("'" + "'") || (matricula == null) || matricula.equals("undefined") ? " " : " and registroerro.matricula_colaborador = '" + matricula + "' " )+" "+
						    (usuario.equals("") || usuario.equals("'" + "'") || (usuario == null) || usuario.equals("undefined") ?" " : " and registroerro.user_code = '"  + usuario  + "' " )+" "+
						    (setor.equals("")  ||  setor.equals("'" + "'") || (setor == null) || setor.equals("undefined") ? " "  : " and registroerro.codigo_setor = '" + setor + "' " )+" "+
						    (codErro.equals("") || codErro.equals("'" + "'") || (codErro == null) || codErro.equals("undefined") ?  " "  : "and registroerro.codigo_erro = '" + codErro + "' " )+" "+
						    (equipe.equals("")  || equipe.equals("'" + "'") || (equipe == null) || equipe.equals("undefined") ? " " : " and strequipe.equipe = '" + equipe + "'  " )+" "+
						    (dataInicio.equals("") || dataInicio.equals("'" + "'") || (dataInicio == null) || dataInicio.equals("undefined") ? " " : "and (registroerro.data_exame = '" + dataInicio+ "' or registroerro.data_exame > '" + dataInicio + "') " )+" "+
							(dataFim.equals("")  || dataFim.equals("'" + "'") || (dataFim == null) || dataFim.equals("undefined") ? " " : "and (registroerro.data_exame = '" + dataFim + "' or registroerro.data_exame < '" + dataFim + "') " )+" ";
		
			System.out.println(" QUERY GetRegistrosEvolucao  " + query);							
			ResultSet resultSet = sql.Select(query);
			
			
			try {
				
				Util util = new Util();
				
				return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				
			}
			finally {
				
				resultSet.close();
				
			}	
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
		finally {
			
			sql.Close();	
			
		}
	
	}
	//Grafico de Evolução do Colaborador_FIM
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/CriaPasta")
	public Response CriaPasta( @QueryParam("parentDocumentId") int parentDocumentId, @QueryParam("publisherId") String publisherId, @QueryParam("documentDescription") String documentDescription) {
		System.out.println("CriaPasta_web_1");
		SoapFolderService folder = new SoapFolderService();
		System.out.println("CriaPasta_web_2");
		return Retorno.Sucesso(folder.CriaPasta(parentDocumentId, publisherId, documentDescription));
	}

	
	
	

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("GetRegistros")
	public Response getRegistros(@QueryParam("matricula")String matricula,
			@QueryParam("usuario")String usuario,
			@QueryParam("setor")String setor,
			@QueryParam("codErro")String codErro,
			@QueryParam("equipe")String equipe,
			@QueryParam("dataInicio")String dataInicio, 
			@QueryParam("dataFim")String dataFim) throws Exception{
		System.out.println("GetRegistros");
		System.out.println("matricula: "+matricula+" usuario: "+usuario+" setor: "
				+setor+" codErro: "+codErro+" equipe: "+equipe+" dataInicio: "+dataInicio+" dataFim: "+dataFim);
	
		RegistroModel registroModel = new RegistroModel();
		return registroModel.getRegistros(matricula, usuario, setor, codErro, equipe, dataInicio, dataFim);

	}
	
	
	
	//TotalAtendimentos
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("GetTotalSoapAtendimentos")
	public Response GetTotalSoapAtendimentos(@QueryParam("matricula") String matricula, @QueryParam("dataIni") String dataIni, @QueryParam("dataFim") String dataFim) throws SOAPException, IOException, Exception{
	
		Parametros parametros = new Parametros();
		TotalAtendimentosSoap atendimentos = new TotalAtendimentosSoap();
		String retorno = atendimentos.CriaRequestSoap(parametros.GetParametro("loginFluig"), parametros.GetParametro("senhaFluig"), matricula, dataIni, dataFim);
		System.out.println("QQQQ----GGGG "+retorno);
		return Retorno.Sucesso(retorno);
	}
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetErrosColaborador")
	public Response GetErrosColaborador(@QueryParam("matricula") String matricula) throws Exception {
		SQL sql = new SQL("DatabaseCustom");
		System.out.println("getRelatorioAnalitico");
		
		try {

			String query = "SELECT  data_erro," 
					 +" cep_setores.descricao AS setor,  "
					 +" cep_erro.descricao AS tipo, "
					 +" cep_colaborador_setor_equipe.unidade as unidade, "
					 +" cep_registros_erros.descricao_erro AS descricao, "
					 +" id_fluig AS num_process  "
					 +" FROM cep_registros_erros  "
					 +" INNER JOIN cep_erro ON codigo_erro = cep_erro.id "
					 +" INNER JOIN cep_setores ON codigo_setor = cep_setores.id "
					 +" INNER JOIN cep_colaborador_setor_equipe on "
					 +" cep_colaborador_setor_equipe.setor=codigo_setor "
					 +" AND cep_registros_erros.matricula_colaborador = cep_colaborador_setor_equipe.user_code "
						+ " WHERE 1 = 1  AND cep_registros_erros.houve_erro='Sim' "
						
					 +" AND  (SELECT DISTINCT ceperro.setor FROM cep_setor_erro AS ceperro  where "
					 +" ceperro.setor=cep_registros_erros.codigo_setor AND ceperro.erro=cep_registros_erros.codigo_erro "
					 +" AND ceperro.ativo=1)=cep_registros_erros.codigo_setor "
 
						+ " AND cep_registros_erros.id_fluig>0  AND cep_registros_erros.matricula_colaborador = '" + matricula + "'";
			System.out.println("SSSSSSSSSSSSS_)))) " + query);
			ResultSet resultSet = sql.Select(query);
			
			
			
			try {
			
				Util util = new Util();
				
				return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				
			}
			finally {
				
				resultSet.close();
				
			}	
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
		finally {
			
			sql.Close();	
			
		}
	
	}
	
	//INSERT INTO cep_solicitacoes(num_process,num_matricula) VALUES('1010','1111');
	
//
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/InsertNovaSolicitacao")
	public Response InsertNovaSolicitacao(@QueryParam("matricula") String matricula, @QueryParam("numProcess") String numProcess) throws Exception {
		SQL sql = new SQL("DatabaseCustom");		
		try {

			String query ="INSERT INTO cep_solicitacoes(num_matricula,num_process) VALUES('" + matricula + "','" + numProcess + "')";
			System.out.println("SSSSSSSSSSSSS_))))_aa " + query);
			
			
			int resultSet = sql.InsertUpdate(query);
			sql.Commit();	
		    return Retorno.Sucesso(resultSet);	
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
		finally {
			
			sql.Close();	
			
		}
	
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetDadosUriAnexo")
	public Response GetDadosUriAnexo(@QueryParam("numProcess") int numProcess) {
		
		SQL sql = null;
		String query = "";
		
		try {
			
			sql = new SQL("DatabaseFluig");
			Parametros parametros = new Parametros();
			query = "SELECT "
					+ "ANEXO_PROCES.NUM_PROCES,  "
					+ "DOCUMENTO.NR_DOCUMENTO,  "
					+ "DOCUMENTO.DS_PRINCIPAL_DOCUMENTO , "
					+ "DOCUMENTO.COD_EMPRESA,  "
					+ "DOCUMENTO.NR_VERSAO  "
					+ "FROM DOCUMENTO "
					+ "INNER JOIN ANEXO_PROCES ON  ANEXO_PROCES.NR_DOCUMENTO = DOCUMENTO.NR_DOCUMENTO "
					+ "WHERE ANEXO_PROCES.NUM_PROCES = '" + numProcess + "' "
					+ "AND DOCUMENTO.VERSAO_ATIVA = 1 "
					+ "AND DOCUMENTO.LOG_USA_VISUALIZ IS NOT NULL";
			
			  
		 System.out.println(">>>QUERY_00_GetDadosUriAnexo " + query);
		 ResultSet resultSet =null;						
			try {
				resultSet= sql.Select(query)	;
				 
				return Retorno.Sucesso(new Util().ResultSetToArrayList(resultSet));
				
			}catch(Exception ex){
				Retorno.Erro(ex);
			}finally{
				resultSet.close();
			}
			
			
		}
		catch (Exception ex) {
			
			System.err.println("####ERRO WEB####");
			System.err.println("####ERRO ANEXOS: " + ex.getMessage());
			System.err.println("####QUERY: " + query);
			ex.printStackTrace();
			return Retorno.Erro(ex);
			
		}
		finally {
			
			if (sql != null) {
				
				try { sql.Close(); } catch(Exception ex) { ; }
				sql = null;
				
			}
			
		}
		return null; 
		
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetSolicitacoesPorColab")
	public Response GetSolicitacoesPorColab(@QueryParam("matricula") String matricula) throws Exception {
		SQL sql = new SQL("DatabaseCustom");		
		try {

			String query ="SELECT * FROM cep_solicitacoes WHERE num_matricula = '" + matricula + "'";
			System.out.println("wwww))))_aa " + query);
			
			
			ResultSet resultSet = sql.Select(query);
			sql.Commit();	
				 return Retorno.Sucesso(new Util().ResultSetToArrayList(resultSet));
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}	
		finally {
			
			sql.Close();	
			
		}
	
	}
	//--
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("/GetSetorDescricao")
	public Response GetSetorDescricao(@QueryParam("setor") String setor) throws Exception {
		
		RegistroModel modelo = new RegistroModel();
		
		return Retorno.Sucesso(modelo.GetSetorDescricao(setor));
		
	}
	//--
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("/GetEquipeDescricao")
	public Response GetEquipeDescricao(@QueryParam("equipe") String equipe) throws Exception {
		
		RegistroModel modelo = new RegistroModel();
		
		return Retorno.Sucesso(modelo.GetEquipeDescricao(equipe));
		
	}
				
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/GetTotalAtendimentoGroup")
	public Response GetTotalAtendimentoGroup() throws Exception {
		
		Parametros parametros = new Parametros();
		
		Consultas consultas = new Consultas();
		ConsultasSoap consultasSoap = consultas.getConsultasSoap();
		String json = consultasSoap.consultaTotalAtendimentosByGroup(parametros.GetParametro("loginFluig"), parametros.GetParametro("senhaFluig"), "01-JAN-20", "05-JAN-20");
				
		return Retorno.Sucesso(json);
		
	}
	
}
