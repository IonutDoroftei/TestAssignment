package com.homework.testassignment.main.repositories;

import com.homework.testassignment.main.models.FileInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
}
