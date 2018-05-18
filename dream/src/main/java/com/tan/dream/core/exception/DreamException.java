package com.tan.dream.core.exception;

/**
 * 封装的异常
 *
 * @author tanleijin
 * @Date 2018/5/2 下午10:32
 */
public class DreamException extends RuntimeException {

    private Integer code;

    private String message;

    public DreamException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
