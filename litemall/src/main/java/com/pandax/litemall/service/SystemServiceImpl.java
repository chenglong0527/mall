package com.pandax.litemall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pandax.litemall.bean.Log;
import com.pandax.litemall.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService{
    @Autowired
    LogMapper logMapper;
    @Override
    public Map<String, Object> logList(Integer page, Integer limit, String name, String sort, String order) {
        if(name!=null&&!"".equals(name)){
            name="%"+name+"%";
        }
        PageHelper.startPage(page,limit);
        //获取信息
        List<Log> logList=logMapper.selectByCondition(name,sort,order);
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<Log> logPageInfo = new PageInfo<>(logList);
        long total = logPageInfo.getTotal();
        map.put("total",total);
        map.put("items",logList);
        return map;
    }
}
