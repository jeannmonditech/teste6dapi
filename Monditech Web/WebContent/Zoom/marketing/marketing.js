var zoom;
var web;

function OnLoad() {

    zoom = window.opener.zoom;
    web = new Web(zoom.web);

    IniciarDataTable();
    //MontaTabela();


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

        responsive: true

    });
}

function MontaTabela() {

    $dataTable.dataTable().fnClearTable();

    var marketing = web.GetUsariosGrupo(web.GetParametros("grupoMarketing"));

    for (var i = 0; i < marketing.length; i++) {

        $dataTable.DataTable().row.add([

            marketing[i].login,
            marketing[i].fullName,

        ]).draw(false);

    }

    $dataTable.dataTable().fnDraw();

}

