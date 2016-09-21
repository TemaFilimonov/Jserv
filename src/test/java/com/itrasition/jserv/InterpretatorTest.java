package com.itrasition.jserv;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nox on 21.09.2016.
 */
public class InterpretatorTest {
    @Test
    public void interptetateTestSimpleString() throws Exception {
        String Output = Interpretator.interpretate("abcdefgh");
        String Expected = "System.out.print(\"abcdefgh\");";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestSimpleCode() throws Exception {
        String Output = Interpretator.interpretate("{%for(int i; i<5; i++){%}!{%}%}");
        String Expected = "for(int i; i<5; i++){System.out.print(\"!\");}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestSimpleFor() throws Exception {
        String Output = Interpretator.interpretate("{%@10%}!{%@%}");
        String Expected = "for(int i1 = 0; i1<10; ++i1){System.out.print(\"!\");}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestSimpleMath() throws Exception {
        String Output = Interpretator.interpretate("{%=100*456/7-321+474%}");
        String Expected = "System.out.print(100*456/7-321+474);";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestSimpleIf() throws Exception {
        String Output = Interpretator.interpretate("{%?true%}#####{%?%}");
        String Expected = "if(true) {System.out.print(\"#####\");}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestForWithMath() throws Exception {
        String Output = Interpretator.interpretate("{%@100%}{%=i=i+100%}{%@%}{%=i%}");
        String Expected = "for(int i1 = 0; i1<100; ++i1){System.out.print(i=i+100);}System.out.print(i);";
        assertEquals(Output, Expected);
    }

}
