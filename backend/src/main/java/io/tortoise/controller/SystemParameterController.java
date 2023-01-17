package io.tortoise.controller;

import io.tortoise.base.domain.SystemParameter;
import io.tortoise.commons.constants.ParamConstants;
import io.tortoise.dto.SystemParameterDTO;
import io.tortoise.notice.domain.MailInfo;
import io.tortoise.service.FileService;
import io.tortoise.service.system.SystemParameterService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/system")
public class SystemParameterController {
    @Resource
    private SystemParameterService systemParameterService;

    @Resource
    private FileService fileService;

    @PostMapping("/edit/email")
    public void editMail(@RequestBody List<SystemParameter> systemParameter) {
        systemParameterService.editMail(systemParameter);
    }

    @PostMapping("/testConnection")
    public void testConnection(@RequestBody HashMap<String, String> hashMap) {
        systemParameterService.testConnection(hashMap);
    }

    @GetMapping("/version")
    public String getVersion() {
        return systemParameterService.getVersion();
    }

    @GetMapping("/mail/info")
    public MailInfo mailInfo() {
        return systemParameterService.mailInfo(ParamConstants.Classify.MAIL.getValue());
    }


    @GetMapping("/base/info")
    public List<SystemParameterDTO> getBaseInfo () {
        return systemParameterService.getSystemParameterInfo(ParamConstants.Classify.BASE.getValue());
    }

    @GetMapping("/ui/info")
    public List<SystemParameterDTO> getDisplayInfo () {
        return systemParameterService.getSystemParameterInfo(ParamConstants.Classify.UI.getValue());
    }

    @GetMapping(value="/ui/image/{imageId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> image(@PathVariable("imageId") String imageId) {
        byte[] bytes = fileService.loadFileAsBytes(imageId);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @PostMapping(value="/save/ui", consumes = {"multipart/form-data"})
    public void saveUIInfo (@RequestPart("request") Map<String,List<SystemParameterDTO>> systemParameterMap,@RequestPart(value = "files", required = false) List<MultipartFile> bodyFiles) throws IOException {
        systemParameterService.saveUIInfo(systemParameterMap,bodyFiles);
    }


}
