package io.tortoise.auth.server;

import io.tortoise.auth.api.DynamicMenuApi;
import io.tortoise.auth.api.dto.DynamicMenuDto;
import io.tortoise.auth.service.DynamicMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DynamicMenuServer implements DynamicMenuApi {
    @Autowired
    private DynamicMenuService dynamicMenuService;

    @Override
    public List<DynamicMenuDto> menus() {
        //ServletUtils.getToken()
        return dynamicMenuService.load(null);
    }
}
