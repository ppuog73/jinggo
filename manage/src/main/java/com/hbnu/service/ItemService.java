package com.hbnu.service;

import com.hbnu.pojo.Item;
import com.hbnu.vo.EasyUITable;


public interface ItemService {

    EasyUITable findItemByPage(Integer page, Integer rows);

    /**
     * 实现商品信息的入库操作
     * 入库之前需要提前将数据补全.  刚新增的商品应该处于上架状态1
     * @param item
     * 注意事项:完成数据库更新操作时,需要注意数据库事务问题
     */
    void saveItem(Item item);

    /**
     * 完成商品信息修改
     * url:http://localhost:8091/item/update
     * 参数: 整个商品表单
     * 返回值: SysResult对象
     */
    void updateItem(Item item);

    /**
     * 业务需求: 完成商品删除操作
     * url请求地址: http://localhost:8091/item/delete
     * 参数: ids=  id1,id2 串
     * 返回值结果:  SysResult对象
     * SpringMVC知识点: 可以根据制定的类型动态的实现参数类型的转化.
     * 					如果字符串使用","号分隔,则可以使用数组的方式接参.
     */
    void deleteItem(Long[] ids);

    /**
     * http://localhost:8091/item/reshelf
     * 商品下架
     */
    void dorpShelf(Long[] ids);

    /**
     * http://localhost:8091/item/reshelf
     * 商品上架
     */
    void putShelf(Long[] ids);
}
