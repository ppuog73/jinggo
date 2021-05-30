package com.hbnu.service;

import com.hbnu.pojo.Item;
import com.hbnu.vo.EasyUITable;

import java.util.List;

public interface ItemService {

    EasyUITable findItemByPage(Integer page, Integer rows);

    void saveItem(Item item);

    void updateItem(Item item);

    void deleteItem(Long[] ids);

    void dorpShelf(Long[] ids);

    void putShelf(Long[] ids);
}
