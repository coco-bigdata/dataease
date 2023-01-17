package io.tortoise.plugins.server;


import io.tortoise.auth.api.dto.CurrentUserDto;
import io.tortoise.commons.constants.AuthConstants;
import io.tortoise.commons.utils.AuthUtils;
import io.tortoise.controller.handler.annotation.I18n;
import io.tortoise.listener.util.CacheUtils;
import io.tortoise.plugins.config.SpringContextUtil;
import io.tortoise.plugins.xpack.auth.dto.request.XpackBaseTreeRequest;
import io.tortoise.plugins.xpack.auth.dto.request.XpackSysAuthRequest;
import io.tortoise.plugins.xpack.auth.dto.response.XpackSysAuthDetail;
import io.tortoise.plugins.xpack.auth.dto.response.XpackSysAuthDetailDTO;
import io.tortoise.plugins.xpack.auth.dto.response.XpackVAuthModelDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import io.tortoise.plugins.xpack.auth.service.AuthXpackService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/plugin/auth")
@RestController
public class XAuthServer {

    @PostMapping("/authModels")
    @I18n
    public List<XpackVAuthModelDTO> authModels(@RequestBody XpackBaseTreeRequest request){
        AuthXpackService sysAuthService = SpringContextUtil.getBean(AuthXpackService.class);
        CurrentUserDto user = AuthUtils.getUser();
        return sysAuthService.searchAuthModelTree(request, user.getUserId(), user.getIsAdmin());
    }

    @PostMapping("/authDetails")
    public Map<String,List<XpackSysAuthDetailDTO>> authDetails(@RequestBody XpackSysAuthRequest request){
        AuthXpackService sysAuthService = SpringContextUtil.getBean(AuthXpackService.class);
        return sysAuthService.searchAuthDetails(request);
    }

    @GetMapping("/authDetailsModel/{authType}")
    @I18n
    public List<XpackSysAuthDetail>authDetailsModel(@PathVariable String authType){
        AuthXpackService sysAuthService = SpringContextUtil.getBean(AuthXpackService.class);
        return sysAuthService.searchAuthDetailsModel(authType);
    }

    @PostMapping("/authChange")
    public void authChange(@RequestBody XpackSysAuthRequest request){
        AuthXpackService sysAuthService = SpringContextUtil.getBean(AuthXpackService.class);
        CurrentUserDto user = AuthUtils.getUser();
        sysAuthService.authChange(request, user.getUserId(), user.getUsername(), user.getIsAdmin());
        // 当权限发生变化 前端实时刷新对应菜单
        Optional.ofNullable(request.getAuthSourceType()).ifPresent(type -> {
            if (StringUtils.equals("menu", type)) {
                CacheUtils.removeAll(AuthConstants.USER_CACHE_NAME);
                CacheUtils.removeAll(AuthConstants.USER_ROLE_CACHE_NAME);
                CacheUtils.removeAll(AuthConstants.USER_PERMISSION_CACHE_NAME);
            }
        });
    }
}
