package com.hbnu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class SysResult {
    private Integer status;     //系统返回状态码,200:正常返回，201：请求失败
    private String msg;         //系统返回消息给调用者
    private Object data;        //系统返回请求数据给调用者

    public static SysResult success(){
        return new SysResult(200,null,null);

    }
    public static SysResult success(String msg){
        return new SysResult(200,msg,null);

    }
    public static SysResult success(String msg,Object data){
        return new SysResult(200,msg,null);

    }
    public static SysResult fail(){
        return new SysResult(201,null,null);
    }
    public static SysResult fail(String msg){
        return new SysResult(201,msg,null);
    }
}
