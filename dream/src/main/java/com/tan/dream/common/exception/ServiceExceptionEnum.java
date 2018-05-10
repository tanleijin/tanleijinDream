package com.tan.dream.common.exception;

/**
 * 抽象接口
 *
 * @author tanleijin
 * @date 2018-2-8-下午10:27
 */
public interface ServiceExceptionEnum {

    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
