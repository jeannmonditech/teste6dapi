function ZoomWeb(fluig) {

    this.fluig = fluig;
}

/**
 * Consulta os colaboradores
 * @param {String} paginacao
 * @param {String} search
 * @param {String} usuario
 */
ZoomWeb.prototype.ZoomColaboradores = function (paginacao, search, grupoUsuario, relacionamento) {

    var retorno = "";

    $.ajax({

        type: "GET",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Zoom/ZoomColaboradores?paginacao=" + paginacao + "&search=" + search + "&grupoUsuario=" + grupoUsuario + "&relacionamento=" + relacionamento,
        async: false,
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar colaboradores:", res.responseText, "danger");

        }

    });

    return retorno;
}

ZoomWeb.prototype.ZoomColaboradoresSetoresEquipes = function (paginacao, search) {

    var retorno = "";

    $.ajax({

        type: "GET",
        contentType: "application/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Zoom/ZoomColaboradoresSetoresEquipes?paginacao=" + paginacao + "&search=" + search,
        async: false,
        success: function (res, status, xhr) {

            retorno = res;

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar colaboradores:", res.responseText, "danger");

        }

    });

    return retorno;
}