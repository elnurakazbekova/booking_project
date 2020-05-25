package com.iitu.booking.service;

import com.iitu.booking.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserRoleService {

    private static final List<UserRole> ROLES = Arrays.asList(UserRole.values());

    public Map<Integer, String> getRolesMap() {
        Map<Integer, String> rolesMap = new HashMap<>();
        for(int i = 0; i < ROLES.size(); i++){
            rolesMap.put(i + 1, ROLES.get(i).name());
        }
        return rolesMap;
//        return getKeyValueMap(UserRole::getId, UserRole::getName);
    }

}
