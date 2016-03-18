package com.emc.test.config;

import com.emc.daas.access_control.UserAccessMode;
import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.data_mgmt.DataManagerMock;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.MetaDataManagerMock;
import com.emc.daas.user_mgmt.UserManagerMock;
import com.emc.test.controllers.MetaDataTestManager;
import com.emc.test.controllers.UserTestAuthenticator;
import com.emc.test.dao.MetadataDao;
import com.emc.test.data.DataTestManager;
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
    public MetadataDBTestManager metadataManager() {
        UserTestAuthenticator authenticator = new UserTestAuthenticator();
        MetadataDBTestManager manager = new MetadataDBTestManager(authenticator, metadataDao);
        authenticator.setManager(manager);
        return manager;
    }

    @Autowired
    MetadataDBTestManager metadataDBTestManager;

    @Bean
    public DataTestManager dataManager() {
        //UserAuthenticator authenticator = new UserTestAuthenticator();
        return new DataTestManager(metadataDBTestManager.getUserAuthenticator(), metadataDBTestManager);
    }

    @Bean
    public UserManagerMock userManager() {
        return new UserManagerMock();
    }

}
