package com.emc.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.File;

/**
 * @author nikita.sokeran@emc.com
 */

@Configuration
@Profile("default")
@PropertySource(TestApplication.PROPERTIES_PATH)
public class LocalDataSourceConfig {

    @Autowired
    Environment env;

    @Bean
    public DriverManagerDataSource derbyDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(TestApplication.DRIVERCLASS));
        File file = new File(env.getProperty(TestApplication.DBPATH));
        dataSource.setUrl("jdbc:hsqldb:file:" + file.getAbsolutePath().replace("\\", "/"));
        //dataSource.setPassword("");
        return dataSource;
    }

//    @Bean
//    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
//        final MongoClientURI mongoClientURI = new MongoClientURI(env.getProperty(Application.PROPERTY_MONGO_URI));
//        return new SimpleMongoDbFactory(mongoClientURI);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() throws UnknownHostException {
//        final MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
//        mongoTemplate.setWriteConcern(WriteConcern.SAFE);
//        return mongoTemplate;
//    }
}