package com.nuo.studyjdk.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Adjust {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Optional.ofNullable(list).map(v->v.size()>2).isPresent();
    }
}
