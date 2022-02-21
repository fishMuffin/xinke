package com.xkyz.xinke;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

public class Demo {

    public static void main(String[] args) throws Exception {

        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        String endpointStr = "oss-cn-beijing.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tNRPAKPDczj51UR529L";
        String accessKeySecret = "EaTb3ckYUe6GVc7Lon3kxV0kp9cwj4";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "xkyz";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = "WechatIMG183.jpeg";

        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
//        String filePath= "/Users/klein/Desktop/xinke/code/xkyz/pom.xml";
        String filePath= "/Users/klein/Desktop/WechatIMG183.jpeg";


        File file = new File(filePath);

        // 创建OSSClient实例。
//        https://xkyz.oss-cn-beijing.aliyuncs.com/WechatIMG183.jpeg?versionId=CAEQHBiBgICr6.TZ.BciIGZmMDk0YTg1ZTM1NjQ3YTM4ZWI5MDRiYmZiYTc0MDYx
//        http://xkyz.oss-cn-beijing.aliyuncs.com/WechatIMG183.jpeg

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        URL url = ossClient.generatePresignedUrl(bucketName, UUID.randomUUID().toString(), new Date());
        String url2 = "http://"+bucketName+"."+endpointStr+"/"+objectName;
        ossClient.setObjectAcl(bucketName,objectName, CannedAccessControlList.PublicRead);

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file);
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传文件。
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
//            System.out.println(url);

            System.out.println(url2);


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
//        return url;
    }
}