function Web(fluig) {

    this.fluig = fluig;

}

/**
 * Apresenta em tela o erro ao usuário. O objeto FLUIGC será utilizado caso disponível
 * @param {String} titulo 
 * @param {String} mensagem 
 */
function ShowToastAlert(titulo, mensagem, tipo, tempo) {

    if (typeof FLUIGC != "undefined") {
        FLUIGC.toast({ title: titulo, message: mensagem, type: tipo, timeout: tempo });
    }
    else {
        alert(titulo + " " + mensagem);
    }
}


Web.prototype.SetSetor = function (descricao, ativo, callback) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Setores/SetSetor?descricao=" + descricao + "&ativo=" + ativo ,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;
                
            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao adicionar setor:", res.responseText, "danger");

        }

    });

    return retorno;

}



Web.prototype.GetSetor = function(ativo,callback){
	$.ajax({ 
	type:"GET",
	contentType: "aplication/json",	
	url: this.fluig.serverURL + "/monditech/api/rest/Setores/GetSetor?ativo=" + ativo+"",
	async:  false,
			data: {},
	        success: function (res, status, xhr) {
	           
	            if (callback) {
 
	                callback(res);

	            }
	            else {

	                retorno = res;
	            }
	            
	        },
	        error: function (res, textStatus, errorThrown) {

	            ShowToastAlert("Erro ao carregar os Setores!:", res.responseText, "danger");

	        }
		
	});
}
Web.prototype.GetSolicitacoesRetorno = function(inicioPeriodo, fimPeriodo, tipoSolicitacao, tipoAtendimento,callback){
	$.ajax({ 
	type:"GET",
	contentType: "aplication/json",	
	url: this.fluig.serverURL + "/monditech/api/rest/GetSolicitacoesRetorno?inicioPeriodo=" + inicioPeriodo+ "&fimPeriodo=" + fimPeriodo + "&tipoSolicitacao=" + tipoSolicitacao + "&tipoAtendimento" + tipoAtendimento,
	async: callback ? true : false,
			data: {},
	        success: function (res, status, xhr) {
	           
	            if (callback) {
 
	                callback(res);

	            }
	            else {

	                retorno = res;
	            }
	            
	        },
	        error: function (res, textStatus, errorThrown) {

	            ShowToastAlert("Erro ao carregar os Setores!:", res.responseText, "danger");

	        }
		
	});
}

Web.prototype.GetSetorPorUsuario = function(matricula,callback){
	
	$.ajax({ 
	type:"GET",
	contentType: "aplication/json",	
	url: this.fluig.serverURL + "/monditech/api/rest/Setores/GetSetorPorUsuario?matricula=" + matricula+"",
	async:   false,
			data: {},
	        success: function (res, status, xhr) {
	           
	            if (callback) {
 
	                callback(res);

	            }
	            else {

	                retorno = res;
	            }
	            
	        },
	        error: function (res, textStatus, errorThrown) {

	            ShowToastAlert("Erro ao carregar os Setores!:", res.responseText, "danger");

	        }
		
	});
}

Web.prototype.GetEquipeDisponivel = function(id,callback){
	var retorno;
	$.ajax({ 
	type:"GET",
	contentType: "aplication/json",	
	url: this.fluig.serverURL + "/monditech/api/rest/Equipes/GetEquipeDisponivel?setor=" + id,
	async: callback ? true : false,
			data: {},
	        success: function (res, status, xhr) {
	           
	            if (callback) {
 
	                callback(res);

	            }
	            else {

	                retorno = res;
	            } 
	            
	        },
	        error: function (res, textStatus, errorThrown) {

	            ShowToastAlert("Erro ao carregar os Setores!:", res.responseText, "danger");

	        }
		
	});
	return retorno;
}

Web.prototype.GetSetorId = function(id,callback, inverter){
	var retorno;
	$.ajax({ 
	type:"GET",
	contentType: "aplication/json",	
	url: this.fluig.serverURL + "/monditech/api/rest/Setores/GetSetorId?id=" + id+ "&inverter=" + (typeof inverter === "undefined" || inverter == false  ? "false" : "true"),
	async: callback ? true : false,
			data: {},
	        success: function (res, status, xhr) {
	           
	            if (callback) {
 
	                callback(res);

	            }
	            else {

	                retorno = res;
	            } 
	            
	        },
	        error: function (res, textStatus, errorThrown) {

	            ShowToastAlert("Erro ao carregar os Setores!:", res.responseText, "danger");

	        }
		
	});
	return retorno;
}




/*
Web.prototype.GetSetor = function (descricao,ativo,callback) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Setores/GetSetor?descricao=" + descricao + "&ativo=" + ativo ,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar setores:", res.responseText, "danger");

        }

    });

    return retorno;

}
*/

/**
 * Cria uma nova solicitação de ouvidoria interna
 */
Web.prototype.StartProcess = function (ouvidoria, callback) {

    $.ajax({

        type: "PUT",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/AberturaSolicitacao/StartProcessOuvidoriaInterna",
        async: true,
        data: JSON.stringify(ouvidoria),
        success: function (res, status, xhr) {

            callback(res, true);

        },
        error: function (res, textStatus, errorThrown) {

            callback(res, false);

        }

    });

}
Web.prototype.EditarSetor = function (descricao, ativo, id, callback) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Setores/EditarSetor?descricao=" + descricao + "&ativo=" + ativo + "&id=" + id,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {
        	
            if (callback) {
            	
                callback(true);

            }
            else {

                retorno = true;
                
            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao editar o setor:", res.responseText, "danger");
            retorno = false;

        }

    });

    return retorno;
} 






Web.prototype.GetEquipes = function(ativo,callback){
	$.ajax({ 
	type:"GET",
	contentType: "aplication/json",	
	url: this.fluig.serverURL + "/monditech/api/rest/Equipes/GetEquipes?ativo=" + ativo+"",
	async: callback ? true : false,
			data: {},
	        success: function (res, status, xhr) {
	           
	            if (callback) {

	                callback(res);

	            }
	            else {

	                retorno = res;
	            }
	        },
	        error: function (res, textStatus, errorThrown) {

	            ShowToastAlert("Erro ao carregar os Equipes!:", res.responseText, "danger");

	        }
		
	});
}

