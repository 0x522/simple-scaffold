package com.frame.oss.service;

import com.frame.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: chenyuntao
 **/
@Service
public class FileService {

    @Autowired
    private StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket() {
        return storageAdapter.getAllBucket();
    }

    /**
     * 在bucket中创建文件
     */
    public void uploadFile(MultipartFile file, String bucket) {
        storageAdapter.uploadFile(file, bucket, null);
    }

    /**
     * 创建bucket
     *
     * @param bucket
     * @return
     */
    public String createBucket(String bucket) {
        storageAdapter.createBucket(bucket);
        return bucket;
    }
}
