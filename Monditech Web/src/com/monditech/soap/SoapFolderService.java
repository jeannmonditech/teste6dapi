package com.monditech.soap;

import javax.jws.WebService;

import com.monditech.fluig.webservices.ECMFolderServiceService;
import com.monditech.fluig.webservices.Exception_Exception;
import com.monditech.fluig.webservices.FolderService;
import com.monditech.fluig.webservices.WebServiceMessageArray;

public class SoapFolderService {	
	public WebServiceMessageArray CriaPasta(int parentDocumentId, String publisherId, String documentDescription) {
		System.out.println("CriaPasta_1aaaaaaaaaaaaa_1");
		ECMFolderServiceService folderService = new ECMFolderServiceService();
		System.out.println("CriaPasta_1aaaaaaaaaaaaa_1_2");
		FolderService service = folderService.getFolderServicePort();
		System.out.println("CriaPasta_1aaaaaaaaaaaaa_1_3");
		WebServiceMessageArray ws = null;

		
		System.out.println("CriaPasta_1aaaaaaaaaaaaa_2");
		try {
			System.out.println("CriaPasta_1aaaaaaaaaaaaa_3");
			ws = service.createSimpleFolder("monditech", "m0nd1t3ch.2018", 1, parentDocumentId, publisherId, documentDescription);
			System.out.println("CriaPasta_1aaaaaaaaaaaaa_4");
		} catch (Exception_Exception e) {
			
			System.out.println("ERRO AO CRIAR A PASTA! " + e.getMessage());
			e.printStackTrace();
		}
		return ws;
	}
}
