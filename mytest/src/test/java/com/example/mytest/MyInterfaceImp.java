package com.example.mytest;

public class MyInterfaceImp implements MyInterface<String>{
    @Override
    public String server(String s) {
        System.out.println("s = " + s);
        return s;
    }
}
