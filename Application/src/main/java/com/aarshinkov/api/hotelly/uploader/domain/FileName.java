package com.aarshinkov.api.hotelly.uploader.domain;

import java.io.*;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public class FileName implements Serializable {

    private String fileName;
    private String extension;

    public String getFullName() {
        return fileName + "." + extension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
