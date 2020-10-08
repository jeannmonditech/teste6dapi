function Feedback(fluig) {

    this.fluig = fluig;

}

Feedback.prototype.GetOcorrenciasFeedback = function (manutencao, incluirInativos, callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/GetOcorrenciasFeedback?manutencao=" + manutencao + "&incluirInativos=" + incluirInativos,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            callback(null);
            ShowToastAlert("Erro ao buscar ocorrências feedback: ", res.responseText, "danger");

        }

    });

};

Feedback.prototype.SetStatusOcorrenciaFeedback = function (id, ativo, callback) {

    $.ajax({

        type: "PUT",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/SetStatusOcorrenciaFeedback?id=" + id + "&ativo=" + ativo,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            callback(null);
            ShowToastAlert("Erro ao atualizar status ocorrência feedback: ", res.responseText, "danger");

        }

    });

};

Feedback.prototype.CadastrarOcorrenciaFeedback = function (descricao, callback) {

    $.ajax({

        type: "PUT",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/CadastrarOcorrenciaFeedback?descricao=" + descricao,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            callback(null);
            ShowToastAlert("Erro ao cadastrar ocorrência feedback: ", res.responseText, "danger");

        }

    });

};

Feedback.prototype.SalvarRegistroFeedback = function (dadosfeedback, callback) {

    $.ajax({

        type: "POST",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/SalvarRegistroFeedback",
        async: true,
        data: JSON.stringify(dadosfeedback),
        success: function (res, status, xhr) {

            callback(res);
            ShowToastAlert("Sucesso!", "Feedback registrado!", "success");

        },
        error: function (res, textStatus, errorThrown) {

            callback(null);
            ShowToastAlert("Erro ao salvar feedback: ", res.responseText, "danger");

        }

    });

};

Feedback.prototype.GetOcorrenciasRegistrosFeedback = function (callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/GetOcorrenciasRegistrosFeedback",
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar ocorrências: ", res.responseText, "danger");
            callback(null);

        }

    });

};

Feedback.prototype.ZoomEquipeRegistrosFeedback = function (search, pagina, qtdPagina, callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/ZoomEquipeRegistrosFeedback?search=" + search + "&pagina=" + pagina + "&qtdPagina=" + qtdPagina,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar equipe(s): ", res.responseText, "danger");
            callback(null);

        }

    });

};

Feedback.prototype.GetColaboradorRegistroFeedback = function (matricula, callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/GetColaboradorRegistroFeedback?matricula=" + matricula,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar colaborador: ", res.responseText, "danger");
            callback(null);

        }

    });

};

Feedback.prototype.ZoomColaboradoresRegistrosFeedback = function (search, pagina, qtdPagina, callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/ZoomColaboradoresRegistrosFeedback?search=" + search + "&pagina=" + pagina + "&qtdPagina=" + qtdPagina,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar colaborador(es): ", res.responseText, "danger");
            callback(null);

        }

    });

};

Feedback.prototype.GetColaboradorRegistroFeedbackLegado = function (matricula, callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/GetColaboradorRegistroFeedbackLegado?matricula=" + matricula,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar colaborador: ", res.responseText, "danger");
            callback(null);

        }

    });

};

Feedback.prototype.ZoomColaboradoresRegistrosFeedbackLegado = function (search, pagina, qtdPagina, callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/ZoomColaboradoresRegistrosFeedbackLegado?search=" + search + "&pagina=" + pagina + "&qtdPagina=" + qtdPagina,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar colaborador(es): ", res.responseText, "danger");
            callback(null);

        }

    });

};

Feedback.prototype.GetSuperiorRegistroFeedback = function (matricula, callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/GetSuperiorRegistroFeedback?matricula=" + matricula,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar superior: ", res.responseText, "danger");
            callback(null);

        }

    });

};

Feedback.prototype.ZoomSuperioresRegistrosFeedback = function (search, pagina, qtdPagina, callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/ZoomSuperioresRegistrosFeedback?search=" + search + "&pagina=" + pagina + "&qtdPagina=" + qtdPagina,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar superior(es): ", res.responseText, "danger");
            callback(null);

        }

    });

};

Feedback.prototype.GetSuperiorRegistroFeedbackLegado = function (matricula, callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/GetSuperiorRegistroFeedbackLegado?matricula=" + matricula,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar superior: ", res.responseText, "danger");
            callback(null);

        }

    });

};

Feedback.prototype.ZoomSuperioresRegistrosFeedbackLegado = function (search, pagina, qtdPagina, callback) {

    $.ajax({

        type: "GET",
        contentType: "aplication/json",
        url: this.fluig.serverURL + "/monditech/api/rest/Feedback/ZoomSuperioresRegistrosFeedbackLegado?search=" + search + "&pagina=" + pagina + "&qtdPagina=" + qtdPagina,
        async: true,
        data: {},
        success: function (res, status, xhr) {

            callback(res);

        },
        error: function (res, textStatus, errorThrown) {

            ShowToastAlert("Erro ao buscar superior(es): ", res.responseText, "danger");
            callback(null);

        }

    });

};