package com.anji.springbootannotationlog.service;

import com.anji.springbootannotationlog.model.SystemLog;

import java.util.List;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/11 15:16
 */
public interface SystemLogService {
    public List<SystemLog> findAll();

    public void saveUser(SystemLog log);

    public SystemLog findOne(long id);

    public void delete(long id);
}
