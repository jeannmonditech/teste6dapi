function Zoom(tipoZoom) {

    //utilizados para zoom de nota fiscal
    this.empresa;
    this.cliente;
    this.loja;

    //utilizados para zoom de item
    this.numeroLote;
    this.numeroNotaFiscal;
    this.valorTotal;
    this.totalQuilos;
    this.quantidade;
    this.unidade;

    //utilizados para zoom de paciente
    this.cdPaciente;
    this.dePaciente;
    this.deAtivo;
    this.rg;
    this.cpf;
    this.estadoCivil;
    this.cep;
    this.endereco;
    this.bairro;
    this.uf;
    this.cidade;

    //utilizados para zoom de exame
    this.cdExame;
    this.deExame;
    this.deAtivo;

    //utilizados em todos os zoom's
    this.newPopup
    this.usuario;
    this.camposRetorno = new Array();
    this.tamanhoZoom;
    this.tamanhoFieldset;
    this.nomeZoom = tipoZoom;
    this.quantidadePorPagina;
    this.search;
}

Zoom.prototype.Open = function () {
	
    var parametros = "menubar=0,resizable=0,status=0,top=250px";

    if (!this.tamanhoZoom) {
        this.tamanhoZoom = "width=600px,height=700px";
    }
    if (zoom.tamanho == "medio")
    	 this.tamanhoZoom = "width=900px,height=700px";
    if (!this.quantidadePorPagina) {
        this.quantidadePorPagina = 18;
    }
    
    this.newPopup = window.open(web.fluig.serverURL + "\\MonditechWeb\\Zoom\\" + this.nomeZoom + "\\" + this.nomeZoom + ".html", this.nomeZoom, this.tamanhoZoom + "," + parametros);

    return this.newPopup;
}