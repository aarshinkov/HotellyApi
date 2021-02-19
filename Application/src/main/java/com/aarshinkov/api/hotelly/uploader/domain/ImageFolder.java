package com.aarshinkov.api.hotelly.uploader.domain;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public enum ImageFolder {

    HOTELS("hotels"),
    USERS("users");

    private final String folder;

    private ImageFolder(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }
}
