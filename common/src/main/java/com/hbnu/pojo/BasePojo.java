package com.hbnu.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/*
*基础类
* */
@Data/**去除Getter,Setter,equals,hashCode,toString方法使得代码简洁，需要lombok*/
@Accessors(chain = true)/**通过该注解可以控制getter和setter方法的形式。使用chain属性，setter方法返回当前对象*/
public class BasePojo implements Serializable {


    private static final long serialVersionUID = 5466937469127555568L;
    /**序列化ID，将对象的状态转化成字节流，在网络上传送*/

    private Date created;
    /**创建时间*/
    private Date updated;
    /**更新时间*/
}
