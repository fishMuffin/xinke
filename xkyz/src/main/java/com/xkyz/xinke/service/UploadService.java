package com.xkyz.xinke.service;

import com.xkyz.xinke.controller.testController;
import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.pojo.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    private static final String FILE_DESTINATION = "/root/xinke/static/images";
//    private static final String FILE_DESTINATION = "/Users/klein/Desktop";

    @Autowired
    private UploadProperties properties;

    public String uploadImage(MultipartFile file) {
        try {
            //校验文件类型
            String contentType = file.getContentType();
            if (!properties.getAllowTypes().contains(contentType)) {
                throw new EmException(ExceptionEnums.INVALID_FILE_TYPE);
            }
            //校验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new EmException(ExceptionEnums.INVALID_FILE_TYPE);
            }
            //目标路径
            File dest = new File(FILE_DESTINATION, file.getOriginalFilename());
            //保存文件到本地
            file.transferTo(dest);

            //返回路径
            logger.info("UploadService,time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " fileURL:" + (properties.getBaseUrl() + FILE_DESTINATION));
            return properties.getBaseUrl() + FILE_DESTINATION;
        } catch (IOException e) {
            log.error("[文件上传] 上传文件失败", e);
            throw new EmException(ExceptionEnums.UPLOAD_ERROR);
        }
    }
}
