package org.github.cmonkey.spark;

public class DebuggableMain implements Debuggable{

    int a = 100;
    String b = "a";

    public static void main(String[] args) {
        Debuggable main = new DebuggableMain();
        System.out.println(main.debug());
    }
}
