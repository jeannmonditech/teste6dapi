
package com.monditech.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ConsultasSoap", targetNamespace = "http://webservices.monditech.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ConsultasSoap {


    /**
     * 
     * @param senha
     * @param login
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "ConsultarUsuariosOnline", action = "http://webservices.monditech.com/ConsultarUsuariosOnline")
    @WebResult(name = "ConsultarUsuariosOnlineResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "ConsultarUsuariosOnline", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ConsultarUsuariosOnline")
    @ResponseWrapper(localName = "ConsultarUsuariosOnlineResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ConsultarUsuariosOnlineResponse")
    public String consultarUsuariosOnline(
        @WebParam(name = "login", targetNamespace = "http://webservices.monditech.com/")
        String login,
        @WebParam(name = "senha", targetNamespace = "http://webservices.monditech.com/")
        String senha);

    /**
     * 
     * @param pagina
     * @param senha
     * @param search
     * @param login
     * @param qntdPorPagina
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "ZoomConsultarPacientes", action = "http://webservices.monditech.com/ZoomConsultarPacientes")
    @WebResult(name = "ZoomConsultarPacientesResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "ZoomConsultarPacientes", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ZoomConsultarPacientes")
    @ResponseWrapper(localName = "ZoomConsultarPacientesResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ZoomConsultarPacientesResponse")
    public String zoomConsultarPacientes(
        @WebParam(name = "login", targetNamespace = "http://webservices.monditech.com/")
        String login,
        @WebParam(name = "senha", targetNamespace = "http://webservices.monditech.com/")
        String senha,
        @WebParam(name = "search", targetNamespace = "http://webservices.monditech.com/")
        String search,
        @WebParam(name = "pagina", targetNamespace = "http://webservices.monditech.com/")
        String pagina,
        @WebParam(name = "qntdPorPagina", targetNamespace = "http://webservices.monditech.com/")
        String qntdPorPagina);

    /**
     * 
     * @param pagina
     * @param senha
     * @param search
     * @param login
     * @param qntdPorPagina
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "ZoomConsultarExames", action = "http://webservices.monditech.com/ZoomConsultarExames")
    @WebResult(name = "ZoomConsultarExamesResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "ZoomConsultarExames", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ZoomConsultarExames")
    @ResponseWrapper(localName = "ZoomConsultarExamesResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ZoomConsultarExamesResponse")
    public String zoomConsultarExames(
        @WebParam(name = "login", targetNamespace = "http://webservices.monditech.com/")
        String login,
        @WebParam(name = "senha", targetNamespace = "http://webservices.monditech.com/")
        String senha,
        @WebParam(name = "search", targetNamespace = "http://webservices.monditech.com/")
        String search,
        @WebParam(name = "pagina", targetNamespace = "http://webservices.monditech.com/")
        String pagina,
        @WebParam(name = "qntdPorPagina", targetNamespace = "http://webservices.monditech.com/")
        String qntdPorPagina);

    /**
     * 
     * @param senha
     * @param search
     * @param login
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "CountConsultarExamesPaciente", action = "http://webservices.monditech.com/CountConsultarExamesPaciente")
    @WebResult(name = "CountConsultarExamesPacienteResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "CountConsultarExamesPaciente", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.CountConsultarExamesPaciente")
    @ResponseWrapper(localName = "CountConsultarExamesPacienteResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.CountConsultarExamesPacienteResponse")
    public String countConsultarExamesPaciente(
        @WebParam(name = "login", targetNamespace = "http://webservices.monditech.com/")
        String login,
        @WebParam(name = "senha", targetNamespace = "http://webservices.monditech.com/")
        String senha,
        @WebParam(name = "search", targetNamespace = "http://webservices.monditech.com/")
        String search);

    /**
     * 
     * @param pagina
     * @param senha
     * @param search
     * @param login
     * @param qntdPorPagina
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "ZoomConsultarExamesPaciente", action = "http://webservices.monditech.com/ZoomConsultarExamesPaciente")
    @WebResult(name = "ZoomConsultarExamesPacienteResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "ZoomConsultarExamesPaciente", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ZoomConsultarExamesPaciente")
    @ResponseWrapper(localName = "ZoomConsultarExamesPacienteResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ZoomConsultarExamesPacienteResponse")
    public String zoomConsultarExamesPaciente(
        @WebParam(name = "login", targetNamespace = "http://webservices.monditech.com/")
        String login,
        @WebParam(name = "senha", targetNamespace = "http://webservices.monditech.com/")
        String senha,
        @WebParam(name = "search", targetNamespace = "http://webservices.monditech.com/")
        String search,
        @WebParam(name = "pagina", targetNamespace = "http://webservices.monditech.com/")
        String pagina,
        @WebParam(name = "qntdPorPagina", targetNamespace = "http://webservices.monditech.com/")
        String qntdPorPagina);

    /**
     * 
     * @param senha
     * @param dataFim
     * @param matricula
     * @param dataInicio
     * @param login
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "ConsultarCartaoPonto", action = "http://webservices.monditech.com/ConsultarCartaoPonto")
    @WebResult(name = "ConsultarCartaoPontoResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "ConsultarCartaoPonto", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ConsultarCartaoPonto")
    @ResponseWrapper(localName = "ConsultarCartaoPontoResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ConsultarCartaoPontoResponse")
    public String consultarCartaoPonto(
        @WebParam(name = "login", targetNamespace = "http://webservices.monditech.com/")
        String login,
        @WebParam(name = "senha", targetNamespace = "http://webservices.monditech.com/")
        String senha,
        @WebParam(name = "matricula", targetNamespace = "http://webservices.monditech.com/")
        String matricula,
        @WebParam(name = "dataInicio", targetNamespace = "http://webservices.monditech.com/")
        String dataInicio,
        @WebParam(name = "dataFim", targetNamespace = "http://webservices.monditech.com/")
        String dataFim);

    /**
     * 
     * @param senha
     * @param matricula
     * @param login
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "UltimaAtualizacaoBanco", action = "http://webservices.monditech.com/UltimaAtualizacaoBanco")
    @WebResult(name = "UltimaAtualizacaoBancoResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "UltimaAtualizacaoBanco", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.UltimaAtualizacaoBanco")
    @ResponseWrapper(localName = "UltimaAtualizacaoBancoResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.UltimaAtualizacaoBancoResponse")
    public String ultimaAtualizacaoBanco(
        @WebParam(name = "login", targetNamespace = "http://webservices.monditech.com/")
        String login,
        @WebParam(name = "senha", targetNamespace = "http://webservices.monditech.com/")
        String senha,
        @WebParam(name = "matricula", targetNamespace = "http://webservices.monditech.com/")
        String matricula);

    /**
     * 
     * @param senha
     * @param matricula
     * @param login
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetDadosFuncionario", action = "http://webservices.monditech.com/GetDadosFuncionario")
    @WebResult(name = "GetDadosFuncionarioResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "GetDadosFuncionario", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.GetDadosFuncionario")
    @ResponseWrapper(localName = "GetDadosFuncionarioResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.GetDadosFuncionarioResponse")
    public String getDadosFuncionario(
        @WebParam(name = "login", targetNamespace = "http://webservices.monditech.com/")
        String login,
        @WebParam(name = "senha", targetNamespace = "http://webservices.monditech.com/")
        String senha,
        @WebParam(name = "matricula", targetNamespace = "http://webservices.monditech.com/")
        String matricula);

    /**
     * 
     * @param senha
     * @param dataFim
     * @param matricula
     * @param dataInicio
     * @param login
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "ConsultaTotalAtendimentos", action = "http://webservices.monditech.com/ConsultaTotalAtendimentos")
    @WebResult(name = "ConsultaTotalAtendimentosResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "ConsultaTotalAtendimentos", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ConsultaTotalAtendimentos")
    @ResponseWrapper(localName = "ConsultaTotalAtendimentosResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ConsultaTotalAtendimentosResponse")
    public String consultaTotalAtendimentos(
        @WebParam(name = "login", targetNamespace = "http://webservices.monditech.com/")
        String login,
        @WebParam(name = "senha", targetNamespace = "http://webservices.monditech.com/")
        String senha,
        @WebParam(name = "matricula", targetNamespace = "http://webservices.monditech.com/")
        String matricula,
        @WebParam(name = "dataInicio", targetNamespace = "http://webservices.monditech.com/")
        String dataInicio,
        @WebParam(name = "dataFim", targetNamespace = "http://webservices.monditech.com/")
        String dataFim);

    /**
     * 
     * @param senha
     * @param dataFim
     * @param dataInicio
     * @param login
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "ConsultaTotalAtendimentosByGroup", action = "http://webservices.monditech.com/ConsultaTotalAtendimentosByGroup")
    @WebResult(name = "ConsultaTotalAtendimentosByGroupResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "ConsultaTotalAtendimentosByGroup", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ConsultaTotalAtendimentosByGroup")
    @ResponseWrapper(localName = "ConsultaTotalAtendimentosByGroupResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.ConsultaTotalAtendimentosByGroupResponse")
    public String consultaTotalAtendimentosByGroup(
        @WebParam(name = "login", targetNamespace = "http://webservices.monditech.com/")
        String login,
        @WebParam(name = "senha", targetNamespace = "http://webservices.monditech.com/")
        String senha,
        @WebParam(name = "dataInicio", targetNamespace = "http://webservices.monditech.com/")
        String dataInicio,
        @WebParam(name = "dataFim", targetNamespace = "http://webservices.monditech.com/")
        String dataFim);

    /**
     * 
     * @param idPaciente
     * @param telefone
     * @param dtnasc
     * @param nome
     * @param email
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "BuscarDadosPaciente", action = "http://webservices.monditech.com/BuscarDadosPaciente")
    @WebResult(name = "BuscarDadosPacienteResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "BuscarDadosPaciente", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.BuscarDadosPaciente")
    @ResponseWrapper(localName = "BuscarDadosPacienteResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.BuscarDadosPacienteResponse")
    public String buscarDadosPaciente(
        @WebParam(name = "id_paciente", targetNamespace = "http://webservices.monditech.com/")
        String idPaciente,
        @WebParam(name = "nome", targetNamespace = "http://webservices.monditech.com/")
        String nome,
        @WebParam(name = "email", targetNamespace = "http://webservices.monditech.com/")
        String email,
        @WebParam(name = "dtnasc", targetNamespace = "http://webservices.monditech.com/")
        String dtnasc,
        @WebParam(name = "telefone", targetNamespace = "http://webservices.monditech.com/")
        String telefone);

    /**
     * 
     * @param idPaciente
     * @param exame
     * @param paciente
     * @param dataInicial
     * @param numeroExame
     * @param dataFinal
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "BuscarExamesPaciente", action = "http://webservices.monditech.com/BuscarExamesPaciente")
    @WebResult(name = "BuscarExamesPacienteResult", targetNamespace = "http://webservices.monditech.com/")
    @RequestWrapper(localName = "BuscarExamesPaciente", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.BuscarExamesPaciente")
    @ResponseWrapper(localName = "BuscarExamesPacienteResponse", targetNamespace = "http://webservices.monditech.com/", className = "com.monditech.webservices.BuscarExamesPacienteResponse")
    public String buscarExamesPaciente(
        @WebParam(name = "id_paciente", targetNamespace = "http://webservices.monditech.com/")
        String idPaciente,
        @WebParam(name = "paciente", targetNamespace = "http://webservices.monditech.com/")
        String paciente,
        @WebParam(name = "numero_exame", targetNamespace = "http://webservices.monditech.com/")
        String numeroExame,
        @WebParam(name = "data_inicial", targetNamespace = "http://webservices.monditech.com/")
        String dataInicial,
        @WebParam(name = "data_final", targetNamespace = "http://webservices.monditech.com/")
        String dataFinal,
        @WebParam(name = "exame", targetNamespace = "http://webservices.monditech.com/")
        String exame);

}