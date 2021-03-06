package com.pandax.litemall.service;

import java.util.HashMap;
import java.util.List;

/**
 * @author Da
 * @version 1.0
 * @date 2019/11/15
 * @time 17:27
 */

public interface AdminService {
    HashMap<String, Object> queryUsers(Integer page,
                                       Integer limit, String sort, String order);
    List<String> selectPermissionByUsername(String username);
}
