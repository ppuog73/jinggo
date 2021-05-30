package com.hbnu.controller;


import com.hbnu.pojo.Item;
import com.hbnu.service.ItemService;
import com.hbnu.vo.EasyUITable;
import com.hbnu.vo.SysResult;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * 请求参数：
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
    @RequestMapping("/update")
    public SysResult updateItem(Item item){

        itemService.updateItem(item);
        return SysResult.success(); //添加成功


        //  return SysResult.fail(); //添加失败
    }

    /**Url:localhost:8091/item/delete
     *
     * 删除商品
     *
     */
    @RequestMapping("/delete")
    public SysResult deleteItem(Long[] ids){
        itemService.deleteItem(ids);
        return SysResult.success();
    }


    /**
     *http://localhost:8091/item/instock
     * 商品上架下架
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
