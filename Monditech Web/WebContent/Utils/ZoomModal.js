function ZoomModal(tipoZoom) {

    //utilizados para zoom de nota fiscal
    this.parametro;
    this.filial;
    this.loja;

    //utilizados para zoom de item
    this.numeroLote;
    this.numeroNotaFiscal;
    this.valorTotal;
    this.totalQuilos;
    this.quantidade;
    this.unidade;

    //utilizados em todos os zoom's
    this.usuario;
    this.camposRetorno = new Array();
    this.tamanhoZoom;
    this.tamanhoFieldset;
    this.nomeZoom = tipoZoom;
    this.quantidadePorPagina;
    this.isMobile;
    this.search;
    this.titulo;
    this.tamanho;
    this.modal;
    
}

ZoomModal.prototype.Open = function () {
	
    if (!this.quantidadePorPagina) {

        this.quantidadePorPagina = 5;

    }
    

    //Na widget năo precisa setar o zoom.IsMobile, pois a widget mobile reconhece o parent.WCMAPI.serverURL, já no processo é necessário setar o zoom.IsMobile
    var fluig = ((this.isMobile == true) ? document.URL : parent.WCMAPI.serverURL);

    
    return fluig + "\\MonditechWeb\\Zoom\\" + this.nomeZoom + "\\" + this.nomeZoom + ".html";

}

ZoomModal.prototype.CreateModal = function () {
		
    if (this.tamanho == "pequeno") {
        this.tamanho = 'small';
    }
    else if (this.tamanho == "medio") {
        this.tamanho = 'large';
    }
    else if (this.tamanho == "grande") {
        this.tamanho = 'full';
    }
    else { // tamanho padrăo
        this.tamanho = '';
    }

    var self = this;

    this.modal = FLUIGC.modal({
        title: this.titulo,
        id: 'myModal',
        content:
            '<div id="resultModal"></div>' 
        ,
        id: 'fluig-modal',
        size: this.tamanho,
    }, function (err, data) {
        if (err) {
            // do error handling
        } else {

            $("#resultModal").closest(".modal").hide();        
            
            var loading = '<div id="text-loading" style="margin-top: 10% !important; color: white; z-index: 999; position: absolute; height: 50px; top: 10%; left: calc(50% + 70px); margin-left: -160px;" >' +

                                '<h1 style="height: 110px;">' +
                                    '<img src="\\MonditechWeb\\Images\\loading_dots.gif" style="width: 150px; height: 150px; position: relative; margin-left: 0%;">' +
                                '</h1>' +
                                    '<b style="font-size: 30px;">' +
                                        'Carregando' +
                                '</b>' +
                                    '<h4 align="center">' +
                                        'Aguarde!' +
                                '</h4>' +
                            '</div>';

            $(loading).insertAfter($("#resultModal").closest(".modal"));

            $("#text-loading").show();

            $("#text-loading").fadeOut(function () {
            	
                $("#resultModal").load(self.Open());
            	
            });            

        }
    });
    
    return this.modal;
}