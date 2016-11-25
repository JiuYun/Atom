package com.origin.atom.model;

/*****
 *
 * 消息返回对象
 *
 */
public class JM {

    private boolean     success = false;                        //是否成功

    private String      message = "操作失败";                    //消息信息

    private String      code    = "-1";                         //消息代码



    public boolean isSuccess() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
