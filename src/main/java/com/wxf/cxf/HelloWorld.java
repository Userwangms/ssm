package com.wxf.cxf;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    public String sayHello(String str);

}
