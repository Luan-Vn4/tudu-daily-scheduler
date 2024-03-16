package br.upe.tudu.configuration;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class TomcatServerConfig {

    private static final String WEBAPP_DIR = new File("src/main/webapp").getAbsolutePath();

    private static final Tomcat tomcat = new Tomcat();

    private static String port = System.getenv("PORT");

    public static void startServer() {
        if (port == null || port.isEmpty()) {
            port = "8080";
        }

        Context context = loadStaticResources();
        loadServletResources(context);

        launchServer();
    }

    private static Context loadStaticResources() {
        tomcat.getConnector().setPort(Integer.parseInt(port));
        return tomcat.addWebapp("/tudu", new File(WEBAPP_DIR).getAbsolutePath());
    }

    private static void loadServletResources(Context context) {
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(context);

        // Especifica, no destino de compilação, onde estarão os servlets que serão utilizadas pelo Tomcat
        resources.addPreResources(new DirResourceSet(
                resources,
                "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(),
                "/"));

        context.setResources(resources);
    }

    private static void launchServer() {
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException("Tomcat Server não pode ser inicializado", e);
        }
        tomcat.getServer().await();
    }

}
