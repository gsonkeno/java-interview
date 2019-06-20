package com.gsonkeno.springaop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by gaosong on 2017-12-01
 */
@Aspect
public class Audience {

//    @Before("execution(** com.gsonkeno.aop.spring.Performance.perform(..))")
//    public void silencePhone(){
//        System.out.println("手机静音");
//    }
//
//    @Before("execution(** com.gsonkeno.aop.spring.Performance.perform(..))")
//    public void takeSeats(){
//        System.out.println("观众做下");
//    }
//    @AfterReturning("execution(** com.gsonkeno.aop.spring.Performance.perform(..))")
//    public void applause(){
//        System.out.println("观众鼓掌");
//    }
//
//    @AfterThrowing("execution(** com.gsonkeno.aop.spring.Performance.perform(..))")
//    public void demandRefund(){
//        System.out.println("观众要求退票");
//    }

    //注意，切点表达式中的参数名称要与切点方法中的参数名称一致，这里都是styles
    @Around("execution(** com.gsonkeno.springaop.Performance.perform(String)) && args(styles)")
    public void watchPerformance(ProceedingJoinPoint jp, String styles){
        try {
            System.out.println("手机静音->" + styles);
            System.out.println("观众坐下->"+ styles);
            jp.proceed();
            System.out.println("观众鼓掌->"+ styles);
        }catch (Throwable e){
            System.out.println("观众要求退票->"+ styles);
        }
    }

    //注意，切点表达式中的参数名称要与切点方法中的参数名称一致，这里都是styles
    @Around("execution(** com.gsonkeno.springaop.Performance.feel(Light)) && args(light)")
    public void watchPerformance(ProceedingJoinPoint jp, Light light){
        try {
            System.out.println("手机静音->" + light.getLightName());
            System.out.println("观众坐下->"+ light.getLightName());
            jp.proceed();
            System.out.println("观众鼓掌->"+ light.getLightName());
        }catch (Throwable e){
            System.out.println("观众要求退票->"+ light.getLightName());
        }
    }
}
