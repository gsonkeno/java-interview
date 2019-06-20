package com.gsonkeno.springaop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by gaosong on 2017-12-01
 */
@Configuration
@EnableAspectJAutoProxy //启用AspectJ自动代理
@ComponentScan
public class ConcertConfig {

    //创建切面
    @Bean
    public Audience audience(){
        return new Audience();
    }

    @Bean
    public Performance getPerformace(){
        return new Performance() {
            @Override
            public void perform(String style) {
                System.out.println("表演形式" + style);
            }

            @Override
            public void feel(Light light) {
               light.disPlayAtmosphere();
            }
        };
    }
}
