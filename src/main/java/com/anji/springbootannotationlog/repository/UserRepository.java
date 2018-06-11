package com.anji.springbootannotationlog.repository;

import com.anji.springbootannotationlog.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/11 16:08
 */
public interface UserRepository extends CrudRepository<User, Long>{

    @Query("select l from User l ")
    public List<User> findAll();
}
