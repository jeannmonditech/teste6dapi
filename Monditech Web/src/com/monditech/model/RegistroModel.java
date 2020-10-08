package com.monditech.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.monditech.classes.Registro;
import com.monditech.classes.Retorno;
import com.monditech.fluig.Parametros;
import com.monditech.fluig.SQL;
import com.monditech.fluig.Util;
import com.monditech.soap.TotalAtendimentosSoap;
import com.monditech.webservices.Consultas;
import com.monditech.webservices.ConsultasSoap;
import com.monditech.webservices.ConsultaTotalAtendimentosByGroup;
import com.monditech.webservices.ConsultaTotalAtendimentosByGroupResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistroModel {

	public Response getRegistros( String matricula,
			 String usuario,
			 String setor,
			 String codErro,
			 String equipe,
			 String dataInicio, 
			 String dataFim) throws Exception{
		SQL sql = null;
		try {
			sql =  new SQL("DatabaseCustom");
			try {
				String query = "SELECT cse.colaborador as nomeColaborador, "
								+ "cep.codigo_setor AS setor,"
								+"cse.equipe AS equipe, "   
								+"cep.codigo_erro AS codErro," 
								+"cep.matricula_colaborador AS matricula," 
								+"COUNT(cep.codigo_erro) AS totalErro," 
								+"cepr.descricao AS nomeErro,"
								+"sub.totalc AS totalDivergencia, "
								+"(COUNT(cep.codigo_erro)*peso*100) AS erroXPesoXcem "
				 				+"FROM cep_registros_erros AS cep "
								+"INNER JOIN cep_colaborador_setor_equipe AS cse ON cse.setor=cep.codigo_setor AND cse.user_code=cep.matricula_colaborador "
								+"INNER JOIN cep_erro AS cepr ON cepr.id=cep.codigo_erro "
								+"INNER JOIN (SELECT COUNT(c.matricula_colaborador) AS totalc,"
								+"c.matricula_colaborador as mat "
								+"FROM cep_registros_erros AS c "
								+"INNER JOIN cep_colaborador_setor_equipe AS cse2 ON cse2.setor=c.codigo_setor AND cse2.user_code=c.matricula_colaborador "
								
								+"INNER JOIN cep_setor_erro AS ceperro ON "
								+" ceperro.setor=c.codigo_setor AND ceperro.erro=c.codigo_erro "
								+" AND ceperro.ativo=1 "
								
								+"WHERE 1=1 AND  c.houve_erro='sim' AND c.id_fluig>0 ";
				
								if(!dataInicio.equals("") && !dataFim.equals("")){
									query=query+"AND c.data_erro BETWEEN '"+dataInicio+"' AND '"+dataFim+"' ";
								}
								if(!matricula.equals("")){
									query = query+"AND c.matricula_colaborador = '"+matricula+"' ";
								}
								if(!setor.equals("")){
									query = query+"AND c.codigo_setor="+setor+" ";
								}
								if(!codErro.equals("")){
									query = query+"AND c.codigo_erro="+codErro+" ";
								}
								if(!equipe.equals("")){
									query = query+"AND cse2.equipe="+equipe+" ";
								}
								query = query + " GROUP BY c.matricula_colaborador) AS sub on "
								+"sub.mat=cep.matricula_colaborador "
								+" INNER JOIN cep_setor_erro AS ceperro ON "
								+" ceperro.setor=cep.codigo_setor AND ceperro.erro=cep.codigo_erro AND ceperro.ativo=1 "
								+"WHERE 1=1 "
								+"AND cep.houve_erro='sim' AND cep.id_fluig>0 ";
								if(!dataInicio.equals("") && !dataFim.equals("")){
									query=query+"AND cep.data_erro BETWEEN '"+dataInicio+"' AND '"+dataFim+"' ";
								}
								if(!matricula.equals("")){
									query = query+"AND cep.matricula_colaborador = '"+matricula+"' ";
								}
								if(!setor.equals("")){
									query = query+"AND cep.codigo_setor="+setor+" ";
								}
								if(!codErro.equals("")){
									query = query+"AND cep.codigo_erro="+codErro+" ";
								}
								if(!equipe.equals("")){
									query = query+"AND cse.equipe="+equipe+" ";
								}
								query = query+" GROUP BY   cep.matricula_colaborador, cep.codigo_erro ,sub.totalc, cse.colaborador,ceperro.peso ;";
				
				System.out.println(" QUERY GetRegistros  " + query);
				ResultSet resultSet = sql.Select(query);
				
				try {
					List<Registro> registros = new ArrayList<Registro>();
					while(resultSet.next()){
						Registro registro =  new Registro();
						registro.setColaborador(resultSet.getString("nomeColaborador"));
						registro.setSetor(resultSet.getInt("setor")); 
						registro.setEquipe(resultSet.getInt("equipe"));
						registro.setCodErro(resultSet.getInt("codErro"));
						registro.setMatricula(String.valueOf(resultSet.getString("matricula"))); 
						registro.setTotalErro(resultSet.getInt("totalErro"));
						registro.setCodErro(resultSet.getInt("codErro"));
						registro.setTotalDivergencia(resultSet.getInt("totalDivergencia"));
						registro.setNomeErro("ERRO_"+resultSet.getString("nomeErro")); 
						registro.setErroXPesoXcem(resultSet.getDouble("erroXPesoXcem")); 
						registro.setDataInicio(dataInicio);
						registro.setDataFim(dataFim);
						registros.add(registro);
					}
					System.out.println("RESULTADO----0000----AAAAA");
				  
				 
					//addTotalAndPercent(getPivotRegistros(registros));
					//System.out.println("RESULTADO----0000----BBBB");
					  return Retorno.Sucesso(addTotalAndPercent(getPivotRegistros(registros)));
					// return Retorno.Sucesso( getPivotRegistros(registros));
					
				} catch (Exception e) {
					System.out.println("GetRegistros Erro resultSet: "+e);
					return Retorno.Erro(e);
				}finally{
					resultSet.close();
				}
			} catch (Exception e) {
				System.out.println("GetRegistros Erro sql: "+e);
				return Retorno.Erro(e);
			}finally{
				sql.Close();
			}
		} catch (Exception e) {
			System.out.println("GetRegistros Erro Instância sql: "+e);
			return Retorno.Erro(e);
		}finally{
			if(sql.getConexao().isClosed()==false){
				sql.Close();
			}
		} 
	}
	

	/**
	 * 
	 * @param registro
	 * @param dataInicio
	 * @param dataFim
	 * @return String com a quantidade de consulta do colaborador
	 * @throws ParseException
	 */
		private String GetConsultaTotalAtendimentos(Registro registro,String dataInicio,String dataFim) throws ParseException{
		 
		
		String dataI="";
		String dataF ="";
		if(!dataInicio.equals("")){
			Date a = new SimpleDateFormat("yyyy-MM-dd").parse(dataInicio);
			dataI = new SimpleDateFormat("dd-MMM-yy").format(a);
			 
			
		}else{
			dataI="09-NOV-10";
		}
		
		if(!dataFim.equals("")){
			Date  b= new SimpleDateFormat("yyyy-MM-dd").parse(dataFim);
			dataF = new SimpleDateFormat("dd-MMM-yy").format(b);
			 
		}else{
			 DateFormat df = new SimpleDateFormat("dd-MMM-yy");
		       Date dateobj = new Date(); 
		       dataF= df.format(dateobj);
		}
		 
		 try {
			 
				Parametros parametros = new Parametros();
				Consultas consultas = new Consultas();
				ConsultasSoap consultasSoap = consultas.getConsultasSoap();
				String retorno = consultasSoap.consultaTotalAtendimentos(parametros.GetParametro("loginFluig"), 
					parametros.GetParametro("senhaFluig"),
					registro.getMatricula(), dataI, dataF)
						
			 ; 
			return retorno;
			
		} catch (Exception ex) {
			 System.out.println("ERRO SOAP EM Web.GetConsultaTotalAtendimentos: "+ex);
		}		
		return null;
		
	}
		
		
		/**
		 * 
		 * @param dataInicio
		 * @param dataFim
		 * @return String com a quantidade de consultas dos colaboradores
		 * @throws ParseException
		 */
		private String GetConsultaTotalAtendimentosByGroup(String dataInicio,String dataFim) throws ParseException{
			 
			
			String dataI="";
			String dataF ="";
			
			if(!dataInicio.equals("")){
				Date a = new SimpleDateFormat("yyyy-MM-dd").parse(dataInicio);
				dataI = new SimpleDateFormat("dd-MMM-yy").format(a);
				 
				
			}else{
				dataI="09-NOV-10";
			}
			
			if(!dataFim.equals("")){
				Date  b= new SimpleDateFormat("yyyy-MM-dd").parse(dataFim);
				dataF = new SimpleDateFormat("dd-MMM-yy").format(b);
				 
			}else{
				 DateFormat df = new SimpleDateFormat("dd-MMM-yy");
			       Date dateobj = new Date(); 
			       dataF= df.format(dateobj);
			}
			
			 try {
				 	
					Parametros parametros = new Parametros();
					Consultas consultas = new Consultas();
					ConsultasSoap consultasSoap = consultas.getConsultasSoap();
					String retorno = consultasSoap.consultaTotalAtendimentosByGroup(parametros.GetParametro("loginFluig"), 
							parametros.GetParametro("senhaFluig"), dataI, dataF); 
					return retorno;
					
				} catch (Exception ex) {
					 System.out.println("ERRO SOAP EM Web.GetConsultaTotalAtendimentosByGroup: "+ex);
				}		
				return null;
		}

		
	private String GetTotal(String json, String matricula) {
		
		JsonArray totals_matricula = new JsonParser().parse(json).getAsJsonArray();
			
		for (int i = 0; i < totals_matricula.size(); i++) {
			
			JsonObject objeto = totals_matricula.get(i).getAsJsonObject();
			
			if (objeto.get("MATRICULA").getAsString().equals(matricula)) {
				
				return objeto.get("TOTAL").getAsString();
				
			}
			
		}
		
		return "0";
		
	}
	
	/**
	 * 
	 * @param Lista de Registros de erros
	 * @return Uma lista de mapas de registros de erros
	 * @Descricao Ordena as linhas do resultado em forma de coluna PIVOT
	 * @throws Exception
	 */
	private List<LinkedHashMap<String, String>> getPivotRegistros(List<Registro> registros) throws Exception{
		
		
		//ARRAY COM A QUANTIDE POR MATRICULA = METODO_NOVO_GET_SOAP
		String totalAtendimentoByGroup = GetConsultaTotalAtendimentosByGroup(registros.get(0).getDataInicio(), registros.get(0).getDataFim());
		
		System.out.println("SSSSS---DDDDDD----DDDD");
		 
		LinkedHashMap<String, String> listaErros =    new LinkedHashMap<String, String>();
		for (int k = 0; k < registros.size(); k++) {
			 System.out.println(registros.get(k).getNomeErro());
			 if(listaErros.containsKey(registros.get(k).getNomeErro())==false){
				 listaErros.put(registros.get(k).getNomeErro(), "");
			
			 } 
			
		}
		List<LinkedHashMap<String, String>> listaColecaoRegistros = new ArrayList<LinkedHashMap<String, String>>();
 
		System.out.println("RRRRRRRRRRRRRRRRRRRRRr");
		LinkedHashMap<String, String> mapaRegistros = null;
		for (int i = 0; i < registros.size(); i++) {
			JSONObject my_obj = new JSONObject();
			System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
			if(mapaRegistros!=null){
				System.out.println("tttttttttttttttttttttttttt");
				System.out.println(mapaRegistros.containsValue(registros.get(i).getMatricula()));
				System.out.println("uuuuuuuu");
				if(mapaRegistros.containsValue(registros.get(i).getMatricula())){
					 mapaRegistros.put(registros.get(i).getNomeErro(), String.valueOf(registros.get(i).getTotalErro()));
					 System.out.println("vvvvvvvvvvvvvvvvvvv");
					 String val = String.valueOf(Double.parseDouble(mapaRegistros.get("erroXPesoXcem"))+registros.get(i).getErroXPesoXcem());
				 
					mapaRegistros.put("erroXPesoXcem", val); 
				}
				
			}else{ 
				
				mapaRegistros = new LinkedHashMap<String, String>(); 
				
				mapaRegistros.put("erroXPesoXcem", String.valueOf(registros.get(i).getErroXPesoXcem())); 
				mapaRegistros.put("matricula", registros.get(i).getMatricula()); 
				mapaRegistros.put("colaborador", registros.get(i).getColaborador());
				mapaRegistros.put("setor", String.valueOf(registros.get(i).getSetor()));
				mapaRegistros.put("equipe", String.valueOf(registros.get(i).getEquipe())); 
				mapaRegistros.put("totalDivergencia", String.valueOf(registros.get(i).getTotalDivergencia()));
				System.out.println("vvvvvvvvvvvvvvvvvvv");
				mapaRegistros.put("totalAtendimento", GetTotal(totalAtendimentoByGroup, registros.get(i).getMatricula()));
				//mapaRegistros.put("totalAtendimento", String.valueOf(totalAtendimentoByGroup.find(registros.get(i).getMatricula())));
			//	System.out.println("SOMENTE UMA MATRICULA: "+registros.get(i).getMatricula());
				
				mapaRegistros.put(registros.get(i).getNomeErro(), String.valueOf(registros.get(i).getTotalErro()));
				
			}
			
			
			if( (i+1) <registros.size()){
				if(!registros.get(i).getMatricula().equals(registros.get(i+1).getMatricula())){
					
					 String val = String.valueOf(Double.parseDouble(mapaRegistros.get("erroXPesoXcem"))/Double.parseDouble(mapaRegistros.get("totalAtendimento")));
					 mapaRegistros.put("mediaPonderada",  val != null? val:"0.0"); 
					 mapaRegistros.put("notasADP", getNotasAdp(mapaRegistros.get("mediaPonderada"), mapaRegistros.get("setor")));
					 mapaRegistros.remove("erroXPesoXcem");
					 for (Map.Entry<String, String> entry : listaErros.entrySet()) {
						    System.out.println(entry.getKey() + " = " + entry.getValue());
						 if(mapaRegistros.containsKey(entry.getKey())==false){
						    	mapaRegistros.put(entry.getKey(), "0");
							}
					  
					 }
					 listaColecaoRegistros.add(mapaRegistros);
					 mapaRegistros =null;
				}
			}else{
				String val = String.valueOf(Double.parseDouble(mapaRegistros.get("erroXPesoXcem"))/Double.parseDouble(mapaRegistros.get("totalAtendimento")));
				mapaRegistros.put("mediaPonderada", val != null? val:"0.0");
				mapaRegistros.put("notasADP", getNotasAdp(mapaRegistros.get("mediaPonderada"), mapaRegistros.get("setor")));
				mapaRegistros.remove("erroXPesoXcem");
				 for (Map.Entry<String, String> entry : listaErros.entrySet()) {
					   // System.out.println(entry.getKey() + " = " + entry.getValue());
					    if(mapaRegistros.containsKey(entry.getKey())==false){
					    	mapaRegistros.put(entry.getKey(), "0");
						}
				  
				 }
				listaColecaoRegistros.add(mapaRegistros);
				mapaRegistros = null;
			}
			
			
		}
		//System.out.println("-------------UUUUB----00000");
		
		return listaColecaoRegistros;
		
	}
	
	/**
	 * 
	 * @param mediaPonderada
	 * @param setor
	 * @return NotasADP
	 * @throws Exception
	 */
	private String getNotasAdp (String mediaPonderada,String setor) throws Exception{
	 //nota media+(1-(media ponderada/(pontuacao media-prontuacao aceitavel)))*(10-nota media)
		
		SQL sql = null;
		try {
			sql =  new SQL("DatabaseCustom");
			try {
				String query = "SELECT cep_adp.nota_media AS notaMedia,"
				+" cep_adp.pontuacao_media AS pontuacaoMedia,"
				+" cep_adp.pontuacao_aceitavel AS pontuacaoAceitavel,"
				+" cep_adp.setor AS setor "
				+" FROM cep_adp "
				+" WHERE cep_adp.ativo=1 AND "
				+" cep_adp.inicio_vigencia AND cep_adp.fim_vigencia ";
				if(!setor.equals("")){

					query =  query+" AND cep_adp.setor='"+setor+"' ";
				}
				
				ResultSet resultSet = sql.Select(query);
				//System.out.println(" QUERY GetADP  " + query);
				try {
					double resultado=0;
					while(resultSet.next()){
						 //nota media+(1-(media ponderada/(pontuacao media-prontuacao aceitavel)))*(10-nota media)
						 //nota media                              +(1-(media ponderada                   /(pontuacao media                      -prontuacao aceitavel                    )))*(10-nota media)
						resultado = resultSet.getDouble("notaMedia")+(1-(Double.parseDouble(mediaPonderada)/(resultSet.getDouble("pontuacaoMedia")-resultSet.getDouble("pontuacaoAceitavel")))*(10-resultSet.getDouble("notaMedia")));
					//	System.out.println("nota media+(1-(c/(pontuacao media-prontuacao aceitavel)))*(10-nota media)"); 
					//	System.out.println("nota media: "+resultSet.getDouble("notaMedia"));
					//	System.out.println("media ponderada: "+Double.parseDouble(mediaPonderada));
					//	System.out.println("pontuacao media: "+resultSet.getDouble("pontuacaoMedia"));
					//	System.out.println("media-prontuacao aceitavel: "+resultSet.getDouble("pontuacaoAceitavel"));
					//	System.out.println("Resultado: "+resultado);
					}
					//System.out.println("-------------------" );
					return String.valueOf(resultado);
				} catch (Exception e) {
			
					System.out.println("GetADP Erro resultSet: "+e);
					throw new  Exception("GetADP Erro resultSet: "+e);
				}finally{
					resultSet.close();
				}
			} catch (Exception e) {
				
				System.out.println("GetADP Erro sql: "+e);
				throw new SQLException("GetADP Erro sql: "+e);
			}finally{
				sql.Close();
			}
		} catch (Exception e) {
			
			System.out.println("GetADP Erro Instância sql: "+e);
			throw new SQLException("GetADP Erro Instância sql: "+e);
		}finally{
			if(sql.getConexao().isClosed()==false){
				sql.Close();
			}
		} 
	}
	 
	private List<LinkedHashMap<String, String>> addTotalAndPercent(List<LinkedHashMap<String, String>> y){
		 LinkedHashMap<String, String> t =  new  LinkedHashMap<String, String>();
		System.out.println("RESULTADO----0000----CCCC");
	 
		 
		for (int i = 0; i < y.size(); i++) {
		 
			for (Map.Entry<String, String> entry : y.get(i).entrySet()) {
				System.out.println("RESULTADO----0000----DDDDD");
				 if(entry.getKey().contains("ERRO")){ 
					 if(t.containsKey("TOTAL_"+entry.getKey())){ 
					System.out.println("RESULTADO----0000----EEE");
						 double temp = Double.parseDouble(t.get("TOTAL_"+entry.getKey())); 
						 System.out.println("RESULTADO----0000----FFFF");
						 t.put("TOTAL_"+entry.getKey(),String.valueOf(Double.parseDouble(entry.getValue())+temp));
						 System.out.println("RESULTADO----0000----GGGGG");
					 }else{ 
						 System.out.println("RESULTADO----0000----HHHHHCC");
						 t.put("TOTAL_"+entry.getKey(),String.valueOf(Double.parseDouble(entry.getValue() != null ?entry.getValue():"0.0")));
						 System.out.println("RESULTADO----0000----IIIII");
					 }
					
				 }
				 if(entry.getKey().contains("totalDivergencia")){ 
					 System.out.println("RESULTADO----0000----JJJJ");
					 if(!t.containsKey("TOTALIDADE_totalDivergencia")){ 

						 //System.out.println("RESULTADO----0000----OOOO");
						 t.put("TOTALIDADE_"+entry.getKey(),String.valueOf(Double.parseDouble(entry.getValue())));
						 //System.out.println("RESULTADO----0000----PPPP");
						 
					 }else{ 
						 //System.out.println("RESULTADO----0000----LLLLL");
						 double temp = Double.parseDouble(t.get("TOTALIDADE_"+entry.getKey()));
						 //System.out.println("RESULTADO----0000----MMMM");
						 t.put("TOTALIDADE_"+entry.getKey(),String.valueOf(Double.parseDouble(entry.getValue())+temp));
						 //System.out.println("RESULTADO----0000----NNN");
						 
					 }
					 
					 //System.out.println("RESULTADO----0000----OOOO");
				 }else  if(entry.getKey().contains("totalAtendimento")){ 
					 if(!t.containsKey("TOTALIDADE_totalAtendimento")){ 

						 //System.out.println("RESULTADO----0000----OOOO");
						 t.put("TOTALIDADE_"+entry.getKey(),String.valueOf(Double.parseDouble(entry.getValue())));
						 //System.out.println("RESULTADO----0000----PPPP");
						 
					 }else{ 
						 //System.out.println("RESULTADO----0000----LLLLL");
						 double temp = Double.parseDouble(t.get("TOTALIDADE_"+entry.getKey()));
						 //System.out.println("RESULTADO----0000----MMMM");
						 t.put("TOTALIDADE_"+entry.getKey(),String.valueOf(Double.parseDouble(entry.getValue())+temp));
						 //System.out.println("RESULTADO----0000----NNN");
						 
					 }
				 } else if(entry.getKey().contains("mediaPonderada")){
					 if(!t.containsKey("TOTALIDADE_mediaPonderada")){ 

						 //System.out.println("RESULTADO----0000----OOOO");
						 t.put("TOTALIDADE_"+entry.getKey(),String.valueOf(Double.parseDouble(entry.getValue())));
						 //System.out.println("RESULTADO----0000----PPPP");
						 
					 }else{ 
						 //System.out.println("RESULTADO----0000----LLLLL");
						 double temp = Double.parseDouble(t.get("TOTALIDADE_"+entry.getKey()));
						 //System.out.println("RESULTADO----0000----MMMM");
						 t.put("TOTALIDADE_"+entry.getKey(),String.valueOf(Double.parseDouble(entry.getValue())+temp));
						 //System.out.println("RESULTADO----0000----NNN");
						 
					 }
				 }
			}
		}
		 //System.out.println("RESULTADO----0000----GGGGGG55555");
		 LinkedHashMap<String, String> k =  new  LinkedHashMap<String, String>();
		for (Map.Entry<String, String> entry : t.entrySet()) {
			 //System.out.println("RESULTADO----0000----LLLLL9999");
			
			 
			
			if(entry.getKey().startsWith("TOTAL_")){
				//System.out.println("DE00000");
				//System.out.println(entry.getKey()+" "+entry.getValue());
				k.put("PORCENTAGEM_"+entry.getKey(),  String.valueOf( String.format("%.3f", Double.parseDouble(entry.getValue())/Double.parseDouble(t.get("TOTALIDADE_totalDivergencia"))*100 )));
				//System.out.println("PARA00000");
				//System.out.println(entry.getKey()+" "+entry.getValue());
			}else{
				//System.out.println(entry.getKey()+" "+entry.getValue());
			}
		}
		t.putAll(k);
		//System.out.println("RESULTADO----0000----DDDD");
		 
		y.add(t);
		return y;
		
	}


	public Response GetEvolucaoDoUsuario(String matricula, String usuario,
			String setor, String codErro, String equipe, String dataInicio,
			String dataFim) throws Exception {
 
			SQL sql = null;
			String query = "";
			try {
				sql =  new SQL("DatabaseCustom");
				try {
					query =  "SELECT  COUNT(DATE_FORMAT(cep.data_erro,'%Y-%m%d') )  AS totalErroColaborador,"
					 +" DATE_FORMAT(cep.data_erro,'%Y-%m-%d') AS dataDoErro, " 
					 +" cep.matricula_colaborador AS matricula, "
					 +" cepc.colaborador AS nome "
					 +" FROM cep_registros_erros AS cep "
					 +" INNER JOIN cep_colaborador_setor_equipe AS cepc ON "
					 +" cepc.user_code=cep.matricula_colaborador AND cepc.setor = cep.codigo_setor "
					 
					 +"INNER JOIN cep_setor_erro AS ceperro ON "
					 +" ceperro.setor=cep.codigo_setor AND ceperro.erro=cep.codigo_erro AND ceperro.ativo=1 "
					 
					 +" WHERE cep.houve_erro='sim' and cep.id_fluig>0 ";
					if(!matricula.equals("")){
						query =  query+" AND cep.matricula_colaborador='"+matricula+"' ";
					}
					if(!usuario.equals("")){
						query =  query+" AND cepc.colaborador='"+usuario+"' ";
					}
					if(!setor.equals("")){
						query =  query+" AND cep.codigo_setor='"+setor+"' ";
					}
					if(!codErro.equals("")){
						query =  query+" AND cep.codigo_erro='"+codErro+"' ";
					}
					if(!equipe.equals("")){
						query =  query+" AND cepc.equipe='"+equipe+"' ";
					}
					if(!equipe.equals("")){
						query =  query+" AND cepc.equipe='"+equipe+"' ";
					}
					if(!dataInicio.equals("") && !dataFim.equals("")){
						query=query+" AND cep.data_erro BETWEEN '"+dataInicio+"' AND '"+dataFim+"' ";
					}
					
					query = query +" GROUP BY  DATE_FORMAT(cep.data_erro,'%Y-%m-%d'),cep.matricula_colaborador  ORDER BY DATE_FORMAT(cep.data_erro,'%Y-%m-%d')  asc;";
									
					System.out.println("GetEvolucaoDoUsuario: "+query);
					ResultSet resultSet = sql.Select(query); 
					try {
						Util util = new Util(); 
						return  Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
					} catch (Exception e) {
						System.out.println("GetEvolucaoDoUsuario ResultSet ERRO: "+e); 
						Retorno.Erro(e);
					}finally{
						resultSet.close();
					}
				} catch (Exception e) {
					System.out.println("GetEvolucaoDoUsuario SQL ERRO: "+e);
					Retorno.Erro(e);
				}finally{
					sql.Close();
					
				}
			} catch (Exception e) {
				 System.out.println("GetEvolucaoDoUsuario ERRO ao instanciar a classe SQL"+e);
				 Retorno.Erro(e);
			} finally{
				if(sql.getConexao().isClosed()==false){
					sql.Close();
				}
			}
			return null; 
			 
	}


	public Response getGraficoQualitativo(String matricula, String usuario,
			String setor, String codErro, String equipe, String dataInicio,
			String dataFim) throws Exception {
	

		SQL sql = null;
		String query = "";
		try {
			sql =  new SQL("DatabaseCustom");
			try {
				query =  "SELECT COUNT(cep.codigo_erro) AS quantidadeDoErro, "
						+ "cep.codigo_erro AS codigoDoErro,cepErro.descricao AS nomeDoErro  "
						+ "FROM cep_registros_erros AS cep "
						+ "INNER JOIN cep_colaborador_setor_equipe AS cepc "
						+ "ON cepc.user_code=cep.matricula_colaborador AND cepc.setor = cep.codigo_setor "
						+ "INNER join cep_erro AS cepErro ON cepErro.id=cep.codigo_erro "
						
						+"INNER JOIN cep_setor_erro AS cepse ON "
						+" cepse.setor=cep.codigo_setor AND cepse.erro=cep.codigo_erro AND cepse.ativo=1 "
						
						+ "WHERE cep.houve_erro='sim' ";

				if(!matricula.equals("")){
					query =  query+" AND cep.matricula_colaborador='"+matricula+"' ";
				}
				if(!usuario.equals("")){
					query =  query+" AND cepc.colaborador='"+usuario+"' ";
				}
				if(!setor.equals("")){
					query =  query+" AND cep.codigo_setor='"+setor+"' ";
				}
				if(!codErro.equals("")){
					query =  query+" AND cep.codigo_erro='"+codErro+"' ";
				}
				if(!equipe.equals("")){
					query =  query+" AND cepc.equipe='"+equipe+"' ";
				}
				if(!equipe.equals("")){
					query =  query+" AND cepc.equipe='"+equipe+"' ";
				}
				if(!dataInicio.equals("") && !dataFim.equals("")){
					query=query+" AND cep.data_erro BETWEEN '"+dataInicio+"' AND '"+dataFim+"' ";
				}
				
				query = query +" GROUP BY  cep.codigo_erro ,cepErro.descricao;";
								
				System.out.println("getGraficoQualitativo: "+query);
				ResultSet resultSet = sql.Select(query); 
				try {
					Util util = new Util(); 
					return  Retorno.Sucesso(util.ResultSetToArrayList(resultSet));
				} catch (Exception e) {
					System.out.println("getGraficoQualitativo ResultSet ERRO: "+e); 
					Retorno.Erro(e);
				}finally{
					resultSet.close();
				}
			} catch (Exception e) {
				System.out.println("getGraficoQualitativo SQL ERRO: "+e);
				Retorno.Erro(e);
			}finally{
				sql.Close();
				
			}
		} catch (Exception e) {
			 System.out.println("getGraficoQualitativo ERRO ao instanciar a classe SQL"+e);
			 Retorno.Erro(e);
		} finally{
			if(sql.getConexao().isClosed()==false){
				sql.Close();
			}
		}
		return null; 
	} 
	
	//
	public String GetSetorDescricao(String setor) throws Exception{
		SQL sql = null;
		try {
			sql =  new SQL("DatabaseCustom");
			String query = "SELECT descricao FROM cep_setores WHERE id  = " + setor + " AND ativo = 1";
			try (ResultSet resultSet = sql.Select(query)) {

				if(resultSet.next()) {
					
					return resultSet.getString("descricao");
				}else {
					
					return "-";
				}	
				
			}	
			
		} catch (Exception e) {
			return e.getMessage();
		}finally{
			
			if(sql.getConexao().isClosed()==false){
				sql.Close();
			}
		} 
	}
	public String GetEquipeDescricao(String equipe) throws Exception{
		SQL sql = null;
		try {
			sql =  new SQL("DatabaseCustom");
			String query = "SELECT descricao FROM cep_equipe WHERE id  = " + equipe + " AND ativo = 1";
			try (ResultSet resultSet = sql.Select(query)) {
				
				if(resultSet.next()) {
					
					return resultSet.getString("descricao");
					
				}else {
					
					return "-";
				}
				
			}	
			
		} catch (Exception e) {
			
			return e.getMessage();
			
		}finally{
			
			if(sql.getConexao().isClosed()==false){
				sql.Close();
			}
		} 
	}
	
	
	
}
