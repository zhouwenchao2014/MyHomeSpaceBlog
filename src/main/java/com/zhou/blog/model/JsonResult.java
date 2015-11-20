package com.zhou.blog.model;

public class JsonResult {

    private int           code;                     // 更为详细的结果说明 1 成功 0 失败 还有其他
    private boolean       success;                  // 是否请求数据成功 ， 较为简单的状态描述
    private Object        attr;                     // 放置其他属性，可以是要返回的数据，也可以是附加说明
    private StringBuilder msg = new StringBuilder(); // 结果信息说明

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getAttr() {
        return attr;
    }

    public void setAttr(Object attr) {
        this.attr = attr;
    }

    public String getMsg() {
        return msg.toString();
    }

    /**
     * 设置信息，将原有信息替换
     * 
     * @param msg
     * @return
     */
    public JsonResult setMsg(String msg) {
        this.msg.delete(0, this.msg.length());
        this.msg.append(msg);
        return this;
    }

    /**
     * 追加信息
     * 
     * @param msg
     * @return
     */
    public JsonResult appendMsg(String msg) {
        this.msg.append(msg);
        return this;
    }

    /**
     * 将信息插入头部
     * 
     * @param msg
     * @return
     */
    public JsonResult appendHead(String msg) {
        this.msg.insert(0, msg);
        return this;
    }
}

