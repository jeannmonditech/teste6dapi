var paciente;
var search = "";
var pagina = 1;
var qtdPagina = 5;
var zoom;
var zoomWeb;
var web;
var pacienteBusca = "";

setTimeout(function() {	
    OnLoad();

}, 1000);
/*$(document).ready(function () {
	alert("aaa11");
    //OnLoad();

});*/

function OnLoad() {

	var fluig = new Object(); 	
	 zoom = window.opener.zoom;
	fluig = ((zoom.isMobile == true) ? document.URL.slice(0, -1) : window.opener.parent.WCMAPI);
	
    web = new Web(fluig); 
   
     
    if (zoom.search != false) {
    	
    	zoom.search = true;
    }
    
    
    BuscaColaboradores();
   

}

function Escolha(id,descPaciente) {


        
        var campos = zoom.camposRetorno;
        
        //document.getElementById(campos[0]).value = id;
        //document.getElementById(campos[1]).value = descPaciente;
        window.opener.document.getElementById(campos[0]).value = id;
        window.opener.document.getElementById(campos[1]).value = descPaciente;
        window.opener.document.getElementById(campos[4]).value = pacienteBusca.estadoCivil;
        window.opener.document.getElementById(campos[5]).value = pacienteBusca.cep;
        window.opener.document.getElementById(campos[6]).value = pacienteBusca.endereco;
        window.opener.document.getElementById(campos[7]).value = pacienteBusca.bairro;
        window.opener.document.getElementById(campos[8]).value = pacienteBusca.uf;
        window.opener.document.getElementById(campos[9]).value = pacienteBusca.cidade;

        
        
//        document.getElementById(campos[4]).value = paciente[id].estadoCivil;
//        document.getElementById(campos[5]).value = paciente[id].cep;
//        document.getElementById(campos[6]).value = paciente[id].endereco;
//        document.getElementById(campos[7]).value = paciente[id].bairro;
//        document.getElementById(campos[8]).value = paciente[id].uf;
//        document.getElementById(campos[9]).value = paciente[id].cidade;

        
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
    //grupo = grupo.replace(/\W/g, "");
    
    var callback = function (paciente) {
    	
    	if (paciente.length == 0) {
        	
            pagina--;
            AtualizaPaginaHTML();
            
            $("#erro").html("<p>Você já esta na última página!</p>");
            $("#erro").fadeIn();
            
        }
        else {
        	pacienteBusca = paciente;
            $("#erro").hidden = true;
            MontaTabela(paciente);
            
        }
    }
    console.log("OOOOOO "+search)
    web.GetPacientes(search, pagina, qtdPagina, callback);

}

function Search(event) {
	console.log("JJJJ---HHHH--0000 "+search);
	search = RetirarAcento($("#search").val()).toUpperCase();
    //search = RetirarAcento($("#search").val()).toLowerCase();
	//search = $("#search").val();
    console.log("JJJJ---HHHH--0001 "+search);
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

function MontaTabela(paciente) {
	
    var tabela;
    var corpoTabela;

    console.log("MONTA TABELA");
    tabela = "<tr>" +
                "<td>Número Paciente</td>" +
                "<td>Nome Paciente</td> " +
                "<td>RG</td> " +
                "<td>CPF</td> " +
                "<td>Endereço</td> " +
                "<td>Bairro</td> " +
                "<td>UF</td> " +
                "<td>Cidade</td> " +
            "</tr>";
    
    
    for (var i = 0; i < paciente.length; i++) {

        
        corpoTabela += " <tr class='click' onclick='Escolha(" + paciente[i].cdPaciente + ",\""+paciente[i].dePaciente+"\")' >" +
                            "<td align='center'>" + paciente[i].cdPaciente + "</td>" +
                            "<td>" + paciente[i].dePaciente + "</td>" +
                           // "<td>" + paciente[i].deAtivo + "</td>" +
                            "<td>" + paciente[i].rg + "</td>" +
                            "<td>" + paciente[i].cpf + "</td>" +
                            //"<td>" + paciente[i].estadoCivil + "</td>" +
                            //"<td>" + paciente[i].cep + "</td>" +
                            "<td>" + paciente[i].endereco + "</td>" +
                            "<td>" + paciente[i].bairro + "</td>" +
                            "<td>" + paciente[i].uf + "</td>" +
                            "<td>" + paciente[i].cidade + "</td>" +
                        "</tr>";

    }
    
    console.log("depois...");
    
    

    $("#tabela").html(tabela);
    $("#contentTabela").html(corpoTabela);    
    $("#resultModal").closest(".modal").fadeIn();
    
}