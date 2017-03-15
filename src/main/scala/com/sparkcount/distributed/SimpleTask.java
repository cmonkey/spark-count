package com.sparkcount.distributed;

public class SimpleTask extends Task{
    @Override
    public void run(){
        System.out.println("run simple task!");
    }
}
