package io.tortoise.controller.sys;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.tortoise.auth.api.dto.CurrentUserDto;
import io.tortoise.commons.utils.AuthUtils;
import io.tortoise.commons.utils.PageUtils;
import io.tortoise.commons.utils.Pager;
import io.tortoise.controller.sys.base.BaseGridRequest;
import io.tortoise.controller.sys.request.SysUserCreateRequest;
import io.tortoise.controller.sys.request.SysUserPwdRequest;
import io.tortoise.controller.sys.request.SysUserStateRequest;
import io.tortoise.controller.sys.response.SysUserGridResponse;
import io.tortoise.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "系统：用户管理")
@RequestMapping("/api/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @ApiOperation("查询用户")
    @PostMapping("/userGrid/{goPage}/{pageSize}")
    public Pager<List<SysUserGridResponse>> userGrid(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody BaseGridRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, sysUserService.query(request));
    }
    /*public Pager<List<SysUserGridResponse>> userGrid(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody UserGridRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, sysUserService.query(request));
    }*/

    @ApiOperation("创建用户")
    @PostMapping("/create")
    public void create(@RequestBody SysUserCreateRequest request){
        sysUserService.save(request);
    }

    @ApiOperation("更新用户")
    @PostMapping("/update")
    public void update(@RequestBody SysUserCreateRequest request){
        sysUserService.update(request);
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete/{userId}")
    public void delete(@PathVariable("userId") Long userId){
        sysUserService.delete(userId);
    }


    @ApiOperation("更新用户状态")
    @PostMapping("/updateStatus")
    public void updateStatus(@RequestBody SysUserStateRequest request){
        sysUserService.updateStatus(request);
    }

    @ApiOperation("用户更新密码")
    @PostMapping("/updatePwd")
    public void updatePwd(@RequestBody SysUserPwdRequest request){

        sysUserService.updatePwd(request);
    }
    @ApiOperation("管理员更新密码")
    @PostMapping("/adminUpdatePwd")
    public void adminUpdatePwd(@RequestBody SysUserPwdRequest request){
        sysUserService.adminUpdatePwd(request);
    }


    @ApiOperation("个人信息")
    @PostMapping("/personInfo")
    public CurrentUserDto personInfo() {
        CurrentUserDto user = AuthUtils.getUser();
        return user;
    }

    @ApiOperation("更新个人信息")
    @PostMapping("/updatePersonInfo")
    public void updatePersonInfo(@RequestBody SysUserCreateRequest request){
        sysUserService.updatePersonInfo(request);
    }

    @ApiOperation("设置语言")
    @PostMapping("/setLanguage/{language}")
    public void setLanguage(@PathVariable String language) {
        CurrentUserDto user = AuthUtils.getUser();
        Optional.ofNullable(language).ifPresent(currentLanguage -> {
            if (!currentLanguage.equals(user.getLanguage())) {
                sysUserService.setLanguage(user.getUserId(), currentLanguage);
            }
        });
    }
}
