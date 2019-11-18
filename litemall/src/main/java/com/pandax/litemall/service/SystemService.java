package com.pandax.litemall.service;

import java.util.Map;

public interface SystemService {
    Map<String, Object> logList(Integer page, Integer limit, String name, String sort, String order);
}
