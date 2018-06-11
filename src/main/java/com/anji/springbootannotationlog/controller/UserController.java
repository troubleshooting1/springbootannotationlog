package com.anji.springbootannotationlog.controller;

import com.anji.springbootannotationlog.annotation.SystemControllerLog;
import com.anji.springbootannotationlog.model.ExecutionResult;
import com.anji.springbootannotationlog.model.User;
import com.anji.springbootannotationlog.service.UserService;
import com.anji.springbootannotationlog.util.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/11 16:01
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/userlist")
    @SystemControllerLog(description = "查询用户信息",actionType = "4")
    public ExecutionResult getUserList(String id) throws Exception{
        ExecutionResult result=new ExecutionResult();

        try{
            List<User> users=userService.findAll();
            result.setTotal(users.size());
            result.setResultCode(ReturnCode.RES_SUCCESS);
            result.setFlag(true);
            result.setData(users);
            result.setMsg("查询成功");

            //异常处理
//            int aa=5/0;
        }catch (Exception e){
            result.setFlag(true);
            result.setData(null);
            result.setResultCode(ReturnCode.RES_FAILED);
            result.setMsg("查询失败");
            throw  e;
        }

        return result;
    }
}
