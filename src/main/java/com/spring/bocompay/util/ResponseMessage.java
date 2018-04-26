package com.spring.bocompay.util;

/**
 * @description: 返回对象信息
 * @author: Katherine
 * @create: 2018/4/25 12:55
 */
public class ResponseMessage {

    /**
     * @fieldName: success
     * @fieldType: boolean
     * @description: 操作是否成功
     */
    private boolean success = true;

    /**
     * @fieldName: message
     * @fieldType: String
     * @description: 消息
     */
    private String message;

    /**
     * @fieldName: code
     * @fieldType: Integer
     * @description: 操作返回码
     */
    private Integer code;

    /**
     * @fieldName: data
     * @fieldType: Object
     * @description: 被返回对象
     */
    private Object data;

    public ResponseMessage() {
    }

    public ResponseMessage(boolean success, String message, Integer code, Object data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
