package com.restart.cache;

import java.util.HashMap;
import java.util.Map;

public class TokenCache {

    private static TokenCache tokenCache;

    private Map<Long, String> tokenMap;

    public TokenCache(){
        this.tokenMap = new HashMap<Long, String >();
    }



    public static TokenCache getInstance(){
        if(tokenCache == null){
            synchronized (TokenCache.class){
                if(tokenCache == null){
                    tokenCache = new TokenCache();
                }
            }
        }
        return  tokenCache;
    }


    public void setToken(Long userName, String token){
        tokenMap.put(userName, token);
    }

    public String getToken(Long userName, String token){
        if(tokenMap.containsKey(userName)){
            return  tokenMap.get(userName);
        }
        return null;
    }
}
