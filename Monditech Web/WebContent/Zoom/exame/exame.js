var zoom;
var web;

var $dataTable;
var search = "";
var pagina = 1;
var qtdPagina = 10;

function IniciarDataTable() {

    $dataTable = $('#registros').DataTable({

        "oLanguage": {
            "sUrl": "../../../MonditechWeb/Datatables/Language/portugues.js"
        },

        columnDefs: [
            {"targets": 0, "width": "10%", "className": "dt-center" },
            {"targets": 1, "text-align": "left" }
        ],

        dom: 'rt',
        order: [[0, "asc"]],
        responsive: true
    });
}

function GetRegistros() {

    var exame = [];

    var callback = function (exame) {

        $dataTable.clear();

        for (var i = 0; i < exame.length; i++) {
            $dataTable.row.add([
                exame[i].cdExame,
                exame[i].deExame
            ]);
        }
        $dataTable.draw();

        $('#registros tbody').on("click", "tr", function (event) {

            var elementos = $dataTable.row(this).data();
    
            if (window.opener && !window.opener.closed) {
    
                var campos = zoom.camposRetorno;
                window.opener.document.getElementById(campos[0]).value = elementos[0]; //cdExame
                window.opener.document.getElementById(campos[1]).value = elementos[1]; //deExame
                window.close();
            }
        });
    }
    web.GetExames(search, pagina, qtdPagina, callback);
}

function AtualizaPaginaHTML() {
    $("#pagina").empty();
    $("#pagina").text(pagina);
}

function MudaPagina(valor) {

    if (valor > 0 || pagina != 1) {

        pagina += valor;
        AtualizaPaginaHTML();
        GetRegistros();

    } else if (valor < 0 && pagina == 1) {

        $("#erro").html("<p>Você está na primeira página!</p>");
        $("#erro").fadeIn();
    }
}

function Search(event) {

    search = Monditech.RetirarAcento( $("#search").val().trim().toUpperCase() );
    pagina = 1;
    AtualizaPaginaHTML();
    GetRegistros();
}

$(function () {

    zoom = window.opener.zoom;
    web = window.opener.web;

    IniciarDataTable();
    GetRegistros();

    /*$('#registros tbody').on("click", "tr", function (event) {

        var elementos = $dataTable.row(this).data();

        if (window.opener && !window.opener.closed) {

            var campos = zoom.camposRetorno;
            window.opener.document.getElementById(campos[0]).value = elementos[0]; //cdExame
            window.opener.document.getElementById(campos[1]).value = elementos[1]; //deExame
            window.close();
        }
    });*/

    $("#pagProxima").on("click", function () {

        MudaPagina(1);
    });

    $("#pagAnterior").on("click", function () {

        MudaPagina(-1);
    });

    $("#buscar").on("click", function () {

        Search(event);
    });

    /*$("#search").on("keyup", function (event) {

        Search(event);
    });*/
});