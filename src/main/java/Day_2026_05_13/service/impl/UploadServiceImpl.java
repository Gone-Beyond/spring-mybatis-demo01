package Day_2026_05_13.service.impl;

import Day_2026_05_13.service.UploadService;
import Day_2026_05_13.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Override
    public String upload(MultipartFile image) throws Exception {
       return aliOSSUtils.upload(image);
    }
}
