package com.bonc.annotation.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Taxz on 2018/5/11.
 * 调用处理器 的invoke() ，用来处理在动态代理类对象上方法的调用，
 * 通常在该方法中实现预处理或分派到委托类实例上执行，
 */
public class Handler implements InvocationHandler {
    private ServiceImpl service;

    public Handler(ServiceImpl service) {
        this.service = service;
    }
    //获取动态代理对象
    public static Object getProxy(ServiceImpl service){
        return Proxy.newProxyInstance(service.getClass().getClassLoader(),service.getClass().getInterfaces(),new Handler(service));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //例如预处理
        if (args[0].equals("bad")){
            return "谢谢再见！";
        }

        //调用委托类的方法
        return method.invoke(service,args);
    }
}
