package com.tan.dream.common.vo;

/**
 * （http请求返回的最外层对象）
 *
 * @Author tanleijin
 * @date Create in 22:23 2018/5/6
 */
public class ResultVO<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO() {
        this.code = 0;
        this.msg = "操作成功";
    }
    public static ResultVO ok() {
        return new ResultVO();
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
