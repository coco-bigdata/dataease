package io.tortoise.controller.panel.api;

import io.tortoise.base.domain.PanelShare;
import io.tortoise.controller.request.panel.PanelShareRequest;
import io.tortoise.controller.sys.base.BaseGridRequest;
import io.tortoise.dto.panel.PanelShareDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 分享API
 */
@Api(tags = "仪表板：分享管理")
@RequestMapping("/api/share")
public interface ShareApi {

    @ApiOperation("创建分享")
    @PostMapping("/")
    void share(PanelShareRequest request);

    @ApiOperation("查询分享")
    @PostMapping("/treeList")
    List<PanelShareDto> treeList(BaseGridRequest request);


    @ApiOperation("根据资源查询分享")
    @PostMapping("/queryWithResourceId")
    List<PanelShare> queryWithResourceId(BaseGridRequest request);

    
}
