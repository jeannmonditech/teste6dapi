var zoom;
var feedback;
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
        },
        {
            "targets": 0,
            "width": "10%"
        },
        {
            "targets": 1,
            "visible": false
        }],

        order: [[2, "asc"]],

        responsive: true

    });

}

function PopulaTabela(colaboradores) {

    $dataTable.clear();

    for (var i = 0; i < colaboradores.length; i++) {

        $dataTable.row.add([

            colaboradores[i].matricula,
            colaboradores[i].login,
            colaboradores[i].nome

        ]);

    }

    $dataTable.draw();

}

function BuscaRegistros() {

    if (!zoom.legado) {

        feedback.ZoomSuperioresRegistrosFeedback(search, pagina, qtdPagina, PopulaTabela);

    }
    else {

        feedback.ZoomSuperioresRegistrosFeedbackLegado(search, pagina, qtdPagina, PopulaTabela);

    }

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
    feedback = window.opener.feedback;

    IniciaDataTable();
    BuscaRegistros();

    $('#registros tbody').on("click", "tr", function (event) {

        var elementos = $dataTable.row(this).data();

        if (window.opener && !window.opener.closed) {

            var campos = zoom.camposRetorno;
            window.opener.document.getElementById(campos[0]).value = elementos[0];
            window.opener.document.getElementById(campos[1]).value = elementos[1];
            window.opener.document.getElementById(campos[2]).value = elementos[2];
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