package io.tortoise.controller.sys;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.tortoise.base.domain.SysRole;
import io.tortoise.commons.utils.PageUtils;
import io.tortoise.commons.utils.Pager;
import io.tortoise.controller.sys.base.BaseGridRequest;
import io.tortoise.controller.sys.request.RoleMenusRequest;
import io.tortoise.controller.sys.response.RoleUserItem;
import io.tortoise.service.sys.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "系统：角色管理")
@RequestMapping("/api/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;


    @ApiOperation("新增角色")
    @PostMapping("/create")
    public void create(@RequestBody SysRole role){
        sysRoleService.add(role);
    }


    @ApiOperation("删除角色")
    @PostMapping("/delete/{roleId}")
    public void delete(@PathVariable("roleId") Long roleId){
        sysRoleService.delete(roleId);
    }


    @ApiOperation("更新角色")
    @PostMapping("/update")
    public void update(@RequestBody SysRole role){
        sysRoleService.update(role);
    }

    @ApiOperation("查询角色")
    @PostMapping("/roleGrid/{goPage}/{pageSize}")
    public Pager<List<SysRole>> roleGrid(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody BaseGridRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        Pager<List<SysRole>> listPager = PageUtils.setPageInfo(page, sysRoleService.query(request));
        return listPager;
    }

    @ApiOperation("查询角色对应的菜单ID")
    @PostMapping("/menuIds/{roleId}")
    public List<Long> menuIdsByRoleId(@PathVariable("roleId") Long roleId){
        return sysRoleService.menuIds(roleId);
    }


    @PostMapping("/saveRolesMenus")
    public void saveRolesMenus(@RequestBody RoleMenusRequest request){
        sysRoleService.batchSaveRolesMenus(request);
    }


    @PostMapping("/all")
    public List<RoleUserItem> all(){
        return sysRoleService.allRoles();
    }
}
