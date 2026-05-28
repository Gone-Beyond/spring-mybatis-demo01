package Day_2026_05_13.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 *
 * OSS = Object Storage Service，对象存储服务
 * 作用：把前端上传的图片文件保存到阿里云 OSS，然后返回图片访问 URL
 */

@Component
public class AliOSSUtils {

    @Autowired
    private AliOSSProperties aliOSSProperties;


    public String upload(MultipartFile file) throws Exception {

        String endpoint = aliOSSProperties.getEndpoint();
        String bucketName = aliOSSProperties.getBucketName();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();

        // 1. 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 2. 获取文件后缀，例如 .jpg / .png / .txt
        String suffix = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 3. 构造 OSS 中保存的文件名，避免文件名重复
        // 例如：2026/05/19/550e8400-e29b-41d4-a716-446655440000.jpg
        LocalDate now = LocalDate.now();
        String objectName = now.getYear() + "/"
                + String.format("%02d", now.getMonthValue()) + "/"
                + String.format("%02d", now.getDayOfMonth()) + "/"
                + UUID.randomUUID() + suffix;

        // 4. 创建 OSS 客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try (InputStream inputStream = file.getInputStream()) {

            // 5. 上传文件到 OSS
            // bucketName：上传到哪个 bucket
            // objectName：文件在 OSS 中的保存路径
            // inputStream：文件二进制输入流
            ossClient.putObject(bucketName, objectName, inputStream);

        } finally {
            // 6. 关闭 OSS 客户端
            ossClient.shutdown();
        }

        // 7. 拼接文件访问 URL
        String endpointWithoutProtocol = endpoint
                .replace("https://", "")
                .replace("http://", "");

        return "https://" + bucketName + "." + endpointWithoutProtocol + "/" + objectName;
    }
}