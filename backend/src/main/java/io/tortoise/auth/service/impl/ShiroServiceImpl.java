package io.tortoise.auth.service.impl;

import io.tortoise.auth.service.ShiroService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ShiroServiceImpl implements ShiroService {
    
    private final static String ANON = "anon";



    @Override
    public Map<String, String> loadFilterChainDefinitionMap() {
        // 权限控制map
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置过滤:不会被拦截的链接 -> 放行 start ----------------------------------------------------------
        // 放行Swagger2页面，需要放行这些

        filterChainDefinitionMap.put("/swagger-ui.html",ANON);
        filterChainDefinitionMap.put("/swagger-ui/**",ANON);
        filterChainDefinitionMap.put("/swagger/**",ANON);
        filterChainDefinitionMap.put("/webjars/**", ANON);
        filterChainDefinitionMap.put("/swagger-resources/**",ANON);
        filterChainDefinitionMap.put("/v2/**",ANON);
        filterChainDefinitionMap.put("/v3/**",ANON);
        filterChainDefinitionMap.put("/static/**", ANON);
        filterChainDefinitionMap.put("/css/**", ANON);
        filterChainDefinitionMap.put("/js/**", ANON);
        filterChainDefinitionMap.put("/img/**", ANON);
        filterChainDefinitionMap.put("/fonts/**", ANON);
        filterChainDefinitionMap.put("/favicon.ico", ANON);
        filterChainDefinitionMap.put("/", ANON);
        filterChainDefinitionMap.put("/login", ANON);
        filterChainDefinitionMap.put("/link/**", ANON);
        filterChainDefinitionMap.put("/index.html", ANON);
        filterChainDefinitionMap.put("/link.html", ANON);

        //验证链接
        filterChainDefinitionMap.put("/api/link/validate**", ANON);
        filterChainDefinitionMap.put("/panel/group/findOne/**", ANON);
        filterChainDefinitionMap.put("/chart/view/getData/**", ANON);


        filterChainDefinitionMap.put("/system/ui/**", ANON);

        filterChainDefinitionMap.put("/api/auth/login", ANON);
        filterChainDefinitionMap.put("/api/auth/logout", ANON);
        filterChainDefinitionMap.put("/api/auth/validateName", ANON);
        filterChainDefinitionMap.put("/unauth", ANON);
        filterChainDefinitionMap.put("/display/**", ANON);
        filterChainDefinitionMap.put("/tokenExpired", ANON);
        filterChainDefinitionMap.put("/downline", ANON);
        filterChainDefinitionMap.put("/common-files/**", ANON);
        filterChainDefinitionMap.put("/api/auth/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/**", "jwt");
        return filterChainDefinitionMap;
    }
    
   

    @Override
    public void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, Integer roleId, Boolean isRemoveSession) {

    }

    @Override
    public void updatePermissionByRoleId(Integer roleId, Boolean isRemoveSession) {

    }
}
