package com.example.mytest;
/*
 *泛型类
 * 语法：类名<T>
 * T:表示类型占位符，表示一种引用类型
 */

public class MyGeneric<T> {
    //使用泛型T
    //1.泛型创建变量
      T t; //不能new方法来创建
    //2.泛型作为方法的参数
      public void show(T t){
            System.out.println("t = " + t);
      }
    //3.泛型作为方法的返回值
      public T getT(){
            return t;
      }
}
