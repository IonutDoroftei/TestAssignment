package com.homework.testassignment.main.services;

import com.homework.testassignment.main.models.FileInfo;
import com.homework.testassignment.main.repositories.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileInfoService {
    @Autowired
    private FileInfoRepository fileInfoRepo;

    public void saveFileMetadata(MultipartFile file) {
        FileInfo info = new FileInfo();
        info.setName(file.getOriginalFilename());
        info.setSize(file.getSize() + "KB");
        info.setUploadDate(String.valueOf(System.currentTimeMillis()));
        //Unable to get creation date
        info.setCreationDate("Unknown");
        fileInfoRepo.save(info);
    }
}
