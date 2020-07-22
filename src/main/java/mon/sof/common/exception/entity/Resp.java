package mon.sof.common.exception.entity;

import java.io.Serializable;

public class Resp implements Serializable {

    private static final long serialVersionUID = 7813927492588533594L;

    private Integer code;

    private String message;

    private Object data;

    public Resp() {
    }

    public Resp(int code) {
        this.code = code;
    }

    public Resp(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Resp(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Resp(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Resp error(Integer code , String message, Object data) {
        return new Resp(code, message, data);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
