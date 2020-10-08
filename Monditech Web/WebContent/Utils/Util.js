if (!Number.prototype.ToMoney) {

    /// <summary>Método que transforma um valor float em uma string no formato moeda.</summary>
    /// <param name="currency" type="String">Sinal da moeda. Ex.: "R$".</param>
    /// <returns type="String">Valor formatado de acordo com a moeda.</returns>
    Number.prototype.ToMoney = function (currency) {

        return (currency || "R$") + " " + this.toFixed(2).replace(/./g, function (c, i, a) {

            return (i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c).replace('.', ';').replace(',', '.').replace(';', ',');

        });

    }

}

if (!Number.prototype.Round) {

    Number.prototype.Round = function (casas) {

        var retorno = +(Math.round(this + "e+" + casas) + "e-" + casas);
        return isNaN(retorno) ? 0 : retorno;

    }

}

if (!String.prototype.ToFixedComma) {

    /// <summary>Arredonda valores em string para 2 casas e faz replace do "." para ",".</summary>
    /// <returns type="String">Valor formatado para correta apresentacao.</returns>
    String.prototype.ToFixedComma = function () {

        return parseFloat(this).toFixed(2).replace(".", ",");

    }

}

if (!Number.prototype.ToFixedComma) {

    /// <summary>Arredonda valores em float para 2 casas e faz replace do "." para ",".</summary>
    /// <returns type="String">Valor formatado para correta apresentacao.</returns>
    Number.prototype.ToFixedComma = function () {

        return this.toFixed(2).replace(".", ",");

    }

}

if (!String.prototype.ToFloat) {
    String.prototype.ToFloat = function () {
        /// <summary>Método que transforma string no formato moeda.</summary>
        /// <returns type="Number">Valor em Float.</returns>
        var valor;
        var index
        var decimal;

        if (this.length > 0 && /R/.test(this)) {

            valor = this.split(",")[0].match(/[0-9]/g).join('');
            decimal = this.split(",")[1].match(/[0-9]/g).join('')
            valor = valor + '.' + decimal;
            return parseFloat(valor);

        }
        else if (this.charAt(0) != "" && !isNaN(this.charAt(0))) {

            index = this.search(/,/);

            if (this.split(".").length > 1 && index != (-1)) {

                index = (index - this.split(".").length) + 1;
                valor = this.match(/[0-9]/g).join('');
                valor = valor.substring(0, index) + '.' + valor.substring(index);
                return parseFloat(valor);

            }
            else {

                valor = this.replace(',', '.');
                return parseFloat(valor);

            }

        }
        else
            return 0;
    }

}

if (!String.prototype.DateFormat) {
    String.prototype.DateFormat = function () {

        var aux = this.split("-");

        if (aux.length == 1) {

            return this;

        }

        var year = aux[0];
        var month = aux[1];
        var day = aux[2];

        return day + "/" + month + "/" + year;


    }

}

if (!String.prototype.Mask) {

    String.prototype.Mask = function (mask, maskField) {

        var $mask = $("#" + maskField);
        $mask.val(this);
        $mask.mask(mask);
        return $mask.val();

    }

}

if (!String.prototype.RemoveMaskCPF) {

    String.prototype.RemoveMaskCPF = function () {

        return this.replace(/[^\d]+/g, '');

    }

}

function RetirarAcento (texto) {

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

}
