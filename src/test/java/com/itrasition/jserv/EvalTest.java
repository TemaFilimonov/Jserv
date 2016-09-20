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
    public void createCode() throws Exception {
        Eval eval = new Eval();
        String s = eval.createCode("System.out.print(\"testing\");");
        String Expected = " import java.lang.String;import java.util.Calendar;public class SpecialClassToCompile {public void evalFunc(){System.out.print(\"testing\");}}";
        assertEquals(s, Expected);
    }
    @Test
    public void createCodeWithOwnClass() throws Exception {
        Eval eval = new Eval();
        String s = eval.createCode(" public class TestClass {public void testFunc(){System.out.print(\"testing\");}}");
        String Expected = "class TestClass {public void testFunc()";
        assertTrue(s.lastIndexOf(Expected) != -1);
    }
    @Test
    public void exec() throws Exception {
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

}