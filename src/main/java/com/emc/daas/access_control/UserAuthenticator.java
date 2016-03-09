package com.emc.daas.access_control;

import com.emc.daas.metadata.DaaSMetadata;

/**
 * Created by timofb on 01-Dec-15.
 */
public interface UserAuthenticator {
    boolean hasAccess(String username, UserAccessMode action, DaaSMetadata meta);
}
