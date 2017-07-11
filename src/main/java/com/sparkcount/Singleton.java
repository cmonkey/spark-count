package com.sparkcount;


import java.io.IOException;
import java.io.Serializable;

/**
 * Created by cmonkey on 7/11/17.
 */
public class Singleton implements Serializable{

    private volatile static Singleton instance = null;

    private Singleton(){
        if(null != instance){
            throw new NullPointerException("Singleton instance is not null");
        }
    }

    public static Singleton getInstance(){

        if(null != instance){

            synchronized (Singleton.class){

                if(null != instance){
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

    protected Singleton readResolve() throws IOException {
        return getInstance();
    }
}
