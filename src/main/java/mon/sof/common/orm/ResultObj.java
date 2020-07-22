package mon.sof.common.orm;

import cn.hutool.http.HttpStatus;

import java.io.Serializable;


public class ResultObj implements Serializable {

    private static final long serialVersionUID = -7194920551885921783L;

    private Integer code = HttpStatus.HTTP_OK;

    private String result = "success";

    private Object data;

    public static ResultObj resp() {
        return new ResultObj();
    }

    public static ResultObj resp(String res) {
        ResultObj resultObj = new ResultObj();
        resultObj.setResult(res);
        return resultObj;
    }

    public static ResultObj resp(Object obj) {
        ResultObj resultObj = new ResultObj();
        resultObj.setData(obj);
        return resultObj;
    }

    public static ResultObj resp(String res, Object obj) {
        ResultObj resultObj = new ResultObj();
        resultObj.setResult(res);
        resultObj.setData(obj);
        return resultObj;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public ResultObj(String result, Integer code, Object data) {
        this.result = result;
        this.code = code;
        this.data = data;
    }

    public ResultObj() {
    }
}
