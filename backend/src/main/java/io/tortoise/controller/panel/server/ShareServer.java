package io.tortoise.controller.panel.server;

import io.tortoise.base.domain.PanelShare;
import io.tortoise.controller.panel.api.ShareApi;
import io.tortoise.controller.request.panel.PanelShareRequest;
import io.tortoise.controller.sys.base.BaseGridRequest;
import io.tortoise.dto.panel.PanelShareDto;
import io.tortoise.service.panel.ShareService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ShareServer implements ShareApi {

    @Resource
    private ShareService shareService;
    @Override
    public void share(@RequestBody PanelShareRequest request) {
        shareService.save(request);
    }

    @Override
    public List<PanelShareDto> treeList(@RequestBody BaseGridRequest request) {
        return shareService.queryTree(request);
    }


    @Override
    public List<PanelShare> queryWithResourceId(@RequestBody BaseGridRequest request) {
        return shareService.queryWithResource(request);
    }
}
