package com.emc.test.controllers;

import com.emc.daas.access_control.UserAuthenticator;
import com.emc.daas.metadata.DaaSMetadata;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeAccessedException;
import com.emc.daas.metadata_mgmt.exceptions.MetadataCannotBeChangedException;
import com.emc.daas.metadata_mgmt.MetadataManager;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

/**
 * Created by timofb on 18-Dec-15.
 */
@Deprecated
public class MetaDataTestManager extends MetadataManager {

    public static final String TESTDIRPATH = System.getProperty("java.io.tmpdir");
    public static final File METADATASTORAGE = new File(System.getProperty("java.io.tmpdir"), "metadataStorage.txt");

    public MetaDataTestManager(UserAuthenticator authenticator) {
        super(authenticator);
    }

    @Override
    protected DaaSMetadata getMetaFromStorage(final UUID id) {
        try {
            Gson gson = new Gson();
            List<DaaSMetadata> list = gson.fromJson(new BufferedReader(new InputStreamReader(new FileInputStream(METADATASTORAGE))), new TypeToken<List<DaaSMetadata>>() {
            }.getType());
            Iterable<DaaSMetadata> filtered = Iterables.filter(list, new com.google.common.base.Predicate<DaaSMetadata>() {
                @Override
                public boolean apply(DaaSMetadata daaSMetaData) {
                    return (daaSMetaData.getUUID().equals(id));
                }
            });
            List<DaaSMetadata> filteredCopy = Lists.newArrayList(filtered);
            return  filteredCopy.get(0);
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected boolean putMetaToStorage(DaaSMetadata meta) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            String content = new String(Files.readAllBytes(Paths.get((METADATASTORAGE
                    .getAbsolutePath()))));
            List<DaaSMetadata> metaList = new ArrayList<DaaSMetadata>();
            if (content.length() != 0) {
                metaList = (new Gson()).fromJson(new BufferedReader(new InputStreamReader(new FileInputStream(METADATASTORAGE))),
                        new TypeToken<List<DaaSMetadata>>() {}.getType());
            }
            metaList.add(meta);
            new PrintWriter(METADATASTORAGE.getAbsolutePath()).close();
            FileWriter fw = new FileWriter(METADATASTORAGE,true);
            fw.write(gson.toJson(metaList));
            fw.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    protected DaaSMetadata cutMetadata(DaaSMetadata meta) {
        HashMap<String, String> newMetaList = new HashMap<String, String>();
        newMetaList.put("admin_email", meta.getMeta().get("admin_email"));
        return new DaaSMetadata(meta.getUUID(), newMetaList);
    }

    @Override
    protected Iterator<DaaSMetadata> searchDataSetsInStorage(DaaSMetadata meta, String username)  {
        return null;
    }

    @Override
    protected Iterator<DaaSMetadata> searchDataSetsInStorage(Predicate<DaaSMetadata> meta, String username)  {
        return null;
    }

    @Override
    protected boolean deleteMetaFromStorage(final UUID id) throws MetadataCannotBeChangedException {
        try {
            Gson gson = new Gson();
            List<DaaSMetadata> list = gson.fromJson(new BufferedReader(new InputStreamReader(new FileInputStream(METADATASTORAGE))), new TypeToken<List<DaaSMetadata>>() {}.getType());
            list.removeIf(new Predicate<DaaSMetadata>() {
                @Override
                public boolean test(DaaSMetadata metadata) {
                    return metadata.getUUID().equals(id);
                }
            });
            new PrintWriter(METADATASTORAGE.getAbsolutePath()).close();
            for (DaaSMetadata metadata: list) {
                putMetaToStorage(metadata);
            }
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
