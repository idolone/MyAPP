package com.example.myapp;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void TestArray(){
        System.out.println("test");
        ArrayList arrayList = new ArrayList<>();
        arrayList.add("000");
        arrayList.add("111");
        arrayList.add("333");
        arrayList.add("222");
        System.out.println(arrayList);

        Collection collection = new ArrayList();

        collection.add("0aaa");
        collection.add("1bbb");
        collection.add("2ccc");
        collection.add("3ddd");
        collection.add("4eee");

        System.out.println("num:"+collection.size());
        System.out.println(collection);

        arrayList.addAll(4,collection);

        System.out.println("num:"+arrayList.size());
        System.out.println(arrayList);



    }

    @Test
    public void TestList1() {

        List list = new ArrayList();
        //1.增加元素
        list.add("苹果");
        list.add("小米");
        list.add("华为");
        list.add("小米");

        System.out.println("元素个数"+list.size());
        System.out.println(list.toString());

        for (Object ob : list) {
            System.out.println(ob.toString());
        }

        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        ListIterator iterator1 = list.listIterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.nextIndex() + "--" + iterator1.next());
        }




    }

}