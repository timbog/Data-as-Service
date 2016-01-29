package com.emc.daas.user_mgmt;

import com.emc.daas.user.DaaSUser;

import java.util.List;

/**
 * Created by timofb on 11/26/2015.
 */
public class UserManagerMock implements UserManager {
    @Override
    public DaaSUser getUser(String username) {
        return null;
    }

    @Override
    public List<DaaSUser> searchUsers(DaaSUser user) {
        return null;
    }

    @Override
    public boolean deleteUser(String username) {
        return false;
    }
}
