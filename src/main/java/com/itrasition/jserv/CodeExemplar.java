package com.itrasition.jserv;

import java.util.Random;

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

    public static String createUniqueToken(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String token = sb.toString();
        return token;
    }
}
