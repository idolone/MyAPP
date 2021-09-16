package com.example.mytest;

public class MyInterfaceImp2<T> implements MyInterface<T>{

    @Override
    public T server(T t) {
        System.out.println("t = " + t);
        return t;
    }
}
