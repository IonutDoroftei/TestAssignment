package com.homework.testassignment.main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FilesInformation")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String creationDate;
    private String size;
    private String uploadDate;

    public FileInfo() {
    }

    public FileInfo(String name, String creationDate, String size, String uploadDate) {
        this.name = name;
        this.creationDate = creationDate;
        this.size = size;
        this.uploadDate = uploadDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return Objects.equals(name, fileInfo.name) &&
                Objects.equals(creationDate, fileInfo.creationDate) &&
                Objects.equals(size, fileInfo.size) &&
                Objects.equals(uploadDate, fileInfo.uploadDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, creationDate, size, uploadDate);
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "name='" + name + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", size='" + size + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}
