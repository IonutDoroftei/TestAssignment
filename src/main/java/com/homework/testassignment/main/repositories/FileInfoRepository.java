package com.homework.testassignment.main.repositories;

import com.homework.testassignment.main.models.FileInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends CrudRepository<FileInfo, Long> {
}
