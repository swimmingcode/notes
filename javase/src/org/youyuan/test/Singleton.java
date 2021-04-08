package org.youyuan.test;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/8 15:38
 */
public class Singleton {

    static private volatile Singleton singleton = null;

    /**
     * 构造器私有
     */
    private Singleton(){
    }

    public static Singleton getInstance() {
        //双重检查锁
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    System.out.println("创建");
                    singleton = new Singleton();
                    return singleton;
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Singleton.getInstance();
            }).start();
        }
    }


}
