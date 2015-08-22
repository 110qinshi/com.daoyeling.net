package com.daoyeling.net.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * url utils
 * @author xianliu
 *
 */
public class URLUtils {
    
    private static final Logger log = LoggerFactory.getLogger(URLUtils.class);

    public static String getProtocol(String url) {
        try {
            URL u = new URL(url);
            return u.getProtocol();
        } catch (MalformedURLException e) {
            log.error("get url "+url+" exception.",e);
        }
        return null;
    }
    
    public static String getHost(String url) {
        try {
            URL u = new URL(url);
            return u.getHost();
        } catch (MalformedURLException e) {
            log.error("get url "+url+" exception.",e);
        }
        return null;
    }
    
    public static String getFile(String url) {
        try {
            URL u = new URL(url);
            return u.getFile();
        } catch (MalformedURLException e) {
            log.error("get url "+url+" exception.",e);
        }
        return null;
    }
    
    public static String getRef(String url) {
        try {
            URL u = new URL(url);
            return u.getRef();
        } catch (MalformedURLException e) {
            log.error("get url "+url+" exception.",e);
        }
        return null;
    }
    
    public static String getQuery(String url) {
        try {
            URL u = new URL(url);
            return u.getQuery();
        } catch (MalformedURLException e) {
            log.error("get url "+url+" exception.",e);
        }
        return null;
    }
    
    public static InputStream getInputStream(String url) {
        try {
            URL u = new URL(url);
            return u.openStream();
        } catch (IOException e) {
            log.error("get url "+url+" exception.",e);
        }
        return null;
    }
    
    public static InputStreamReader getInputStreamReader(String url) {
        try {
            URL u = new URL(url);
            InputStream in = u.openStream();
            in = new BufferedInputStream(in);
            return new InputStreamReader(in);
        } catch (IOException e) {
            log.error("get url "+url+" exception.",e);
        }
        return null;
    }
    
    public static URLConnection getConnection(String url) {
        try {
            URL u = new URL(url);
            return u.openConnection();
        }catch(IOException e) {
            log.error("get url "+url+" exception.",e);
        }
        return null;
    }
}
