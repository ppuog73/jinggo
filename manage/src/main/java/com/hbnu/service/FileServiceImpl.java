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
         *   校验文件是否为恶意程序
         * 2.创建存储目录
         *   2.1创建目录方式：目录需要分级，方便检索  年/月/日  yyyy/mm//dd
         * 3.创建目标文件
         * 4.上传文件
         * 5.准备一个访问图片的虚拟路径
         * */


    //2.创建图片存储根目录，本地路径
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
            //校验是否有图片的特有属性  高度/宽度
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            //校验宽度和高度是否有值
            if (width == 0 || height == 0) {
                return ImageVo.fail();//返回失败即可
            }

            //2.1目录分级存储，提高用户检索图片的效率  利用工具API将时间转化为指定的格式，按yyyy-mm-dd的格式创建
            String dateDir = new SimpleDateFormat("yyyy-MM-dd/").format(new Date());
            //2.2动态生成文件目录//目录分级之后的目录//把目录名称拼接=根目录+时间目录
            String realDir = localDirPath + dateDir;//目录分级之后的目录//把目录名称拼接=根目录+时间目录
            //2.3判断目录是否存在, 如果不存在则新建目录
            File dirFile = new File(realDir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();//如果不存在,则新建目录
            }

            //3.创建存储图片的文件  防止文件重名,需要自定义文件名称 UUID
            String uuid = UUID.randomUUID().toString();
            //最后一个点出来的时候截取，xxxx.jpg
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            //实现路径拼接，图片存储的根目录  E:\Images\jinggou2021-06-04\动态文件名称.jpg
            String realFileName = uuid + fileType;
            //拼接指定的虚拟路径
            String realFilePath = realDir + realFileName;


            //4.上传图片，new File(realFilePath)  封装文件真实对象
            uploadFile.transferTo(new File(realFilePath));

            //5.图片回显 http://image.jg.com/2021-05-24/uuid.jpg
            //虚拟路径
            String url = urlDirPath + dateDir + realFileName;
            ImageVo imageVo = new ImageVo(0, url, width, height);
            return imageVo;

        } catch (IOException e) {
            e.printStackTrace();
            return ImageVo.fail();//告知文件上传失败
        }
    }
}
