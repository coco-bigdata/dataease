package io.tortoise.controller.panel.api;


import io.tortoise.base.domain.ChartView;
import io.tortoise.base.domain.ChartViewWithBLOBs;
import io.tortoise.dto.panel.PanelViewDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(tags = "仪表板：视图管理")
@RequestMapping("/api/panelView")
public interface ViewApi {


    @ApiOperation("授权的视图树")
    @PostMapping("/tree")
    List<PanelViewDto> treeWithAuth();


    @ApiOperation("根据仪表板Id查询视图")
    @PostMapping("/viewsWithIds")
    List<ChartView> viewsWithIds(List<String> viewIds);

    @ApiOperation("获取单个视图")
    @GetMapping("/findOne/{id}")
    ChartViewWithBLOBs findOne(String id);






}
