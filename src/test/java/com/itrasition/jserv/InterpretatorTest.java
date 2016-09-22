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
        String Output = Interpretator.interpretate("{%for(int i=0; i<5; i++){%}!{%}%}");
        String Expected = "for(int i=0; i<5; i++){System.out.print(\"!\");}";
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
        String Expected = "if(true){System.out.print(\"#####\");}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestForWithMath() throws Exception {
        String Output = Interpretator.interpretate("{%int i=0;%}{%@11%}{%=i=i+100%}{%@%}{%=i%}");
        String Expected = "int i=0;for(int i1 = 0; i1<11; ++i1){System.out.print(i=i+100);}System.out.print(i);";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestForWithIf() throws Exception {
        String Output = Interpretator.interpretate("{%@12%}{%?true%}!{%?%}{%@%}");
        String Expected = "for(int i1 = 0; i1<12; ++i1){if(true){System.out.print(\"!\");}}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestForWithCode() throws Exception {
        String Output = Interpretator.interpretate("{%@3%}{%for(int i=0; i<3; i++){System.out.print(i*i);}%}{%@%}");
        String Expected = "for(int i1 = 0; i1<3; ++i1){for(int i=0; i<3; i++){System.out.print(i*i);}}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestIfWithCode() throws Exception {
        String Output = Interpretator.interpretate("{%?5==5%}{%for(int i=0; i<10; i++){System.out.print(i*i);}%}{%?%}");
        String Expected = "if(5==5){for(int i=0; i<10; i++){System.out.print(i*i);}}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestIfWithMath() throws Exception {
        String Output = Interpretator.interpretate("{%?5*3==15%}{%=121*111%}{%?%}");
        String Expected = "if(5*3==15){System.out.print(121*111);}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestCodeWithMath() throws Exception {
        String Output = Interpretator.interpretate("{%int i=121, j=111;%}{%=i*j%}");
        String Expected = "int i=121, j=111;System.out.print(i*j);";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestMathWithVariables() throws Exception {
        String Output = Interpretator.interpretate("{%=i*j/i-j+i%}","int i = 9","int j = 34");
        String Expected = "int i = 9;int j = 34;System.out.print(i*j/i-j+i);";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestIfWithVariables() throws Exception {
        String Output = Interpretator.interpretate("{%?k==true%}.....{%?%}", "boolean k=true");
        String Expected = "boolean k=true;if(k==true){System.out.print(\".....\");}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestForWithMathAndVariables() throws Exception {
        String Output = Interpretator.interpretate("{%@j%}{%=i=i+100%}{%@%}", "int i=0", "int k=100", "int j=11");
        String Expected = "int i=0;int k=100;int j=11;for(int i1 = 0; i1<j; ++i1){System.out.print(i=i+100);}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestForWithIfAndVariables() throws Exception {
        String Output = Interpretator.interpretate("{%@j%}{%?f=true%}!{%?%}{%@%}","boolean f=false","int j =12");
        String Expected = "boolean f=false;int j =12;for(int i1 = 0; i1<j; ++i1){if(f=true){System.out.print(\"!\");}}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestForWithCodeAndVariables() throws Exception {
        String Output = Interpretator.interpretate("{%@j%}{%for(int i=0; i<5; i++){System.out.print(k*k);}%}{%@%}","int k=10", "int j =3");
        String Expected = "int k=10;int j =3;for(int i1 = 0; i1<j; ++i1){for(int i=0; i<5; i++){System.out.print(k*k);}}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestIfWithCodeAndVariables() throws Exception {
        String Output = Interpretator.interpretate("{%?f==true%}{%for(int i=0; i<j; i++){System.out.print(f);}%}{%?%}","boolean f=true","int j=10");
        String Expected = "boolean f=true;int j=10;if(f==true){for(int i=0; i<j; i++){System.out.print(f);}}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestIfWithMathAndVariables() throws Exception {
        String Output = Interpretator.interpretate("{%?j*3==k%}{%=(k+j)/13%}{%?%}","int k =15","long j = 5");
        String Expected = "int k =15;long j = 5;if(j*3==k){System.out.print((k+j)/13);}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestCodeWithMathAndVariables() throws Exception {
        String Output = Interpretator.interpretate("{%String s = \"IAmString\";System.out.print(s);%}{%=i*j*p%}","int i=121","int j=111","double p =3.14");
        String Expected = "int i=121;int j=111;double p =3.14;String s = \"IAmString\";System.out.print(s);System.out.print(i*j*p);";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestExample1() throws Exception {
        String Output = Interpretator.interpretate("{% fo%}{%r(int i = 0; i < 5; i++){ %}*{%}%}");
        String Expected = " for(int i = 0; i < 5; i++){ System.out.print(\"*\");}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestExample2() throws Exception {
        String Output = Interpretator.interpretate("{%for(int i = 0; i < 5; i++){%}{%=i%}{%}%}");
        String Expected = "for(int i = 0; i < 5; i++){System.out.print(i);}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestExample3() throws Exception {
        String Output = Interpretator.interpretate("{%@n%}<{%@ n + m %}*{%@%}>{%@%}","int n=2","int m=3");
        String Expected = "int n=2;int m=3;for(int i1 = 0; i1<n; ++i1){System.out.print(\"<\");for(int i2 = 0; i2< n + m ; ++i2){System.out.print(\"*\");}System.out.print(\">\");}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestExample4() throws Exception {
        String Output = Interpretator.interpretate("{%?s.equals(\"\"TEST\"\")%}TRUE{%@3%}!{%@%}{%?%}");
        String Expected = "if(s.equals(\"\"TEST\"\")){System.out.print(\"TRUE\");for(int i1 = 0; i1<3; ++i1){System.out.print(\"!\");}}";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestExample5() throws Exception {
        String Output = Interpretator.interpretate("ab{%int i = 10;%}cd{%=i%}ef");
        String Expected = "System.out.print(\"ab\");int i = 10;System.out.print(\"cd\");System.out.print(i);System.out.print(\"ef\");";
        assertEquals(Output, Expected);
    }
    @Test
    public void interptetateTestAll() throws Exception {
        String Output = Interpretator.interpretate("{%for(int i = 0; i<5; i++){%}{%?true%}{%=i%}{%?%}{%}%}");
        String Expected = "for(int i = 0; i<5; i++){if(true){System.out.print(i);}}";
        assertEquals(Output, Expected);
    }
}
