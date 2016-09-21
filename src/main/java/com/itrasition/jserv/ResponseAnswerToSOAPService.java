package com.itrasition.jserv;

/**
 * Created by Артем Константинович on 21.09.2016.
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "responseAnswerToSOAPService", namespace = "http://jserv.itransition.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseAnswerToSOAPService", namespace = "http://jserv.itransition.com/")
public class ResponseAnswerToSOAPService {

    @XmlElement(name = "answer", namespace = "")
    private String answer;


    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}