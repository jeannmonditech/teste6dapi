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
@Path("/RegistroErro")
public class RegistroErro {

	@GET
	@Path("/GetTeste")
	public Response GetTeste(){
		
		return Retorno.Sucesso("Erro OK!");
	}
	
	
	
	
	
	@GET
	@Path("/SetRegistroErro")
	public Response SetErro(@QueryParam("descricao") String descricao){
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			try{
				String query = 	"";					
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
					 query ="SELECT id, descricao, ativo FROM dapi_homologacao.cep_erro WHERE ativo = 1";
				}else{
					 query ="SELECT id, descricao, ativo FROM dapi_homologacao.cep_erro";
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
	
	
	
}
