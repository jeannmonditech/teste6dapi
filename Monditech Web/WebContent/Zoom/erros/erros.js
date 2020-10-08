var zoom;
var web;

function OnLoad() {

    zoom = window.opener.zoom;
    web = window.opener.web;

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
            { targets: 0,  },
            { targets : 1,  },
            { targets: 2,  },

        ],

        responsive: true

    });
} 

function MontaTabela() {
    $dataTable.dataTable().fnClearTable();


    var callback = function (retorno) {
        for (var i = 0; i < retorno.length; i++) {
        	$dataTable.DataTable().row.add([
        	                                retorno[i].id,
        	                                retorno[i].descricao + " ",
        	                                retorno[i].ativo ? "Ativo" : "Inativo"

        	                            ]).draw(false);
        }
        erros = retorno;
        $dataTable.dataTable().fnDraw();
    }
    web.GetErros(true, callback);
    
    
}

