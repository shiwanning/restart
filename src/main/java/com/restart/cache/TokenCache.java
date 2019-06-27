package com.restart.cache;


import java.util.HashMap;
import java.util.Map;

public class TokenCache {

    private static TokenCache tokenCache;

    private Map<String, Long> tokenMap;

    public TokenCache(){
        this.tokenMap = new HashMap<String, Long>();
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
        tokenMap.put(token, userName);
    }

    public Long getToken(String token){
        if(tokenMap.containsKey(token)){
            return  tokenMap.get(token);
        }
        return null;
    }
}
