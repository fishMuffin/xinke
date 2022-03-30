package com.xkyz.xinke.pojo;



/**
 * 异步请求统一返回结果集
 * @author 
 */
public class ApiResult {

    /** 成功还是失败 */
    private boolean success = true;
    /** 返回code */
    private String code = "";
    /** 返回的信息 */
    private String message = "";
    /** 返回的数据 */
    private Object data;
    
    

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ApiResult() {
        super();
    }

    public ApiResult(boolean success) {
        super();
        this.success = success;
    }

    public ApiResult(String code, String message) {
        super();
        this.code = code;
        this.message = message;
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResult(boolean success, String code, String message) {
        super();
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public ApiResult(String message) {
        super();
        this.message = message;
    }

    public ApiResult(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }
    
    public ApiResult(boolean success, String code, String message,Object data) {
        super();
        this.success = success;
        this.code = code;
        this.message = message;
        this.data=data;
    }    
    
    public ApiResult(String code, String message,Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data=data;
    }    
    
    public ApiResult(Object data) {
        super();
        this.data=data;
    }    
}