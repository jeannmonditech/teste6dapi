var zoom;
var web;
var $dataTable;
var search = "";
var pagina = 1;
var qtdPagina = 10;

function IniciaDataTable() {

    $dataTable = $('#registros').DataTable({

        "oLanguage": {

            "sUrl": "../../../MonditechWeb/Datatables/Language/portugues.json"

        },

        dom: 'rt',

        columnDefs: [{
            "targets": "_all",
            "className": "dt-center"
        }],

        order: [[1, "asc"]],

        responsive: true

    });

}

function PopulaTabela(equipes) {

    $dataTable.clear();

    for (var i = 0; i < equipes.length; i++) {

        $dataTable.row.add([

            equipes[i].codigo,
            equipes[i].descricao

        ]);

    }

    $dataTable.draw();

}

function BuscaRegistros() {

    web.ZoomGrupos(search, pagina, qtdPagina, PopulaTabela);

}

function AtualizaPaginaHTML() {

    $("#pagina").empty();
    $("#pagina").text(pagina);

}

function MudaPagina(valor) {

    if (valor > 0 || pagina != 1) {

        pagina += valor;
        AtualizaPaginaHTML();
        BuscaRegistros();

    }
    else if (valor < 0 && pagina == 1) {

        $("#erro").html("<p>Você está na primeira página!</p>");
        $("#erro").fadeIn();

    }

}

function Search(event) {

    search = Monditech.RetirarAcento($("#search").val().trim().toUpperCase());
    pagina = 1;
    AtualizaPaginaHTML();
    BuscaRegistros();

}

$(function () {

    zoom = window.opener.zoom;
    web = window.opener.web;

    IniciaDataTable();
    BuscaRegistros();

    $('#registros tbody').on("click", "tr", function (event) {

        var elementos = $dataTable.row(this).data();

        if (window.opener && !window.opener.closed) {

            var campos = zoom.camposRetorno;
            window.opener.document.getElementById(campos[0]).value = elementos[0];
            window.opener.document.getElementById(campos[1]).value = elementos[1];
            window.close();

        }

    });

    $("#pagProxima").on("click", function () {

        MudaPagina(1);

    });

    $("#pagAnterior").on("click", function () {

        MudaPagina(-1);

    });

    $("#buscar").on("click", function () {

        Search(event);

    });

    $("#search").on("keyup", function (event) {

        Search(event);

    });

});