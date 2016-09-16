package com.jserver;

/**
 * Created by Артем Константинович on 17.09.2016.
 */
import java.util.ArrayList;

public class SavedCodes {
    private ArrayList<CodeExemplar> codeList = new ArrayList();

    public void addCodeExemplar(CodeExemplar ce){
        codeList.add(ce);
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