package com.itrasition.jserv;



public class Main {

    public static void main(String[] args) {
        Eval e = new Eval();
        SavedCodes savedCodes = new SavedCodes();

        //TODO: take from C# part source with some code.
        //System.out.println(Interpretator.interpretate("{%@n%}<{%@ n + m %}*{%@%}>{%@%}","int n = 2","int m = 3"));

        String uniqueToken = CodeExemplar.createUniqueToken();
        savedCodes.addCodeExemplar(new CodeExemplar(uniqueToken, Interpretator.interpretate("{%@n%}<{%@ n + m %}*{%@%}>{%@%}","int n = 2","int m = 3")));
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
