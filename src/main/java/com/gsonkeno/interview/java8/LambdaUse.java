package com.gsonkeno.interview.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaUse {

    //优雅的处理User可能为null的情况
    static  String getUserName(User user){

        //将一般对象转换为Optional对象，可能是一个包裹值的Optional，
        // 也可能是一个没有值的Optional
        Optional<User> optUser = Optional.ofNullable(user);

        //map映射，获取name属性；如果u为空实例，直接返回空实例;
        //如果u不为空实例，但是u.getName()返回null，则结果返回空Optional
        Optional<String> name = optUser.map(u -> u.getName());


        //如果name为空Optional,则返回自定义的值；否则，返回name实例包裹的value值
        return name.orElse("unKown");

        //1.
        // 如果采用链式调用，则代码应该书写为以下形式
        //return Optional.ofNullable(user).map(u->u.getName()).orElse("unKown");

        //2.
        //但是如果我们并不需要返回一个默认值，而是结果为null时，抛出自定义异常怎么办呢?
        //可以使用orElseThrow方法如下
        //name.orElseThrow(()->new IllegalArgumentException("the name of the user is not prsent"));
    }

    static class User{
        private  String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        User user = new User();
        String userName = getUserName(user);
        System.out.println(userName);

        user = null;
        System.out.println(getUserName(user));
    }

    @Test
    public void testOption1(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        //转化成Optional对象->optional为空，则转为空集合list->逐一打印
        Optional.ofNullable(list).orElse(Collections.emptyList()).forEach(System.out::println);
        //list为null或者空集合(无元素)都不会报错
        list = new ArrayList<>();
        Optional.ofNullable(list).orElse(Collections.emptyList()).forEach(System.out::println);
        list =null;
        Optional.ofNullable(list).orElse(Collections.emptyList()).forEach(System.out::println);

        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(null);
        //报错因为有空
        try {
            Optional.ofNullable(list).orElse(Collections.emptyList()).forEach(o-> System.out.println(o.intern()));
        }catch (Exception e){
            e.printStackTrace();
        }

        //使用Filter过滤空元素
        list.stream().filter(Objects::nonNull).forEach(o-> System.out.println(o.intern()));
    }

    @Test
    public void testList(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"斯特恩"));
        students.add(new Student(2,"肖华"));
        students.add(null);

        //求出name集合
        List<String> names = students.stream().filter(Objects::nonNull).map(Student::getName).collect(Collectors.toList());
        names.forEach(System.out::println);

        //求出map
        Map<Integer, Student> map = students.stream().filter(Objects::nonNull).collect(
                Collectors.toMap(Student::getId, s -> s)
        );
        System.out.println(map);

        //还可以使用limit, skip对stream进行分页
    }


    final class Student{
        int id;
        String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
