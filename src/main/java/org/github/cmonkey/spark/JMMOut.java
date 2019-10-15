package org.github.cmonkey.spark;

import java.lang.ref.WeakReference;

/**
 * Created by cmonkey on 17-7-12.
 */
public class JMMOut {

    public static void main(String[] args){
        JMMOut jmmOut = new JMMOut();

        jmmOut.call(jmmOut);
    }

    public void call(JMMOut jmmOut){

        new MyTask(jmmOut).start();
    }

    class MyTask extends Thread{

        private WeakReference<JMMOut> reference;

        public MyTask(JMMOut jmmOut){
            reference = new WeakReference<>(jmmOut);
        }

        private JMMOut doSome(){

            return new JMMOut();
        }
        @Override
        public void run(){

            if(reference.get() != null) {
                doSome();
            }
        }
    }
}
