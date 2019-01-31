/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.web.domain;

/**
 *
 * @author otahmadov
 */
public class FileWrapper {
    private int id;
    private String path;
    private String fullPath;
    private String name;
    private String type;
    private String titleAz;
    private String titleEn;
    private String titleRu;
    private String descriptionAz;
    private String descriptionEn;
    private String descriptionRu;
    private byte[] file;

    public FileWrapper() {
    }

    public FileWrapper(int id) {
        this.id = id;
    }

    public FileWrapper(String path, String fullPath, String name, String titleAz, String titleEn, String titleRu, String descriptionAz, String descriptionEn, String descriptionRu) {
        this.path = path;
        this.fullPath = fullPath;
        this.name = name;
        this.titleAz = titleAz;
        this.titleEn = titleEn;
        this.titleRu = titleRu;
        this.descriptionAz = descriptionAz;
        this.descriptionEn = descriptionEn;
        this.descriptionRu = descriptionRu;
    }

    
    
    public FileWrapper(int id, String path, String name, String type, byte[] file) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.type = type;
        this.file = file;
    }

    public FileWrapper(int id, String path, String fullPath, String name, String type) {
        this.id = id;
        this.path = path;
        this.fullPath = fullPath;
        this.path = path;
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getTitleAz() {
        return titleAz;
    }

    public void setTitleAz(String titleAz) {
        this.titleAz = titleAz;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getDescriptionAz() {
        return descriptionAz;
    }

    public void setDescriptionAz(String descriptionAz) {
        this.descriptionAz = descriptionAz;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }
    
    
    
}