Web.prototype.GetEquipesPorUsuario = function(matricula,callback){
	$.ajax({ 
		type:"GET",
		contentType: "aplication/json",	
		url: this.fluig.serverURL + "/monditech/api/rest/Equipes/GetEquipesPorUsuario?matricula=" + matricula,
		async: callback ? true : false,
				data: {},
		        success: function (res, status, xhr) {
		           
		            if (callback) {

		                callback(res);

		            }
		            else {

		                retorno = res;
		            }
		        },
		        error: function (res, textStatus, errorThrown) {

		            ShowToastAlert("Erro ao carregar as equipes do usuario: " + matricula + " ! Erro:", res.responseText, "danger");

		        }
			
	});
}

Web.prototype.GetEquipesPorSetor = function(setor,callback, inverter){
	$.ajax({
	type:"GET",
	contentType: "aplication/json",	
	url: this.fluig.serverURL + "/monditech/api/rest/Equipes/GetEquipesPorSetor?setor=" + setor,
	async:  false,
			data: {},
	        success: function (res, status, xhr) {
	           
	            if (callback) {

	                callback(res);

	            }
	            else {

	                retorno = res;
	            }
	        },
	        error: function (res, textStatus, errorThrown) {

	            ShowToastAlert("Erro ao carregar os Equipes!:", res.responseText, "danger");

	        }
		
	});
}


Web.prototype.FiltrarResultadosSetorEquipe = function(setor, equipe, callback){
	
	$.ajax({
		
		type:"GET",
		contentType: "aplication/json",	
		url: this.fluig.serverURL + "/monditech/api/rest/Equipes/FiltrarResultadosSetorEquipe?setor=" + setor+"&equipe=" + equipe,
		async: callback ? true : false,
		data: {},
        success: function (res, status, xhr) {
           
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;
            }
        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao carregar os Equipes!:", res.responseText, "danger");

        }
		
	});
	
}


Web.prototype.GetColaboradorEquipeSetor = function(matricula,setor,equipe,unidade, callback){
	 var retorno = new Array();
	 this.colEqpSet = new Object();
	$.ajax({
	type:"GET",
	contentType: "aplication/json",	
	url: this.fluig.serverURL + "/monditech/api/rest/ColaboradorEquipeSetor/GetColaboradorEquipeSetor?matricula="+matricula+"&setor=" + setor+"&equipe="+equipe+ "&unidade=" + unidade + "",
	async: callback ? true : false,
			data: {},
	        success: function (res, status, xhr) {
	           
	            if (callback) {

	                callback(res);

	            }
	            else {

	            	retorno = res;
	                
	            }
	        },
	        error: function (res, textStatus, errorThrown) {

	            ShowToastAlert("Erro ao carregar os colaboradores!:", res.responseText, "danger");

	        }
		
	});
	
	  this.colEqpSet = retorno;
	
	
}





Web.prototype.NewColaboradorEquipeSetor = function (matricula,setor,equipe,unidade,colaborador, callback) {

    
    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/ColaboradorEquipeSetor/SetColaboradorEquipeSetor?matricula="+matricula+"&setor=" + setor+"&equipe="+equipe+"&unidade="+unidade+"&colaborador="+colaborador,
        async: true,
        data: {},
        success: function (res, status, xhr) {
        	ShowToastAlert("Vinculo salvo com sucesso!", "", "success");        	
            callback(res,true);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao salvar vinculo:", res.responseText, "danger");
            callback(res,false);
            
           

        }

    });

    

}

Web.prototype.updateColaboradorEquipeSetor = function (matricula,setor,equipe,callback) {

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/ColaboradorEquipeSetor/updateColaboradorEquipeSetor?matricula="+matricula+"&setor=" + setor+"&equipe="+equipe+"",
        async: true,
        data: {},
        success: function (res, status, xhr) {
        	ShowToastAlert("Vinculo alterado com sucesso!", "", "success");
        	
            callback(res,true);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao alterar vinculo:", res.responseText, "danger");
            callback(res, false);
  
        }
    });
}

Web.prototype.deleteColaboradorEquipeSetor = function (matricula,callback) {

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/ColaboradorEquipeSetor/deleteColaboradorEquipeSetor?matricula="+matricula +"",
        async: true,
        data: {},
        success: function (res, status, xhr) {
        	ShowToastAlert("Vinculo deletado com sucesso!", "", "success");
        	
            callback(res,true);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao deletar vinculo:", res.responseText, "danger");
            callback(res, false);
  
        }
    });
}

/**
 * Busca os usuários online
 */
Web.prototype.GetUsuariosOnlineAsync = function (callback, countError) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetUsuariosOnline",
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res, false);

        },
        error: function (res, textStatus, errorThrown) {

            //if (countError == 0) { ShowToastAlert("Erro ao buscar usuários online:", res.responseText, "danger"); }

            callback(res, true);            

        }

    });

    return retorno;

}

/**
 * Busca os usuários online
 */
Web.prototype.GetUsuariosOnline = function () {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetUsuariosOnline",
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar usuários online:", res.responseText, "danger");

        }

    });

    return retorno;

}

/**
 * Seta o Prazo das ocorrencias e etapas de validação gerencia e supervisao do processo Registro Ponto
 */
Web.prototype.GetPrazoOcorrenciaRegistroPonto = function (tipo, valor, data, dataAtual) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetPrazoOcorrenciaRegistroPonto?tipo=" + tipo + "&valor=" + valor + "&data=" + data + "&dataAtual=" + dataAtual,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao setar prazo:", res.responseText, "danger");

        }

    });

    return retorno;

}

/**
 * Seta o Prazo pradrão ou alterado
 */
Web.prototype.SetPrazo = function (tipo, valor) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/SetPrazo?tipo=" + tipo + "&valor=" + valor,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar prazo:", res.responseText, "danger");

        }

    });

    return retorno;

}


/**
 * Busca os assuntos da ouvidoria
 */
