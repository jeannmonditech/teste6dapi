var colaborador;
var search = "";
var pagina = 1;
var zoom;
var zoomWeb;
var web;

function OnLoad() {

    zoom = window.opener.zoom;
    zoomWeb = new ZoomWeb(window.opener.parent.WCMAPI);
    web = new Web(window.opener.parent.WCMAPI);

    if (zoom.search != false) {

        zoom.search = true;
    }


    BuscaColaboradores();

}

function Escolha(id) {

    if (window.opener && !window.opener.closed) {

        var campos = zoom.camposRetorno;

        window.opener.document.getElementById(campos[0]).value = colaborador[id].nome;
        window.opener.document.getElementById(campos[1]).value = colaborador[id].matricula;
        window.opener.document.getElementById(campos[2]).value = colaborador[id].setor;
        window.opener.document.getElementById(campos[3]).value = colaborador[id].unidade;
        if (campos[4] && campos[5] && campos[6]){ // If usado para zoom com menos campos.
        	window.opener.document.getElementById(campos[4]).value = colaborador[id].cep;
            window.opener.document.getElementById(campos[5]).value = colaborador[id].set;
            window.opener.document.getElementById(campos[6]).value = colaborador[id].eqp;
            window.opener.document.getElementById(campos[7]).value = colaborador[id].unidadeFluigId;
        }
        
        

        var event;

        if (document.createEvent) {

            event = window.opener.document.createEvent("Event");
            event.initEvent("change", true, true);

        }
        else {

            event = document.createEventObject();
            event.eventType = "change";
            event.eventName = "change";

        }

        if (document.createEvent) {

            window.opener.document.getElementById(campos[0]).dispatchEvent(event);
            window.opener.document.getElementById(campos[1]).dispatchEvent(event);
            window.opener.document.getElementById(campos[2]).dispatchEvent(event);
            window.opener.document.getElementById(campos[3]).dispatchEvent(event);
            if (campos[4] && campos[5] && campos[6]){
            	 window.opener.document.getElementById(campos[4]).dispatchEvent(event);
                 window.opener.document.getElementById(campos[5]).dispatchEvent(event);
                 window.opener.document.getElementById(campos[6]).dispatchEvent(event);
                 window.opener.document.getElementById(campos[7]).dispatchEvent(event);
            }
           
            
        }
        else {

            window.opener.document.getElementById(campos[0]).fireEvent("on" + event.eventType, event);
            window.opener.document.getElementById(campos[1]).fireEvent("on" + event.eventType, event);
            window.opener.document.getElementById(campos[2]).fireEvent("on" + event.eventType, event);
            window.opener.document.getElementById(campos[3]).fireEvent("on" + event.eventType, event);
            if (campos[4] && campos[5] && campos[6]){
	            window.opener.document.getElementById(campos[4]).fireEvent("on" + event.eventType, event);
	            window.opener.document.getElementById(campos[5]).fireEvent("on" + event.eventType, event);
	            window.opener.document.getElementById(campos[6]).fireEvent("on" + event.eventType, event);
	            window.opener.document.getElementById(campos[7]).fireEvent("on" + event.eventType, event);
            }
        }

    }

    window.close();

}

function MudaPagina(valor) {

    if (valor > 0 || pagina != 1) {

        pagina += valor;
        AtualizaPaginaHTML();
        BuscaColaboradores();

    }
    else if (valor < 0 && pagina == 1) {

        $("#erro").html("<p>Você já esta na última página!</p>");
        $("#erro").fadeIn();

    }

}

function BuscaColaboradores() {

    var paginacao = pagina + ";" + zoom.quantidadePorPagina;
    var grupo = web.GetGrupoDeTrabalho(zoom.usuario);
    grupo = grupo.replace(/\W/g, "");
    relac = (typeof  zoom.relacionamento === "undefined" ||  zoom.relacionamento == false  ? "false" : "true");
    colaborador = zoomWeb.ZoomColaboradores(paginacao, search, grupo,relac);
 
    if (colaborador.length == 0) {
 
        pagina--;
        AtualizaPaginaHTML();

        $("#erro").html("<p>Você já esta na última página!</p>");
        $("#erro").fadeIn();

    }
    else { 

        $("#erro").hidden = true;
        MontaTabela();

    } 
 
}

function Search(event) { 
    search = RetirarAcento($("#search").val()).toLowerCase();

    if (event == true || zoom.search == true) {

        pagina = 1;
        AtualizaPaginaHTML();
        BuscaColaboradores();

    }

}

function AtualizaPaginaHTML() {

    $("#pagina").empty();
    $("#pagina").text(pagina);

}

function MontaTabela() {

    var tabela;
    var corpoTabela;


    tabela = "<tr>" +
                "<td>Nome</td>" +
                "<td>Matricula</td> " +
                "<td>Setor</td> " +
                "<td>Unidade</td> " +
            "</tr>";
    

    for (var i = 0; i < colaborador.length; i++) {
    	colaborador[i].setor = (typeof colaborador[i].setorCep  != null && colaborador[i].setorCep != null && colaborador[i].setorCep != "" ? colaborador[i].setorCep : colaborador[i].setorFluig );
    	colaborador[i].unidade = (typeof colaborador[i].unidadeCep  != null && colaborador[i].unidadeCep != null && colaborador[i].unidadeCep != "" ? colaborador[i].unidadeCep : colaborador[i].unidadeFluig );
        corpoTabela += " <tr class='click' onclick='Escolha(" + i + ")' >" +
                            "<td align='center'>" + colaborador[i].nome + "</td>" +
                            "<td>" + colaborador[i].matricula + "</td>" +
                            "<td>" + colaborador[i].setor + "</td>" +
                            "<td>" + colaborador[i].unidade + "</td>" +
                        "</tr>";
        colaborador[i].cep = (typeof colaborador[i].setorCepId  != null && colaborador[i].setorCepId != null && colaborador[i].setorCepId != "" ? true : false);
        colaborador[i].set = (typeof colaborador[i].setorCepId  != null && colaborador[i].setorCepId != null && colaborador[i].setorCepId != "" ? colaborador[i].setorCepId : colaborador[i].setorFluigId );
    	colaborador[i].eqp = colaborador[i].equipeCepId;

    }

    $("#tabela").html(tabela);
    $("#contentTabela").html(corpoTabela);


}