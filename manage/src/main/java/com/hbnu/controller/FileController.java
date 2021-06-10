package com.hbnu.controller;
import com.hbnu.service.FileService;
import com.hbnu.vo.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/*
 * 上传成功
 * http://localhost:8080/file
 * */
@RestController
public class FileController {
    //测试代码
    @RequestMapping("/file")
    public String file(MultipartFile fileImage) throws IOException {
        //1.获取上传文件的名称
        String fileName = fileImage.getOriginalFilename();

        //2.创建保存用户上传的文件的目录
        File dirFile = new File("E:/Images/jinggou");
        //判断文件是否存在
        if (!dirFile.exists()) {
            dirFile.mkdirs();//多级目录
        }

        //3.创建目标文件,上传成功会保存到这里
        String filePathName = "E:/Images/jinggou/" + fileName;
        File realFile = new File(filePathName);

        //4.上传文件
        fileImage.transferTo(realFile);

        return "上传成功";
    }

    /*
    *    //1.获取图片，校验图片
        //1.1 校验上传的是否是图片的格式
        //2.创建图片存储目录
        //2.1目录分级存储  按yyyy-mm-dd的格式创建
        //3.创建存储图片的文件
        //4.上传图片
    * */
    @Autowired
    private FileService fileService;

    /**
     * 实现图片上传操作.
     * url地址:http://localhost:8091/pic/upload?dir=image
     * 参数信息: uploadFile
     * 返回值: ImageVo对象
     */
    @RequestMapping("pic/upload")
    public ImageVo fileUpload(MultipartFile uploadFile) {

        return fileService.uploadFile(uploadFile);
    }


}




























