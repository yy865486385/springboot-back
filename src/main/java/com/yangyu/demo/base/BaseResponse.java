package com.yangyu.demo.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yangyu
 * Date 2020-03-25
 * 
 * BaseResponse 返回
 */
@Getter
@Setter
public class BaseResponse {

    private Integer code;
    
    private String msg;

    private Object data;

    private BaseResponse() {

    }

    public static BaseResponse success() {
        BaseResponse resultBean = new BaseResponse();
        resultBean.setCode(200);
        resultBean.setMsg("success");
        return resultBean;
    }

    public static BaseResponse success(Object data) {
        BaseResponse resultBean = new BaseResponse();
        resultBean.setCode(200);
        resultBean.setMsg("success");
        resultBean.setData(data);
        return resultBean;
    }

    public static BaseResponse error() {
        BaseResponse resultBean = new BaseResponse();
        resultBean.setCode(500);
        resultBean.setMsg("error");
        return resultBean;
    }

    public static BaseResponse error(Object data) {
        BaseResponse resultBean = new BaseResponse();
        resultBean.setCode(500);
        resultBean.setMsg("error");
        resultBean.setData(data);
        return resultBean;
    }


}