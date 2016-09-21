package com.itrasition.jserv;

/**
 * Created by Артем Константинович on 21.09.2016.
 */

import javax.jws.WebService;

@WebService(endpointInterface = "com.itrasition.jserv.ISOAPService")
public class SOAPSevice implements ISOAPService{

    @Override
    public String getEncriptedCode(String answer) {
        return answer;
    }
}