Web.prototype.GetAssuntos = function (assunto, ativo) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/GetAssuntos?assunto=" + assunto + "&ativo=" + ativo,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar assuntos:", res.responseText, "danger");

        }

    });

    return retorno;

}


/**
 * Cadastra os assuntos da ouvidoria
 */
Web.prototype.CadastrarAssuntos = function (assunto, ativo) {

    var sucesso;

    $.ajax({

        type: "PUT",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/CadastrarAssuntos?assunto=" + assunto + "&ativo=" + ativo,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            ShowToastAlert("Assunto cadastrado com sucesso", "", "success");
            sucesso = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao cadastrar o assunto:", res.responseText, "danger");
            sucesso = false;

        }

    });

    return sucesso;

}


/**
 * Editar os assuntos da ouvidoria
 */
Web.prototype.EditarAssuntos = function (assunto, ativo, id) {

    var sucesso;

    $.ajax({

        type: "PUT",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/EditarAssuntos?assunto=" + assunto + "&ativo=" + ativo + "&id=" + id,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            ShowToastAlert("Assunto editado com sucesso", "", "success");
            sucesso = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao editar o assunto:", res.responseText, "danger");
            sucesso = false;

        }

    });

    return sucesso;

}


/**
* Busca usuários ativos do Fluig
*/
Web.prototype.GetUsariosAtivos = function () {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/api/public/2.0/users/list/ACTIVE",
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar usuários:", res.responseText, "danger");

        }

    });

    return retorno;

}



/**
 * Busca todos os usuários do Fluig
 */
Web.prototype.GetTodosUsuarios = function () {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/api/public/2.0/users/listAll?limit=146",
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res["content"];

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar usuários:", res.responseText, "danger");

        }

    });

    return retorno;

}
/**
 * Retorna somente as tarefas com thread 0
 */
Web.prototype.GetTarefaAtrasada = function (numeroSolicitacao) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetTarefaAtrasada?numeroSolicitacao=" + numeroSolicitacao,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar tarefa atrasada:", res.responseText, "danger");

        }

    });

    return retorno;

}



/**
 * Busca usuários ativos do Fluig
 */
Web.prototype.GetUsuariosAtivos = function () {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/GetUsuariosAtivos",
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar usuários:", res.responseText, "danger");

        }

    });

    return retorno;

}

/**
 * Busca usuário em específico pelo login
 */
Web.prototype.GetUsuario = function (usuario, callback) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/api/public/2.0/users/getUser/" + usuario,
        async: (callback ? true : false),
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res["content"]);

            }
            else {

                retorno = res["content"];

            }

        },
        error: function (res, textStatus, errorThrown) {

            if (res.responseText == "java.lang.NullPointerException") {

                ShowToastAlert("", "Usuário <b>inválido</b>.", "danger");

            }
            else if (res.status == 500) {

                ShowToastAlert("", "Usuário <b>inválido</b>.", "danger");
            }
            else {

                ShowToastAlert("", "Erro ao buscar usuário.", "danger");

            }

            if (callback) {

                callback(null);

            }

        }

    });

    return retorno;

}

/**
 * Busca informações na tabela parametros
 */ 
Web.prototype.GetParametros = function (parametro) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetParametros?parametro=" + parametro,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            retorno = res.responseText;

        }

    });

    return retorno;

}

/**
 * Busca data de inicio de um fluig
 */
Web.prototype.DataInicioFluig = function (fluig) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/DataInicioFluig?fluig=" + fluig,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            retorno = res.responseText;

        }

    });

    return retorno;

}

/**
 * Verifica se o usuário está orientando
 */
Web.prototype.IsOrienting = function (matricula, i, callback, callbackaux, last) {

    $.ajax({

        type: "GET",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/IsOrienting?matricula=" + matricula,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res, i, false);

        },
        error: function (res, textStatus, errorThrown) {

            callback(null, null, true);

            //comentado para não aparecer quando deslogar por tempo de inatividade
            //ShowToastAlert("Erro ao verificar se usuário está orientando: ", res.responseText, "danger");

        },
        complete: function () {

            if (last == true) {
                callbackaux();
            }

        }

    });

}


Web.prototype.GetGrupos = function (callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetGrupos",
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            callback(null);
            ShowToastAlert("Erro ao buscar grupos:", res.responseText, "danger");

        }

    });

}

/**
 * Buscar dados pacientes
 */
Web.prototype.GetPacientes = function (search, pagina, qtdPagina, callback) {

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Zoom/ZoomConsultarPacientes?search=" + search + "&pagina=" + pagina + "&qtdPagina=" + qtdPagina,
        async: true,
        data: {}, 
        success: function (res, status, xhr) {
            callback(res);
        },
        error: function (res, textStatus, errorThrown) {
            ShowToastAlert("Erro ao buscar listagem de pacientes:", res.responseText, "danger");
            callback(null);
        }
    }); 
}

/**
 * Buscar listagem de exames
 */
Web.prototype.GetExames = function (search, pagina, qtdPagina, callback) {

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Zoom/ZoomConsultarExames?search=" + search + "&pagina=" + pagina + "&qtdPagina=" + qtdPagina,
        async: true,
        data: {},
        success: function (res, status, xhr) {
            callback(res);
        },
        error: function (res, textStatus, errorThrown) {
            ShowToastAlert("Erro ao buscar listagem de exames:", res.responseText, "danger");
            callback(null);
        }
    });
}

/**
 * Buscar listagem de exames do paciente
 */



Web.prototype.GetExamesPaciente = function (search, pagina, qtdPagina, callback) {

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Zoom/ZoomConsultarExamesPaciente?search=" + search + "&pagina=" + pagina + "&qtdPagina=" + qtdPagina,
        async: true,
        data: {},
        success: function (res, status, xhr) {
            callback(res);
        },
        error: function (res, textStatus, errorThrown) {
            ShowToastAlert("Erro ao buscar listagem de exames do paciente:", res.responseText, "danger");
            callback(null);
        }
    });
}

/**
 * Buscar count de exames do paciente
 */
