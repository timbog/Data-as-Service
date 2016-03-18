package com.emc.test.controllers;

import com.emc.daas.access_control.UserAccessMode;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;
import com.emc.test.config.TestApplication;
import com.emc.test.metadata.MetadataDBTestManager;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Created by timofb on 16-Mar-16.
 */
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebAppConfiguration
public class ManagerTest {

    protected DaaSMetadata meta;
    protected UUID testId;
    protected String guestUserName = "guest";
    protected String adminUserName = "admin";
    protected MockMvc mockMvc ;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    protected MetadataDBTestManager metadataManager;
    
    public void config() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .apply(springSecurity())
                .dispatchOptions(true).build();

        meta = new DaaSMetadata();
        testId = UUID.randomUUID();
        meta.setUUID(testId);
        Map<String, String> metaRecords = new HashMap<String, String>();
        Map<String, String> acl = new HashMap<String, String>();
        acl.put(adminUserName, UserAccessMode.FULL.name());
        acl.put(guestUserName, UserAccessMode.READMETA.name());
        //acl.put("readMetaUser")
        String json = (new Gson()).toJson(acl);
        metaRecords.put("ACL", json);
        metaRecords.put("admin_email", "admin@emc.com");
        metaRecords.put("location", "/tmp");
        meta.setMeta(metaRecords);
        try {
            metadataManager.putMeta(testId, metaRecords, adminUserName);
        }
        catch (MetadataCannotBeChangedException ex) {
            Assert.fail();
        }
    }
}
