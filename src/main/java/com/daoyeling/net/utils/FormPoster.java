package com.daoyeling.net.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.daoyeling.net.model.QueryObj;

/**
 * form connect, post
 * @author xianliu
 *
 */
public class FormPoster {
    
    private static final Logger log = LoggerFactory.getLogger(FormPoster.class);

    private static String StrBodyFormat = "string";
    private static String jsonBodyFormat = "json";
    
    private int defaultTimeOut = 3000;
    
    private String bodyFormat;
    
    private QueryObj query;
    
    private URL u;
    
    private URLConnection connect;
    
    public FormPoster(String url) {
       this(url, jsonBodyFormat);
    }
    
    public FormPoster(String url, String format) {
        this.bodyFormat = format;
        query = new QueryObj();
        try {
            u = new URL(url);
            connect = u.openConnection();
        }catch(IOException e) {
            log.error("FormUtils exception.",e);
        }
    }
    
    public void setQuery(String key, Object value) {
        query.setQuery(key, value);
    }
    
    public String queryStr() throws Exception {
        return getQuery(bodyFormat);
    }
    
    public String getQuery(String format) throws Exception {
        if(StrBodyFormat.equalsIgnoreCase(format)) {
            return query.toString();
        }else if(jsonBodyFormat.equalsIgnoreCase(format)) {
            return query.toJsonString();
        }else {
            throw new Exception("unsupport format :"+format);
        }
    }
    
    public InputStream post() throws Exception {
        return this.post(StrBodyFormat, defaultTimeOut);
    }
    
    public InputStream post(String format, int timeout) throws Exception {
        connect.setDoOutput(true);
        connect.setConnectTimeout(timeout);
        OutputStreamWriter writer = new OutputStreamWriter(connect.getOutputStream(),"utf-8");
        String queryStr = getQuery(format);
        log.debug("query string:"+queryStr);
        writer.write(queryStr);
        writer.write("\r\n");
        writer.flush();
        writer.close();
        return connect.getInputStream();
    }
    
    public InputStream httpPost(String format, int timeout,Map<String, Object> head) throws Exception {
        HttpURLConnection httpConnect = (HttpURLConnection) connect;
        httpConnect.setRequestMethod("POST");
        httpConnect.setDoOutput(true);
        httpConnect.setDoInput(true);
        if(null != query) {//set head params
            for(Map.Entry<String, Object> entry : head.entrySet()) {
                httpConnect.setRequestProperty(entry.getKey(), entry.getValue().toString());
            }
        }
        OutputStreamWriter writer = new OutputStreamWriter(httpConnect.getOutputStream(),"utf-8");
        String queryStr = getQuery(format);
        writer.write(queryStr);
        writer.write("\r\n");
        writer.flush();
        writer.close();
        return httpConnect.getInputStream();
    }
}
