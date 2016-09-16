package com.jserver;



public class Main {

    public static void main(String[] args) {
        Eval e = new Eval();
        try {
            e.exec("System.out.pntln(\"testing\");");
            e.exec("System.out.println(\"testing2\");");
        } catch (Exception e1) {
            e1.getMessage();
        }
    }

}
