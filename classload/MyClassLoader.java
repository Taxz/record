package com.txz.web;

import java.io.*;

/**
 * Created by Taxz on 2019/2/20.
 */
public class MyClassLoader extends ClassLoader {
    
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public MyClassLoader() {
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File file = getClassFile(name);
        try {
            byte[] bytes = getClassBytes(file);
            Class<?> zlass = this.defineClass(name, bytes, 0, bytes.length);
            return zlass;
        } catch (Exception e) {
        }
        return super.findClass(name);
    }

    private byte[] getClassBytes(File file) {
        FileInputStream in =null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] size = new byte[8024];
            int index;
            while ((index = in.read(size)) != -1) {
                out.write(size,0,index);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }

    private File getClassFile(String name) {
        return new File("D:\\tmp\\Client.class");
    }

    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader();
        try {
            Class<?> c = Class.forName("com.txz.web.proxy.Client", true, classLoader);
            Object obj = c.newInstance();
            System.out.println(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
