package com.sparkcount.distributed;

import java.io.Serializable;

abstract class Task implements Serializable{
    public void run(){
        System.out.println("run task");
    }
}

public class SimpleTask extends Task{
    @Override
    public void run(){
        System.out.println("run simple task!");
    }
}
