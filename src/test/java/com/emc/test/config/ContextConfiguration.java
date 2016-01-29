package com.emc.test.config;

import com.emc.daas.data_mgmt.DataManagerMock;
import com.emc.daas.user_mgmt.UserManagerMock;
import com.emc.test.controllers.MetaDataTestManager;
import com.emc.test.controllers.UserTestAuthenticator;
import com.emc.test.dao.MetadataDao;
import com.emc.test.metadata.MetadataDBTestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by timofb on 19-Jan-16.
 */
@Configuration
@PropertySource(TestApplication.PROPERTIES_PATH)
public class ContextConfiguration {
    @Autowired
    MetadataDao metadataDao;

    @Bean
    public DataManagerMock dataManager() {
        return new DataManagerMock();
    }

    @Bean
    public MetadataDBTestManager metadataManager() {
        UserTestAuthenticator authenticator = new UserTestAuthenticator();
        MetadataDBTestManager manager = new MetadataDBTestManager(authenticator, metadataDao);
        authenticator.setManager(manager);
        return manager;
    }

    @Bean
    public UserManagerMock userManager() {
        return new UserManagerMock();
    }

}
