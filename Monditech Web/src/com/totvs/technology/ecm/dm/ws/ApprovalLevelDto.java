/**
 * ApprovalLevelDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.totvs.technology.ecm.dm.ws;

public class ApprovalLevelDto  implements java.io.Serializable {
    private int approvalMode;

    private java.lang.String levelDescription;

    private int levelId;

    private boolean mandatorySignature;

    public ApprovalLevelDto() {
    }

    public ApprovalLevelDto(
           int approvalMode,
           java.lang.String levelDescription,
           int levelId,
           boolean mandatorySignature) {
           this.approvalMode = approvalMode;
           this.levelDescription = levelDescription;
           this.levelId = levelId;
           this.mandatorySignature = mandatorySignature;
    }


    /**
     * Gets the approvalMode value for this ApprovalLevelDto.
     * 
     * @return approvalMode
     */
    public int getApprovalMode() {
        return approvalMode;
    }


    /**
     * Sets the approvalMode value for this ApprovalLevelDto.
     * 
     * @param approvalMode
     */
    public void setApprovalMode(int approvalMode) {
        this.approvalMode = approvalMode;
    }


    /**
     * Gets the levelDescription value for this ApprovalLevelDto.
     * 
     * @return levelDescription
     */
    public java.lang.String getLevelDescription() {
        return levelDescription;
    }


    /**
     * Sets the levelDescription value for this ApprovalLevelDto.
     * 
     * @param levelDescription
     */
    public void setLevelDescription(java.lang.String levelDescription) {
        this.levelDescription = levelDescription;
    }


    /**
     * Gets the levelId value for this ApprovalLevelDto.
     * 
     * @return levelId
     */
    public int getLevelId() {
        return levelId;
    }


    /**
     * Sets the levelId value for this ApprovalLevelDto.
     * 
     * @param levelId
     */
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }


    /**
     * Gets the mandatorySignature value for this ApprovalLevelDto.
     * 
     * @return mandatorySignature
     */
    public boolean isMandatorySignature() {
        return mandatorySignature;
    }


    /**
     * Sets the mandatorySignature value for this ApprovalLevelDto.
     * 
     * @param mandatorySignature
     */
    public void setMandatorySignature(boolean mandatorySignature) {
        this.mandatorySignature = mandatorySignature;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApprovalLevelDto)) return false;
        ApprovalLevelDto other = (ApprovalLevelDto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.approvalMode == other.getApprovalMode() &&
            ((this.levelDescription==null && other.getLevelDescription()==null) || 
             (this.levelDescription!=null &&
              this.levelDescription.equals(other.getLevelDescription()))) &&
            this.levelId == other.getLevelId() &&
            this.mandatorySignature == other.isMandatorySignature();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getApprovalMode();
        if (getLevelDescription() != null) {
            _hashCode += getLevelDescription().hashCode();
        }
        _hashCode += getLevelId();
        _hashCode += (isMandatorySignature() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApprovalLevelDto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.dm.ecm.technology.totvs.com/", "approvalLevelDto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("approvalMode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "approvalMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("levelDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "levelDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("levelId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "levelId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandatorySignature");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandatorySignature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
