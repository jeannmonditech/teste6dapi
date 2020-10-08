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
@Path("/Equipes")
public class Equipes {

	@GET
    @Path("/GetTeste")
	public Response GetTeste() {
		
		return Retorno.Sucesso("Setores OK!");
	
	}
	
		
	@GET
	@Path("/EditarEquipe")
	public Response EditNota(	
			@QueryParam("nomeEquipe") String nomeEquipe,
			@QueryParam("ativo") boolean ativo,
			@QueryParam("id") int id
			){


		try { 

			SQL sql = new SQL("DatabaseCustom");

			try {
				String query = "UPDATE  cep_equipe "
						+ " SET descricao = '"  + nomeEquipe
						+ "' , ativo = "+ ativo
					    + " where id = " + id ;
				
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
	@Path("/CriarEquipe")
	public Response SetEquipe(	@QueryParam("nomeEquipe") String nomeEquipe){
		try { 
			SQL sql = new SQL("DatabaseCustom");
			try {
				sql.InsertUpdate("INSERT INTO cep_equipe (descricao, ativo)"
						+ "VALUES ('" + nomeEquipe + "' , " +  1  +  " )" ); 
				
				sql.Commit();

			} finally {
				sql.Close();
			}

			return Retorno.Sucesso("OK");
		} catch (Exception ex) {

			return Retorno.Erro(ex);

		}


	}
	
	
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetEquipes")
	public Response GetEquipes(@QueryParam("ativo")boolean ativo) {
		
		try{
			SQL sql = new SQL("DatabaseCustom");
			String query;
			query = "select *  from cep_equipe  "  + ((ativo) ?   " where ativo = 1 " : " " ) + " order by descricao";
			
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
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetEquipeDisponivel")
	public Response GetEquipeDisponivel(@QueryParam("setor") int setor) {
		
		try{
			SQL sql = new SQL("DatabaseCustom");
			String query;
			
			query = "SELECT cep_equipe.id as id, cep_equipe.descricao as descricao, cep_equipe.ativo as ativo FROM cep_equipe "
					+ "WHERE cep_equipe.id NOT IN ( SELECT cep_setor_equipe.equipe  FROM cep_setor_equipe  WHERE cep_setor_equipe.setor = " + setor + ") and cep_equipe.ativo = 1 ";
			
			
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
	
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetEquipesPorSetor")
	public Response GetEquipesPorSetor(@QueryParam("setor")String setor) {
		
		try{

			String query;
			
			if(!setor.equals("")){
				query ="SELECT cep_setor_equipe.setor as idSetor,cep_setor_equipe.equipe as idEquipe, cep_setores.descricao as setor, cep_equipe.descricao as equipe FROM "+
						"cep_setor_equipe "+
						"inner join cep_setores on cep_setores.id = cep_setor_equipe.setor "+
						"inner join cep_equipe on cep_equipe.id = cep_setor_equipe.equipe "+
						"where cep_setores.ativo = 1 and cep_equipe.ativo = 1 "+
						"and cep_setor_equipe.setor    = "  +setor+" "+
						"order by cep_setores.descricao, cep_equipe.id";
			}else{
				query="SELECT cep_setor_equipe.setor as idSetor,cep_setor_equipe.equipe as idEquipe, cep_setores.descricao as setor, cep_equipe.descricao as equipe FROM "+
						"cep_setor_equipe "+
						"inner join cep_setores on cep_setores.id = cep_setor_equipe.setor "+
						"inner join cep_equipe on cep_equipe.id = cep_setor_equipe.equipe "+
						"where cep_setores.ativo = 1 and cep_equipe.ativo = 1 "+						
						"order by cep_setores.descricao, cep_equipe.id";
			}
			System.out.println("AA-query "+query);

			SQL sql = new SQL("DatabaseCustom");
			
			try {
				
				ResultSet resultSet = sql.Select(query);
				
				try{
					Util util = new Util();
					return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
					
				}finally{
					resultSet.close();
				}
			}
			finally{
				sql.Close();
			}

			
		}catch(Exception ex){
			return Retorno.Erro(ex);
		}
	}
	
	@GET
	@Path("/NewSetorEquipe")
	public Response NewSetorEquipe(@QueryParam("setor") int setor, @QueryParam("equipe") int equipe){
		
		try{
			SQL sql = new SQL("DatabaseCustom");
			try{
				
				sql.InsertUpdate("INSERT INTO dapi_homologacao.cep_setor_equipe(setor, equipe) VALUES ("+ setor +", "+ equipe +")");
				sql.Commit();
				
				return Retorno.Sucesso("OK");
			
			}finally{
				sql.Close();
			}

		}catch(Exception ex){
			return Retorno.Erro(ex);
		}
		
	}
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/FiltrarResultadosSetorEquipe")
	public Response FiltrarResultadosSetorEquipe(@QueryParam("setor") String setor, @QueryParam("equipe") String equipe){
		try{
			System.out.println("HHHHHHHHHHHHH");
			String query;
			
			query  = "SELECT cep_setor_equipe.setor as idSetor,cep_setor_equipe.equipe as idEquipe,  "
			+" cep_setores.descricao as setor, cep_equipe.descricao as equipe FROM  "
			+" cep_setor_equipe "
			+" inner join cep_setores on cep_setores.id = cep_setor_equipe.setor "
			+" inner join cep_equipe on cep_equipe.id = cep_setor_equipe.equipe "			
			+" where cep_setores.ativo = 1 and cep_equipe.ativo = 1 ";
			if(!setor.equals("")){
				query =  query +" and cep_setor_equipe.setor="+setor+" ";
			}else if(!equipe.equals("")){
				query =  query +" and cep_setor_equipe.equipe="+equipe+" ";
			}
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				System.out.println("HHHHHHHHHHHHH: "+query);
				ResultSet resultSet = sql.Select(query);
				
				try{
					Util util = new Util();
					return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				}catch(Exception e){
					
					return Retorno.Erro(e);
				}
				finally{					
					resultSet.close();					
				}
				
			}catch(Exception e){
				return Retorno.Erro(e);
			}finally{
				sql.Close();
			}
			
			
		}catch(Exception ex){
			return Retorno.Erro(ex);
		}
	}
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/VerificaCount")
	public Response VerificaCount(@QueryParam("setor")int setor, @QueryParam("equipe") int equipe){
		try{
			SQL sql = new SQL("DatabaseCustom");
			String query;
			query = "SELECT count(*) FROM dapi_homologacao.cep_colaborador_setor_equipe where setor ="+ setor +" and equipe ="+ equipe;
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
	
	@GET
	@Path("/ExcluirRelacaoSetorEquipe")
	public Response ExcluirRelacaoSetorEquipe(@QueryParam("setor")int setor, @QueryParam("equipe") int equipe){
		
		try{
			SQL sql = new SQL("DatabaseCustom");
			try{
				System.out.println("ExcluirRelacaoSetorEquipe");
				System.out.println("Excluindo equipe");
				System.out.println(equipe);
				sql.InsertUpdate("DELETE FROM dapi_homologacao.cep_setor_equipe WHERE setor = "+ setor +" and equipe =  " + equipe);
				sql.Commit();
			}finally{
				sql.Close();
			}
			return Retorno.Sucesso("OK");
		}catch(Exception ex){
			return Retorno.Erro(ex);
		}
		
		
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetEquipesPorUsuario")
	public Response GetEquipesPorUsuario(@QueryParam("matricula") String matricula) {
		
		try{
			System.out.println("MATRICULA GetEquipesPorUsuario" + matricula);
			SQL sql = new SQL("DatabaseCustom");
			String query;
			query = "SELECT " + 
						"equipe, " + 
					    "descricao, " + 
					    "id " +
					"FROM " +
						"cep_colaborador_setor_equipe " +
						"RIGHT JOIN cep_equipe ON cep_colaborador_setor_equipe.equipe = cep_equipe.id " + 
					 "WHERE " + 
						"ativo = 1 " +
						(!matricula.equals("") && matricula != null && !matricula.equals("undefined") ? "AND user_code = " + matricula + " " : " ") + " " +
					"ORDER BY "
						+ "descricao;";
			
			System.out.println("QUERY GetEquipesPorUsuario " + query);
			
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
	
	
	
}