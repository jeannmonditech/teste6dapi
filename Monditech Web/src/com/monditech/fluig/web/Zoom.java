package com.monditech.fluig.web;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
import com.monditech.webservices.Consultas;
import com.monditech.webservices.ConsultasSoap;

@LocalBean
@Path("/Zoom")
public class Zoom {
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetTeste")
	public Response GetTeste() {
		
		return Retorno.Sucesso("ZOOM OK!");
	
	}	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/ZoomColaboradoresSetoresEquipes")
	public Response ZoomColaboradoresSetoresEquipes(@QueryParam("paginacao") String paginacao, 
			@QueryParam("search") String search ) throws Exception{
		System.out.println("ZoomColaboradoresSetoresEquipes");
		SQL sql =null;
		
		try{
			sql =  new SQL("DatabaseCustom");
			String[] aux = paginacao.split(";");
			Integer pagina = Integer.parseInt(aux[0]);
			Integer quantidadePagina = Integer.parseInt(aux[1]);
			
			try {
				
				String query=" SELECT colaborador AS nome, user_code AS matricula,"
				 +" cs.descricao AS setorNome, unidade, ce.descricao AS equipeNome,"
				 +" cs.id AS setorId, equipe AS equipeId  FROM cep_colaborador_setor_equipe"
				 +" INNER JOIN cep_setores AS cs on"
				 +"  cs.id=setor"
				 +" INNER JOIN cep_equipe AS ce on"
				 +" ce.id=equipe  where 1=1 ";
				 if(!search.equals("")){
					 query = query+" and colaborador LIKE '%"+search+"%' " 
					+ "OR user_code LIKE '%" +search+"%' OR cs.descricao LIKE '%"+search+"%'";
				 }
				 
				 System.out.println("PPPPPPGGGGTTT   "+query);
				 
				 query = query +" limit "+ (pagina == 1 ? quantidadePagina : 
					(((pagina * quantidadePagina) - quantidadePagina)) + "," + quantidadePagina);
				
				System.out.println("QUERY de ZoomColaboradoresSetoresEquipes");
				System.out.println(query);
				ResultSet resultSet = sql.Select(query);
				try {
					Util util = new Util();
					List<Object> lista = util.ResultSetToArrayList(resultSet);
					
					return Retorno.Sucesso(lista);
				} catch (Exception e) {
					System.out.println("Zoom.ZoomColaboradoresSetoresEquipes Erro ResultSet sql: "+e);
				}finally{
					resultSet.close();
				}
			} catch (Exception e) { 
				System.out.println("Zoom.ZoomColaboradoresSetoresEquipes Erro sql: "+e);
			}finally{
				sql.Close();
			}
			
		}catch(Exception e){
			System.out.println("Zoom.ZoomColaboradoresSetoresEquipes Erro Inst�ncia sql: "+e);
		}finally{
			 
			if(sql.getConexao().isClosed()==false){
				sql.Close();
			}
		}
		 
		return null;
		
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/ZoomColaboradores")
	public Response ZoomColaboradores(@QueryParam("paginacao") String paginacao, @QueryParam("search") String search, @QueryParam("grupoUsuario") String grupoUsuario, @QueryParam("relacionamento") boolean relacionamento) {

		try{
			
			SQL sql = new SQL("DatabaseFluig");
			
			String[] aux = paginacao.split(";");
			Integer pagina = Integer.parseInt(aux[0]);
			Integer quantidadePagina = Integer.parseInt(aux[1]);
			
			try{
				/*
				 * 
				 *  SELECT  fdn_user.full_name as nome, fdn_usertenant.user_code as matricula, dapi_homologacao.cep_colaborador_setor_equipe.setor as setor FROM fdn_userdata
 INNER JOIN fdn_usertenant  on fdn_usertenant.user_tenant_id = fdn_userdata.USER_TENANT_ID   
									INNER JOIN fdn_user on fdn_user.USER_ID = fdn_usertenant.USER_ID
                                    LEFT JOIN dapi_homologacao.cep_colaborador_setor_equipe on dapi_homologacao.cep_colaborador_setor_equipe.user_code = fdn_usertenant.user_code 
                                    WHERE fdn_usertenant.user_state = 1  
								AND fdn_userdata.DATA_KEY = 'UserWorkflowGroup' 
				 * 
				 */
				/*String query = 	"SELECT " +  
									"fdn_user.full_name AS nome, " +
									"fdn_usertenant.user_code AS matricula, " +
								    "fdn_groupdata2.data_value AS setor, " +
									"fdn_groupdata.data_value AS unidade " +
								"FROM fdn_userdata " +
									"INNER JOIN fdn_usertenant  on fdn_usertenant.user_tenant_id = fdn_userdata.USER_TENANT_ID  " +
									"INNER JOIN fdn_user on fdn_user.USER_ID = fdn_usertenant.USER_ID " +
									"INNER JOIN fdn_group on fdn_group.GROUP_CODE = TRIM(fdn_userdata.DATA_value) " +
								    "LEFT JOIN fdn_groupdata on fdn_groupdata.group_id = fdn_group.GROUP_ID " +
										"AND (TRIM(fdn_groupdata.DATA_KEY) = 'unidade' OR TRIM(fdn_groupdata.DATA_KEY) = 'Unidade') " +
								    "LEFT JOIN fdn_groupdata fdn_groupdata2 on fdn_groupdata2.group_id = fdn_group.group_id " +
										"AND (TRIM(fdn_groupdata2.DATA_KEY) = 'setor' OR TRIM(fdn_groupdata2.DATA_KEY) = 'Setor') " +
								"WHERE fdn_usertenant.user_state = 1 " +
								"AND fdn_userdata.DATA_KEY = 'UserWorkflowGroup' " +
								(relacionamento == true ? "AND (fdn_usertenant.user_code NOT IN ( select dapi_homologacao.cep_colaborador_setor_equipe.user_code from dapi_homologacao.cep_colaborador_setor_equipe)) " : "") +
								(search == null || search.isEmpty() ? "" : " AND (fdn_groupdata2.data_value LIKE '%" + search + "%' OR fdn_user.full_name LIKE '%" + search + "%') ") +
								(grupoUsuario.equals("RH") ? "" : "AND fdn_groupdata2.data_value = '" + grupoUsuario + "' ") +
								"ORDER BY fdn_usertenant.user_tenant_id LIMIT " + (pagina == 1 ? quantidadePagina : 
									(((pagina * quantidadePagina) - quantidadePagina)) + "," + quantidadePagina);
				*/
				
				String query = " SELECT  fdn_user.full_name as nome, fdn_usertenant.user_code as matricula,  (select id from dapi_homologacao.cep_setores where id = dapi_homologacao.cep_colaborador_setor_equipe.setor   ) as setorCepId, "
						+ " (select id from dapi_homologacao.cep_equipe where id = dapi_homologacao.cep_colaborador_setor_equipe.equipe   ) as equipeCepId,"
						+ "  fdn_groupdata2.data_value AS setorFluig, fdn_groupdata.GROUPDATA_ID as unidadeFluigId, fdn_groupdata2.GROUPDATA_ID AS setorFluigId,   "
						+ "fdn_groupdata.data_value AS unidadeFluig, fdn_groupdata.data_value AS unidadeFluig  FROM fdn_userdata" +
						" INNER JOIN fdn_usertenant  on fdn_usertenant.user_tenant_id = fdn_userdata.USER_TENANT_ID   "+
									" INNER JOIN fdn_user on fdn_user.USER_ID = fdn_usertenant.USER_ID"+
                                    " INNER JOIN fdn_group on fdn_group.GROUP_CODE = TRIM(fdn_userdata.DATA_value)  "+
                                    " LEFT JOIN fdn_groupdata on fdn_groupdata.group_id = fdn_group.GROUP_ID  "+
									"	AND (TRIM(fdn_groupdata.DATA_KEY) = 'unidade' OR TRIM(fdn_groupdata.DATA_KEY) = 'Unidade') " +
								    " LEFT JOIN fdn_groupdata fdn_groupdata2 on fdn_groupdata2.group_id = fdn_group.group_id  "+
									"	AND (TRIM(fdn_groupdata2.DATA_KEY) = 'setor' OR TRIM(fdn_groupdata2.DATA_KEY) = 'Setor')"+ 
                                    " LEFT JOIN dapi_homologacao.cep_colaborador_setor_equipe on dapi_homologacao.cep_colaborador_setor_equipe.user_code = fdn_usertenant.user_code "+
                                    " WHERE fdn_usertenant.user_state = 1  "+
								"AND fdn_userdata.DATA_KEY = 'UserWorkflowGroup' " +
								(relacionamento == true ? "AND (fdn_usertenant.user_code NOT IN ( select dapi_homologacao.cep_colaborador_setor_equipe.user_code from dapi_homologacao.cep_colaborador_setor_equipe)) " : "") +
								(search == null || search.isEmpty() ? "" : " AND (fdn_groupdata2.data_value LIKE '%" + search + "%' OR fdn_user.full_name LIKE '%" + search + "%') ") +
								(grupoUsuario.equals("RH") ? "" : "AND fdn_groupdata2.data_value = '" + grupoUsuario + "' ") +
								"ORDER BY fdn_usertenant.user_tenant_id LIMIT " + (pagina == 1 ? quantidadePagina : 
									(((pagina * quantidadePagina) - quantidadePagina)) + "," + quantidadePagina)    
								;
				
				System.out.println("QUERY de ZoomColaboradores");
				System.out.println(query);
				ResultSet resultSet = sql.Select(query);
				
				try{
					
					Util util = new Util();
					List<Object> lista = util.ResultSetToArrayList(resultSet);
					
					return Retorno.Sucesso(lista);
					
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
    @Path("/ZoomConsultarPacientes")
	public Response ZoomConsultarPacientes(@QueryParam("search") String search, @QueryParam("pagina") String pagina, @QueryParam("qtdPagina") String qntdPorPagina){
				
		try {
			Parametros parametros = new Parametros();
			
			URL url = new URL(parametros.GetParametro("webserviceMonditech"));
			
			Consultas ws = new Consultas(url);
			ConsultasSoap wss = ws.getConsultasSoap();
			
			return Retorno.Sucesso(wss.zoomConsultarPacientes(parametros.GetParametro("loginFluig"), parametros.GetParametro("senhaFluig"), search, pagina, qntdPorPagina));
			
		} catch (Exception ex) {
			return Retorno.Erro(ex);
		}
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/ZoomConsultarExames")
	public Response ZoomConsultarExames(@QueryParam("search") String search, @QueryParam("pagina") String pagina, @QueryParam("qtdPagina") String qntdPorPagina){
				
		try {
			Parametros parametros = new Parametros();
			
			URL url = new URL(parametros.GetParametro("webserviceMonditech"));
			
			Consultas ws = new Consultas(url);
			ConsultasSoap wss = ws.getConsultasSoap();
			
			return Retorno.Sucesso(wss.zoomConsultarExames(parametros.GetParametro("loginFluig"), parametros.GetParametro("senhaFluig"), search, pagina, qntdPorPagina));
			
		} catch (Exception ex) {
			return Retorno.Erro(ex);
		}		
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/ZoomConsultarExamesPaciente")
	public Response ZoomConsultarExamesPaciente(@QueryParam("search") String search, @QueryParam("pagina") String pagina, @QueryParam("qtdPagina") String qntdPorPagina){
		
		try {
			Parametros parametros = new Parametros();
			
			URL url = new URL(parametros.GetParametro("webserviceMonditech"));
			
			Consultas ws = new Consultas(url);
			ConsultasSoap wss = ws.getConsultasSoap();
			
			return Retorno.Sucesso(wss.zoomConsultarExamesPaciente(parametros.GetParametro("loginFluig"), parametros.GetParametro("senhaFluig"), search, pagina, qntdPorPagina));
			
		} catch (Exception ex) {
			return Retorno.Erro(ex);
		}		
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/CountConsultarExamesPaciente")
	public Response CountConsultarExamesPaciente(@QueryParam("search") String search){
		
		try {
			Parametros parametros = new Parametros();
			
			URL url = new URL(parametros.GetParametro("webserviceMonditech"));
			
			Consultas ws = new Consultas(url);
			ConsultasSoap wss = ws.getConsultasSoap();
			
			return Retorno.Sucesso(wss.countConsultarExamesPaciente(parametros.GetParametro("loginFluig"), parametros.GetParametro("senhaFluig"), search));
			
		} catch (Exception ex) {
			return Retorno.Erro(ex);
		}		
	}
	
}
