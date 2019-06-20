package com.gsonkeno.springaop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gaosong on 2017-12-01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcertConfig.class)
public class ConcertTest {
    @Autowired
    private Audience audience;

    @Autowired
    private Performance performance;

    @Test
    public void test(){
        //Spring的AOP行为是在方法级别上的
        performance.perform("音乐");
        System.out.println("----------");
        performance.feel(new RedLight());
    }

}
