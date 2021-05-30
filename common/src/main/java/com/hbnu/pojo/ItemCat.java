package com.hbnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_item_cat")//对应表名
public class ItemCat extends BasePojo{
    @TableId(type = IdType.AUTO)//主键自增

    private Long id;//id
    private Long parentId;//父级id
    private String name;
    private Integer status;//状态
    private Integer sortOrder;
    private Boolean isParent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
