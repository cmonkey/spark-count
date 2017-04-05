package com.sparkcount.hack;

import sun.nio.ch.Net;

import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.ServerSocketChannel;

import static io.netty.channel.epoll.EpollChannelOption.SO_REUSEPORT;

/**
 * Created by cmonkey on 17-4-1.
 */
public class SoReusePortHack {

    public void setReusePort(ServerSocketChannel serverSocketChannel){
        try {
            Field fieldFd = serverSocketChannel.getClass().getDeclaredField("fd");
            fieldFd.setAccessible(true);
            FileDescriptor fd = (FileDescriptor)fieldFd.get(serverSocketChannel);

            Method methodSetIntOption0 = Net.class.getDeclaredMethod("setIntOption0", FileDescriptor.class,
                    Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            methodSetIntOption0.setAccessible(true);
            methodSetIntOption0.invoke(null, fd, false, '\uffff',
                    SO_REUSEPORT, 1);
            Class<?> returnType = methodSetIntOption0.getReturnType();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
