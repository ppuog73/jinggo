package com.hbnu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
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











































