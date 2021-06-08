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

    /**
     * 实现商品信息的入库操作
     * 入库之前需要提前将数据补全.  刚新增的商品应该处于上架状态1
     * @param item
     * 注意事项:完成数据库更新操作时,需要注意数据库事务问题
     */
    @Override
    public void saveItem(Item item) {
        item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated()); //初始化时间和状态，保证入库的时间一致
        itemMapper.insert(item);
    }

    /**
     * 完成商品信息修改
     * url:http://localhost:8091/item/update
     * 参数: 整个商品表单
     * 返回值: SysResult对象
     */
    @Override
    public void updateItem(Item item) {
        item.setUpdated(new Date());  //更新商品时间
        itemMapper.updateById(item);
    }

    /**
     * 业务需求: 完成商品删除操作
     * url请求地址: http://localhost:8091/item/delete
     * 参数: ids=  id1,id2 串
     * 返回值结果:  SysResult对象
     * SpringMVC知识点: 可以根据制定的类型动态的实现参数类型的转化.
     * 					如果字符串使用","号分隔,则可以使用数组的方式接参.
     */
    @Override
    public void deleteItem(Long[] ids) {
        List<Long> idList = Arrays.asList(ids);  //将数组转为list集合
        itemMapper.deleteBatchIds(idList);
    }


    /**
     * http://localhost:8091/item/reshelf
     * 商品下架
     */
    @Override
    public void dorpShelf(Long[] ids) {
        List<Long> idList = Arrays.asList(ids); //批量上架，批量下架

        List<Item> itemList = itemMapper.selectBatchIds(idList); //得到所有要上架的商品
        for(Item item: itemList){
            item.setStatus(2);//将商品状态改为下架
        }


    }

    /**
     * http://localhost:8091/item/reshelf
     * 商品上架
     */
    @Override
    public void putShelf(Long[] ids) {
        List<Long> idList = Arrays.asList(ids); // 商品批量下架
        List<Item> itemList = itemMapper.selectBatchIds(idList); //得到所有要下架的商品信息
        for(Item item: itemList){
            item.setStatus(1);//将商品状态改为上架架
        }
    }


}
