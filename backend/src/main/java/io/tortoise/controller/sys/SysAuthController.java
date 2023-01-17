package io.tortoise.controller.sys;

import io.tortoise.base.domain.SysAuthDetail;
import io.tortoise.controller.handler.annotation.I18n;
import io.tortoise.controller.request.BaseTreeRequest;
import io.tortoise.controller.request.SysAuthRequest;
import io.tortoise.dto.SysAuthDetailDTO;
import io.tortoise.dto.VAuthModelDTO;
import io.tortoise.service.sys.SysAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Author: wangjiahao
 * Date: 2021-05-11
 * Description:
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：权限管理")
@RequestMapping("/api/sys_auth")
public class SysAuthController {

    @Resource
    private SysAuthService sysAuthService;

    @ApiOperation("查询视图")
    @PostMapping("/authModels")
    @I18n
    public  List<VAuthModelDTO> authModels(@RequestBody BaseTreeRequest request){
        return sysAuthService.searchAuthModelTree(request);
    }


    @ApiOperation("查询授权")
    @PostMapping("/authDetails")
    public Map<String,List<SysAuthDetailDTO>> authDetails(@RequestBody SysAuthRequest request){
        return sysAuthService.searchAuthDetails(request);
    }


    @ApiOperation("查询授权模板")
    @GetMapping("/authDetailsModel/{authType}")
    @I18n
    public List<SysAuthDetail>authDetailsModel(@PathVariable String authType){
        return sysAuthService.searchAuthDetailsModel(authType);
    }


    @ApiOperation("修改权限")
    @PostMapping("/authChange")
    public void authChange(@RequestBody SysAuthRequest request){
        sysAuthService.authChange(request);
    }
}
