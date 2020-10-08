$(document).ready(function () {

    $(".navigationBar").MoveAndPaint();

});

$.fn.GetSelectedValues = function () {

    var retorno = [];
    var lista = $(this).find(".active");

    for (var i = 0; i < lista.length; i++) {

        retorno[i] = $(lista[i]).val();

    }

    return retorno;

}

$.fn.MoveAndPaint = function () {

    return this.each(function () {

        var $objeto = $("li", this);

        var mouseDown = false;
        var active = false;

        $objeto.mousedown(function () {

            $(this).toggleClass("active");
            mouseDown = true;

            active = $(this).attr("class").indexOf("active") != -1 ? true : false;

        });

        $objeto.mouseenter(function () {

            if (mouseDown) {

                if (active) {

                    $(this).addClass("active");

                }
                else {

                    $(this).removeClass("active");

                }

            }

        });

        $(window).mouseup(function () {

            mouseDown = false;

        });

    });

}
