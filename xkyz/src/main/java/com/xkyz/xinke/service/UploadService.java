package com.xkyz.xinke.service;

import com.aliyun.oss.*;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.xkyz.xinke.controller.testController;
import com.xkyz.xinke.enums.ExceptionEnums;
import com.xkyz.xinke.exception.EmException;
import com.xkyz.xinke.pojo.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    private static final String FILE_DESTINATION = "/root/xinke/static/images";
//    private static final String FILE_DESTINATION = "/Users/klein/Desktop";


    private static final String END_POINTS="https://oss-cn-beijing.aliyuncs.com";
    private static final String ACCESS_KEY="LTAI5tNRPAKPDczj51UR529L";
    private static final String ACCESS_KEY_SECRET="EaTb3ckYUe6GVc7Lon3kxV0kp9cwj4";
    @Autowired
    private UploadProperties properties;


    public String uploadImageOss(MultipartFile file) throws IOException {

        String endpointStr = "oss-cn-beijing.aliyuncs.com";
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
        File dest = new File(file.getOriginalFilename());
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。

        // 填写Bucket名称，例如examplebucket。
        String bucketName = "xkyz";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = file.getOriginalFilename();
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
//        String filePath= "/Users/klein/Desktop/xinke/code/xkyz/pom.xml";
//        String filePath= "/Users/klein/Desktop/WechatIMG183.jpeg";

//校验文件类型


//        File file = new File(filePath);
        String url = "";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(END_POINTS, ACCESS_KEY, ACCESS_KEY_SECRET);

        try {

            // 创建PutObjectRequest对象。
            new PutObjectRequest(bucketName, objectName, dest);
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传文件。
            url="http://"+bucketName+"."+endpointStr+"/"+objectName;
            return url;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
}
