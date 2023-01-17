package io.tortoise.auth.service.impl;

import io.tortoise.auth.api.dto.CurrentRoleDto;
import io.tortoise.auth.entity.SysUserEntity;
import io.tortoise.base.domain.SysUser;
import io.tortoise.base.mapper.SysUserMapper;
import io.tortoise.base.mapper.ext.AuthMapper;
import io.tortoise.auth.service.AuthUserService;
import io.tortoise.commons.constants.AuthConstants;
import io.tortoise.commons.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthUserServiceImpl implements AuthUserService {


    @Resource
    private AuthMapper authMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private DynamicMenuServiceImpl dynamicMenuService;

    /**
     * 此处需被F2CRealm登录认证调用 也就是说每次请求都会被调用 所以最好加上缓存
     * @param userId
     * @return
     */
    @Cacheable(value = AuthConstants.USER_CACHE_NAME,  key = "'user' + #userId" )
    @Override
    public SysUserEntity getUserById(Long userId){
        return authMapper.findUser(userId);
    }

    @Override
    public SysUserEntity getUserByName(String username) {
        return authMapper.findUserByName(username);
    }

    @Override
    public List<String> roles(Long userId){
        return authMapper.roleCodes(userId);
    }

    /**
     * 此处需被F2CRealm登录认证调用 也就是说每次请求都会被调用 所以最好加上缓存
     * @param userId
     * @return
     */
    @Cacheable(value = AuthConstants.USER_PERMISSION_CACHE_NAME,  key = "'user' + #userId" )
    @Override
    public List<String> permissions(Long userId){
        try {
            // 用户登录获取菜单权限时同时更新插件菜单表
            dynamicMenuService.syncPluginMenu();
        }catch (Exception e){
            LogUtil.error(e);
            //ignore
        }
        List<String> permissions;
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if(sysUser.getIsAdmin()!=null&&sysUser.getIsAdmin()){
            permissions = authMapper.permissionsAll();
        }else{
            permissions = authMapper.permissions(userId);
        }
        return Optional.ofNullable(permissions).orElse(new ArrayList<>()).stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());
    }

    /**
     * 此处需被F2CRealm登录认证调用 也就是说每次请求都会被调用 所以最好加上缓存
     * @param userId
     * @return
     */
    @Cacheable(value = AuthConstants.USER_ROLE_CACHE_NAME,  key = "'user' + #userId" )
    @Override
    public List<CurrentRoleDto> roleInfos(Long userId) {
        return authMapper.roles(userId);
    }


    /**
     * 一波清除3个缓存
     * @param userId
     */
    @Caching(evict = {
            @CacheEvict(value = AuthConstants.USER_CACHE_NAME, key = "'user' + #userId"),
            @CacheEvict(value = AuthConstants.USER_ROLE_CACHE_NAME, key = "'user' + #userId"),
            @CacheEvict(value = AuthConstants.USER_PERMISSION_CACHE_NAME, key = "'user' + #userId")
    })
    @Override
    public void clearCache(Long userId) {
        LogUtil.info("正在清除用户缓存【{}】",userId);
    }

}