Web.prototype.GetCountExamesPaciente = function (search, callback) {

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Zoom/CountConsultarExamesPaciente?search=" + search,
        async: true,
        data: {},
        success: function (res, status, xhr) {
            callback(res);
        },
        error: function (res, textStatus, errorThrown) {
            ShowToastAlert("Erro ao buscar count de exames do paciente:", res.responseText, "danger");
            callback(null);
        }
    });
}

/**
 * Busca o cartão ponto do funcionário
 */
Web.prototype.GetCartaoPonto = function (callback, dataInicio, dataFim) {

    var retorno;

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/CartaoPonto/GetPonto?matricula=" + this.fluig.userCode + "&dataInicio=" + dataInicio + "&dataFim=" + dataFim,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
            
        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar o ponto do colaborador:", res.responseText, "danger");

            if (callback) {

                callback(null);

            }
            else {

                retorno = res;

            }

        }

    });

    return retorno;

}





Web.prototype.GetNotasADP = function (callback, setor) {

    var retorno;

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/NotasAdp/GetNotasADP?setorVinculado=" + setor,
        async:  false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
            
        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar as notas de ADP:", res.responseText, "danger");

            if (callback) {

                callback(null);

            }
            else {

                retorno = res;

            }

        }

    });

    return retorno;

}





/**
 * Busca uma solicitação de registro ponto
 */
Web.prototype.AbreRegistroPonto = function (callback, solicitacao) {

    var retorno;

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/CartaoPonto/AbreRegistroPonto?tipoOcorrencia=" + solicitacao.tipoOcorrencia + 
                                                                                        "&matricula=" + solicitacao.matricula +
                                                                                        "&nomeColaborador=" + solicitacao.nomeColaborador +
                                                                                        "&dataOcorrencia=" + solicitacao.dataOcorrencia +
                                                                                        "&horarioOcorrencia=" + solicitacao.horarioOcorrencia +
                                                                                        "&horaInicialOcorrencia=" + solicitacao.horaInicialOcorrencia +
                                                                                        "&horaFinalOcorrencia=" + solicitacao.horaFinalOcorrencia +
                                                                                        "&justificativaColaborador=" + solicitacao.justificativaColaborador +
                                                                                        "&gestor=" + solicitacao.gestor +
                                                                                        "&ehPapelRh=" + solicitacao.ehPapelRh,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res); 

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao abrir uma correção de ponto:", res.responseText, "danger");

            if (callback) {

                callback(null);

            }
            else {

                retorno = res;

            }

        }

    });

    return retorno;

}


Web.prototype.GetErrosSetorPeso = function(erroSetorPeso, callback){
	var retorno;
	$.ajax({ 
		type:"GET",
		contentType: "aplication/json", 
		url: this.fluig.serverURL + "/monditech/api/rest/ErroSetorPeso/GetErroSetorPeso?idErro=" + erroSetorPeso.idErro +
											"&pesoSetorNovo=" +erroSetorPeso.pesoSetorNovo +
											"&idSetor=" +erroSetorPeso.idSetor,
		async: callback ? true : false,
				data: {},
		        success: function (res, status, xhr) {
		           
		            if (callback) {

		                callback(res);

		            }
		            else {

		                retorno = res;
		            }
		        },
		        error: function (res, textStatus, errorThrown) {

		            ShowToastAlert("Erro ao carregar os Registros!:", res.responseText, "danger");

		        }
		
	});
	return retorno;
}


Web.prototype.DeletarErroSetorPeso = function (erroSetorPeso) {

    var retorno; 

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/ErroSetorPeso/DeletarErroSetorPeso?idErro=" + erroSetorPeso.idErro + 
        																			"&pesoSetor=" + erroSetorPeso.pesoSetor + 
        																			"&idSetor=" + erroSetorPeso.idSetor
        ,
        async: false,
        data: {},
        success: function (res, status, xhr) {
        	ShowToastAlert("Relação de Erro com Setor deletada com sucesso!", "", "success");
            retorno = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao deletar relação de erro com setor para atribuição de peso ", res.responseText, "danger");
            retorno = false;
           

        }

    });

    return retorno;

}

Web.prototype.AtualizarErroSetorPeso = function (erroSetorPeso) {

 

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/ErroSetorPeso/AtualizarErroSetorPeso?id=" + erroSetorPeso.id + 
        																			"&setor=" + erroSetorPeso.setor + 
        																			"&erro=" + erroSetorPeso.erro +
        																			"&peso=" + erroSetorPeso.peso+
        																			"&ativo="+erroSetorPeso.ativo+
        																			"&dataVigencia="+erroSetorPeso.dataVigencia
        ,
        async: false,
        data: {},
        success: function (res, status, xhr) {
        	ShowToastAlert("Relação de Erro com Setor atualizada com sucesso!", "", "success");
            retorno = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao atualizar relação de erro com setor para atribuição de peso ", res.responseText, "danger");
            retorno = false;
           

        }

    });

    return retorno;

}


Web.prototype.CriarErroSetorPeso = function (erroSetorPeso) {

 
	
    var retorno; 
    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/ErroSetorPeso/CriarErroSetorPeso?idErro=" + erroSetorPeso.idErro + 
        																			"&pesoSetorNovo=" + erroSetorPeso.pesoSetorNovo + 
        																			"&idSetor=" + erroSetorPeso.idSetor+
        																			"&dataVigencia="+erroSetorPeso.dataVigencia
        ,
        async: false,
        data: {},
        success: function (res, status, xhr) {
        	ShowToastAlert("Relação de Erro com Setor para Atribuição de Peso salva!", "", "success");
            retorno = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao salvar relação de erro com eetor para atribuição de peso ", res.responseText, "danger");
            retorno = false;
           

        }

    });

    return retorno;

}

/**
 * Busca se o usuário é paticipante do grupo 
 */

Web.prototype.VerificaUsuarioGrupo = function (grupo, usuario) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/api/public/2.0/groups/containsUser/" + grupo + "/" + usuario + "",
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res["content"];

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao verificar se o usuário é participante do grupo:", res.responseText, "danger");

        }

    });

    return retorno;

}




