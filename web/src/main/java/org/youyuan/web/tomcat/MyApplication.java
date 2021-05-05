package org.youyuan.web.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/3 19:36
 */
public class MyApplication {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8888);
        //context Path
        Context context = tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());
        StandardRoot resourceRoot = new StandardRoot(context);

        context.setResources(resourceRoot);
        tomcat.start();
        tomcat.getServer().await();
    }

}
