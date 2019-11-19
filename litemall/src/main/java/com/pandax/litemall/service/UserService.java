package com.pandax.litemall.service;

import java.util.List;

/**
 * @author Da
 * @version 1.0
 * @date 2019/11/15
 * @time 19:48
 */

public interface UserService {
    int countUsers();

    List<String> selectPermissionByUsername(String username);
}
