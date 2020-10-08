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
            window.opener.document.getElementById(campos[2]).value = elementos[2];
            window.opener.document.getElementById(campos[3]).value = elementos[3];

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
        dom: 'Bfrtip',

        buttons: [
        ],

        "columnDefs": [
            { targets: 2, visible: false },
            { targets: 3, visible: false },

        ],

        responsive: true

    });
}

function MontaTabela() {

    $dataTable.dataTable().fnClearTable();

    var grupo;

    if (zoom.unidade == "Merces") {

        grupo = web.GetParametros("grupoGenrenteMerces");
    }
    else {

        grupo = web.GetParametros("grupoGerentePalladium");
    }

    var gerente = web.GetUsariosGrupo(grupo);

    for (var i = 0; i < gerente.length; i++) {

        $dataTable.DataTable().row.add([

            gerente[i].login,
            gerente[i].fullName,
            gerente[i].email,
            gerente[i].code

        ]).draw(false);

    }

    $dataTable.dataTable().fnDraw();

}

