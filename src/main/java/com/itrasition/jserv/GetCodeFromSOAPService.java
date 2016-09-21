package com.itrasition.jserv;

/**
 * Created by Артем Константинович on 21.09.2016.
 */


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name = "getCodeFromSOAPService", namespace = "http://jserv.itransition.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCodeFromSOAPService", namespace = "http://jserv.itransition.com/")
public class GetCodeFromSOAPService {

    @XmlElement(name = "sourcecode", namespace = "")
    private String sourcecode;

    @XmlElement(name = "namespaces", namespace = "")
    private String namespaces;

    @XmlElement(name = "variabletype", namespace = "")
    private String variabletype;


    public String getSourcecode() {
        return sourcecode;
    }
    public void setSourcecode(String sourcecode) {
        this.sourcecode = sourcecode;
    }

    public String getNamespaces() {
        return namespaces;
    }
    public void setNamespaces(String namespaces) {
        this.namespaces = namespaces;
    }

    public String getVariabletype() {
        return variabletype;
    }
    public void setVariabletype(String variabletype) {
        this.variabletype = variabletype;
    }

}