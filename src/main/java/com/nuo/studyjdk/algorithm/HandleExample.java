package com.nuo.studyjdk.algorithm;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class HandleExample {
    private int value;

    public static void main(String[] args) throws Throwable {
        VarHandle handle = MethodHandles.lookup().findVarHandle(HandleExample.class, "value", int.class);

        HandleExample obj = new HandleExample();
        handle.set(obj, 10);

        int value = (int) handle.get(obj);
        System.out.println("Value: " + value);
    }
}
