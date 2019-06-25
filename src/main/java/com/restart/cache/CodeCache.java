package com.restart.cache;

import java.util.HashMap;
import java.util.Map;

public class CodeCache {


    private static CodeCache instance;

    private Map<Long, String> codeMap;

    public CodeCache() {
        this.codeMap = new HashMap<Long, String>();
    }

    public static CodeCache getInstance(){
        if(instance == null){
            synchronized (CodeCache.class){
                if(instance == null){
                    instance = new CodeCache();
                }
            }
        }
        return instance;
    }

    public boolean save(Long userName, String code){
        if(codeMap.containsKey(userName)){
            return false;
        }
        codeMap.put(userName, code);
        return  true;
    }

    public String getCode(Long userName){
        if(codeMap.containsKey(userName)){
            return  codeMap.get(userName);
        }
        return  null;
    }
}
