package com.thriteen.bgd.usersync.utils;

/**
 * @Author: Lph
 * @Date: 2020/11/13 15:25
 * @Function:
 * @Version 1.0
 */
public class WebResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public WebResult() {
        this.code = ExceptionEnum.SUCCESS.getCode();
        this.msg = ExceptionEnum.SUCCESS.getMessage();
        this.data = (T) "sucessful";
    }
    public WebResult(T data) {
        this.code = ExceptionEnum.SUCCESS.getCode();
        this.msg = ExceptionEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public WebResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public WebResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
