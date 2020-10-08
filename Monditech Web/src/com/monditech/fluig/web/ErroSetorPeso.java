package com.monditech.fluig.web;


import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.monditech.classes.ErroSetorPesos;
import com.monditech.classes.Notas;
import com.monditech.classes.Retorno;
import com.monditech.fluig.Parametros;
import com.monditech.fluig.SQL;
import com.monditech.fluig.Util;
import com.monditech.fluig.Workflow;
import com.monditech.webservices.Consultas;
import com.monditech.webservices.ConsultasSoap;

@LocalBean
@Path("/ErroSetorPeso")
public class ErroSetorPeso {

/*

	@GET
	@Path("/DeletarErroSetorPeso")
	public Response DeletarErroSetorPeso(	
			@QueryParam("idErro") int idErro,
			@QueryParam("pesoSetor") float pesoSetor,
			@QueryParam("idSetor") int idSetor) throws Exception {


		try { 

			SQL sql = new SQL("DatabaseCustom");

			try {
				String query ="";
				query = "DELETE FROM cep_setor_erro WHERE erro = " + idErro + " AND peso = " + pesoSetor + " AND setor = " + idSetor ;
				
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
	*/
	
	@GET
	@Path("/AtualizarErroSetorPeso")
	public Response AtualizarErroSetorPeso(	
			@QueryParam("id") int id,
			@QueryParam("setor") int setor,
			@QueryParam("erro") int erro,
			@QueryParam("peso") int peso,
			@QueryParam("ativo") boolean ativo,
			@QueryParam("dataVigencia") String dataVigencia) throws Exception {
		 
		

		try { 
			System.out.println("ATTLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			System.out.println(id+" "+setor+" "+erro+" "+peso+" "+ativo);
			SQL sql = new SQL("DatabaseCustom");

			try {
				String query ="";
				/*
				query = "UPDATE cep_setor_erro "
						+ "SET peso = " + novoPeso
						+ "WHERE erro = " + idErro + " AND peso = " + pesoSetor + " AND setor = " + idSetor ;
				*/
				query = "update cep_setor_erro set peso="+peso+", setor="+setor+", erro="+erro+", ativo="+ativo+", dataVigencia='"+dataVigencia+"'  where id="+id;
				System.out.println("000000000000000000000000");
				System.out.println(query);
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
	@Path("/CriarErroSetorPeso")
	public Response CriarErroSetorPeso(	
			@QueryParam("idErro") int idErro,
			@QueryParam("pesoSetorNovo") float pesoSetorNovo,
			@QueryParam("idSetor") int idSetor,
			@QueryParam("dataVigencia") String dataVigencia
			) throws Exception {
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");

		try { 

			SQL sql = new SQL("DatabaseCustom");

			try {

				sql.InsertUpdate("INSERT INTO cep_setor_erro (erro, peso, setor,ativo,dataVigencia)"
						+ "VALUES (" + idErro + ", " + pesoSetorNovo + ", " + idSetor +  ", true, '"+dataVigencia+"')" ); 
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
	@Path("/GetErroSetorPeso") 
	public Response GetErroSetorPeso(	@QueryParam("idErro") String idErro,
			@QueryParam("pesoSetorNovo") String pesoSetorNovo,
			@QueryParam("idSetor") String idSetor) throws Exception {

	
		SQL sql = new SQL("DatabaseCustom");
		try{
			ResultSet resultSet = sql.Select("select cep_setor_erro.id,  setor, erro, peso,cep_setor_erro.ativo, cep_setor_erro.dataVigencia,cep_setores.descricao as descSetor,cep_erro.descricao as descErro "
					+ "from cep_setor_erro "
					+ "inner join cep_setores on cep_setores.id = cep_setor_erro.setor "
					+ "inner join cep_erro on cep_erro.id = cep_setor_erro.erro "
					+ " where 1 = 1 and  cep_setor_erro.ativo=1 "
					+ (idErro.equals("") ? "" :  " and cep_setor_erro.erro = " + idErro + " " )
					+ (pesoSetorNovo.equals("") ? "" :  " and cep_setor_erro.peso LIKE '%" + pesoSetorNovo +  "%'" ) 
					+ (idSetor.equals("") ? "" : "  and cep_setor_erro.setor = " + idSetor  ));

			ArrayList<ErroSetorPesos> lista = new ArrayList<ErroSetorPesos>();
			try{
				System.out.println("_______________________________pPPPPP");
				Util util = new Util();
				List<Object> teste  =  util.ResultSetToArrayList(resultSet);
				System.out.println(teste);
				return Retorno.Sucesso(teste);	
			}finally{
				resultSet.close();
				
			}
		}finally{
			sql.Close();
		}
		

	}
	
	


}
