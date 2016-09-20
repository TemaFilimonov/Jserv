package com.itrasition.jserv;



public class Main {

    public static void main(String[] args) {
        Eval e = new Eval();
        SavedCodes savedCodes = new SavedCodes();

        //TODO: take from C# part source with some code.
        String uniqueToken = CodeExemplar.createUniqueToken();
        savedCodes.addCodeExemplar(new CodeExemplar(uniqueToken, e.createCode("System.out.println(\"testing\");")));
        //TODO: Send to C# part token "uniqueToken".

        //TODO: after request compile code.
        try {
            e.exec(savedCodes.findCodeExemplar(uniqueToken).getCode());
        } catch (Exception e1) {
            e1.getMessage();
        }
        //TODO: send to c# part result of compiling.
    }

}
