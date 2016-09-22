package com.itrasition.jserv;

import org.junit.Assert;
import org.junit.Test;
import java.io.*;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.*;

/**
 * Created by Артем Константинович on 20.09.2016.
 */
public class EvalTest {
    @Test
    public void createCodeTest() throws Exception {
        Eval eval = new Eval();
        String s = eval.createCode("System.out.print(\"testing\");");
        String Expected = " import java.lang.String;import java.util.Calendar;public class SpecialClassToCompile {public void evalFunc(){System.out.print(\"testing\");}}";
        assertEquals(s, Expected);
    }
    @Test
    public void createCodeTestWithOwnClass() throws Exception {
        Eval eval = new Eval();
        String s = eval.createCode(" public class TestClass {public void testFunc(){System.out.print(\"testing\");}}");
        String Expected = "class TestClass {public void testFunc()";
        assertTrue(s.lastIndexOf(Expected) != -1);
    }
    @Test
    public void execTest() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("System.out.print(\"testing\");"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "testing");
    }
    @Test
    public void execFail() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("System.out.int(\"testing\");"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertTrue(s.lastIndexOf("error: <identifier> expected") != -1);
    }
    @Test
    public void createCodeTestSimpleString() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("System.out.print(\"abcdefgh\");"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "abcdefgh");
    }
    @Test
    public void createCodeTestSimpleCode() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("for(int i=0; i<5; i++){System.out.print(\"!\");}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "!!!!!");
    }
    @Test
    public void createCodeTestSimpleFor() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("for(int i1 = 0; i1<10; ++i1){System.out.print(\"!\");}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "!!!!!!!!!!");
    }
    @Test
    public void createCodeTestSimpleMath() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("System.out.print(100*456/7-321+474);"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "6667");
    }
    @Test
    public void createCodeTestSimpleIf() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("if(true){System.out.print(\"#####\");}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "#####");
    }
    @Test
    public void createCodeTestForWithMath() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("int i=0;for(int i1 = 0; i1<11; ++i1){System.out.print(i=i+100);}System.out.print(i);"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "100200300400500600700800900100011001100");
    }
    @Test
    public void createCodeTestForWithIf() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("for(int i1 = 0; i1<12; ++i1){if(true){System.out.print(\"!\");}}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "!!!!!!!!!!!!");
    }
    @Test
    public void createCodeTestForWithCode() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("for(int i1 = 0; i1<3; ++i1){for(int i=0; i<3; i++){System.out.print(i*i);}}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "014014014");
    }
    @Test
    public void createCodeTestIfWithCode() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("if(5==5){for(int i=0; i<10; i++){System.out.print(i*i);}}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "0149162536496481");
    }
    @Test
    public void createCodeTestIfWithMath() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("if(5*3==15){System.out.print(121*111);}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "13431");
    }
    @Test
    public void createCodeTestCodeWithMath() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("int i=121, j=111;System.out.print(i*j);"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "13431");
    }
    @Test
    public void createCodeTestMathWithVariables() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("int i = 9;int j = 34;System.out.print(i*j/i-j+i);"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "9");
    }
    @Test
    public void createCodeTestIfWithVariables() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("boolean k=true;if(k==true){System.out.print(\".....\");}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, ".....");
    }
    @Test
    public void createCodeTestForWithMathAndVariables() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("int i=0;int k=100;int j=11;for(int i1 = 0; i1<j; ++i1){System.out.print(i=i+100);}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "10020030040050060070080090010001100");
    }
    @Test
    public void createCodeTestForWithIfAndVariables() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("boolean f=false;int j =12;for(int i1 = 0; i1<j; ++i1){if(f=true){System.out.print(\"!\");}}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "!!!!!!!!!!!!");
    }
    @Test
    public void createCodeTestForWithCodeAndVariables() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("int k=10;int j =3;for(int i1 = 0; i1<j; ++i1){for(int i=0; i<5; i++){System.out.print(k*k);}}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "100100100100100100100100100100100100100100100");
    }
    @Test
    public void createCodeTestIfWithCodeAndVariables() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("boolean f=true;int j=10;if(f==true){for(int i=0; i<j; i++){System.out.print(f);}}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "truetruetruetruetruetruetruetruetruetrue");
    }
    @Test
    public void createCodeTestIfWithMathAndVariables() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("int k =15;long j = 5;if(j*3==k){System.out.print((k+j)/13);}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "1");
    }
    @Test
    public void createCodeTestCodeWithMathAndVariables() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("int i=121;int j=111;double p =3.14;String s = \"IAmString\";System.out.print(s);System.out.print(i*j*p);"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertEquals(s, "IAmString42173.340000000004");
    }
    @Test
    public void execFailVariableDoNotInitialized() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("for(int i; i<5; i++){System.out.print(\"@\");}"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertTrue(s.lastIndexOf("error: variable i might not have been initialized") != -1);
    }
    @Test
    public void execFailException() throws Exception {
        Eval eval = new Eval();
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baOut);
        System.setOut(out);
        System.setErr(out);
        eval.exec(eval.createCode("for(int i=0; i<5; i++){System.out.print(\"@\");"));
        String s = baOut.toString();
        PrintStream sysOut = System.out;
        System.setOut(sysOut);
        assertTrue(s.lastIndexOf("error: reached end of file while parsing") != -1);
    }
}