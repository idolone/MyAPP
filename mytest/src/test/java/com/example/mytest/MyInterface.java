package com.example.mytest;
/*
 *
 * 泛型接口
 * 语法：接口名<T>
 注意：不能创建泛型静态常量
 */
public interface MyInterface<T>{
    String name="张三";
    T server(T t);
}
