package com.emc.test.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by timofb on 19-Jan-16.
 */
@SpringBootApplication
@ComponentScan(value = "com.emc.test")
@EnableAutoConfiguration
public class TestApplication extends SpringBootServletInitializer {

    public static final String PROPERTIES_PATH = "classpath:myconfig.properties";
    public static final String HSQLDB_URL = "hsqldb.url";
    public static final String DRIVERCLASS = "hsqldb.driverclass";
    public static final String DBPATH = "hsqldb.path";

    public static void main(final String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}

