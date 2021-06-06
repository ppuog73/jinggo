package com.hbnu.controller;

import com.hbnu.service.FileService;
import com.hbnu.vo.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileController {
    /*
    * url地址：http://localhost：8091/file
    * */
    @RequestMapping("/file")
    public String file(MultipartFile fileImage) throws IOException {
        //1.获取上传文件的名称
        String fileName = fileImage.getOriginalFilename();
        //2.创建用户上传文件保存的路径
        File dirFile = new File("E:\\Images for jinggou\\");
        if (!dirFile.exists()){
            //创建多层目录
            dirFile.mkdirs();
        }
        //3.创建目标文件（要上传难道哪个文件上去）
        String filePathName = "E:\\Images for jinggou\\"+fileName;
        File realFile = new File(filePathName);
        //4.准备上传文件

            fileImage.transferTo(realFile);

        return "用户上传文件成功！";
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

    @RequestMapping("pic/upload")
    public ImageVo fileUpload(MultipartFile uploadFile) {

        return fileService.uploadFile(uploadFile);
    }

}






























