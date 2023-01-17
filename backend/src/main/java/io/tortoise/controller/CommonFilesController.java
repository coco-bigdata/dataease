package io.tortoise.controller;

import io.tortoise.service.CommonFilesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("common-files")
public class CommonFilesController {
    @Resource
    private CommonFilesService commonFilesService;

    @GetMapping("/images/{imageId}")
    public ResponseEntity<byte[]> image(@PathVariable("imageId") String imageId) {
        return commonFilesService.getImageById(imageId,null);
    }

    @GetMapping("/images/{imageId}/{defaultImage}")
    public ResponseEntity<byte[]> image(@PathVariable("imageId") String imageId,@PathVariable("defaultImage") String defaultImage) {
        return commonFilesService.getImageById(imageId,defaultImage);
    }
}
