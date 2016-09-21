package com.itrasition.jserv;

/**
 * Created by Артем Константинович on 21.09.2016.
 */

import javax.jws.WebService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@WebService(endpointInterface = "com.itrasition.jserv.ISOAPService")
public class SOAPService implements ISOAPService{

    @Override
    public String getCode(String code) {
        Eval e = new Eval();
        e.exec(e.createCode(Interpretator.interpretate(code,"int n = 2","int m = 3")));

        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        String answer = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);

        return answer;
    }
}
