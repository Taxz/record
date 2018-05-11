package com.bonc.annotation.proxy;

/**
 * Created by Taxz on 2018/5/11.
 */
public class ServiceImpl implements Services {
    @Override
    public String service(String url) {
        System.out.println("来自："+url);
        return "欢迎使用！";
    }
}
