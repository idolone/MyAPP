package com.example.mytest;
/*
*泛型方法
* 语法：<T>返回值类型*
 */
public class MyGenericMethod {
    //泛型方法
    public <T> void show(T t){
        System.out.println("泛型方法..."+t);
    }
}
