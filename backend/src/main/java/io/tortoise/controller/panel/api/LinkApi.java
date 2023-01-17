package io.tortoise.controller.panel.api;


import io.tortoise.controller.request.panel.link.EnablePwdRequest;
import io.tortoise.controller.request.panel.link.LinkRequest;
import io.tortoise.controller.request.panel.link.PasswordRequest;
import io.tortoise.dto.panel.link.GenerateDto;
import io.tortoise.dto.panel.link.ValidateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "仪表板：链接管理")
@RequestMapping("/api/link")
public interface LinkApi {


    @ApiOperation("重制密码")
    @PostMapping("/resetPwd")
    void replacePwd(PasswordRequest request);

    @ApiOperation("启用密码")
    @PostMapping("/enablePwd")
    void enablePwd(EnablePwdRequest request);

    @ApiOperation("切换开关")
    @PostMapping("/switchLink")
    void switchLink(LinkRequest request);

    @ApiOperation("当前链接信息")
    @PostMapping("/currentGenerate/{resourceId}")
    GenerateDto currentGenerate(String resourceId);

    @ApiOperation("验证访问")
    @PostMapping("/validate")
    ValidateDto validate(Map<String, String> param) throws Exception;

    @ApiOperation("验证密码")
    @PostMapping("/validatePwd")
    boolean validatePwd(PasswordRequest request) throws Exception;
}
