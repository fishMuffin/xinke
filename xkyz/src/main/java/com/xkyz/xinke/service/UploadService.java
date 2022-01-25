package com.xkyz.xinke.service;

import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.pojo.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {

    private static final String FILE_DESTINATION = "/root/xinke/static/images";

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
//            目标路径
            File dest = new File(FILE_DESTINATION, file.getOriginalFilename());
            //保存文件到本地
            file.transferTo(dest);

            //上传到FastDFS

//            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
//            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);

            //返回路径
            return properties.getBaseUrl() + FILE_DESTINATION;
        } catch (IOException e) {
            log.error("[文件上传] 上传文件失败", e);
            throw new EmException(ExceptionEnums.UPLOAD_ERROR);
        }
    }
}
