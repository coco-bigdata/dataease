package io.tortoise.service;

import io.tortoise.base.domain.FileMetadata;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommonFilesService {
    public final static String VIEW_DEFAULT_IMAGE="VIEW_DEFAULT_IMAGE";

    @Resource
    private FileService fileService;


    public ResponseEntity<byte[]> getImageById(String imageId,String defaultImage) {
        byte[] bytes = null;
        MediaType contentType = MediaType.parseMediaType("application/octet-stream");
        FileMetadata fileMetadata = fileService.copyFile(imageId);
        if (fileMetadata == null&& StringUtils.isNotEmpty(defaultImage)) {
            imageId = defaultImage;
            fileMetadata = fileService.copyFile(imageId);
        }
        if (fileMetadata == null) {
            return null;
        }
        bytes = fileService.loadFileAsBytes(imageId);
        return ResponseEntity.ok()
                .contentType(contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileMetadata.getName() + "\"")
                .body(bytes);
    }
}
