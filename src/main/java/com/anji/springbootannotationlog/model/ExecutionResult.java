package com.anji.springbootannotationlog.model;

import java.util.List;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/11 15:35
 */
public class ExecutionResult {
    //错误编码
    private String resultCode;

    //是否成功标志
    private boolean flag;

    //返回消息
    private String msg;

    //数据条数
    private int total;

    //数据集
    private List<?> data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