Web.prototype.CriarEquipe = function (equipe) {

    var retorno; 

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Equipes/CriarEquipe?nomeEquipe=" + equipe.nome,
        async: false,
        data: {},
        success: function (res, status, xhr) {
        	ShowToastAlert("Equipe salva!", "", "success");
            retorno = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao salvar equipe: ", res.responseText, "danger");
            retorno = false;
           

        }

    });

    return retorno;

}

Web.prototype.EditarEquipe = function (equipe) {

    var retorno;

    $.ajax({
        type: "GET",
        contentType: "aplication/json", 
        url: this.fluig.serverURL + "/monditech/api/rest/Equipes/EditarEquipe?nomeEquipe=" +equipe.nomeEquipe +
        																				"&ativo=" + equipe.ativo + 
                                                                                        "&id=" + equipe.id,
        async: false,
        data: {},
        success: function (res, status, xhr) {
        	if (equipe.ativo == 1)
        		ShowToastAlert("Equipe editada!", "",   "success");
        	else
        		ShowToastAlert("Equipe editada!!", "",   "success");
            retorno = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao editar ADP:", res.responseText, "danger");
            retorno = false;
           

        }

    });

    return retorno;

}


Web.prototype.CriarNotaAdp = function (nota) {

    var retorno;

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/NotasAdp/CriarNotaAdp?setorVinculado=" + nota.setorVinculado + 
                                                                                        "&inicioVigencia=" + nota.inicioVigencia +
                                                                                        "&fimVigencia=" + nota.fimVigencia +
                                                                                        "&pontuacaoAceitavel=" + nota.pontuacaoAceitavel +
                                                                                        "&pontuacaoMedia=" + nota.pontuacaoMedia +
                                                                                        "&notaMedia=" + nota.notaMedia,
        async: false,
        data: {},
        success: function (res, status, xhr) {
        	ShowToastAlert("Nota de adp salva!", "", "success");
            retorno = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao salvar a Nota ADP:", res.responseText, "danger");
            retorno = false;
           

        }

    });

    return retorno;

}


/**
 * Busca informações de todos os usuários que estão um determinado grupo
 */

Web.prototype.GetUsariosGrupo = function (grupo) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/api/public/2.0/groups/listUsersByGroup/" + grupo + "",
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res["content"];

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao usuários do grupo:", res.responseText, "danger");

        }

    });

    return retorno;

}



Web.prototype.EditarNotaAdp = function (nota,callback) {

    var retorno;

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/NotasAdp/EditarNotaAdp?id=" +nota.id +
        																				"&setorVinculado=" + nota.setorVinculado + 
                                                                                        "&inicioVigencia=" + nota.inicioVigencia +
                                                                                        "&fimVigencia=" + nota.fimVigencia +
                                                                                        "&pontuacaoAceitavel=" + nota.pontuacaoAceitavel +
                                                                                        "&pontuacaoMedia=" + nota.pontuacaoMedia +
                                                                                        "&notaMedia=" + nota.notaMedia +
                                                                                        "&ativo=" + nota.ativo,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res, i);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao editar dados: ", res.responseText, "danger");

            if (callback) {

                callback(null);

            }
            else {

                retorno = res;

            }

        }

    });

    return retorno;

}






/**
 * Busca a data da úlltima atualização do cartão ponto
 */
Web.prototype.GetSolicitacaoCriada = function (callback, dataOcorrencia, i) {

    var retorno;

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/CartaoPonto/GetSolicitacaoCriada?matricula=" + this.fluig.userCode + "&dataOcorrencia=" + dataOcorrencia,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res, i);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar a solicitação criada do colaborador:", res.responseText, "danger");

            if (callback) {

                callback(null);

            }
            else {

                retorno = res;

            }

        }

    });

    return retorno;

}
/**
 * Cadastra a hierarquia de supervisores
 */
Web.prototype.CadastrarHierarquiaSupervisor = function (matriculaUsuario, loginUsuario, nomeUsuario, matriculaSupervisor, loginSupervisor, nomeSupervisor) {

    var sucesso;

    $.ajax({

        type: "PUT",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/CadastrarHierarquiaSupervisor?matriculaUsuario=" + matriculaUsuario + "&loginUsuario=" + loginUsuario + "&nomeUsuario=" + nomeUsuario + "&matriculaSupervisor=" + matriculaSupervisor + "&loginSupervisor=" + loginSupervisor + "&nomeSupervisor=" + nomeSupervisor,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            ShowToastAlert("Relação cadastrada com sucesso", "", "success");
            sucesso = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao cadastrar a relação:", res.responseText, "danger");
            sucesso = false;

        }

    });

    return sucesso;

}

/**
 * Busca a data da úlltima atualização do cartão ponto
 */
Web.prototype.GetUltimaAtualizacao = function (callback) {

    var retorno;

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/CartaoPonto/GetUltimaAtualizacao?matricula=" + this.fluig.userCode,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar a ultima atualização do ponto:", res.responseText, "danger");

            if (callback) {

                callback(null);

            }
            else {

                retorno = res;

            }

        }

    });

    return retorno;

}

/**
 * Busca os hierarquia supervisor
 */
Web.prototype.GetHierarquiaSupervisor = function (usuario, supervisor) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/GetHierarquiaSupervisor?usuario=" + usuario + "&supervisor=" + supervisor,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar Relação:", res.responseText, "danger");

        }

    });

    return retorno;

}

/**
 * Busca a as informações do funcionário
 */
Web.prototype.GetDadosFuncionario = function (callback) {

    var retorno;

    $.ajax({
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/CartaoPonto/GetDadosFuncionario?matricula=" + this.fluig.userCode,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar os dados do funcionário:", res.responseText, "danger");

            if (callback) {

                callback(null);

            }
            else {

                retorno = res;

            }

        }

    });

    return retorno;

}
Web.prototype.GetColaboradoresSetoresEquipes = function (usuario) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetColaboradoresSetoresEquipes?usuario=" + usuario,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;
            

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar grupos:", res.responseText, "danger");

        }

    });

    return retorno;

}

/**
 * Editar Hierarquia Supervisor da ouvidoria
 */
