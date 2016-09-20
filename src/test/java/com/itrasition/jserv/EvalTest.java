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
        Assert.assertEquals(s, "testing");
    }

}