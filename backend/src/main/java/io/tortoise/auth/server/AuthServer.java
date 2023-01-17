package io.tortoise.auth.server;

import io.tortoise.auth.api.AuthApi;
import io.tortoise.auth.api.dto.CurrentRoleDto;
import io.tortoise.auth.api.dto.CurrentUserDto;
import io.tortoise.auth.api.dto.LoginDto;
import io.tortoise.auth.config.RsaProperties;
import io.tortoise.auth.entity.SysUserEntity;
import io.tortoise.auth.entity.TokenInfo;
import io.tortoise.auth.service.AuthUserService;
import io.tortoise.auth.util.JWTUtils;
import io.tortoise.auth.util.RsaUtil;
import io.tortoise.commons.utils.BeanUtils;
import io.tortoise.commons.utils.CodingUtil;
import io.tortoise.commons.utils.ServletUtils;

import io.tortoise.exception.TortoiseException;
import io.tortoise.i18n.Translator;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthServer implements AuthApi {

    @Autowired
    private AuthUserService authUserService;


    @Override
    public Object login(@RequestBody LoginDto loginDto) throws Exception {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        SysUserEntity user = authUserService.getUserByName(username);

        if (ObjectUtils.isEmpty(user)) {
            TortoiseException.throwException(Translator.get("i18n_id_or_pwd_error"));
        }
        if (user.getEnabled() == 0) {
            TortoiseException.throwException(Translator.get("i18n_id_or_pwd_error"));
        }
        String realPwd = user.getPassword();
        //私钥解密
        String pwd = RsaUtil.decryptByPrivateKey(RsaProperties.privateKey, password);
        //md5加密
        pwd = CodingUtil.md5(pwd);

        if (!StringUtils.equals(pwd, realPwd)) {
            TortoiseException.throwException(Translator.get("i18n_id_or_pwd_error"));
        }
        Map<String, Object> result = new HashMap<>();
        TokenInfo tokenInfo = TokenInfo.builder().userId(user.getUserId()).username(username).build();
        String token = JWTUtils.sign(tokenInfo, realPwd);
        // 记录token操作时间
        result.put("token", token);
        ServletUtils.setToken(token);
        return result;
    }

    @Override
    public CurrentUserDto userInfo() {
        CurrentUserDto userDto = (CurrentUserDto) SecurityUtils.getSubject().getPrincipal();
        if (ObjectUtils.isEmpty(userDto)) {
            String token = ServletUtils.getToken();
            Long userId = JWTUtils.tokenInfoByToken(token).getUserId();
            SysUserEntity user = authUserService.getUserById(userId);
            CurrentUserDto currentUserDto = BeanUtils.copyBean(new CurrentUserDto(), user);
            List<CurrentRoleDto> currentRoleDtos = authUserService.roleInfos(user.getUserId());
            List<String> permissions = authUserService.permissions(user.getUserId());
            currentUserDto.setRoles(currentRoleDtos);
            currentUserDto.setPermissions(permissions);
            return currentUserDto;
        }
        return userDto;
    }

    @Override
    public String logout() {
        String token = ServletUtils.getToken();
        Long userId = JWTUtils.tokenInfoByToken(token).getUserId();
        authUserService.clearCache(userId);
        return "success";
    }

    @Override
    public Boolean validateName(@RequestBody Map<String, String> nameDto) {
        String userName = nameDto.get("userName");
        if (StringUtils.isEmpty(userName)) return false;
        SysUserEntity userEntity = authUserService.getUserByName(userName);
        if (ObjectUtils.isEmpty(userEntity)) return false;
        return true;
    }

    @Override
    public Boolean isLogin() {
        return null;
    }


}
