package com.hbnu.service;

import com.hbnu.vo.ImageVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:/properties/image.properties")//加载配置文件
public class FileServiceImpl implements FileService {


         /* 1.获取图片
         *   1.1首先要确定用户上传的是不是图片，校验图片格式
         * 2.创建存储目录
         *   2.1创建目录方式：目录需要分级，方便检索  年/月/日  yyyy/mm//dd
         * 3.创建目标文件
         * 4.上传文件
         * */


    //2.创建图片存储根目录
    @Value("${image.localDirPath}")
    String localDirPath ;

    //虚拟路径url
    @Value("${image.urlDirPath}")
    String urlDirPath ;


    @Override
    public ImageVo uploadFile(MultipartFile uploadFile) {


        //1.1首先要确定用户上传的是不是图片，校验图片格式  .jpg
        String fileName = uploadFile.getOriginalFilename().toLowerCase();//防止盗版系统图片文件格式为大写JPG，转成小写
        //1.1 校验上传的是否是图片的格式，字符串校验方法之一：正则表达式
        //不匹配的情况
        if (!fileName.matches("^.+\\.(jpg|jpge|png|gif)$")) {
            return ImageVo.fail();
        }
        //1.2校验非图片文件
        try {
            //确认是图片后
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            //拿到宽高并校验
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width == 0 || height == 0) {
                return ImageVo.fail();
            }

            //2.1目录分级存储  按yyyy-mm-dd的格式创建
            String dateDir = new SimpleDateFormat("yyyy-MM-dd/").format(new Date());
            String realDir = localDirPath + dateDir;//目录分级之后的目录//把目录名称拼接
            File realFileDir = new File(realDir);
            if (!realFileDir.exists()) {
                realFileDir.mkdirs();
            }

            //3.创建存储图片的文件 为了上传的名字不重复用uuid.jpg
            String realFileName = UUID.randomUUID().toString();
            //最后一个点出来的时候截取
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            //真实文件路径
            String realFilePath = localDirPath + dateDir + realFileName + fileType;


            //4.上传图片
            uploadFile.transferTo(new File(realFilePath));

            //5.图片回显 http://image.jg.com/2021-05-24/uuid.jpg
            //虚拟路径
            String url = urlDirPath + dateDir + realFileName + fileType;
            ImageVo imageVo = new ImageVo(0, url, width, height);
            return imageVo;

        } catch (IOException e) {
            e.printStackTrace();
            return ImageVo.fail();
        }
    }
}
