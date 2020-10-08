var zoom;
var web;

function OnLoad() {

    zoom = window.opener.zoom;
    web = new Web(zoom.web);

    IniciarDataTable();
    MontaTabela();


    $('#registros tbody').on("click", "tr", function (event) {

        var elementos = $dataTable.DataTable().row(this).data();

        if (window.opener && !window.opener.closed) {

            var campos = zoom.camposRetorno;

            window.opener.document.getElementById(campos[0]).value = elementos[0];
            window.opener.document.getElementById(campos[1]).value = elementos[1];

            if (zoom.tipoRegistro == "cadastro") {

                window.opener.document.getElementById(campos[2]).value = elementos[2];
            }

            window.close();
        }

    });

}

function IniciarDataTable() {

    $dataTable = $('#registros');

    $dataTable.DataTable({
        "oLanguage": {

            "sUrl": "../../../MonditechWeb/Datatables/Language/portugues.json"

        },

        "columnDefs": [
            { targets: 2, visible: false },

        ],

        dom: 'Bfrtip',

        buttons: [
        ],

        responsive: true

    });
}

function MontaTabela() {

    $dataTable.dataTable().fnClearTable();

    var supervisor;

    if (zoom.tipoRegistro == "pesquisa") {

        supervisor = web.GetSupervisores("");
    }
    else if (zoom.tipoRegistro == "cadastro" || zoom.tipoRegistro == "edicao"){

        supervisor = web.GetUsuariosAtivos();
    }

    for (var i = 0; i < supervisor.length; i++) {

        $dataTable.DataTable().row.add([

            supervisor[i].login ? supervisor[i].login : supervisor[i].supervisor,
            supervisor[i].fullName ? supervisor[i].fullName : supervisor[i].nome_supervisor,
            supervisor[i].code ? (zoom.tipoRegistro == "cadastro" || zoom.tipoRegistro == "edicao" ? supervisor[i].code : "") : ""

        ]).draw(false);

    }

    $dataTable.dataTable().fnDraw();

}

