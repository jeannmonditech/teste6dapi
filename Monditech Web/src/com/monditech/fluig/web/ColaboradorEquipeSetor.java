package com.monditech.fluig.web;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.monditech.classes.Dados;
import com.monditech.classes.Retorno;
import com.monditech.fluig.SQL;
import com.monditech.fluig.Util;

@LocalBean
@Path("/ColaboradorEquipeSetor")
public class ColaboradorEquipeSetor {

	@GET
    @Path("/GetTeste")
	public Response GetTeste() {
		
		return Retorno.Sucesso("Setores OK!");
	
	}
	
	
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetColaboradorEquipeSetor")
	public Response GetColaboradorEquipeSetor(@QueryParam("matricula")String matricula,@QueryParam("setor")String setor, @QueryParam("equipe")String equipe, @QueryParam("unidade")String unidade) {
		
		SQL sql;
		
		try{
			sql = new SQL("DatabaseCustom"); 
			String query; 
			query = "SELECT cse.user_code AS matricula,  "
					+ "cse.colaborador AS colaborador, cse.setor AS setorId," 
					+ "cs.descricao AS setor, cse.equipe AS equipeId, ce.descricao AS equipe,"
					+ "cse.unidade AS unidade "
					+ " FROM cep_colaborador_setor_equipe AS cse "
					+ " INNER JOIN cep_equipe AS ce on "
					+ " ce.id=cse.equipe "
				 	+ " INNER JOIN cep_setores AS cs on "
				 	+ " cs.id=cse.setor "
				 	+ " WHERE 1= 1 ";
					
					 if(!matricula.equals("")){
						 query = query+"  and cse.user_code='"+matricula+"' ";
					 }
					 if(!setor.equals("")){
						query =  query+ "  AND cse.setor="+setor+" ";
					 }
					 if(!equipe.equals("")){
						 query = query+"   AND cse.equipe="+equipe+" ";
					 }
					 if(!unidade.equals("")){
						query = query+ "   AND cse.unidade LIKE '%"+unidade+"%' ";
					 } 
 

			System.out.println("AA#@-GetColaboradorEquipeSetor "+query);
			try{				 
				
				ResultSet resultSet = sql.Select(query);
				
				try{
					Util util = new Util();
					return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
					
				}finally{
					resultSet.close();
				}
				
			}finally{
				sql.Close();
			}
			
		}catch(Exception ex){
			return Retorno.Erro(ex);
		}
	}
	
	
	/*
	@GET
	@Path("/SetColaboradorEquipeSetor")
	public Response SetColaboradorEquipeSetort(@QueryParam("matricula")String matricula,@QueryParam("setor")String setor, @QueryParam("equipe")String equipe){
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			try{
				String query = 	"insert into cep_colaborador_setor_equipe(user_code,setor,equipe) values('"+matricula+"',"+setor+","+equipe+");";					
				sql.InsertUpdate(query);				
				sql.Commit();				
				
				return Retorno.Sucesso("OK");
			}finally{
				sql.Close();
			}
			
			
		}catch(Exception ex){
			
			
			return Retorno.Erro(ex);
		}
	}
	*/
	
	
	
	
	@GET
	@Path("/SetColaboradorEquipeSetor")
	public Response SetColaboradorEquipeSetor(@QueryParam("matricula")String matricula,
			@QueryParam("setor")String setor, 
			@QueryParam("equipe")String equipe, 
			@QueryParam("unidade")String unidade,
			@QueryParam("colaborador") String colaborador)
			throws Exception {


		try { 

			SQL sql = new SQL("DatabaseCustom");

			try {

				String query = 	"insert into cep_colaborador_setor_equipe"
						+ "(user_code,setor,equipe, unidade, colaborador) "
						+ "values('"+matricula+"',"+setor+","+equipe+ ", '" +unidade + "', '" +colaborador + "');";
				
				System.out.println("@@##-SetColaboradorEquipeSetor "+query);
				sql.InsertUpdate(query);				
				sql.Commit();

			} finally {

				sql.Close();

			}

			return Retorno.Sucesso("OK");
		} catch (Exception ex) {

			return Retorno.Erro(ex);

		}


	}
	
	
	@GET
	@Path("/updateColaboradorEquipeSetor")
	public Response updateColaboradorEquipeSetor(@QueryParam("matricula")String matricula,@QueryParam("setor")String setor, @QueryParam("equipe")String equipe)
			throws Exception {


		try { 

			SQL sql = new SQL("DatabaseCustom");

			try {

				String query = 	"update cep_colaborador_setor_equipe set setor='"+setor+"',equipe='"+equipe+"' where user_code = '"+matricula+"'";
				
				System.out.println("@@##-updateColaboradorEquipeSetor "+query);
				sql.InsertUpdate(query);				
				sql.Commit();

			} finally {

				sql.Close();

			}

			return Retorno.Sucesso("OK");
		} catch (Exception ex) {

			return Retorno.Erro(ex);

		}


	}
	
	/*
	 *  Deleta pela matrícula uma vez que vai existir apenas uma relação por colaborador.
	 */
	@GET
	@Path("/deleteColaboradorEquipeSetor")
	public Response deleteColaboradorEquipeSetor(@QueryParam("matricula")String matricula)
			throws Exception {


		try { 

			SQL sql = new SQL("DatabaseCustom");

			try {

				String query = 	"delete from cep_colaborador_setor_equipe where  user_code = '"+matricula+"'";
				
				System.out.println("@@##-updateColaboradorEquipeSetor "+query);
				sql.InsertUpdate(query);				
				sql.Commit();

			} finally {

				sql.Close();

			}

			return Retorno.Sucesso("OK");
		} catch (Exception ex) {

			return Retorno.Erro(ex);

		}


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
