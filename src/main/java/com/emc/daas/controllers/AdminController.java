package com.emc.daas.controllers;

import com.emc.daas.user.DaaSUser;
import com.emc.daas.user_mgmt.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by timofb on 11/12/2015.
 */
@RestController
@Component
@Secured("ROLE_ADMIN")
@RequestMapping("/management")
public class AdminController {
    @Autowired
    UserManager manager;

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public DaaSUser getUser(@PathVariable String username) {
        return manager.getUser(username);
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public List<DaaSUser> searchUsers(@RequestBody DaaSUser user) {
        return manager.searchUsers(user);
    }


    @RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable String username) {
        return manager.deleteUser(username);
    }

}
