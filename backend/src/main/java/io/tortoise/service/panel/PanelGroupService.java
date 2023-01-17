package io.tortoise.service.panel;

import io.tortoise.base.domain.*;
import io.tortoise.base.mapper.ChartViewMapper;
import io.tortoise.base.mapper.PanelGroupMapper;
import io.tortoise.base.mapper.ext.ExtPanelGroupMapper;
import io.tortoise.commons.constants.PanelConstants;
import io.tortoise.commons.utils.AuthUtils;
import io.tortoise.commons.utils.TreeUtils;
import io.tortoise.controller.request.panel.PanelGroupRequest;
import io.tortoise.dto.chart.ChartViewDTO;
import io.tortoise.dto.panel.PanelGroupDTO;
import io.tortoise.exception.TortoiseException;
import io.tortoise.i18n.Translator;
import io.tortoise.service.chart.ChartViewService;
import io.tortoise.service.sys.SysAuthService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: wangjiahao
 * Date: 2021-03-05
 * Description:
 */
@Service
public class PanelGroupService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PanelGroupMapper panelGroupMapper;
    @Resource
    private ExtPanelGroupMapper extPanelGroupMapper;
    @Resource
    private ChartViewService chartViewService;
    @Resource
    private ChartViewMapper chartViewMapper;
    @Resource
    private StoreService storeService;
    @Resource
    private ShareService shareService;
    @Resource
    private PanelLinkService panelLinkService;
    @Resource
    private SysAuthService sysAuthService;

    public List<PanelGroupDTO> tree(PanelGroupRequest panelGroupRequest) {
        String userId = String.valueOf(AuthUtils.getUser().getUserId());
        panelGroupRequest.setUserId(userId);
        List<PanelGroupDTO> panelGroupDTOList = extPanelGroupMapper.panelGroupList(panelGroupRequest);
        List<PanelGroupDTO> result = TreeUtils.mergeTree(panelGroupDTOList,"panel_list");
        return result;
    }

    public List<PanelGroupDTO> defaultTree(PanelGroupRequest panelGroupRequest) {
        String userId = String.valueOf(AuthUtils.getUser().getUserId());
        panelGroupRequest.setUserId(userId);
        List<PanelGroupDTO> panelGroupDTOList = extPanelGroupMapper.panelGroupListDefault(panelGroupRequest);
        List<PanelGroupDTO> result = TreeUtils.mergeTree(panelGroupDTOList,"default_panel");
        return result;
    }

    @Transactional
    public PanelGroup saveOrUpdate(PanelGroupRequest request) {
        String panelId = request.getId();
        if (StringUtils.isEmpty(panelId)) {
            // 新建
            checkPanelName(request.getName(), request.getPid(), PanelConstants.OPT_TYPE_INSERT, null);
            panelId = UUID.randomUUID().toString();
            request.setId(panelId);
            request.setCreateTime(System.currentTimeMillis());
            request.setCreateBy(AuthUtils.getUser().getUsername());
            panelGroupMapper.insert(request);
        } else if ("toDefaultPanel".equals(request.getOptType())) {
            panelId = UUID.randomUUID().toString();
            // 转存为默认仪表板
            PanelGroupWithBLOBs newDefaultPanel = panelGroupMapper.selectByPrimaryKey(request.getId());
            newDefaultPanel.setPanelType(PanelConstants.PANEL_TYPE_SYSTEM);
            newDefaultPanel.setNodeType(PanelConstants.PANEL_NODE_TYPE_PANEL);
            newDefaultPanel.setName(request.getName());
            newDefaultPanel.setId(panelId);
            newDefaultPanel.setPid(PanelConstants.PANEL_GATHER_DEFAULT_PANEL);
            newDefaultPanel.setLevel(0);
            newDefaultPanel.setSource(request.getId());
            newDefaultPanel.setCreateBy(AuthUtils.getUser().getUsername());
            checkPanelName(newDefaultPanel.getName(), newDefaultPanel.getPid(), PanelConstants.OPT_TYPE_INSERT, newDefaultPanel.getId());
            panelGroupMapper.insertSelective(newDefaultPanel);
        } else {
            // 更新
            if (StringUtils.isNotEmpty(request.getName())) {
                checkPanelName(request.getName(), request.getPid(), PanelConstants.OPT_TYPE_UPDATE, request.getId());
            }
            panelGroupMapper.updateByPrimaryKeySelective(request);
        }

        //带有权限的返回
        PanelGroupRequest authRequest = new PanelGroupRequest();
        authRequest.setId(panelId);
        authRequest.setUserId(String.valueOf(AuthUtils.getUser().getUserId()));
        List<PanelGroupDTO> panelGroupDTOList = extPanelGroupMapper.panelGroupList(authRequest);
        if(!CollectionUtils.isNotEmpty(panelGroupDTOList)){
            TortoiseException.throwException("未查询到用户对应的资源权限，请尝试刷新重新保存");
        }

        return panelGroupDTOList.get(0);
    }


    private void checkPanelName(String name, String pid, String optType, String id) {
        PanelGroupExample groupExample = new PanelGroupExample();
        if (PanelConstants.OPT_TYPE_INSERT.equalsIgnoreCase(optType)) {
            groupExample.createCriteria().andPidEqualTo(pid).andNameEqualTo(name);
        } else if (PanelConstants.OPT_TYPE_UPDATE.equalsIgnoreCase(optType)) {
            groupExample.createCriteria().andPidEqualTo(pid).andNameEqualTo(name).andIdNotEqualTo(id);
        }

        List<PanelGroup> checkResult = panelGroupMapper.selectByExample(groupExample);
        if (CollectionUtils.isNotEmpty(checkResult)) {
            TortoiseException.throwException(Translator.get("i18n_same_folder_can_not_repeat"));
        }
    }


    public void deleteCircle(String id) {
        Assert.notNull(id, "id cannot be null");
        sysAuthService.checkTreeNoManageCount("panel",id);
        // 同时会删除对应默认仪表盘
        extPanelGroupMapper.deleteCircle(id);
        storeService.removeByPanelId(id);
        shareService.delete(id, null);
        panelLinkService.deleteByResourceId(id);
    }


    public PanelGroupWithBLOBs findOne(String panelId) {
        PanelGroupWithBLOBs panelGroupWithBLOBs = panelGroupMapper.selectByPrimaryKey(panelId);
        if(panelGroupWithBLOBs!=null&& StringUtils.isNotEmpty(panelGroupWithBLOBs.getSource())){
            return  panelGroupMapper.selectByPrimaryKey(panelGroupWithBLOBs.getSource());
        }
        return panelGroupWithBLOBs;
    }


    public List<ChartViewDTO> getUsableViews(String panelId) throws Exception {
        List<ChartViewDTO> chartViewDTOList = new ArrayList<>();
        List<ChartView> allChartView = chartViewMapper.selectByExample(null);
        Optional.ofNullable(allChartView).orElse(new ArrayList<>()).stream().forEach(chartView -> {
            try {
                chartViewDTOList.add(chartViewService.getData(chartView.getId(), null));
            } catch (Exception e) {
                LOGGER.error("获取view详情出错：" + chartView.getId(), e);
            }
        });
        return chartViewDTOList;
    }
}
