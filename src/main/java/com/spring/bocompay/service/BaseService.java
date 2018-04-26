package com.spring.bocompay.service;

import com.spring.bocompay.util.ResponseMessage;

/**
 * @description: 基础服务
 * @author: Katherine
 * @create: 2018/4/25 12:54
 */
public class BaseService {

    /**
     * @param errMsg 错误信息
     * @description: 失败调用
     * @author: Katherine
     * @create: ${DATE} ${TIME}
     */
    public ResponseMessage fail(String errMsg) {
        return this.fail(500, errMsg, null);
    }

    /**
     * @param data 被返回对象
     * @description: 成功调用
     * @author: Katherine
     * @create: ${DATE} ${TIME}
     */
    public ResponseMessage success(Object data) {
        return this.success(200, "成功", data);
    }

    public ResponseMessage success(Integer code, String message, Object data) {
        return new ResponseMessage(true, message, code, data);
    }

    public ResponseMessage fail(Integer code, String message, Object data) {
        return new ResponseMessage(false, message, code, data);
    }

}
