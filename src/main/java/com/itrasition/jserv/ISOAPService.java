package com.itrasition.jserv;

/**
 * Created by Артем Константинович on 21.09.2016.
 */

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
public interface ISOAPService {
    @WebMethod String getCode(String code);
}
