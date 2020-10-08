package com.monditech.fluig.web;

import java.io.Console;
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

import sun.security.action.GetBooleanAction;

import com.google.gson.Gson;
import com.monditech.classes.Retorno;
import com.monditech.fluig.*;
import com.monditech.fluig.webservices.ECMOutstandingServiceService;
import com.monditech.fluig.webservices.OutstandingService;
import com.monditech.fluig.webservices.StringArray;

@LocalBean
@Path("/Erro")
public class Erro {

	@GET
	@Path("/GetTeste")
	public Response GetTeste(){
		
		return Retorno.Sucesso("Erro OK!");
	}
	
	
	
	
	@GET
	@Path("/UpdateErro")
	public Response UpdateErro(@QueryParam("id")int id, @QueryParam("ativo")boolean ativo ){
		try{
			SQL sql = new SQL("DatabaseCustom");
			try{
				System.out.println("OOOOO---11111((((((999999");
				String query;
				if(ativo){
					 query = "UPDATE dapi_homologacao.cep_erro SET ativo = "+ (ativo ? "1" : "0")   +" ,  dataUltimaAlteracao = CURDATE(),  dataAtivacao=CURDATE() WHERE id = "+ id +"";
					
				}else{
					 query = "UPDATE dapi_homologacao.cep_erro SET ativo = "+ (ativo ? "1" : "0")   +" ,  dataUltimaAlteracao = CURDATE(),  dataInativacao=CURDATE() WHERE id = "+ id +"";
					
				}
				 System.out.println(query);
				sql.InsertUpdate(query);
				sql.Commit();
			}finally{
				sql.Close();
			}
			return Retorno.Sucesso("OK");
		}catch(Exception ex){
			return Retorno.Erro(ex);			
		}
		
	}
	
	@GET
	@Path("/SetErro")
	public Response SetErro(@QueryParam("descricao") String descricao){
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			try{
				
				java.util.Date agora = new java.util.Date();
				 System.out.println("MMMMMMMMMMMMMM" + agora.getTime());
				String query = 	"INSERT INTO dapi_homologacao.cep_erro (descricao, ativo,dataAtivacao,dataUltimaAlteracao,dataInativacao) VALUES ('"+ descricao +"', 1,   CURDATE() ,  CURDATE() ,null)";					
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
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/GetErros")
	public Response GetErros(@QueryParam("ativo")Boolean ativo){
		
		try{
			SQL sql = new SQL("DatabaseCustom");
			 String query;
			 if(ativo){ 
			 	 query = "SELECT id, descricao, ativo, dataAtivacao, dataInativacao, dataUltimaAlteracao FROM dapi_homologacao.cep_erro where ativo = 1 ";
			 	 System.out.println(query);
			}else{
				 query = "SELECT id, descricao, ativo, dataAtivacao, dataInativacao, dataUltimaAlteracao FROM dapi_homologacao.cep_erro";
				 System.out.println(query);
			} 
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
	
	
	
	//################################
	
	@GET
	@Path("/SetRegistroErro")
	public Response SetRegistroErro(@QueryParam("descricao") String descricao){
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			try{
				String query = 	"INSERT INTO dapi_homologacao.cep_erro (descricao, ativo) VALUES ('"+ descricao +"', 1)";					
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
	
}
