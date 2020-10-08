
package com.monditech.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZoomConsultarExamesResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "zoomConsultarExamesResult"
})
@XmlRootElement(name = "ZoomConsultarExamesResponse")
public class ZoomConsultarExamesResponse {

    @XmlElement(name = "ZoomConsultarExamesResult")
    protected String zoomConsultarExamesResult;

    /**
     * Obt�m o valor da propriedade zoomConsultarExamesResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZoomConsultarExamesResult() {
        return zoomConsultarExamesResult;
    }

    /**
     * Define o valor da propriedade zoomConsultarExamesResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZoomConsultarExamesResult(String value) {
        this.zoomConsultarExamesResult = value;
    }

}
