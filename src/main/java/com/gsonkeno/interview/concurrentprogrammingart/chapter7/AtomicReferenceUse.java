package com.gsonkeno.interview.concurrentprogrammingart.chapter7;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子更新引用类型
 * @author gaosong
 * @since 2019-03-30
 */
public class AtomicReferenceUse {

    public static void main(String[] args) {
        User zhang = new User("zhang", "13");
        AtomicReference<User> ar = new AtomicReference<User>();
        ar.set(zhang);
        User li = new User("li", "14");
        ar.compareAndSet(zhang,li);
        System.out.println(ar.get());
    }

    final static class User{
        String name;
        String old;

        public User(String name, String old) {
            this.name = name;
            this.old = old;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", old='" + old + '\'' +
                    '}';
        }
    }
}
