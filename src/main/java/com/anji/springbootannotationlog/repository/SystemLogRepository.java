package com.anji.springbootannotationlog.repository;

import com.anji.springbootannotationlog.model.SystemLog;
import org.springframework.data.repository.CrudRepository;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/11 15:33
 */
public interface SystemLogRepository extends CrudRepository<SystemLog, Long> {
}
