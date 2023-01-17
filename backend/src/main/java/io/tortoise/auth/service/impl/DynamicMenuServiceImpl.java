package io.tortoise.auth.service.impl;

import io.tortoise.auth.api.dto.DynamicMenuDto;
import io.tortoise.auth.api.dto.MenuMeta;
import io.tortoise.auth.service.DynamicMenuService;
import io.tortoise.base.domain.SysMenu;
import io.tortoise.base.domain.SysMenuExample;
import io.tortoise.base.mapper.SysMenuMapper;
import io.tortoise.base.mapper.ext.ExtPluginSysMenuMapper;
import io.tortoise.plugins.common.dto.PluginSysMenu;
import io.tortoise.plugins.util.PluginUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DynamicMenuServiceImpl implements DynamicMenuService {

    @Autowired(required = false)
    private SysMenuMapper sysMenuMapper;

    @Resource
    private ExtPluginSysMenuMapper extPluginSysMenuMapper;

    @Override
    public List<DynamicMenuDto> load(String userId) {
        SysMenuExample sysMenuExample = new SysMenuExample();
        sysMenuExample.createCriteria().andTypeLessThanOrEqualTo(1);
        sysMenuExample.setOrderByClause(" menu_sort ");
        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(sysMenuExample);
        List<DynamicMenuDto> dynamicMenuDtos = sysMenus.stream().map(this::convert).collect(Collectors.toList());
        //增加插件中的菜单
        List<PluginSysMenu> pluginSysMenus = PluginUtils.pluginMenus();
        if (CollectionUtils.isNotEmpty(pluginSysMenus) ) {
            pluginSysMenus = pluginSysMenus.stream().filter(menu -> menu.getType() <= 1).collect(Collectors.toList());
            List<DynamicMenuDto> pluginDtos = pluginSysMenus.stream().map(this::convert).collect(Collectors.toList());
            dynamicMenuDtos.addAll(pluginDtos);
        }
        dynamicMenuDtos = dynamicMenuDtos.stream().sorted((s1, s2) -> {
            int sortIndex1 = null == s1.getMenuSort() ? 999: s1.getMenuSort();
            int sortIndex2 = null == s2.getMenuSort() ? 999: s2.getMenuSort();
            return sortIndex1 - sortIndex2;
        }).collect(Collectors.toList());
        dynamicMenuDtos.sort((s1, s2) -> s1.getHidden().compareTo(s2.getHidden()));
        List<DynamicMenuDto> result = buildTree(dynamicMenuDtos);
        return result;
    }

    private DynamicMenuDto convert(SysMenu sysMenu){
        DynamicMenuDto dynamicMenuDto = new DynamicMenuDto();
        dynamicMenuDto.setId(sysMenu.getMenuId());
        dynamicMenuDto.setPid(sysMenu.getPid());
        dynamicMenuDto.setName(sysMenu.getName());
        dynamicMenuDto.setPath(sysMenu.getPath());
        dynamicMenuDto.setRedirect(null);
        dynamicMenuDto.setType(sysMenu.getType());
        dynamicMenuDto.setComponent(sysMenu.getComponent());
        MenuMeta menuMeta = new MenuMeta();
        menuMeta.setTitle(sysMenu.getTitle());
        menuMeta.setIcon(sysMenu.getIcon());
        dynamicMenuDto.setMeta(menuMeta);
        dynamicMenuDto.setPermission(sysMenu.getPermission());
        dynamicMenuDto.setMenuSort(sysMenu.getMenuSort());
        dynamicMenuDto.setHidden(sysMenu.getHidden());
        dynamicMenuDto.setIsPlugin(false);
        return dynamicMenuDto;
    }
    private DynamicMenuDto convert(PluginSysMenu sysMenu){
        DynamicMenuDto dynamicMenuDto = new DynamicMenuDto();
        dynamicMenuDto.setId(sysMenu.getMenuId());
        dynamicMenuDto.setPid(sysMenu.getPid());
        dynamicMenuDto.setName(sysMenu.getName());
        dynamicMenuDto.setPath(sysMenu.getPath());
        dynamicMenuDto.setRedirect(null);
        dynamicMenuDto.setType(sysMenu.getType());
        dynamicMenuDto.setComponent(sysMenu.getComponent());
        MenuMeta menuMeta = new MenuMeta();
        menuMeta.setTitle(sysMenu.getTitle());
        menuMeta.setIcon(sysMenu.getIcon());
        dynamicMenuDto.setMeta(menuMeta);
        dynamicMenuDto.setPermission(sysMenu.getPermission());
        dynamicMenuDto.setMenuSort(sysMenu.getMenuSort());
        dynamicMenuDto.setHidden(sysMenu.getHidden());
        dynamicMenuDto.setIsPlugin(true);
        dynamicMenuDto.setNoLayout(!!sysMenu.isNoLayout());
        return dynamicMenuDto;
    }

    private List<DynamicMenuDto> buildTree(List<DynamicMenuDto> lists){
        List<DynamicMenuDto> rootNodes = new ArrayList<>();
        lists.forEach(node -> {
            if (isParent(node.getPid())) {
                rootNodes.add(node);
            }
            lists.forEach(tNode -> {
                if (tNode.getPid() == node.getId()) {
                    if (node.getChildren() == null) {
                        node.setChildren(new ArrayList<DynamicMenuDto>());
                        node.setRedirect(node.getPath()+"/"+tNode.getPath());//第一个子节点的path
                    }
                    node.getChildren().add(tNode);
                }
            });
        });
        return rootNodes;

    }

    private Boolean isParent(Long pid){
        return null == pid || pid==0L;
    }

    @Transactional
    public void syncPluginMenu() {
        List<PluginSysMenu> pluginSysMenuList = PluginUtils.pluginMenus();
        extPluginSysMenuMapper.deletePluginMenu();
        if(CollectionUtils.isNotEmpty(pluginSysMenuList)){
            extPluginSysMenuMapper.savePluginMenu(pluginSysMenuList);
        }
    }
}
