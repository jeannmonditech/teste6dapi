
package com.monditech.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UltimaAtualizacaoBancoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ultimaAtualizacaoBancoResult"
})
@XmlRootElement(name = "UltimaAtualizacaoBancoResponse")
public class UltimaAtualizacaoBancoResponse {

    @XmlElement(name = "UltimaAtualizacaoBancoResult")
    protected String ultimaAtualizacaoBancoResult;

    /**
     * Obtém o valor da propriedade ultimaAtualizacaoBancoResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUltimaAtualizacaoBancoResult() {
        return ultimaAtualizacaoBancoResult;
    }

    /**
     * Define o valor da propriedade ultimaAtualizacaoBancoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUltimaAtualizacaoBancoResult(String value) {
        this.ultimaAtualizacaoBancoResult = value;
    }

}
