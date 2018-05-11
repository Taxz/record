package com.bonc.annotation.proxy;

/**
 * Created by Taxz on 2018/5/11.
 */
public class Test {
    public static void main(String[] args) {
        ServiceImpl service = new ServiceImpl();
        Services proxy = (Services) Handler.getProxy(service);
        String s = proxy.service("good");
        System.out.println(s);
    }
}
