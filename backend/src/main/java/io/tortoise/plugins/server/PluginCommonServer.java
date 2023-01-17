package io.tortoise.plugins.server;

import io.tortoise.commons.utils.ServletUtils;
import io.tortoise.plugins.common.dto.PluginSysMenu;
import io.tortoise.plugins.common.service.PluginMenuService;
import io.tortoise.plugins.config.SpringContextUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/pluginCommon")
public class PluginCommonServer {

    @GetMapping("/async/{menuId}")
    public void componentInfo(@PathVariable Long menuId) {
        Map<String, PluginMenuService> pluginMenuServiceMap = SpringContextUtil.getApplicationContext().getBeansOfType(PluginMenuService.class);
        pluginMenuServiceMap.values().stream().forEach(service -> {
            AtomicReference<PluginSysMenu> atomicReference = new AtomicReference<>();
            List<PluginSysMenu> menus = service.menus();
            if (menus.stream().anyMatch(menu -> {
                atomicReference.set(menu);
                return menu.getMenuId() == menuId;
            })) {
                String jsName = atomicReference.get().getComponent();
                HttpServletResponse response = ServletUtils.response();
                BufferedInputStream bis = null;
                InputStream inputStream = null;
                OutputStream os = null; //输出流
                try{
                    inputStream = service.vueResource(jsName);
                    byte[] buffer = new byte[1024];
                    os = response.getOutputStream();
                    bis = new BufferedInputStream(inputStream);
                    int i = bis.read(buffer);
                    while(i != -1){
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    os.flush();
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        bis.close();
                        inputStream.close();
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return;
        });
    }
}
