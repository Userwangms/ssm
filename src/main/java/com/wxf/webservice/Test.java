package com.wxf.webservice;


/**
 * 测试webservice
 */
public class Test {
    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorldImplService().getHelloWorldImplPort();
        String mm = helloWorld.sayHello("MM");
        System.out.println(mm);
    }
}
