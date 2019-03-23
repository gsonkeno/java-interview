package com.gsonkeno.interview.jvm.understandJvm;
/**
 * 创建线程导致内存溢出异常
 * @author gaosong
 * @since 2019-03-24
 */
public class JavaVMStackOOM {

    private void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    //执行时，要小心，可能导致操作系统假死
    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
