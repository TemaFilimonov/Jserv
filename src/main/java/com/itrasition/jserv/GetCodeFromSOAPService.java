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
    @XmlElement(name = "encriptedCode", namespace = "")
    private String encriptedCode;


    public String getEncriptedCode() {
        return this.encriptedCode;
    }

    public void setEncriptedCode(String encriptedCode) {
        this.encriptedCode = encriptedCode;
    }
}


