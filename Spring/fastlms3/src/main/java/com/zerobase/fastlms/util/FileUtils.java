package com.zerobase.fastlms.util;

import com.zerobase.fastlms.admin.model.UploadFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUtils {
    private final String fileDir = "C:\\Users\\1\\Desktop\\fast\\";

    public String getFullPath(String filename){
        return fileDir + filename;
    }

    private String createStoreFileName(String originalFilename){
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid+"."+ext;
    }

    private String extractExt(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);
    }
}
