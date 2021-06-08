package com.hbnu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbnu.dao.ItemCatMapper;
import com.hbnu.pojo.Item;
import com.hbnu.pojo.ItemCat;
import com.hbnu.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public ItemCat findItemCatNameById(Long itemCatId) {

        return itemCatMapper.selectById(itemCatId);
    }

    /**
    * pojo———转化—————vo
    *
    * 1.通过parentId查询数据库，查询出的是List<ItemCat>
    * 2.遍历List,将itemCat对象中的部分属性封装到EasyUITree对象中
    * 3.将第2步封装好的EasyUITree对象添加到集合中
    *
    * 代码优化：一般一个方法执行一个任务
    * */
    @Override
    public List<EasyUITree> findEasyUITree(Long parentId) {
        //1.调用findItemCatByParentId方法查询数据库
        List<ItemCat> itemList = findItemCatByParentId(parentId);

        List<EasyUITree> treeList = new ArrayList<>();
        //2.遍历List，讲ItemCat对象中的部分属性封装到EasyUITree对象中
        for (ItemCat itemCat : itemList) {

            Long id = itemCat.getId();
            String text = itemCat.getName();
            String state = itemCat.getIsParent() ? "closed" : "open";//一级目录不展开，所以是closed
        //3.将封装好的EasyUITree对象添加到集合中
            EasyUITree easyUITree = new EasyUITree(id, text, state);
            treeList.add(easyUITree);
        }
        return treeList;
    }

    /**
     * 根据parentId查询数据库
     * @param parentId
     * @return
     */
    private List<ItemCat> findItemCatByParentId(Long parentId) {   //根据父级id查询商品类目
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        return itemCatMapper.selectList(queryWrapper);
    }
}
