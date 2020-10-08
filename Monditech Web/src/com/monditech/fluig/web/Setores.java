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
@Path("/Setores")
public class Setores {

	@GET
    @Path("/GetTeste")
	public Response GetTeste() {
		
		return Retorno.Sucesso("Setores OK!");
	
	}
	
	@GET
    @Path("/SetSetor")
	public Response SetSetor(@QueryParam("descricao") String descricao,@QueryParam("ativo")Boolean ativo) {
		System.out.println("entrou na funcao setsetor");
		
		try {
			
			SQL sql = null;
			sql = new SQL("DatabaseCustom");
			try {
				
				
				 System.out.println("escolhendo base");
				sql.InsertUpdate("insert into cep_setores (descricao,ativo) " +
								 "values('"+ descricao +"'," + ativo + ")");
				                 
				sql.Commit();
				
				return Retorno.Sucesso("OK !!!");
				
			}
			catch (Exception ex) {
				
				return Retorno.Erro(ex);
				
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
    @Path("/GetSetorId")
	public Response GetSetorId(@QueryParam("id") int id) {
		
		try{
			SQL sql = new SQL("DatabaseCustom");
			String query;
			
			query = "SELECT id as id,descricao as descricao,ativo as ativo FROM cep_setores  WHERE ID = " + id  + " order by descricao";

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
    @Path("/GetSetor")
	public Response GetSetor(@QueryParam("ativo")boolean ativo) {
		
		try{
			SQL sql = new SQL("DatabaseCustom");
			String query;
			
			query = "SELECT id as id,descricao as descricao,ativo as ativo FROM cep_setores  "  + ((ativo) ?   " where ativo = 1 " : " " ) + " order by descricao";
					
			
			System.out.println("AA-GetSetor "+query);
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
	
	
/*	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetSetorPorDescricao")
	public Response GetSetorPorDescricao(@QueryParam("descricaoSetor")String descricaoSetor) {
		
		try{
			SQL sql = new SQL("DatabaseCustom");
			String query;
			
			query = "SELECT DISTINCT " +
						"id as id, " + 
					    "descricao as descricao, " +
					    "ativo as ativo " +
					"FROM " +
						"cep_setores " + 
					"WHERE " + 
						"ativo = 1 " + 
						"and descricao = '" + descricaoSetor + "' " +
					"ORDER BY " + 
						"descricao; ";
					
			
			System.out.println("AA-GetSetor "+query);
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
	}*/
	
	
	
	
	
	
	/*
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetSetor")
	public Response GetSetor(@QueryParam("buscar")int buscar) {
		System.out.println("entrou na funcao getsetor");
			ArrayList<String[]> listaRetorno =  new ArrayList<String[]>();
		 
			Dados dados = new Dados();
		
				try {
					System.out.println("try da funcao getsetor");
					SQL sql = null;
					
					try {
						
						sql = new SQL("DatabaseCustom");
						  
			            String query ="select "
			            		+"id as id, "
			                    +"descricao as descricao, "
			            		+"ativo as ativo "
			                    +"from cep_setores "
			                    +"where 1 = 1 ";
                               /* +(descricao.equals("")? "" :"and cep_setor.descricao = '" + descricao + "' ")
			                      (ativo == false ? "" :"and cep_setor.ativo = " + ativo + " ");*/
			                    
			            
	/*ResultSet resultSet = sql.Select(query);
			        	System.out.println("query da funcao getsetor "+query);
			            while (resultSet.next()) {
			                
			                String[] data = new String[3];
			                data[0]  = resultSet.getString("descricao");
			                data[1]  = resultSet.getString("ativo");
			                data[2]  = resultSet.getString("id");
			               
			                listaRetorno.add(data);   
			                
			            }
			            System.out.println("imprimindo retorno");
			            System.out.println(listaRetorno); 
			            dados.data = listaRetorno.toArray(new String[listaRetorno.size()][]);
			            Gson gson = new Gson();
			            
			            System.out.println("imprimindo dados");
			            System.out.println(gson.toJson(dados)); 

			            return Retorno.Sucesso(gson.toJson(dados));
						
					}
					catch(Exception ex){
			            
			            return Retorno.Erro(ex);
			            
			        }
					finally {
							
						sql.Close();
					
					}

		        }
				catch(Exception ex){
		            
		            return Retorno.Erro(ex);
		            
		        }
	
	}
	*/
	
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
    @Path("/EditarSetor")
	public Response EditarSetor(@QueryParam("descricao") String descricao, @QueryParam("ativo") Boolean ativo, @QueryParam("id") String id) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"UPDATE cep_setores SET descricao = '" + descricao + "', ativo = " + ativo + " WHERE id = '" + id + "' ";
								
				sql.InsertUpdate(query);
				sql.Commit();
				
				return Retorno.Sucesso("OK");
				
			}
			finally {
				
				sql.Close();
				
			}
			
		}
		catch (Exception ex) {
			
			return Retorno.Erro(ex);
			
		}
	}
 
}
