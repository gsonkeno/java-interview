package com.gsonkeno;

import com.gsonkeno.appleI.Apple;
import com.gsonkeno.appleI.AppleDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ServiceLoader;

/**
 * Hello world!
 * https://www.jianshu.com/p/46b42f7f593c
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        // 构造出一个专门针对AppleDriver接口的ServiceLoader，以下几点比较重要
        // 1. 成员变量Class<S> service 表示待加载的类或接口
        // 2. ClassLoader loader 用于寻找，加载，初始化真正的服务提供者。
        // 如果在应用程序的全局范围内都没有设置过的话，那这个类加载器默认就是应用程序类加载器
        // 3. LinkedHashMap<String,S> providers 缓存起来的真正服务提供者列表，按照初始化顺序排序
        // 4. LazyIterator lookupIterator懒寻找真正服务提供者
        // 5. ServiceLoader实现了迭代器Iterable<S>接口，所以可以进行遍历
        ServiceLoader<AppleDriver> drivers = ServiceLoader.load(AppleDriver.class);
        Apple apple = new Apple();
        for(AppleDriver driver: drivers ){
            driver.process(apple);
        }

        Enumeration<URL> resources = App.class.getClassLoader().getResources("META-INF/services/com.gsonkeno.appleI.AppleDriver");
        while (resources.hasMoreElements()){
            System.out.println(resources.nextElement());
        }


    }
}
