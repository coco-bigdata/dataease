package io.tortoise.plugins.util;

import io.tortoise.commons.license.DefaultLicenseService;
import io.tortoise.commons.license.F2CLicenseResponse;
import io.tortoise.plugins.common.dto.PluginSysMenu;
import io.tortoise.plugins.common.service.PluginMenuService;
import io.tortoise.plugins.config.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PluginUtils {



    private static DefaultLicenseService defaultLicenseService;

    @Autowired
    public void setDefaultLicenseService(DefaultLicenseService defaultLicenseService) {
        PluginUtils.defaultLicenseService = defaultLicenseService;
    }

    public static List<PluginSysMenu> pluginMenus() {
        F2CLicenseResponse f2CLicenseResponse = currentLic();
        if (f2CLicenseResponse.getStatus() != F2CLicenseResponse.Status.valid)
            return new ArrayList<>();
        Map<String, PluginMenuService> pluginMenuServiceMap = SpringContextUtil.getApplicationContext().getBeansOfType(PluginMenuService.class);
        List<PluginSysMenu> menus = pluginMenuServiceMap.values().stream().flatMap(item -> item.menus().stream()).collect(Collectors.toList());
        return menus;
    }

    public static F2CLicenseResponse currentLic() {
        Environment environment = SpringContextUtil.getBean(Environment.class);
        Boolean need_validate_lic = environment.getProperty("tortoise.need_validate_lic", Boolean.class, true);
        if (!need_validate_lic) {
            F2CLicenseResponse f2CLicenseResponse = new F2CLicenseResponse();
            f2CLicenseResponse.setStatus(F2CLicenseResponse.Status.valid);
            return f2CLicenseResponse;
        }
        F2CLicenseResponse f2CLicenseResponse = defaultLicenseService.validateLicense();
        return f2CLicenseResponse;
    }




}
