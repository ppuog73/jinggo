package com.hbnu.service;


import com.hbnu.dao.ItemMapper;
import com.hbnu.pojo.Item;
import com.hbnu.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public EasyUITable findItemByPage(Integer page, Integer rows) {
        //1.获取商品总记录数total
        Integer total = itemMapper.selectCount(null);
        //2.获取分页商品的数据
        int start=(page-1) * rows;//计算分页的下标
        List<Item> itemList = itemMapper.findItemByPage(start,rows);//查的是pojo

        return new EasyUITable(total, itemList);
    }

    @Override
    public void saveItem(Item item) {
        item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated()); //初始化时间和状态
        itemMapper.insert(item);
    }

    @Override
    public void updateItem(Item item) {
        item.setUpdated(new Date());  //更新商品时间
        itemMapper.updateById(item);
    }

    @Override
    public void deleteItem(Long[] ids) {
        List<Long> idList = Arrays.asList(ids);  //将数组转为集合
        itemMapper.deleteBatchIds(idList);
    }

    @Override
    public void dorpShelf(Long[] ids) {
        List<Long> idList = Arrays.asList(ids); //批量上架，批量下架

        List<Item> itemList = itemMapper.selectBatchIds(idList); //得到所有要上架的商品
        for(Item item: itemList){
            item.setStatus(2);//将商品状态改为下架
        }


    }

    @Override
    public void putShelf(Long[] ids) {
        List<Long> idList = Arrays.asList(ids); // 商品批量下架
        List<Item> itemList = itemMapper.selectBatchIds(idList); //得到所有要下架的商品信息
        for(Item item: itemList){
            item.setStatus(1);//将商品状态改为上架架
        }
    }


}
