package com.itrasition.jserv;

/**
 * Created by Артем Константинович on 17.09.2016.
 */
import java.util.ArrayList;

public class SavedCodes {
    private ArrayList<CodeExemplar> codeList = new ArrayList();

    public String addCodeExemplar(CodeExemplar ce){
        codeList.add(ce);
        return ce.getUniqueToken();
    }

    public CodeExemplar findCodeExemplar(String token){
        for (CodeExemplar exem: codeList){
            if (exem.getUniqueToken().equals(token)){
                return exem;
            }
        }
        return null;
    }
}
