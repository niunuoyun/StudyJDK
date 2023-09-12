package com.nuo.studyjdk.algorithm;

import com.nuo.studyjdk.queryvo.ChatDocQueryVo;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

public class TestJDK9 {
    public static void main(String[] args) throws Exception {
        var t = "hello word";
        System.out.println(t);

        //  queryVo.setPresence_penalty(1.0f);
        ChatDocQueryVo queryVo = new ChatDocQueryVo();
        Field privateField = ChatDocQueryVo.class.getDeclaredField("top_k");
        privateField.setAccessible(true);
        privateField.setInt(queryVo,10);
        System.out.println(privateField.get(queryVo));
        VarHandle varHandle = MethodHandles.lookup().findVarHandle(ChatDocQueryVo.class,"random_seed",int.class);
        varHandle.set(queryVo,1);
        System.out.println(varHandle.get(queryVo));
    }
}
