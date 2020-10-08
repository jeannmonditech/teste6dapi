/**
 * ECMFolderServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.totvs.technology.ecm.dm.ws;

public class ECMFolderServiceServiceLocator extends org.apache.axis.client.Service implements com.totvs.technology.ecm.dm.ws.ECMFolderServiceService {

    public ECMFolderServiceServiceLocator() {
    }


    public ECMFolderServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ECMFolderServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FolderServicePort
    private java.lang.String FolderServicePort_address = "http://fluig_dev.dapi.com.br/webdesk/ECMFolderService";

    public java.lang.String getFolderServicePortAddress() {
        return FolderServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FolderServicePortWSDDServiceName = "FolderServicePort";

    public java.lang.String getFolderServicePortWSDDServiceName() {
        return FolderServicePortWSDDServiceName;
    }

    public void setFolderServicePortWSDDServiceName(java.lang.String name) {
        FolderServicePortWSDDServiceName = name;
    }

    public com.totvs.technology.ecm.dm.ws.FolderService getFolderServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FolderServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFolderServicePort(endpoint);
    }

    public com.totvs.technology.ecm.dm.ws.FolderService getFolderServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.totvs.technology.ecm.dm.ws.ECMFolderServiceServiceSoapBindingStub _stub = new com.totvs.technology.ecm.dm.ws.ECMFolderServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getFolderServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFolderServicePortEndpointAddress(java.lang.String address) {
        FolderServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.totvs.technology.ecm.dm.ws.FolderService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.totvs.technology.ecm.dm.ws.ECMFolderServiceServiceSoapBindingStub _stub = new com.totvs.technology.ecm.dm.ws.ECMFolderServiceServiceSoapBindingStub(new java.net.URL(FolderServicePort_address), this);
                _stub.setPortName(getFolderServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("FolderServicePort".equals(inputPortName)) {
            return getFolderServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.dm.ecm.technology.totvs.com/", "ECMFolderServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.dm.ecm.technology.totvs.com/", "FolderServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FolderServicePort".equals(portName)) {
            setFolderServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
