package com.hbnu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;


/*
    该VO对象是系统返回值VO对象.主要包含3个属性
    1.定义状态码   200表示执行成功    201 执行失败  人为定义的(和浏览器没关系)业务定义.
    2.定义返回值信息   服务器可能会给用户一些提示信息.
    3.定义返回值对象   服务器在后端处理完成业务之后,将对象返回给前端.
 */

@Data/**去除Getter,Setter,equals,hashCode,toString方法使得代码简洁，需要lombok*/
@Accessors(chain = true)/**通过该注解可以控制getter和setter方法的形式。使用chain属性，setter方法返回当前对象*/
@AllArgsConstructor/**自动生成有参构造函数，该构造函数含有所有已声明字段属性参数*/
public class SysResult {
    private Integer status;
    /**系统返回状态码,200:正常返回，201：请求失败*/
    private String msg;
    /**系统返回消息给调用者*/
    private Object data;
    /**系统返回请求数据给调用者*/

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
