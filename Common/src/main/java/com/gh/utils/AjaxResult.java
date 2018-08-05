package com.gh.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class AjaxResult {
    @Getter
     private Map<String,Object> params;

    public  Map<String,Object> put(String key,Object value){
        if(params == null){
            params = new HashMap<>();
        }
        params.put(key,value);
        return params;
    }
}
