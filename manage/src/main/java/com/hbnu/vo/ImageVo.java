package com.hbnu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@NoArgsConstructor//自动生成无参数构造函数。
@AllArgsConstructor//自动生成有参构造函数，该构造函数含有所有已声明字段属性参数
public class ImageVo {
    private  Integer error; //0是图片 正常上传 1则图片上传失败
    private String url;  //图片的虚拟路径，网络上的路径都是虚拟路径
    private Integer width; //   图片宽度
    private Integer height;  //图片高度
    //上传失败调用
    public static ImageVo fail(){
        return new ImageVo(1,null,null,null);
    }
}











































