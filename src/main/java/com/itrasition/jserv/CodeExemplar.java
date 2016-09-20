package com.itrasition.jserv;

/**
 * Created by Артем Константинович on 17.09.2016.
 */
public class CodeExemplar {
    private String uniqueToken;
    private String code;

    public CodeExemplar(String uniqueToken, String code) {
        this.uniqueToken = uniqueToken;
        this.code = code;
    }

    public String getUniqueToken() {
        return uniqueToken;
    }

    public void setUniqueToken(String uniqueToken) {
        this.uniqueToken = uniqueToken;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
