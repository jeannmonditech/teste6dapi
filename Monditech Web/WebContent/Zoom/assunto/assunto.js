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
            { targets: 0, visible: false },
            { targets: 2, visible: false },

        ],

        responsive: true

    });
}

function MontaTabela() {

    $dataTable.dataTable().fnClearTable();

    var assuntos = web.GetAssuntos("", false);

    for (var i = 0; i < assuntos.length; i++) {

        $dataTable.DataTable().row.add([

            assuntos[i].id,
            assuntos[i].assunto,
            assuntos[i].ativo

        ]).draw(false);

    }

    $dataTable.dataTable().fnDraw();

}

