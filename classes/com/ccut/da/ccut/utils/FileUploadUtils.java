package ccut.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import ccut.Exception.CustomizeException;
import ccut.common.ErrorEnum;
import java.io.File;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class FileUploadUtils {

    @Value("${oos.accessKey}")
    private static String accessKey = "LTAI5tFVZUUkSEz4Fro8LF1P";

    @Value("${oos.secretKey}")
    private static String secretKey = "tdOoaFU036DJ8PC1IIHl9f48vQ6Dkr";

    @Value("${oos.domain}")
    private static String domain = "http://dbjvbk.top/";


    @Value("${oos.bucketname}")
    private static String bucketname = "wzpicture";

    @Value("${oos.endpoint}")
    private static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";


    public static String upLoadImg(MultipartFile file, String storagePath, String PName) {
        System.out.println(accessKey);

        if (file.getSize() > 1048576L) {
            throw new CustomizeException(ErrorEnum.IMG_OVER_5MB);
        }
        String avatarName = file.getOriginalFilename();
        System.out.println(avatarName);
        if (!avatarName.endsWith(".jpg") && !avatarName.endsWith(".jpeg") && !avatarName.endsWith(".png") && !avatarName.endsWith(".webp") && !avatarName.endsWith(".jifi")) {
            throw new CustomizeException(ErrorEnum.WRONG_FILE_TYPE);
        }


        avatarName = PName + avatarName.substring(avatarName.lastIndexOf("."));


        File newfile = MultipartFileToFile.toFileTwo(file);


        OSS ossClient = (new OSSClientBuilder()).build(endpoint, accessKey, secretKey);

        PutObjectResult putObjectResult = ossClient.putObject(new PutObjectRequest(bucketname, storagePath + avatarName, newfile));


        newfile.delete();


        String ImgUrl = domain + storagePath + avatarName;

        if (putObjectResult == null) {
            throw new CustomizeException(ErrorEnum.IMG_UPLOAD_FAILED);
        }
        log.info("头像上传成功地址为: {}", ImgUrl);
        return ImgUrl;
    }
}

