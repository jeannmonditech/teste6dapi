package com.monditech.fluig.web;


import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.monditech.classes.Notas;
import com.monditech.classes.Retorno;
import com.monditech.fluig.SQL;

@LocalBean
@Path("/NotasAdp")
public class NotasAdp {


 
	@GET
	@Path("/EditarNotaAdp")
	public Response EditNota(	
			@QueryParam("id") String id,
			@QueryParam("setorVinculado") String setorVinculado,
			@QueryParam("inicioVigencia") String inicioVigencia,
			@QueryParam("fimVigencia") String fimVigencia,
			@QueryParam("pontuacaoAceitavel") String pontuacaoAceitavel,
			@QueryParam("pontuacaoMedia") String pontuacaoMedia,
			@QueryParam("notaMedia") String notaMedia,
			@QueryParam("ativo") int ativo
			) throws Exception {


		try { 

			SQL sql = new SQL("DatabaseCustom");

			try {
				String query ="";
				if (ativo == 1){
					query = "UPDATE  cep_adp "
							+ "SET pontuacao_aceitavel = "  + pontuacaoAceitavel
							+ " , pontuacao_media = "+ pontuacaoMedia
							+ " , ativo = 1 " 
							+" ,inicio_vigencia ='"+inicioVigencia+"' "
							+" ,fim_vigencia='" +fimVigencia+"' "
							+" ,setor=" +setorVinculado
							+ " , nota_media = " + notaMedia + " " + "where id = " + id ;
				}else{
					query = "UPDATE  cep_adp "
							+ "SET ativo = 0 "  + " where id = " + id;
				
				}
				System.out.println("LLLLLL----999999 --- 2222");
				System.out.println(query);
				sql.InsertUpdate(query);
				sql.Commit();
				return Retorno.Sucesso("OK");
			}  catch (Exception ex) {

				return Retorno.Erro(ex);

			}finally {

				sql.Close();

			}
		} catch (Exception ex) {
			return Retorno.Erro(ex);

		}
		
}
	
	
	@GET
	@Path("/CriarNotaAdp")
	public Response SetNota(	@QueryParam("setorVinculado") String setorVinculado,
			@QueryParam("inicioVigencia") String inicioVigencia,
			@QueryParam("fimVigencia") String fimVigencia,
			@QueryParam("pontuacaoAceitavel") String pontuacaoAceitavel,
			@QueryParam("pontuacaoMedia") String pontuacaoMedia,
			@QueryParam("notaMedia") String notaMedia
			) throws Exception {


		try { 

			SQL sql = new SQL("DatabaseCustom");

			try {


				sql.InsertUpdate("INSERT INTO cep_adp (setor, inicio_vigencia, fim_vigencia, pontuacao_aceitavel, pontuacao_media, nota_media, ativo)"
						+ "VALUES (" + setorVinculado + ", '" + inicioVigencia + "', '" + fimVigencia + "', " + pontuacaoAceitavel+ ", " +pontuacaoMedia+ ", " +notaMedia +  ", " + 1 +  " )" ); 
				//sql.InsertUpdate("insert into cep_setores (id, descricao, ativo) values(1,'Teste', 1);");
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
	@Path("/GetNotasADP") 
	public Response GetNotasADP(@QueryParam("setorVinculado") String setorVinculado) throws Exception {


		SQL sql = new SQL("DatabaseCustom");
		
		try{
			String query =  "select cep_adp.id AS id, cep_adp.setor AS setor, "
					+" cep_adp.inicio_vigencia AS inicio_vigencia, "
					+" cep_adp.fim_vigencia AS fim_vigencia, "
					+" cep_adp.pontuacao_aceitavel AS pontuacao_aceitavel, "
					+" cep_adp.pontuacao_media AS pontuacao_media, cep_adp.nota_media AS nota_media, "
					+" cep_setores.id AS idSetor, cep_setores.descricao AS descricao "
					+" from cep_adp "
					+" INNER JOIN cep_setores ON "
					+" cep_adp.setor=cep_setores.id "
					+" WHERE cep_adp.ativo = 1 AND cep_setores.ativo=1 ";
			if(setorVinculado.equals("")){
				 query =  query+" AND cep_adp.setor= " +setorVinculado;
			}
			ResultSet resultSet = sql.Select( query );
			try{
			
	
				ArrayList<Notas> lista = new ArrayList<Notas>();
				while (resultSet.next()) {
					Notas notas = new Notas();
					notas.setId(resultSet.getInt("id"));
					notas.setSetor(resultSet.getInt("setor"));
					notas.setInicioVigencia(resultSet.getString("inicio_vigencia"));
					notas.setFimVigencia(resultSet.getString("fim_vigencia"));
					notas.setPontuacaoAceitavel(resultSet.getFloat("pontuacao_aceitavel"));
					notas.setPontuacaoMedia(resultSet.getFloat("pontuacao_media"));
					notas.setNotaMedia(resultSet.getFloat("nota_media") );
					notas.setDescricao(resultSet.getString("descricao"));
					notas.setIdSetor(resultSet.getString("idSetor"));
					lista.add(notas);
	
				}
				Gson gson = new Gson();
				return Retorno.Sucesso(gson.toJson(lista.toArray()));	
			
			}catch(Exception e){
				return Retorno.Erro(e);
			}finally{
				resultSet.close();
			}
		}catch(Exception e){
			return Retorno.Erro(e);
		}finally{
			sql.Close();

		}




	}


}
