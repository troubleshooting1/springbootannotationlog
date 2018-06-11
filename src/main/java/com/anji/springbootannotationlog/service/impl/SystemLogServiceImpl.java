package com.anji.springbootannotationlog.service.impl;

import com.anji.springbootannotationlog.model.SystemLog;
import com.anji.springbootannotationlog.repository.SystemLogRepository;
import com.anji.springbootannotationlog.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/11 15:31
 */
@Service
public class SystemLogServiceImpl implements SystemLogService {
    @Autowired
    SystemLogRepository systemLogRepository;

    @Override
    public List<SystemLog> findAll() {
        return null;
    }

    @Override
    public void saveUser(SystemLog log) {
        systemLogRepository.save(log);
    }

    @Override
    public SystemLog findOne(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