Web.prototype.EditarHierarquiaSupervisor = function (supervisor, nomeSupervisor, id, matriculaSupervisor) {

    var sucesso;

    $.ajax({

        type: "PUT",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/EditarHierarquiaSupervisor?supervisor=" + supervisor + "&nomeSupervisor=" + nomeSupervisor + "&id=" + id + "&matriculaSupervisor=" + matriculaSupervisor,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            ShowToastAlert("Relação editada com sucesso", "", "success");
            sucesso = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao editar Relação:", res.responseText, "danger");
            sucesso = false;

        }

    });

    return sucesso;

}

Web.prototype.GetGrupoDeTrabalho = function (usuario) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetGrupoDeTrabalho?usuario=" + usuario,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;
            

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar grupos:", res.responseText, "danger");

        }

    });

    return retorno;

}

/**
 * Busca os Surpevisores
 */
Web.prototype.GetSupervisores = function (supervisor) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/GetSupervisores?supervisor=" + supervisor,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar Supervisores:", res.responseText, "danger");

        }

    });

    return retorno;

}

Web.prototype.GetGrupoDeTrabalho = function (usuario) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetGrupoDeTrabalho?usuario=" + usuario,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar grupos:", res.responseText, "danger");

        }

    });

    return retorno;

}


/**
 * Busca os Usuários Hierarquia
 */
Web.prototype.GetUsuariosHierarquia = function (usuario) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/GetUsuariosHierarquia?usuario=" + usuario,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar Usuários:", res.responseText, "danger");

        }

    });

    return retorno;

}

Web.prototype.GetDetalhamentoUsuario = function (callback, matricula) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetDetalhamentoUsuario?matricula=" + matricula,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

        	 if (callback) {

                 callback(res);

             }
             else {

                 retorno = res;

             }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar detalhes usuarios:", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}

/**
 * Verifica se a iraquia que está sendo inserida já existe
 */
Web.prototype.VerificaHierarquia = function (loginUsuario, nomeUsuario, loginSupervisor, nomeSupervisor) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/VerificaHierarquia?loginUsuario=" + loginUsuario + "&nomeUsuario=" + nomeUsuario + "&loginSupervisor=" + loginSupervisor + "&nomeSupervisor=" + nomeSupervisor,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao Verificar relação:", res.responseText, "danger");

        }

    });

    return retorno;

}


