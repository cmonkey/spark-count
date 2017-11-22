package org.github.cmonkey.spark.enums;

public enum StandardSystemProperty {

    OS_NAME("os.name");

    private final String key;

    StandardSystemProperty(String key ){
                                                this.key = key;
                                                               }

    public String key(){
                                return this.key;
                                                }

    public String value(){
        return System.getProperty(key);
    }

    @Override
    public String toString(){
        return key() + "-" + value();
    }
}

