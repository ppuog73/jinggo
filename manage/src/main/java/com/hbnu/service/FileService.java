package com.hbnu.service;

import com.hbnu.vo.ImageVo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ImageVo uploadFile(MultipartFile uploadFile);
}
