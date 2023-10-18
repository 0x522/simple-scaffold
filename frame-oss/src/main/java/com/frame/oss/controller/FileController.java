package com.frame.oss.controller;

import com.frame.oss.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chenyuntao
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;

    @GetMapping("/getAllBucket")
    public List<String> testGetAllBuckets() {
        return fileService.getAllBucket();
    }

    @PostMapping("/createBucket")
    public String createBucket(@RequestBody String bucket) {
        return fileService.createBucket(bucket);
    }

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file, String bucket) {
        fileService.uploadFile(file, bucket);
    }

}
