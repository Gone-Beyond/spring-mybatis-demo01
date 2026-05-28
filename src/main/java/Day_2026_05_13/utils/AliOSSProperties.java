package Day_2026_05_13.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {


    private String endpoint;
    private String bucketName;
    private String accessKeyId;
    private String accessKeySecret;
}