Web.prototype.GetRelatorioAnalitico = function (callback, filtroErros) {

    var retorno;
   
    $.ajax({
    	 
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetRelatorioAnalitico?matricula="   + filtroErros.matricula +
        																	  "&usuario="    + filtroErros.usuario   + 
        																	  "&setor="      + filtroErros.setor     +
        																	  "&codErro="    + filtroErros.codErro   + 
        																	  "&equipe="     + filtroErros.equipe     + 
        																	  "&dataInicio=" + filtroErros.dataInicio +
        																	  "&dataFim="    + filtroErros.dataFim,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar relatorio:", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}


Web.prototype.GetErrosRegistros = function (callback, filtroErros) {

    var retorno;
   
    $.ajax({
    	 
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetErrosRegistros?matricula="   + filtroErros.matricula +
        																  "&usuario="    + filtroErros.usuario   + 
        																  "&setor="      + filtroErros.setor     +
        																  "&codErro="    + filtroErros.codErro   + 
        																  "&equipe="     + filtroErros.equipe     + 
        																  "&dataInicio=" + filtroErros.dataInicio +
        																  "&dataFim="    + filtroErros.dataFim,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar GetErrosRegistros :", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}

/**
 * Excluir a relação de hierarquia selecionada
 */
Web.prototype.ExcluirHierarquia = function (id) {

    var sucesso;

    $.ajax({

        type: "PUT",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/ExcluirHierarquia?id=" + id,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            ShowToastAlert("Relação excluida com sucesso", "", "success");
            sucesso = true;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao excluir Relação:", res.responseText, "danger");
            sucesso = false;

        }

    });

    return sucesso;

}

Web.prototype.GetCodProcesso = function (callback, codcolaborador, codpaciente) {

    var retorno;
   
    $.ajax({
    
        type: "GET",
        contentType: "aplication/json",
        
        
        url: this.fluig.serverURL + "/monditech/api/rest/GetCodProcesso?codColaborador=" + codcolaborador + 
        															   "&cdPaciente=" + codpaciente,
        																	
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar relatorio:", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}


Web.prototype.GetTotalAtendimentos = function (callback, codcolaborador) {

    var retorno;
   
    $.ajax({
    
        type: "GET",
        contentType: "aplication/json",
        
        
        url: this.fluig.serverURL + "/monditech/api/rest/GetTotalAtendimentos" + (codcolaborador != "" ? "?matricula=" + codcolaborador : ""), 
        																	
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar relatorio:", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}

Web.prototype.GetUsuarioMatricula = function (matricula, callback) {

    var retorno;
   
    $.ajax({
    
        type: "GET",
        contentType: "aplication/json",
        
        
        url: this.fluig.serverURL + "/monditech/api/rest/GetUsuarioMatricula?matricula=" + matricula, 
        																	
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar relatorio:", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });
    return retorno;

}

//Erros

Web.prototype.GetUnidades = function(callback){
	var retorno = "";
	$.ajax({ 
		type:"GET",
		contentType: "aplication/json",
		url: this.fluig.serverURL + "/monditech/api/rest/GetUnidades"  ,
		async: callback ? true : false,
				data: {},
		        success: function (res, status, xhr) {
		           
		            if (callback) {

		                callback(res);

		            }
		            else {

		                retorno = res;
		            }
		        },
		        error: function (res, textStatus, errorThrown) {
		        		
		            ShowToastAlert("Erro ao carregar os Unidades!:", res.responseText, "danger");

		        }
		
	});
	return retorno;
}



Web.prototype.GetErros = function(ativo,callback){
	$.ajax({ 
		type:"GET",
		contentType: "aplication/json",
		url: this.fluig.serverURL + "/monditech/api/rest/Erro/GetErros?ativo=" + ativo ,
		async: callback ? true : false,
				data: {},
		        success: function (res, status, xhr) {
		           
		            if (callback) {

		                callback(res);

		            }
		            else {

		                retorno = res;
		            }
		        },
		        error: function (res, textStatus, errorThrown) {
		        		
		            ShowToastAlert("Erro ao carregar os Registros!:", res.responseText, "danger");

		        }
		
	});
}




Web.prototype.NewErro = function (descricao,callback) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/text",
        url: this.fluig.serverURL + "/monditech/api/rest/Erro/SetErro?descricao="+descricao,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao adicionar Registro:", res.responseText, "danger");

        }
 
    });

    return retorno;

}

Web.prototype.UpdateErro = function(id, ativo){ 
	var retorno;
	$.ajax({
		type: "GET",
		contentType: "aplication/text",
		url: this.fluig.serverURL + "/monditech/api/rest/Erro/UpdateErro?id=" + id + "&ativo=" + ativo,
		async:  false,
		data: {},
		success: function(res, status, xhr){
				retorno = res;	
		},
		error: function(res, textStatus, errorThrown){
				
			ShowToastAlert("Erro ao alterar Registro", res.responseText, "danger")			
		}
	});
	return retorno;
	
} 


Web.prototype.NewSetorEquipe = function(setor, equipe, callback){
	
	var retorno;
	
	$.ajax({
		type: "GET",
		contentType: "plain/text",
        url: this.fluig.serverURL + "/monditech/api/rest/Equipes/NewSetorEquipe?setor="+setor+"&equipe="+ equipe,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

        	if (callback) {
        		
        		callback(res)
        		
        	} 
        	else {
        		
                retorno = res;
        		
        	}

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao adicionar Registro:", res.responseText, "danger");

        }
		
		
	});
	return retorno;
}

Web.prototype.ExcluirRelacaoSetorEquipe = function(setor, equipe, callback){
	
	var retorno;
	
	$.ajax({
		type: "GET",
		contentType: "plain/text",
        url: this.fluig.serverURL + "/monditech/api/rest/Equipes/ExcluirRelacaoSetorEquipe?setor="+setor+"&equipe="+ equipe,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

    	if (callback) {
    		
    		callback(res)
    		
    	} 
    	else {
    		
            retorno = res;
    		
    	}

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao adicionar Registro:", res.responseText, "danger");

        }
		
		
	});
	return retorno;
	
	
}


/**
 * Busca usuário em específico pelo login
 */
Web.prototype.GetUsuario = function (usuario) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/api/public/2.0/users/getUser/" + usuario,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res["content"];

        },
        error: function (res, textStatus, errorThrown) {

            if (res.responseText == "java.lang.NullPointerException") {

                ShowToastAlert("", "Usuário <b>inválido</b>.", "danger");

            }
            else if (res.status == 500){

                ShowToastAlert("", "Usuário <b>inválido</b>.", "danger");
            }
            else {

                ShowToastAlert("", "Erro ao buscar usuário.", "danger");

            }

        }

    });

    return retorno;

}


Web.prototype.VerificaCount = function (setor, equipe, callback){
	
var retorno;
	
	$.ajax({
		type: "GET",
		contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Equipes/VerificaCount?setor="+setor+"&equipe="+ equipe,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

    	if (callback) {
    		
    		callback(res)
    		
    	} 
    	else {
    		
            retorno = res;
    		
    	}

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao Verificar Registro:", res.responseText, "danger");

        }
		
		
	});
	return retorno;
	
	
}

 
Web.prototype.GetSetorEquipe = function (callback, filtroErros) {

    var retorno;
   
    $.ajax({
    	 
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetSetorEquipe?matricula="   + filtroErros.matricula +
        																  "&setor="      + filtroErros.setor     +
        																  "&codErro="    + filtroErros.codErro   + 
        																  "&equipe="     + filtroErros.equipe     + 
        																  "&dataInicio=" + filtroErros.dataInicio +
        																  "&dataFim="    + filtroErros.dataFim,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar GetSetorEquipe :", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}

Web.prototype.GetRegistrosEvolucao = function (callback, filtroErros) {

    var retorno;
   
    $.ajax({
    	 
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetRegistrosEvolucao?matricula="   + filtroErros.matricula +
        																  "&usuario="    + filtroErros.usuario   + 
        																  "&setor="      + filtroErros.setor     +
        																  "&codErro="    + filtroErros.codErro   + 
        																  "&equipe="     + filtroErros.equipe     + 
        																  "&dataInicio=" + filtroErros.dataInicio +
        																  "&dataFim="    + filtroErros.dataFim,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar GetErrosRegistros :", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}


Web.prototype.GetEvolucaoDoUsuario = function (callback, filtroErros) {

    var retorno;
   
    $.ajax({
    	 
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetEvolucaoDoUsuario?matricula="   + filtroErros.matricula +
        																  "&usuario="    + filtroErros.usuario   + 
        																  "&setor="      + filtroErros.setor     +
        																  "&codErro="    + filtroErros.codErro   + 
        																  "&equipe="     + filtroErros.equipe     + 
        																  "&dataInicio=" + filtroErros.dataInicio +
        																  "&dataFim="    + filtroErros.dataFim,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar GetErrosRegistros :", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}
/**
 * Busca líder maior 
 */
Web.prototype.GetLideresMaiores = function (usuario) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/GetLideresMaiores?usuario=" + usuario,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar Líderes Maiores:", res.responseText, "danger");

        }

    });

    return retorno;

}

/**
 * Busca líder menor 
 */
Web.prototype.GetLideresMenores = function (usuario) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/GetLideresMenores?usuario=" + usuario,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar Líderes Maiores:", res.responseText, "danger");

        }

    });

    return retorno;

}
 

//
Web.prototype.InsertNovaSolicitacao = function (callback, matricula, numProcess) {

    var retorno;
   
    $.ajax({
    	 
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/InsertNovaSolicitacao?matricula="   + matricula + "&numProcess="   + numProcess,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {
            if (callback) {

                callback(res);
            }
            else {
                retorno = res;
            }
        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao inserir registros :", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}

//
Web.prototype.GetSolicitacoesPorColab = function (callback, matricula) {

    var retorno;
   
    $.ajax({
    	 
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetSolicitacoesPorColab?matricula="   + matricula,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {
            if (callback) {

                callback(res);
            }
            else {
                retorno = res;
            }
        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar registros :", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}



/**
 * Usuario logado
 */
Web.prototype.GetUsuarioLogado = function () {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/api/public/ecm/document/loggedUser",
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res["content"];

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar Líderes Maiores:", res.responseText, "danger");

        }

    });

    return retorno;

}

/**
 * Busca Informações da solicitação de Ouvidoria Interna
 */
Web.prototype.GetSolicitacoesOuvidoriaInterna = function (inicioPeriodo, fimPeriodo, tipoClassificacao, assunto, tipoUnidade, tipoSituacao, tipoEtapa, gerente, liderMaior, liderMenor, marketing) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/GetSolicitacoesOuvidoriaInterna?inicioPeriodo=" + inicioPeriodo + "&fimPeriodo=" + fimPeriodo + "&tipoClassificacao="
                                  + tipoClassificacao + "&tipoClassificacao=" + tipoClassificacao + "&assunto=" + assunto + "&tipoUnidade=" + tipoUnidade + "&tipoSituacao=" + tipoSituacao + "&tipoEtapa=" + tipoEtapa
                                  + "&gerente=" + gerente + "&liderMaior=" + liderMaior + "&liderMenor=" + liderMenor + "&marketing=" + marketing, 
                             
                                  
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar Informações Ouvidoria Interna:", res.responseText, "danger");

        }

    });

    return retorno;

}




