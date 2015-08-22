package com.daoyeling.net.utils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * connect utils
 * @author xianliu
 *
 */
public class ConnectUtils {

    private static final Logger log = LoggerFactory.getLogger(ConnectUtils.class);
    
    public static InputStream getInputStream(String url) throws IOException {
        URLConnection connect = URLUtils.getConnection(url);
        return connect.getInputStream();
    }
    
    public static InputStreamReader getInputStreamReader(String url) throws IOException {
        InputStream in = getInputStream(url);
        return new InputStreamReader(in);
    }
    
    public static void downLoadBinaryFile(String url, String saveFiledir) throws IOException {
        log.debug("downLoadBinaryFile, url:{}, saveFile:{}",url, saveFiledir);
        URL u = new URL(url);
        URLConnection connect = u.openConnection();
        String contentType = connect.getContentType();
        int contextLength = connect.getContentLength();
        
        if(contentType.startsWith("text") || contextLength == -1) {
            throw new IOException("this is not a binary file url");
        }
        InputStream in = connect.getInputStream();
        in = new BufferedInputStream(in);
        byte[] datas = new byte[contextLength];
        int byteRead = 0;
        int offset = 0;
        while(offset < contextLength) {
            byteRead = in.read(datas, offset, datas.length-offset);
            if(byteRead == -1) {
                break;
            }
            offset += byteRead;
        }
        //read done
        in.close();
        if(offset != contextLength) {
            throw new IOException("only read:"+offset+" byte, but contentLength "+contextLength+" byte.");
        }
        String file = u.getFile();
        int index = file.lastIndexOf("/");
        if(-1 != index) {
            file = file.substring(index, file.length());
        }
        FileOutputStream fout = new FileOutputStream(saveFiledir + file);
        fout.write(datas);
        fout.flush();
        fout.close();
    }
    
}
