package com.monditech.fluig.web;

import java.sql.ResultSet;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.monditech.classes.Retorno;
import com.monditech.fluig.Parametros;
import com.monditech.fluig.SQL;
import com.monditech.fluig.Util;

@LocalBean
@Path("/OuvidoriaInterna")
public class OuvidoriaInterna {
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetTeste")
	public Response GetTeste() {
		
		return Retorno.Sucesso("OUVIDORIA INTERNA OK!");
	
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetAssuntos")
	public Response GetAssuntos(@QueryParam("assunto") String assunto, @QueryParam("ativo") Boolean ativo) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"select * from ouvidoria_assuntos WHERE 1 = 1 " + 
								(assunto.equals("") ? "" : "AND id = '" + assunto + "' ") + 
								(ativo ? "" : "AND ativo = 1 ");
								
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
	
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
    @Path("/CadastrarAssuntos")
	public Response CadastrarAssuntos(@QueryParam("assunto") String assunto, @QueryParam("ativo") Boolean ativo) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"INSERT INTO ouvidoria_assuntos (assunto, ativo) values ('" + assunto + "', " + ativo + ") ";
								
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
	
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
    @Path("/EditarAssuntos")
	public Response EditarAssuntos(@QueryParam("assunto") String assunto, @QueryParam("ativo") Boolean ativo, @QueryParam("id") String id) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"UPDATE ouvidoria_assuntos SET assunto = '" + assunto + "', ativo = " + ativo + " WHERE id = '" + id + "' ";
								
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
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetSolicitacoesOuvidoriaInterna")
	public Response GetSolicitacoesOuvidoriaInterna(@QueryParam("inicioPeriodo") String inicioPeriodo, @QueryParam("fimPeriodo") String fimPeriodo,
			@QueryParam("tipoClassificacao") String tipoClassificacao, @QueryParam("assunto") String assunto, @QueryParam("tipoUnidade") String tipoUnidade,
			@QueryParam("tipoSituacao") String tipoSituacao, @QueryParam("tipoEtapa") String tipoEtapa, @QueryParam("gerente") String gerente, 
			@QueryParam("liderMaior") String liderMaior, @QueryParam("liderMenor") String liderMenor, @QueryParam("marketing") String marketing) {
		System.out.println("TIPO ETAPA ---- " + tipoEtapa);
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			String query = "";
			
			query += "SELECT " +
					 "DISTINCT OUVREL.fluig, " +
					 "OUVREL.classificacao, " +
					 "OUVREL.situacao, " +
					 "OUVREL.etapa, " +
					 "OUVREL.data_criacao, " +
					 "OUVREL.data_finalizacao, " +
					 "OUVREL.unidade, " +
					 "OUVREL.setor, " +
					 "OUVREL.turno, " +
					 "OUVREL.nome_funcionario, " +
					 "OUVREL.matricula_usuario, " +
					 "OUVREL.login_usuario, " +
					 "OUVREL.nome_usuario, " +
					 "OUVREL.assunto, " +
					 "OUVREL.outro_assunto, " +
					 "OUVREL.comentario, " +
					 "ETAPA.nome_etapa, " +
					 
					 "(select login_usuario from ouvidoria_ultimos_movimentos where fluig = OUVREL.fluig and etapa = 1 order by data_aprovacao desc limit 1) as usuarioOuvidoria, " +
					 "(select login_usuario from ouvidoria_ultimos_movimentos where fluig = OUVREL.fluig and etapa = 2 order by data_aprovacao desc limit 1) as usuarioMarketing, " +
					 "(select login_usuario from ouvidoria_ultimos_movimentos where fluig = OUVREL.fluig and etapa = 3 order by data_aprovacao desc limit 1) as usuarioGerencia, " +
					 "(select login_usuario from ouvidoria_ultimos_movimentos where fluig = OUVREL.fluig and etapa = 4 order by data_aprovacao desc limit 1) as usuarioLiderancaMaior, " +
					 "(select login_usuario from ouvidoria_ultimos_movimentos where fluig = OUVREL.fluig and etapa = 5 order by data_aprovacao desc limit 1) as usuarioLiderancaMenor " +

				     "FROM ouvidoria_relatorio as OUVREL " +
					 "INNER JOIN ouvidoria_ultimos_movimentos as OUVMOV on OUVMOV.fluig = OUVREL.fluig " +
				     "INNER JOIN  ouvidoria_etapas as ETAPA on ETAPA.etapa = OUVREL.etapa " +
								
							"WHERE 1 = 1 " +
							
							((!(inicioPeriodo.isEmpty()) && !(fimPeriodo.isEmpty())) ? "AND date(OUVREL.data_criacao) >= '" + inicioPeriodo + "' AND date(OUVREL.data_criacao) <= '" + fimPeriodo + "'" : "");	

										
							String filtrosClassificacao[] = tipoClassificacao.split(";");
							
							System.out.println();
							
							if (filtrosClassificacao.length >= 1 && !tipoClassificacao.equals("")) {
								
								query += "AND (";
								
								for (int i = 0; i < filtrosClassificacao.length; i++) {
									
									query += (i > 0 ? "OR" : "") + " OUVREL.classificacao = '" + filtrosClassificacao[i] + "' ";
									
								}
								
								query += ") ";
								
							}
							
							query += (assunto.equals("0") ? "" : "AND OUVREL.assunto = '" + assunto + "' ");
														
							String filtrosUnidade[] = tipoUnidade.split(";");	
							
							if (filtrosUnidade.length >= 1 && !tipoUnidade.equals("")) {
								
								query += "AND (";
								
								for (int j = 0; j < filtrosUnidade.length; j++) {

									query += ((j > 0 ? "OR" : "")) + " OUVREL.unidade = '" + filtrosUnidade[j] + "' ";

								}
								
								query += ") ";
								
							}
							
							String filtrosSituacao[] = tipoSituacao.split(";");
							
							if (filtrosSituacao.length >= 1 && !tipoSituacao.equals("")) {
								
								query += "AND (";
								
								for (int k = 0; k < filtrosSituacao.length; k++) {

									if (filtrosSituacao[k].equals("encerradas")) { 
										
										query += ((k > 0 ? "OR" : "")) + " OUVREL.situacao = 'Encerrada' ";
									}
									else{
										
										query += ((k > 0 ? "OR" : "")) + " OUVREL.situacao = 'Aberta' ";
										
									}
																							
									
								}
								
								query += ") ";
								
							}
							
							String filtrosEtapa[] = tipoEtapa.split(";");
							
							if (filtrosEtapa.length >= 1 && !tipoEtapa.equals("")) {

								query += "AND (";
								
								for (int l = 0; l < filtrosEtapa.length; l++) {

									query += ((l > 0 ? "OR" : "")) + " OUVREL.etapa = '" + filtrosEtapa[l] + "' ";													
									
								}
								
								query += ") ";
								
							}
							
							if(!gerente.equals("") || !liderMaior.equals("") || !liderMenor.equals("") || !marketing.equals("")){
								
								query += "HAVING 1=1 ";
								query += (gerente.equals("") ? "" : " AND usuarioGerencia = '" + gerente + "' ");
								query += (liderMaior.equals("") ? "" : " AND usuarioLiderancaMaior = '" + liderMaior + "' ");
								query += (liderMenor.equals("") ? "" : " AND usuarioLiderancaMenor = '" + liderMenor + "' ");
								query += (marketing.equals("") ? "" : " AND usuarioMarketing = '" + marketing + "' ");
							}
							
							query += "ORDER BY OUVREL.fluig desc ";
							
			System.out.println("RYEO-000");
			System.out.println(query);
			System.out.println(filtrosClassificacao.length);
			System.out.println(!tipoClassificacao.equals(""));
			System.out.println(filtrosEtapa.length);
			System.out.println(filtrosSituacao.length);
			System.out.println(filtrosClassificacao.length);
			
							
			ResultSet resultSet = sql.Select(query);
			Util util = new Util();
			try{
				return Retorno.Sucesso(util.ResultSetToArrayList(resultSet));		
			}finally {
				resultSet.close();
				sql.Close();
				
			}
				
			
		} catch (Exception ex) {
			
			return Retorno.Erro(ex);
		}		
	
	}
	
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
    @Path("/CadastrarHierarquiaSupervisor")
	public Response CadastrarHierarquiaSupervisor(@QueryParam("matriculaUsuario") String matriculaUsuario, @QueryParam("loginUsuario") String loginUsuario, @QueryParam("nomeUsuario") String nomeUsuario,
												  @QueryParam("matriculaSupervisor") String matriculaSupervisor, @QueryParam("loginSupervisor") String loginSupervisor, @QueryParam("nomeSupervisor") String nomeSupervisor) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"INSERT ouvidoria_hierarquia_supervisor (matricula_supervisionado, supervisionado, nome_supervisionado, matricula_supervisor, supervisor, nome_supervisor) " +
								"VALUES ('" + matriculaUsuario + "', '" + loginUsuario + "', '" + nomeUsuario + "', '" + matriculaSupervisor + "', '" + loginSupervisor + "', '" + nomeSupervisor + "') ";
				
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
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetHierarquiaSupervisor")
	public Response GetHierarquiaSupervisor(@QueryParam("usuario") String usuario, @QueryParam("supervisor") String supervisor) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"select * from ouvidoria_hierarquia_supervisor WHERE 1 = 1 " + 
								(usuario.equals("") ? "" : "AND supervisionado = '" + usuario + "' ") + 
								(supervisor.equals("") ? "" : "AND supervisor = '" + supervisor + "' ");
								
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
	
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
    @Path("/EditarHierarquiaSupervisor")
	public Response EditarHierarquiaSupervisor(@QueryParam("supervisor") String supervisor, @QueryParam("nomeSupervisor") String nomeSupervisor, @QueryParam("id") String id, @QueryParam("matriculaSupervisor") String matriculaSupervisor) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"UPDATE ouvidoria_hierarquia_supervisor set supervisor = '" + supervisor + "', nome_supervisor = '" + nomeSupervisor + "', matricula_supervisor = '" + matriculaSupervisor + "'  WHERE id = '" + id + "' ";
						
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
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetSupervisores")
	public Response GetSupervisores(@QueryParam("supervisor") String supervisor) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"select distinct supervisor, nome_supervisor from ouvidoria_hierarquia_supervisor " + 
								(supervisor.equals("") ? "" : "WHERE supervisor = '" + supervisor + "' "); 
								
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
    @Path("/GetUsuariosHierarquia")
	public Response GetUsuariosHierarquia(@QueryParam("usuario") String usuario) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"select distinct usuario.supervisionado, usuario.nome_supervisionado from ouvidoria_hierarquia_supervisor as usuario " +
								"where usuario.supervisionado not in (select distinct supervisor from ouvidoria_hierarquia_supervisor)" + 
								(usuario.equals("") ? "" : "and usuario.supervisionado = '" + usuario + "' ");
				
