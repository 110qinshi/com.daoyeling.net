package com.daoyeling.net.enums;

/**
 * @author xianliu
 * 
 */
public enum ProtocolEnum {
    HTTP("http"), HTTPS("https"), FTP("ftp"), MAILTO("mailto"), TELNET("telnet"), FILE("file"), JAR("jar");

    private String name;

    private ProtocolEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