/**
 * Busca setores sendo os grupos
 */
Web.prototype.GetSetores = function () {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/GetSetores",
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            retorno = res.responseText;

        }

    });

    return retorno;

}


/**
 * Busca Grupos do Fluig na tabela parametros
 */
//metodo com  mesmo nome e utilidade diferente.. nome alterado para evitar conflito
//implementar a modificação nas widg e fluxo
Web.prototype.OuvidoriaGetParametros = function (parametro) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/GetParametros?parametro=" + parametro,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            retorno = res.responseText;

        }

    });

    return retorno;

}



/**
 * Verifica se usuário é líder maior 
 */
Web.prototype.VerificaUsuarioLiderMaior = function (usuario) {

    var retorno;

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/OuvidoriaInterna/VerificaUsuarioLiderMaior?usuario=" + usuario,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao Verificar Líder Maior:", res.responseText, "danger");

        }

    });

    return retorno;

}

Web.prototype.getGraficoQualitativo = function (callback, filtroErros) {

    var retorno;
   
    $.ajax({
    	 
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/getGraficoQualitativo?matricula="   + filtroErros.matricula +
        																  "&usuario="    + filtroErros.usuario   + 
        																  "&setor="      + filtroErros.setor     +
        																  "&codErro="    + filtroErros.codErro   + 
        																  "&equipe="     + filtroErros.equipe     + 
        																  "&dataInicio=" + filtroErros.dataInicio +
        																  "&dataFim="    + filtroErros.dataFim,
        async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar getGraficoQualitativo :", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}



Web.prototype.GetRegistros = function (callback, filtroErros) {

    var retorno;
   
    $.ajax({
    	 
        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetRegistros?matricula="   + filtroErros.matricula +
		  "&usuario="    + filtroErros.usuario   + 
		  "&setor="      + filtroErros.setor     +
		  "&codErro="    + filtroErros.codErro   + 
		  "&equipe="     + filtroErros.equipe     + 
		  "&dataInicio=" + filtroErros.dataInicio +
		  "&dataFim="    + filtroErros.dataFim,   
		  
		async: callback ? true : false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar GetRegistros :", res.responseText, "danger");
            
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }
        }

    });

    return retorno;

}

Web.prototype.GetErrosColaborador = function (callback, matricula) {
   var retorno = "";
   
	$.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetErrosColaborador?matricula=" + matricula,
        async: false,
        data: {},
        success: function (res, status, xhr) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {

            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        }

    });
}
//--
Web.prototype.GetSetorDescricao = function (callback, setor) {
	   var retorno = "";
	   
		$.ajax({

	        type: "GET",
	        contentType: "aplication/json",
	        url: this.fluig.serverURL + "/monditech/api/rest/GetSetorDescricao?setor=" + setor,
	        async: false,
	        data: {},
	        success: function (res, status, xhr) {
	            if (callback) {

	                callback(res);

	            }
	            else {

	                retorno = res;

	            }

	        },
	        error: function (res, textStatus, errorThrown) {
	        	
	            if (callback) {

	                callback(res);

	            }
	            else {

	                retorno = res;

	            }

	        }

	    });
		return retorno;
	}
Web.prototype.GetEquipeDescricao = function (callback, equipe) {
	 var retorno = "";	
	$.ajax({

	        type: "GET",
	        contentType: "aplication/json",
	        url: this.fluig.serverURL + "/monditech/api/rest/GetEquipeDescricao?equipe=" + equipe,
	        async: false,
	        data: {},
	        success: function (res, status, xhr) {

	            if (callback) {

	                callback(res);

	            }
	            else {

	                retorno = res;

	            }

	        },
	        error: function (res, textStatus, errorThrown) {

	        	  if (callback) {

		                callback(res);

		            }
		            else {

		                retorno = res;

		            }

	        }

	    });
		return retorno;
	}




//

/////////////
 
Web.prototype.GetDadosUriAnexo = function (callback, numProcess) {
	 var retorno = "";
	$.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/GetDadosUriAnexo?numProcess=" + numProcess,
        async: false,
        data: {},
        success: function (res, status, xhr) { 
            if (callback) {

                callback(res);

            }
            else {

                retorno = res;

            }

        },
        error: function (res, textStatus, errorThrown) {
        	ShowToastAlert("Erro ao buscar registros :", res.responseText, "danger");
        	  if (callback) {

	                callback(res);

	            }
	            else {

	                retorno = res;

	            }

        }

    });
	return retorno;
}