				//and usuario.supervisionado = ''
								
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
    @Path("/GetSurpevisores")
	public Response GetSurpevisores() {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"select distinct supervisor, nome_supervisor from ouvidoria_hierarquia_supervisor ";
								
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
    @Path("/VerificaHierarquia")
	public Response VerificaHierarquia(@QueryParam("loginUsuario") String loginUsuario, @QueryParam("nomeUsuario") String nomeUsuario,
			  						   @QueryParam("loginSupervisor") String loginSupervisor, @QueryParam("nomeSupervisor") String nomeSupervisor) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"select * from ouvidoria_hierarquia_supervisor where supervisionado = '" + loginUsuario + "' and nome_supervisionado = '" + nomeUsuario + "' and supervisor = '" + loginSupervisor + "' and nome_supervisor = '" + nomeSupervisor + "' ";
								
				ResultSet resultSet = sql.Select(query);
				
				try{
					
					if (resultSet.next()) {

						return Retorno.Sucesso(true);
						
					}
					else {

						return Retorno.Sucesso(false);
						
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
	
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
    @Path("/ExcluirHierarquia")
	public Response ExcluirHierarquia(@QueryParam("id") String id) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"delete from ouvidoria_hierarquia_supervisor where id = '" + id + "' ";
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
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/GetLideresMaiores")
	public Response GetLideresMaiores(@QueryParam("usuario") String usuario) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"select distinct menor.matricula_supervisionado, menor.supervisionado, menor.nome_supervisionado, menor.matricula_supervisionado from ouvidoria_hierarquia_supervisor as menor " + 
								"inner join ouvidoria_hierarquia_supervisor as maior on maior.supervisor = menor.supervisionado" +
								(usuario.equals("") ? "" : " WHERE maior.supervisor = '" + usuario + "' "); 
								
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
    @Path("/GetLideresMenores")
	public Response GetLideresMenores(@QueryParam("usuario") String usuario) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			try{
				
				String query = 	"select distinct usuario.matricula_supervisionado, usuario.supervisionado, usuario.nome_supervisionado from ouvidoria_hierarquia_supervisor as usuario " +
								"where usuario.supervisionado not in (select distinct supervisor from ouvidoria_hierarquia_supervisor) " +
								(usuario.equals("") ? "" : "AND usuario.supervisionado = '" + usuario + "' "); 
								
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
    @Path("/GetSetores")
	public Response GetGruposSetores() {

		try{
			
			SQL sql = new SQL("DatabaseFluig");
			
			try{
				
				String query = 	"SELECT group_code from fdn_group where group_type = 'user' and group_code not like '%teste%'"; 
								
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
    @Path("/GetUsuariosAtivos")
	public Response GetUsuariosAtivos() {

		try{
			
			SQL sql = new SQL("DatabaseFluig");
			
			try{
				
				String query = 	"SELECT fdn_usertenant.user_code as code, fdn_usertenant.login, fdn_user.full_name as fullName " +
								"FROM fdn_usertenant " +
								"INNER JOIN fdn_user on fdn_user.user_id = fdn_usertenant.user_id AND fdn_usertenant.user_state = 1 " +
								"where fdn_usertenant.user_code not like '%teste%' " +
								"and fdn_usertenant.user_code not like '%admin%' " +
								"and fdn_usertenant.user_code not like '%totvs%' ";
								
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
    @Path("/GetParametros")
	public Response GetParametros(@QueryParam("parametro") String parametro){
		
		try {

			Parametros parametros = new Parametros();
			
			System.out.println("EEEEEE-88888");
			System.out.println(parametro);
			
			String retorno = parametros.GetParametros(parametro);
			
			return Retorno.Sucesso(retorno);
			
		} catch (Exception ex) {
			
			return Retorno.Erro(ex);
		}		
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
    @Path("/VerificaUsuarioLiderMaior")
	public Response VerificaUsuarioLiderMaior(@QueryParam("usuario") String usuario) {
		
		try{
			
			SQL sql = new SQL("DatabaseCustom");
			
			boolean retorno = false;
			
			try{
				
				String query = 	"select distinct menor.matricula_supervisionado, menor.supervisionado, menor.nome_supervisionado, menor.matricula_supervisionado from ouvidoria_hierarquia_supervisor as menor " + 
								"inner join ouvidoria_hierarquia_supervisor as maior on maior.supervisor = menor.supervisionado" +
								(usuario.equals("") ? "" : " WHERE menor.supervisionado = '" + usuario + "' "); 
								
				ResultSet resultSet = sql.Select(query);
				
				try{
					
					if(resultSet.next()){
						
						retorno = true;
					}
					
					return Retorno.Sucesso(retorno);
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
}
