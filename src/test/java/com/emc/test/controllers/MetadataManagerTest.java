package com.emc.test.controllers;

import com.emc.daas.access_control.UserAccessMode;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;
import com.emc.test.config.TestApplication;
import com.emc.test.metadata.MetadataDBTestManager;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.After;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import static org.junit.Assert.assertTrue;
/**
 * Created by timofb on 18-Dec-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebAppConfiguration
public class MetadataManagerTest {

    private DaaSMetadata meta;
    private UUID testId;
    private String guestUserName = "guest";
    private String adminUserName = "admin";
    private MockMvc mockMvc ;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    private MetadataDBTestManager metadataManager;

    @Before
    public void setUp() {
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
        //metadataManager.pu
        //metadataManager.putMetaToStorage(meta);
    }

    @Test
    public void testAdminHasFullAccess() {
        try {
            DaaSMetadata meta1 = metadataManager.getMeta(testId, adminUserName);
            assertTrue(meta1.getMeta().get("location").equals("/tmp"));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testUserHasNoAccess() {
        try {
            DaaSMetadata meta1 = metadataManager.getMeta(testId, "badUser");
            assertTrue(!meta1.getMeta().containsKey("location"));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


    @Test
    public void testUserCanOnlyReadMeta() {
        try {
            DaaSMetadata meta = metadataManager.getMeta(testId, guestUserName);
            assertTrue(meta.getMeta().get("location").equals("/tmp"));
            try {
                metadataManager.deleteMeta(testId, guestUserName);
            }
            catch (MetadataCannotBeChangedException ex) {
                return;
            }
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @After
    public void tearDown() {
        metadataManager.cleanData();
    }




}
