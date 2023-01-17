package io.tortoise.controller.chart;

import io.tortoise.base.domain.ChartGroup;
import io.tortoise.controller.request.chart.ChartGroupRequest;
import io.tortoise.dto.chart.ChartGroupDTO;
import io.tortoise.service.chart.ChartGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("chart/group")
public class ChartGroupController {
    @Resource
    private ChartGroupService chartGroupService;

    @PostMapping("/save")
    public ChartGroupDTO save(@RequestBody ChartGroup ChartGroup) {
        return chartGroupService.save(ChartGroup);
    }

    @PostMapping("/tree")
    public List<ChartGroupDTO> tree(@RequestBody ChartGroupRequest ChartGroup) {
        return chartGroupService.tree(ChartGroup);
    }

    @PostMapping("/treeNode")
    public List<ChartGroupDTO> treeNode(@RequestBody ChartGroupRequest ChartGroup) {
        return chartGroupService.tree(ChartGroup);
    }

    @PostMapping("/delete/{id}")
    public void tree(@PathVariable String id) {
        chartGroupService.delete(id);
    }

    @PostMapping("/getScene/{id}")
    public ChartGroup getScene(@PathVariable String id) {
        return chartGroupService.getScene(id);
    }
}
