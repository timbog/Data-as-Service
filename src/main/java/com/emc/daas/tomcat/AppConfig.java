package com.emc.daas.tomcat;

import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.access_control.UserMetadataAccess;
import com.emc.daas.data_mgmt.DataManagerMock;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.MetaDataManagerMock;
import com.emc.daas.user_mgmt.UserManagerMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.FilterChainProxy;

import java.util.UUID;

/**
 * Created by timofb on 10/31/2015.
 */
@Configuration
public class AppConfig {

    @Bean
    public DataManagerMock manager() {
        return new DataManagerMock();
    }

    @Bean
    public MetaDataManagerMock metaDataManager() {
        return new MetaDataManagerMock(new UserAuthenticator() {
            @Override
            public boolean hasAccess(String username, UserMetadataAccess action, DaaSMetadata meta) {
                return true;
            }
        });
    }

    @Bean
    public UserManagerMock userManager() {
        return new UserManagerMock();
    }

    @Bean
    public FilterChainProxy springSecurityFilterChain() {
        return new FilterChainProxy();
    }




}
