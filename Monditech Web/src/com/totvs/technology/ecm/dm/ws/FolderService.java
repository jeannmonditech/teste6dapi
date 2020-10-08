/**
 * FolderService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.totvs.technology.ecm.dm.ws;

public interface FolderService extends java.rmi.Remote {
    public com.totvs.technology.ecm.dm.ws.WebServiceMessage[] destroyDocument(java.lang.String user, java.lang.String password, int companyId, int documentId, java.lang.String colleagueId) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.DocumentDto[] getSubPrivateFolders(java.lang.String username, java.lang.String password, int companyId, int documentId) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.WebServiceMessage[] updateFolder(java.lang.String username, java.lang.String password, int companyId, com.totvs.technology.ecm.dm.ws.DocumentDto[] document, com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDto[] security, com.totvs.technology.ecm.dm.ws.ApproverDto[] approvers) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.ApproverWithLevelDto[] getApprovers(java.lang.String username, java.lang.String password, int companyId, int documentId) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.DocumentDto[] getSubFolders(java.lang.String username, java.lang.String password, int companyId, int documentId, java.lang.String colleagueId) throws java.rmi.RemoteException;
    public com.totvs.technology.ecm.dm.ws.WebServiceMessage[] restoreDocument(java.lang.String user, java.lang.String password, int companyId, int documentId, java.lang.String colleagueId) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.DocumentDto[] findRecycledDocuments(java.lang.String user, java.lang.String password, int companyId, java.lang.String colleagueId) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.DocumentDto[] getFolder(java.lang.String username, java.lang.String password, int companyId, int documentId, java.lang.String colleagueId) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.DocumentDto[] getChildren(java.lang.String username, java.lang.String password, int companyId, int documentId, java.lang.String colleagueId) throws java.rmi.RemoteException;
    public com.totvs.technology.ecm.dm.ws.DocumentDto[] getSubFoldersOnDemand(int companyId, java.lang.String user, java.lang.String password, int parentDocumentId, boolean privateFolder, java.lang.String[] documentTypes, int limit, int lastRowId, java.lang.String colleagueId) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.DocumentDto[] getRootFolders(java.lang.String username, java.lang.String password, int companyId, java.lang.String colleagueId) throws java.rmi.RemoteException;
    public com.totvs.technology.ecm.dm.ws.WebServiceMessage[] createFolderWithApprovementLevels(java.lang.String username, java.lang.String password, int companyId, com.totvs.technology.ecm.dm.ws.DocumentDto[] document, com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDto[] security, com.totvs.technology.ecm.dm.ws.ApproverWithLevelDto[] approversWithLevels, com.totvs.technology.ecm.dm.ws.ApprovalLevelDto[] levels) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.DocumentDto[] getSubFoldersPermission(java.lang.String username, java.lang.String password, int companyId, int documentId, java.lang.String colleagueId, int permission) throws java.rmi.RemoteException;
    public int getUserPermissions(int companyId, java.lang.String username, int documentId, int version) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.WebServiceMessage[] updateSimpleFolder(java.lang.String username, java.lang.String password, int companyId, com.totvs.technology.ecm.dm.ws.DocumentDto[] document) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.WebServiceMessage[] deleteDocument(java.lang.String user, java.lang.String password, int companyId, int documentId, java.lang.String colleagueId) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDto[] getSecurity(java.lang.String username, java.lang.String password, int companyId, int documentId) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.WebServiceMessage[] createFolder(java.lang.String username, java.lang.String password, int companyId, com.totvs.technology.ecm.dm.ws.DocumentDto[] document, com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDto[] security, com.totvs.technology.ecm.dm.ws.ApproverDto[] approvers) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.DocumentDto[] getPrivateChildren(java.lang.String username, java.lang.String password, int companyId, int parentDocumentId) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.WebServiceMessage[] updateFolderWithApprovementLevels(java.lang.String username, java.lang.String password, int companyId, com.totvs.technology.ecm.dm.ws.DocumentDto[] document, com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDto[] security, com.totvs.technology.ecm.dm.ws.ApproverWithLevelDto[] approversWithLevels, com.totvs.technology.ecm.dm.ws.ApprovalLevelDto[] levels) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
    public com.totvs.technology.ecm.dm.ws.WebServiceMessage[] createSimpleFolder(java.lang.String username, java.lang.String password, int companyId, int parentDocumentId, java.lang.String publisherId, java.lang.String documentDescription) throws java.rmi.RemoteException, com.totvs.technology.ecm.dm.ws.Exception;
}
