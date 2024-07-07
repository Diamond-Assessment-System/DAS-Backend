package com.project.DASBackend.resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Base64MultipartFile implements MultipartFile {

    private final byte[] fileContent;
    private final String header;

    public Base64MultipartFile(byte[] fileContent, String header) {
        this.fileContent = fileContent;
        this.header = header;
    }

    @Override
    public String getName() {
        return header.split(";")[0];
    }

    @Override
    public String getOriginalFilename() {
        return header.split(";")[0];
    }

    @Override
    public String getContentType() {
        return header.split(";")[1];
    }

    @Override
    public boolean isEmpty() {
        return fileContent == null || fileContent.length == 0;
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return fileContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
        java.nio.file.Files.write(dest.toPath(), fileContent);
    }
}
