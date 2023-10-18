package com.frame.oss.adapter;

import com.frame.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 京东 oss 适配器
 *
 * @author: chenyuntao
 **/
public class JdStorageAdapter implements StorageAdapter {

    @Override
    public void createBucket(String bucket) {

    }

    @Override
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) {

    }

    @Override
    public List<String> getAllBucket() {
        return null;
    }

    @Override
    public List<FileInfo> getAllFile(String bucket) {
        return null;
    }

    @Override
    public InputStream downLoad(String bucket, String objectName) {
        return null;
    }

    @Override
    public void deleteBucket(String bucket) {

    }

    @Override
    public void deleteObject(String bucket, String objectName) {

    }
}
