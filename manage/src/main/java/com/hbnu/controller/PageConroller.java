package com.hbnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageConroller {
    /*
    * restful风格
    * 1.参数之间用/隔开
    * 2.参数需要用{}包裹
    * 3.方法要获取url中的参数需要借用@PathVariable注解
    *
    * 当url中参数较多的时候可以通过对象去接收参数，此时要求对象中的属性名称和url参数的属性名称一致
    *
    * restful高级应用：
    * url:userId   根据id查询用户/根据id删除
    * 用户发送url，后台根据url执行不同的操作,根据请求方式可以让一个url执行不同的操作
    * http请求方式有8种：
    * 常见有4种：
    * get:查询操作
    * post:添加操作（入库操作）
    * put:修改
    * delete:删除
    *
    * */
    @RequestMapping("/page/{pageName}")
    public String page(@PathVariable String pageName){
        return pageName;
    }


}
