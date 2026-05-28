package Day_2026_05_13.controller;


import Day_2026_05_13.pojo.Result;
import Day_2026_05_13.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public Result upload( @RequestParam("image") MultipartFile image) throws Exception {

        String url = uploadService.upload(image);
        return Result.success(url);
    }

}
