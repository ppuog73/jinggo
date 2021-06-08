package com.hbnu.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
/**
* 后台拿到的数据转成json
* */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class EasyUITable {
    private Integer total;
    private List<?> rows;

    public EasyUITable(Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }
}
