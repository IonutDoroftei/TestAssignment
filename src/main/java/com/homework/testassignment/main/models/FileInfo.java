package com.homework.testassignment.main.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "FilesInformation")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String creationDate;
    private String size;
    private String uploadDate;
}
