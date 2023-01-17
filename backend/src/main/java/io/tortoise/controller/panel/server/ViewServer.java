package io.tortoise.controller.panel.server;

import io.tortoise.base.domain.ChartView;
import io.tortoise.base.domain.ChartViewWithBLOBs;
import io.tortoise.commons.utils.TreeUtils;
import io.tortoise.controller.panel.api.ViewApi;
import io.tortoise.dto.panel.PanelViewDto;
import io.tortoise.service.chart.ChartViewService;
import io.tortoise.service.panel.PanelViewService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ViewServer implements ViewApi {

    @Autowired
    private PanelViewService panelViewService;

    @Autowired
    private ChartViewService chartViewService;

    /**
     * 为什么查两次？
     * 因为left join 会导致全表扫描
     * 查两次在索引合理情况下 效率比查询一次高
     * @return
     */
    @Override
    public List<PanelViewDto> treeWithAuth() {
        List<PanelViewDto> groups = panelViewService.groups();
        List<PanelViewDto> views = panelViewService.views();
        if(CollectionUtils.isNotEmpty(groups)&&CollectionUtils.isNotEmpty(views)){
            groups.addAll(views);
        }
//        List<PanelViewDto> panelViewDtos = panelViewService.buildTree(groups, views);
        return TreeUtils.mergeTree(groups);
    }

    @Override
    public List<ChartView> viewsWithIds(@RequestBody List<String> viewIds) {
        return chartViewService.viewsByIds(viewIds);
    }

    @Override
    public ChartViewWithBLOBs findOne(@PathVariable String id) {
        return chartViewService.findOne(id);
    }
}
