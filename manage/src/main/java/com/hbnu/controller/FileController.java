package com.hbnu.controller;

import com.hbnu.vo.ImageVo;
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
        File dirFile = new File("在硬盘中创建一个本地目录用来存放上传的图片，目录之间加/ ");
        if (!dirFile.exists()){
            //创建多层目录
            dirFile.mkdirs();
        }
        //3.创建目标文件（要上传难道哪个文件上去）
        String filePathName = "目录同2上"+fileName;
        File realFile = new File(filePathName);
        //4.准备上传文件

            fileImage.transferTo(realFile);



        return "用户上传文件成功！";
    }

    /*
    *
    * url地址：localhost:8091/pic/upload?dir=image
    * 返回结果：
    * */

    @RequestMapping("/pic/upload")
    public ImageVo fileUpload(MultipartFile uploadFile){
        /*
        * 1.获取图片
        *   1.1首先要确定用户上传的是不是图片，校验图片格式
        * 2.创建存储目录
        *   2.1创建目录方式：目录需要分级，方便检索  年/月/日  yyyy/mm//dd
        * 3.创建目标文件
        * 4.上传文件
        * */

        //图片存储根目录
        String localDirPath = "图片存储的目录";

        String urlDirPath = "http://image.jg.com/";
        //1.1首先要确定用户上传的是不是图片，校验图片格式  .jpg
        String fileName = uploadFile.getOriginalFilename().toLowerCase();
        //防止盗版系统图片文件格式为大写JPG，转成小写
        //String fileLowNamem = fileName.toLowerCase();
        //字符串校验方法之一：正则表达式
        if (!fileName.matches("上尖括号.+\\.(jpg|jpge|png|gif)$")){
            return ImageVo.fail();
        }

        //1.2校验其他文件
        try {
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            if(width == 0 || height == 0){

                return  ImageVo.fail();
            }

            // 2.创建目录方式：目录需要分级，方便检索  年/月/日  yyyy/mm//dd
            String dateDir = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
            String realDir = localDirPath + dateDir; //目录分级之后的目录
            File realFileDir = new File(realDir);
            if (!realFileDir.exists()) {
                realFileDir.mkdirs();
            }
            //3.创建存储图片的文件
            //文件名字不能重复
            String realFileName = UUID.randomUUID().toString();
            //最后一个点出来的时候截取
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            //真实文件路径
            String realFilePath = localDirPath + dateDir + realFileName + fileType;
            System.out.println(realFilePath);

            //4.上传文件
            uploadFile.transferTo(new File(realFilePath));
            System.out.println("success!");

            //图片回显给页面
            //虚拟路径
            String url = urlDirPath+dateDir+realFileName+fileType;
            ImageVo imageVo = new ImageVo(0,url,width,height);
            return imageVo;
        } catch (IOException e) {
            e.printStackTrace();
            return ImageVo.fail();
        }
        //return null;
    }
}






























