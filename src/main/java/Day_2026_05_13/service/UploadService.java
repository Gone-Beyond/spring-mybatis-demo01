package Day_2026_05_13.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String upload(MultipartFile image) throws Exception;
}
