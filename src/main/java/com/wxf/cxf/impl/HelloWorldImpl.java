package com.wxf.cxf.impl;

import com.wxf.cxf.HelloWorld;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@WebService
@Component("helloWorld")
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String sayHello(String str) {
        System.out.println("Hello," + str);
        return "Hello," + str;
    }
}
