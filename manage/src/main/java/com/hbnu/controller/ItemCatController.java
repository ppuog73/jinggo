package com.hbnu.controller;

import com.hbnu.pojo.ItemCat;
import com.hbnu.service.ItemCatService;
import com.hbnu.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/queryItemName")
    public String findItemCatName(Long itemCatId) {
        ItemCat itemCat = itemCatService.findItemCatNameById(itemCatId);
        return itemCat.getName();
    }


    /*
     *返回值：List<EasyUITree>
     *查询一级目录结构
     * 请求参数：ID
     * 目的：将id作为parentID进行数据库查询
     * 要求：id默认值应该为0
     */

    @RequestMapping("/list")
    public List<EasyUITree> findItemCatByParentId(
        @RequestParam(value = "id",defaultValue = "0") Long parentId){
        return itemCatService.findEasyUITree(parentId);

    }
}
