package com.itrasition.jserv;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;



public class Main {

    public static void main(String[] args) {

        final String ADDRESS = "http://localhost:56405/WebService1.asmx?op=HelloWorld";

        Endpoint.publish(ADDRESS,  new SOAPService());
        URL url = null;
        
        try {
            url = new URL(ADDRESS);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        QName qname = new QName("http://jserv.itransition.com/", "AnswerToSharp");
        Service service = Service.create(url, qname);
        ISOAPService hello = service.getPort(ISOAPService.class);

        System.out.println(hello.getCode(Eval.createCode(Interpretator.interpretate("{%@n%}<{%@ n + m %}*{%@%}>{%@%}","int n = 2","int m = 3"))));




    }

}
