package com.emc.daas.user_mgmt;
import com.emc.daas.user.DaaSUser;

import java.util.List;

/**
 * Created by timofb on 11/26/2015.
 */
public interface UserManager {

    public DaaSUser getUser(String username);

    public List<DaaSUser> searchUsers(DaaSUser user);

    public boolean deleteUser(String username);
}
