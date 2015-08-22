package com.daoyeling.net.model;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author xianliu
 *
 */
public class QueryObj {

    private Map<String, Object> query = new HashMap<String, Object>();
    
    public void setQuery(String key, Object value) {
        query.put(key, value);
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for(Map.Entry<String, Object> entry : query.entrySet()) {
            if(i == 0) {
                builder.append(entry.getKey()+"="+entry.getValue());
            }else {
                builder.append("&"+entry.getKey()+"="+entry.getValue());
            }
            i++;
        }
        return  builder.toString();
    }
    
    public String toJsonString() {
        return JSON.toJSONString(query);
    }
}
