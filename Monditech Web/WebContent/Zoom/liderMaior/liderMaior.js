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
            { targets: 0, visible: false },

        ],

        dom: 'Bfrtip',

        buttons: [
        ],

        responsive: true

    });
}

function MontaTabela() {

    $dataTable.dataTable().fnClearTable();

    var lideres = web.GetLideresMaiores("");

    if (lideres != "") {

        for (var i = 0; i < lideres.length; i++) {

            $dataTable.DataTable().row.add([

                lideres[i].matricula_supervisionado,
                lideres[i].supervisionado,
                lideres[i].nome_supervisionado
                

            ]).draw(false);

        }
    }

    $dataTable.dataTable().fnDraw();

}

