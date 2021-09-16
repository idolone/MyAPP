package com.example.mytest;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

    /*
    1.添加元素
    2.删除元素
    3.遍历元素
    4.判断
     */
    @Test
    public void testCollection(){

        Collection collection = new ArrayList();

        collection.add("111");
        collection.add("222");
        collection.add("333");
        collection.add("444");
        collection.add("111");

        System.out.println("num:"+collection.size());
        System.out.println(collection);

//        collection.remove("333");
//        System.out.println(collection);

        //遍历
        //1.增强for
        for (Object co:collection
             ) {
            System.out.println(co);
        }
        //迭代器（专门用来遍历集合的一种方式）
        /*
          hasNext():有没有下一个元素
          next():获取下一个元素
          remove():
         */
        Iterator iterator = collection.iterator();

        while(iterator.hasNext()){
            String s = (String)iterator.next();
            System.out.println(s);
           // collection.remove(s);//在遍历过程中，不能通过集合来删除元素  可以使用迭代器来删除
           // iterator.remove();
        }
        /*
        判断
         */
        System.out.println(collection.contains("111"));
        System.out.println(collection.isEmpty());
    }

    @Test
    public void TestList(){

        List list = new ArrayList();
        //1.增加元素
        list.add("苹果");
        list.add("小米");
        list.add("华为");
        list.add("小米");

        System.out.println("元素个数"+list.size());
        System.out.println(list.toString());
        //2.删除元素
        // list.remove(0);
//        list.remove("小米");
//        System.out.println("删除后元素个数"+list.size());
//        System.out.println(list.toString());
        //3.遍历
        //3.1.for遍历
        System.out.println("-----for 遍历-------");
         for (int i = 0;i < list.size();i++){
             System.out.println(list.get(i));
         }
         //3.2增强for遍历
        System.out.println("-----增强for 遍历-------");
        for (Object l:list) {
            System.out.println(l);

        }

         //3.3 iterator遍历
         System.out.println("-----Iterator 遍历-------");
         Iterator iterator = list.iterator();
         while(iterator.hasNext()){
             System.out.println(iterator.next());
         }
        //3.4 列表迭代器iterator遍历  可以向前向后遍历，可以添加可以删除元素
        System.out.println("-----listIterator 遍历-------");
        ListIterator iterator1 = list.listIterator();
//        System.out.println("-----listIterator 向后遍历-------");
//        while(iterator1.hasNext()){
//            System.out.print(iterator1.nextIndex()+": ");
//            System.out.println(iterator1.next());
//        }
        System.out.println("-----listIterator 向前遍历-------");

        while(iterator1.hasPrevious()){
            System.out.print(iterator1.previousIndex()+": ");
            System.out.println(iterator1.previous());
        }
        //4.判断
        System.out.println(list.contains("1"));
        System.out.println(list.isEmpty());

        //5.获取位置
        System.out.println(list.indexOf("小米"));
    }
    /*
      ArrayList的使用
      存储结构：数组，查找遍历速度快，增删慢
     */
    @Test
    public void TestArrayList(){
         ArrayList arrayList = new ArrayList<>();
         //1.增加元素
        Customer s1 = new Customer("liudehua",20);
        Customer s2 = new Customer("li昂",20);
        Customer s3 = new Customer("guo",20);
        Customer s4 = new Customer("张",20);

         arrayList.add(s1);
         arrayList.add(s2);
         arrayList.add(s3);
         arrayList.add(s4);

        System.out.println("元素个数："+arrayList.size());
        System.out.println(arrayList.toString());
         //2.删除元素

         //3.遍历元素
            //3.1使用迭代器
        System.out.println("-------3.1使用迭代器-------");
        Iterator iterator = arrayList.iterator();
        while(iterator.hasNext()){
            Customer student = (Customer)iterator.next();
            System.out.println(student.toString());
        }
        //3.2使用列表迭代器
        System.out.println("-------3.2使用列表迭代器-------");
        ListIterator iterator1 = arrayList.listIterator();
        while(iterator1.hasNext()){
            Customer student = (Customer)iterator1.next();
            System.out.println(student.toString());
        }
        System.out.println("-------3.2使用列表迭代器向前遍历-------");
        while(iterator1.hasPrevious()){
            Customer student = (Customer)iterator1.previous();
            System.out.println(student.toString());
        }

        //4.判断
        System.out.println("-------判断-------");
        System.out.println(arrayList.contains(new Customer("张",20)));
        System.out.println(arrayList.isEmpty());

        //5.查找
        System.out.println("-------查找-------");
        System.out.println(arrayList.indexOf(new Customer("张",20)));

    }
    /*
     * LinkedList的使用
     * 存储结构：双向链表
     */
    @Test
    public void TestLinkedList(){

        LinkedList linkedList = new LinkedList<>();
        //1.添加元素
        Customer s1 = new Customer("liudehua",20);
        Customer s2 = new Customer("li昂",20);
        Customer s3 = new Customer("guo",20);
        Customer s4 = new Customer("张",20);

        linkedList.add(s1);
        linkedList.add(s2);
        linkedList.add(s3);
        linkedList.add(s4);

        System.out.println("元素个数："+linkedList.size());
        System.out.println(linkedList.toString());
        //2.删除元素
//        linkedList.remove(s1);
//        System.out.println("删除之后："+linkedList.toString());
//        linkedList.clear();


        //3.遍历元素
          //3.1使用for
        System.out.println("------  for 遍历------");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
        //3.2增强for
        System.out.println("------  增强for 遍历------");

        for (Object ll:linkedList) {
            Customer cs = (Customer)ll;
            System.out.println(cs.toString());
        }
        //3.3使用迭代器
        System.out.println("-------3.3使用迭代器-------");
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()){
            Customer cs = (Customer) iterator.next();
            System.out.println(cs.toString());
        }
        //3.4使用列表迭代器
        System.out.println("-------3.4使用列表迭代器-------");
        ListIterator listIterator = linkedList.listIterator();
        while(listIterator.hasNext()){
            Customer cs = (Customer) listIterator.next();
            System.out.println(cs.toString());
        }

        //4.判断
        System.out.println("-------判断-------");
        System.out.println(linkedList.contains(new Customer("张",20)));
        System.out.println(linkedList.isEmpty());

        //5.查找
        System.out.println("-------查找-------");
        System.out.println(linkedList.indexOf(s2));
    }

    @Test
    public void TestGeneric(){
     //注意1.泛型只能使用引用类型 2.不同泛型对象之间不能相互赋值
        MyGeneric<String>  myGeneric = new MyGeneric<String>();

        myGeneric.t = "Hello";
        myGeneric.show("world");
        String str = myGeneric.getT();

        MyGeneric<Integer>  myGeneric1 = new MyGeneric<>();

        myGeneric1.t = 100;
        myGeneric1.show(200);
        Integer a = myGeneric1.getT();

        MyInterfaceImp imp = new MyInterfaceImp();

        imp.server("implemetion  interface");

        MyInterfaceImp2<String> imp2 = new MyInterfaceImp2<>();
        imp2.server("test MyInterfaceImp2");

        MyGenericMethod myGenericMethod = new MyGenericMethod();
        myGenericMethod.show("test myGenericMethod");
        myGenericMethod.show(200);
    }
    //1.添加元素
    //2.删除元素
    //3.遍历
    //4.判断
    //5.添加元素
    /*
     * 特点1.无序，无下标 2.不能重复
     */
    @Test
    public void TestSet(){
        //创建集合
        Set<String> set = new HashSet<>();
        //1.添加元素
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
       // set.add("bbb");
        System.out.println("集合元素个数:"+set.size());
        System.out.println(set);

        //2.删除元素
//        set.remove("aaa");
//        System.out.println("集合元素个数:"+set.size());
//        System.out.println(set);
        //3.遍历
          //3.1使用增强for
        System.out.println("------增强for遍历-----");
        for (String s:set) {
            System.out.println(s);
        }
         //3.2使用迭代器遍历
        System.out.println("------3.2使用迭代器遍历-----");
         Iterator<String> iterator= set.iterator();
         while (iterator.hasNext()){
             System.out.println(iterator.next());
         }
        //4.判断
        //5.添加元素
    }
    /*
    *HashSet集合的使用
    * 存储结构：哈希表（数组+链表+红黑树）
    * 1.根据hashcode计算保存的位置，如果此位置为空，则直接保存，如果不为空执行第二步
    * 2.再执行equals方法，如果equals方法为true，则认为是重复的，否则形成链表
     */

    /*
    *TreeSet
    * 存储结构：红黑树
    * 要求：元素必须要实现Comparable接口,compareto()返回值为0，认为是重复元素
    * Comparator:实现订制比较（比较器）
    * Comparable：可比较的
     */
    @Test
    public void TestTreeSet(){
        TreeSet<Person> persons = new TreeSet<>();
        //1.添加元素
        Person p1 = new Person("xyz",20);
        Person p2 = new Person("abc",22);
        Person p3 = new Person("lmn",25);
        Person p4 = new Person("lmn",22);

        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);


        System.out.println("Treeset元素个数："+persons.size());
        System.out.println(persons.toString());
        //2.删除
        persons.remove(new Person("lmn",25));
        System.out.println("Treeset元素个数："+persons.size());
        System.out.println(persons.toString());
        //3.遍历
        //4.判断
        System.out.println(persons.contains(new Person("lmn",22)));

    }

    @Test
    public void TestTreeSet1(){
        //这个就不需要元素来实现Comparable接口
        TreeSet<Person> persons = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int n1 = o1.getAge()-o2.getAge();
                int n2 = o1.getName().compareTo(o2.getName());
                return n1==0?n2:n1;
            }
        });
    }
    /**
     * 要求：使用TreeSet集合实现字符串按照长度进行排序
     *

     */
    @Test
    public void TestTreeSet2(){

           TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
               @Override
               public int compare(String o1, String o2) {
                   int n1 = o1.length() - o2.length();
                   int n2 = o1.compareTo(o2);
                   return n1 == 0 ? n2 : n1;
               }
           });;

           treeSet.add("helloworld");
           treeSet.add("pingguo");
           treeSet.add("cat");
           treeSet.add("xian");
           treeSet.add("beijing");
           treeSet.add("lisi");
           treeSet.add("jack");

           System.out.println(treeSet.toString());
    }
    /**
     * Map接口的使用
     * 特点1.存储键值对 2.键不能重复，值可以重复 3.无序
     *
     */
    @Test
    public void TestKeyMap(){
        Map<String,String> map = new HashMap<>();
        //1.添加元素
        map.put("cn","中国");
        map.put("uk","英国");
        map.put("usa","美国");
        map.put("jp","日本");
        map.put("cn","zhongguo");

        System.out.println("元素个数："+map.size());
        System.out.println(map.toString());

        //2.删除
//        map.remove("jp");
//        System.out.println("删除后元素个数："+map.size());
//        System.out.println(map.toString());

        //3.遍历
           //3.1使用keySet（）
        System.out.println("-------keySet()遍历--------");
       // Set<String> keyset = map.keySet();
        for (String str:map.keySet()) {
            System.out.println(str + "----" + map.get(str));
        }
        //3.2使用entrySet（）
        System.out.println("-------entrySet()遍历--------");

        Set<Map.Entry<String,String>> entries = map.entrySet();
        for (Map.Entry<String,String> entry:entries ) {
            System.out.println(entry.getKey()+"----"+entry.getValue());
        }

        //4.判断
        System.out.println(map.containsKey("jk"));
        System.out.println(map.containsValue("泰国"));
    }
    /**
     * HashMap的使用
     * 存储结构：哈希表(素组+链表+红黑树)
     * 使用key和hashcode与equals
     */
    @Test
    public void TestHashMap(){

        HashMap<Police,String> pl = new HashMap<>();
        //1.添加元素
        Police p1 = new Police("孙悟空",001);
        Police p2 = new Police("猪八戒",002);
        Police p3 = new Police("杀神",003);

        pl.put(p1,"beijing");
        pl.put(p2,"shanghai");
        pl.put(p3,"hangzhou");
        pl.put(new Police("杀神",003),"hangzhou");

        System.out.println("元素个数："+ pl.size());
        System.out.println(pl.toString());

        //删除元素
        pl.remove(p1);
        System.out.println("删除后元素个数："+ pl.size());
        System.out.println(pl.toString());

        //3.遍历
         //3.1 keySet遍历
        System.out.println("------keySet遍历------");
        for (Police police : pl.keySet()) {
            System.out.println(police.toString() + "----" + pl.get(police));
        }
        System.out.println("------entrySet遍历------");
        for (Map.Entry<Police, String> entry  :pl.entrySet()) {
            System.out.println(entry.getKey() + "----" + entry.getValue());

        }



    }



}

