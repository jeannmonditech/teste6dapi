var Monditech = (function () {

    return {

        SomenteNumeros: function (evento) {

            if (evento.keyCode >= 48 && evento.keyCode <= 57) {

                return true

            }
            else {

                return false;

            }

        },

        SomenteDecimais: function (element, event, decimais) {

            var patt = new RegExp("[,]\\d{" + (decimais || 2) + "}");

            if (((event.keyCode == 44 || event.keyCode == 46) && !/[,]/.test(element.value)) || (event.keyCode >= 48 && event.keyCode <= 57 && !patt.test(element.value))) {

                return true;

            }
            else {

                return false;

            }

        },

        RetirarAcento: function (texto) {

            //àáâãçèéêìíñòóôõùúûÀÁÂÃÇÈÉÊÌÍÑÒÓÔÕÙÚÛ Os acentos estão representados com valor Octal para melhor compatibilidade
            var acentos = "\340\341\342\343\347\350\351\352\354\355\361\362\363\364\365\371\372\373\300\301\302\303\307\310\311\312\314\315\321\322\323\324\325\331\332\333";
            var semAcentos = "aaaaceeeiinoooouuuAAAACEEEIINOOOOUUU";
            var retorno = "";

            for (var i = 0; i < texto.length; i++) {

                var char = texto.substring(i, i + 1);

                for (var j = 0; j < acentos.length; j++) {

                    if (acentos.substring(j, j + 1) == char) {

                        char = semAcentos.substring(j, j + 1);

                    }
                }

                retorno += char;


            }

            return retorno;

        },

        ICheckStyle: function () {

            $(".iCheck").iCheck({

                checkboxClass: 'icheckbox_square-aero',
                radioClass: 'iradio_square-aero',
                increaseArea: '20%' // optional

            });

        },

        MascaraValor: function (element, moeda) {

            if (element.value) {

                if (!/[,]/.test(element.value)) {
                    element.value += ",00";
                }

                var aux = "";
                var valor = element.value.split(",")[0].match(/\d/g).join("");

                if (valor.length > 3) {

                    var index = valor.length - 3;

                    aux = valor.slice(0, index) + "." + valor.slice(index);

                    while (aux.indexOf(".") - 3 > 0) {

                        aux = aux.slice(0, aux.indexOf(".") - 3) + "." + aux.slice(aux.indexOf(".") - 3);

                    }

                }

                element.value = (aux != "" ? aux : valor) + "," + element.value.split(",")[1];

                if (moeda) {

                    element.value = "R$ " + element.value;

                }

            }

        },

        InputFile: function () {

            $('.inputfile').each(function () {
                var $input = $(this),
                    $label = $input.next('label'),
                    labelVal = $label.html();

                $input.on('change', function (e) {
                    var fileName = '';

                    if (this.files && this.files.length > 1)
                        fileName = (this.getAttribute('data-multiple-caption') || '').replace('{count}', this.files.length);
                    else if (e.target.value)
                        fileName = e.target.value.split('\\').pop();

                    if (fileName)
                        $label.find('span').html(fileName);
                    else
                        $label.html(labelVal);
                });

                // Firefox bug fix
                $input
                    .on('focus', function () { $input.addClass('has-focus'); })
                    .on('blur', function () { $input.removeClass('has-focus'); });
            });

        },

        Now: function () {

            var dt = new Date();
            return ("0" + dt.getDate()).slice(-2) + "/" + ("0" + (dt.getMonth() + 1)).slice(-2) + "/" + dt.getFullYear() + " " + ("0" + dt.getHours()).slice(-2) + ":" + ("0" + dt.getMinutes()).slice(-2) + ":" + ("0" + dt.getSeconds()).slice(-2);

        },

        OpenZoom: function (param) {

            zoom = new Zoom(param.tipoZoom);
            zoom.idEmpresa = param.idEmpresa;
            zoom.nomeZoom = param.nomeZoom;
            zoom.tamanhoZoom = param.tamanhoZoom;
            zoom.tamanhoFieldset = param.tamanhoFieldset;
            zoom.camposRetorno = param.camposRetorno;

            zoom.Open();

        },

        FadeInFundoEscuro: function ($displayDiv) {

            $("#fundoEscuro").fadeIn(function () {

                $displayDiv.fadeIn();

            });

        },

        FadeOutFundoEscuro: function ($displayDiv) {

            $displayDiv.fadeOut(function () {

                $("#fundoEscuro").fadeOut();

            });

        },

        FadeOutFadeIn: function ($outerDiv, $innerDiv) {

            $outerDiv.fadeOut(function () {

                $innerDiv.fadeIn();

            });

        },

        DatePickerMask: function (id, options) {

            if (!options) {

                options = {};

            }

            FLUIGC.calendar(id, {
                pickDate: (options.pickDate || true),
                pickTime: (options.pickTime || false),
                useMinutes: (options.useMinutes || false),
                useSeconds: (options.useSeconds || false),
                useCurrent: (options.useCurrent || false),
                minuteStepping: (options.minuteStepping || 1),
                maxDate: (options.maxDate || new Date()),
                showToday: (options.showToday || true),
                language: (options.language || 'pt-br'),
                defaultDate: (options.defaultDate || ""),
                disabledDates: (options.disabledDates || []),
                enabledDates: (options.enabledDates || []),
                useStrict: (options.useStrict || false),
                sideBySide: (options.sideBySide || false),
                daysOfWeekDisabled: (options.daysOfWeekDisabled || [])

            });

        },

        Extend: function (mae, filha) {

            var copiaDaMae = Object.create(mae.prototype);
            filha.prototype = copiaDaMae;
            filha.prototype.constructor = filha;

        },

        Clone: function (obj) {

            if (obj === null || typeof obj !== 'object')
                return obj;

            var clone = {};

            for (var key in obj)
                clone[key] = obj[key];

            return clone;

        },

        SetColumnVisible: function ($dataTable, columnIndex, visible) {

            $dataTable.settings().columns(columnIndex).visible(visible);

        },

        SetColumnOrder: function ($dataTable, columnIndex, desc) {

            var ordenacao = desc ? "desc" : "asc";

            $dataTable.settings().order([[columnIndex, ordenacao]]);
            $dataTable.draw();

        },

        ValidaData: function (data) {

            try {

                var dataSplit = data.split("/");

                var dia = parseInt(dataSplit[0]);
                var mes = parseInt(dataSplit[1]);
                var ano = parseInt(dataSplit[2]);

                if ((dia > 0 && dia <= 31) && (mes > 0 && mes < 13)) {

                    var dataAux = new Date(ano, (mes - 1), dia);

                    if (dataAux.getDate() != dia) {

                        return false;

                    }

                }
                else {

                    return false;

                }

            }
            catch (ex) {

                console.log(ex.message);
                return false;

            }

            return true;

        },

        GetPasta: function (pai, descricao, offset, limit) {

            if (!web)
                web = new Web(parent.WCMAPI);

            var pastas = web.GetDocumentList(pai, offset, limit);

            if (pastas.length == 0) {

                return 0;

            }

            for (var i = 0; i < pastas.length; i++) {

                if (pastas[i].description == descricao) {

                    return pastas[i].id;

                }
                else if (i == pastas.length - 1) {

                    offset += limit;
                    return Monditech.GetPasta(pai, descricao, offset, limit);

                }

            }

        },

        GetAnexos: function (pasta, offset, limit) {

            if (!web)
                web = new Web(parent.WCMAPI);

            var documentos = web.GetDocumentList(pasta, offset, limit);

            if (documentos.length == 0) {

                return [];

            }
            else {

                offset += limit;
                return documentos.concat(Monditech.GetAnexos(pasta, offset, limit));

            }

        },

        /**
         * Função utilizada para esconder/mostrar os painéis. 
         * @param {any} idControl - id do ícone para esconder/mostrar o painel
         */
        ToggleCollapse: function (idControl, callback) {

            var $control = $("#" + idControl);

            if ($control.hasClass("glyphicon-chevron-up")) {

                $control.removeClass("glyphicon-chevron-up");
                $control.addClass("glyphicon-chevron-down");

                if (!callback) {

                    $("#" + idControl + "Panel").slideUp();

                }
                else {

                    $("#" + idControl + "Panel").slideUp("slow", callback);

                }


            }
            else if ($control.hasClass("glyphicon-chevron-down")) {

                $control.removeClass("glyphicon-chevron-down");
                $control.addClass("glyphicon-chevron-up");

                if (!callback) {

                    $("#" + idControl + "Panel").slideDown();

                }
                else {

                    $("#" + idControl + "Panel").slideDown("slow", callback);

                }

            }

        },

        FillSelect: function (collection, fieldValue, fieldDescription, defaultAll, $select, fnChange) {

            $select.empty();

            if (defaultAll) {

                $select.append("<option value='0'>TODOS</option>");

            }
            else {

                $select.append("<option value='0'>SELECIONE</option>");

            }

            for (var i = 0; i < collection.length; i++) {

                $select.append("<option value='" + collection[i][fieldValue] + "'>" + collection[i][fieldDescription] + "</option>")

            }

            if (fnChange) {

                $select.on("chage", fnChange);

            }

            if (empresas.length == 1) {

                $select.val(empresas[0].codigo);
                $select.change();

            }

        },

        AutoResizeTextArea: function () {
         
            $("textarea").each(function () {
                this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
            }).on('input', function () {
                this.style.height = 'auto';
                this.style.height = (this.scrollHeight) + 'px';
            });

            $("textarea").on("paste", function () {

                $(this).trigger('input');

            });

            $("textarea").trigger("input");

        }

    }

})();