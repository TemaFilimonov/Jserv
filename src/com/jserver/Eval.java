package com.jserver;

/**
 * Created by Артем Константинович on 16.09.2016.
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;


public class Eval {
    public void exec(String code){
        String src = createCode(code);
        tryCompile(src);
    }
    public void exec(String[] pcg, String code)  {
        String src = addPcges(pcg);
        src += createCode(code);
        tryCompile(src);
    }

    public void compileMemoryMemory(String src, String name, SpecialClassLoader classLoader, PrintStream err) {
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diacol = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager standartFileManager = javac.getStandardFileManager(diacol, null, null);
        SpecialJavaFileManager fileManager = new SpecialJavaFileManager(standartFileManager, classLoader);
        CompilationTask compile = javac.getTask(null, fileManager, diacol, null, null,
                Arrays.asList(new JavaFileObject[] { new MemorySource(name, src) }));
        boolean status = compile.call();
        if(err != null) {
            err.println("Compile status: " + status);
            for(Diagnostic<? extends JavaFileObject> dia : diacol.getDiagnostics()) {
                err.println(dia);
            }
        }
    }
    private void tryCompile(String src){
        try {
            SpecialClassLoader classLoader = new SpecialClassLoader();
            compileMemoryMemory(src, "SpecialClassToCompile", classLoader, System.err);
            Class<?> c = Class.forName("SpecialClassToCompile", false, classLoader);
            Object o = c.newInstance();
            c.getMethod("evalFunc", new Class[]{}).invoke(o, new Object[]{});
        } catch(Exception ex){
            ex.getMessage();
        }
    }
    private String addPcges(String[] pcg){
        String src = "";
        for (int i = 0; i < pcg.length; ++i){
            src += "package "+pcg[i]+";";
        }
        return src;
    }
    public String createCode(String code){
        String src = addIncludes();
        if (code.lastIndexOf("class") == -1){
            src += "public class SpecialClassToCompile {";
        }
        if (code.lastIndexOf("public")<=2){
            src += "public void evalFunc(){";
        }
        src += code + "}}";
        return src;

    }
    private String addIncludes (){
        return " import java.lang.String;" +
                "import java.util.Calendar;";
    }
}

class MemorySource extends SimpleJavaFileObject {
    private String src;
    public MemorySource(String name, String src) {
        super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
        this.src = src;
    }
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return src;
    }
}

class MemoryByteCode extends SimpleJavaFileObject {
    private ByteArrayOutputStream oStream;
    public MemoryByteCode(String name) {
        super(URI.create("byte:///" + name.replace('/','.') + Kind.CLASS.extension),Kind.CLASS);
    }
    public OutputStream openOutputStream() {
        oStream = new ByteArrayOutputStream();
        return oStream;
    }
    public byte[] getBytes() {
        return oStream.toByteArray();
    }
}

class SpecialJavaFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
    private SpecialClassLoader classLoader;
    public SpecialJavaFileManager(StandardJavaFileManager fileManager, SpecialClassLoader specClassLoader) {
        super(fileManager);
        classLoader = specClassLoader;
    }
    public JavaFileObject getJavaFileForOutput(Location location, String name, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        MemoryByteCode byteCode = new MemoryByteCode(name);
        classLoader.addClass(byteCode);
        return byteCode;
    }
}

class SpecialClassLoader extends ClassLoader {
    private MemoryByteCode byteCode;
    protected Class<?> findClass(String name) {
        return defineClass(name, byteCode.getBytes(), 0, byteCode.getBytes().length);
    }
    public void addClass(MemoryByteCode code) {
        byteCode = code;
    }
}