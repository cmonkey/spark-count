package org.github.cmonkey.spark.distributed;
import java.io.Serializable;

/**
 * Created by cmonkey on 3/15/17.
 */
public abstract class Task implements Serializable{

    public void run(){

        System.out.println("run task!");
    }
}
