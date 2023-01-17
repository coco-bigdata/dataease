package io.tortoise.service.panel;

import io.tortoise.auth.api.dto.CurrentRoleDto;
import io.tortoise.auth.api.dto.CurrentUserDto;
import io.tortoise.base.domain.PanelShare;
import io.tortoise.base.domain.PanelShareExample;
import io.tortoise.base.mapper.PanelShareMapper;
import io.tortoise.base.mapper.ext.ExtPanelShareMapper;
import io.tortoise.base.mapper.ext.query.GridExample;
import io.tortoise.commons.utils.AuthUtils;
import io.tortoise.commons.utils.BeanUtils;
import io.tortoise.commons.utils.CommonBeanFactory;
import io.tortoise.controller.request.panel.PanelShareRequest;
import io.tortoise.controller.sys.base.BaseGridRequest;
import io.tortoise.dto.panel.PanelShareDto;
import io.tortoise.dto.panel.PanelSharePo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ShareService {

    @Autowired(required = false)
    private PanelShareMapper mapper;


    @Resource
    private ExtPanelShareMapper extPanelShareMapper;

    @Transactional
    public void save(PanelShareRequest request){

        //1.先根据仪表板删除所有已经分享的
        Integer type = request.getType();
        List<String> panelIds = request.getPanelIds();
        List<Long> targetIds = request.getTargetIds();
        // 使用原生对象会导致事物失效 所以这里需要使用spring代理对象
        if (CollectionUtils.isNotEmpty(panelIds)){
            ShareService proxy = CommonBeanFactory.getBean(ShareService.class);
            panelIds.forEach(panelId -> {
                proxy.delete(panelId, type);
            });
        }
        if (CollectionUtils.isEmpty(targetIds)) return;

        long now = System.currentTimeMillis();
        List<PanelShare> shares = panelIds.stream().flatMap(panelId ->
                targetIds.stream().map(targetId -> {
                PanelShare share = new PanelShare();
                share.setCreateTime(now);
                share.setPanelGroupId(panelId);
                share.setTargetId(targetId);
                share.setType(type);
                return share;
            })
        ).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(shares)){
            extPanelShareMapper.batchInsert(shares);
        }
    }

    /**
     * panel_group_id建了索引 效率不会很差
     * @param panel_group_id
     */
    @Transactional
    public void delete(String panel_group_id, Integer type){
        PanelShareExample example = new PanelShareExample();
        PanelShareExample.Criteria criteria = example.createCriteria();
        criteria.andPanelGroupIdEqualTo(panel_group_id);
        if(type != null){
            criteria.andTypeEqualTo(type);
        }
        mapper.deleteByExample(example);
    }


    public List<PanelShareDto> queryTree(BaseGridRequest request){
        CurrentUserDto user = AuthUtils.getUser();
        Long userId = user.getUserId();
        Long deptId = user.getDeptId();
        List<Long> roleIds = user.getRoles().stream().map(CurrentRoleDto::getId).collect(Collectors.toList());

        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("deptId", deptId);
        param.put("roleIds", roleIds);

        List<PanelSharePo> datas = extPanelShareMapper.query(param);

        /*List<Long> targetIds = new ArrayList<>();
        targetIds.add(userId);
        targetIds.add(deptId);
        targetIds.addAll(roleIds);

        ConditionEntity condition = new ConditionEntity();
        condition.setField("s.target_id");
        condition.setOperator("in");
        condition.setValue(targetIds);

        request.setConditions(new ArrayList<ConditionEntity>(){{add(condition);}});

        GridExample example = request.convertExample();
        List<PanelSharePo> datas = extPanelShareMapper.query(example);*/
        List<PanelShareDto> dtoLists = datas.stream().map(po -> BeanUtils.copyBean(new PanelShareDto(), po)).collect(Collectors.toList());
        return convertTree(dtoLists);
    }

    //List构建Tree
    private List<PanelShareDto> convertTree(List<PanelShareDto> datas){
        String username = AuthUtils.getUser().getUsername();
        Map<String, List<PanelShareDto>> map = datas.stream().filter(panelShareDto -> StringUtils.isNotEmpty(panelShareDto.getCreator()) && !StringUtils.equals(username, panelShareDto.getCreator())).collect(Collectors.groupingBy(PanelShareDto::getCreator));
        return map.entrySet().stream().map(entry -> {
            PanelShareDto panelShareDto = new PanelShareDto();
            panelShareDto.setName(entry.getKey());
            panelShareDto.setChildren(entry.getValue());
            return panelShareDto;
        }).collect(Collectors.toList());
    }

    public List<PanelShare> queryWithResource(BaseGridRequest request){
        GridExample example = request.convertExample();
        return extPanelShareMapper.queryWithResource(example);
    }


}
