package com.example.nursinghome.UploadFileCOnfig;

public class FileUpLoadResponse {
    private String filename;
    private String downloadUri;
    private long size;

    public FileUpLoadResponse(String filename, String dowloadUri, long size) {
        this.filename = filename;
        this.downloadUri = dowloadUri;
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}