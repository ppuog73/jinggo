package com.hbnu.controller;


import com.hbnu.pojo.Item;
import com.hbnu.service.ItemService;
import com.hbnu.vo.EasyUITable;
import com.hbnu.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/query")
    public EasyUITable findItemByPage(Integer page, Integer rows){
        return itemService.findItemByPage(page,rows);
    }

    /**
     *item/save
     * 请求参数：整个form表单
     * 返回结果：200/201---->SysResult
     * 添加商品
     *
     */
   @RequestMapping("/save")
    public SysResult saveItem(Item item){

           itemService.saveItem(item);
           return SysResult.success(); //添加成功


         //  return SysResult.fail(); //添加失败
       }

       // item/update
    /**
     * 完成商品信息修改
     * url:http://localhost:8091/item/update
     * 参数: 整个商品表单
     * 返回值: SysResult对象
     */
    @RequestMapping("/update")
    public SysResult updateItem(Item item){

        itemService.updateItem(item);
        return SysResult.success(); //添加成功


        //  return SysResult.fail(); //添加失败
    }

    /**
     * 业务需求: 完成商品删除操作
     * url请求地址: http://localhost:8091/item/delete
     * 参数: ids=  id1,id2 串
     * 返回值结果:  SysResult对象
     * SpringMVC知识点: 可以根据制定的类型动态的实现参数类型的转化.
     * 					如果字符串使用","号分隔,则可以使用数组的方式接参.
     */
    @RequestMapping("/delete")
    public SysResult deleteItem(Long[] ids){
        itemService.deleteItem(ids);
        return SysResult.success();
    }


    /**
     *http://localhost:8091/item/instock
     * 商品上架下架
     *
     * * 利用restFul方式实现状态修改.
     * * 1./item/1   status=1
     * * 2./item/2   status=2
     *
     */
    @RequestMapping("/instock")
    public SysResult dropShelf(Long[] ids){
        itemService.dorpShelf(ids);
        return SysResult.success();
    }
    /**
     * http://localhost:8091/item/reshelf
     * 商品上架
     */
    @RequestMapping("/reshelf")
    public SysResult putShelf(Long[] ids){
        itemService.putShelf(ids);
        return SysResult.success();
    }


}
