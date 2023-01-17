package io.tortoise.service.sys;


import io.tortoise.auth.api.dto.CurrentUserDto;
import io.tortoise.base.domain.SysAuth;
import io.tortoise.base.domain.SysAuthDetail;
import io.tortoise.base.mapper.SysAuthMapper;
import io.tortoise.base.mapper.ext.ExtSysAuthDetailMapper;
import io.tortoise.base.mapper.ext.ExtSysAuthMapper;
import io.tortoise.base.mapper.ext.ExtVAuthModelMapper;
import io.tortoise.commons.constants.SystemConstants;
import io.tortoise.commons.utils.AuthUtils;
import io.tortoise.controller.request.BaseTreeRequest;
import io.tortoise.controller.request.SysAuthRequest;
import io.tortoise.dto.SysAuthDetailDTO;
import io.tortoise.dto.VAuthModelDTO;
import io.tortoise.i18n.Translator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class SysAuthService {


    @Resource
    private ExtSysAuthMapper extSysAuthMapper;

    @Resource
    private SysAuthMapper sysAuthMapper;

    @Resource
    private ExtSysAuthDetailMapper extSysAuthDetailMapper;

    @Resource
    private ExtVAuthModelMapper extVAuthModelMapper;


    private static List<String> PRI_MODEL_TYPE = Arrays.asList("link", "dataset", "chart", "panel", "menu");



    /**
     * @Description: 查询可见授权数据的数据如果是管理员（IsAdmin = true）且modelType 为link dataset chart panel menu可以查询到所有的数据，
     * 如果是普通用户，只能查询到自己的数据；但是 node_type 为spine 时 节点也会返回
     **/
    public List<VAuthModelDTO> searchAuthModelTree(BaseTreeRequest request) {
        CurrentUserDto currentUserDto = AuthUtils.getUser();
        request.setCreateBy(String.valueOf(currentUserDto.getUserId()));
        if (PRI_MODEL_TYPE.contains(request.getModelType()) && (currentUserDto.getIsAdmin() == null || !currentUserDto.getIsAdmin())) {
            request.setWithAuth("1");
        } else {
            request.setWithAuth("0");
        }
        return extVAuthModelMapper.searchTree(request);
    }


    /**
     * @Description: 查询授权明细map
     **/
    public Map<String, List<SysAuthDetailDTO>> searchAuthDetails(SysAuthRequest request) {
        List<SysAuthDetailDTO> authDetailDTOList = extSysAuthMapper.searchAuth(request);
        return Optional.ofNullable(authDetailDTOList).orElse(new ArrayList<>()).stream()
                .collect(groupingBy(SysAuthDetailDTO::getAuthSource));
    }

    /**
     * @Description: 每个类型的授权都会在表中预制各个授权项的模板 存在auth_id 中；
     **/
    public List<SysAuthDetail> searchAuthDetailsModel(String authType) {
        return extSysAuthDetailMapper.searchAuthTypeModel(authType);
    }

    public void authChange(SysAuthRequest request) {
        SysAuthDetail sysAuthDetail = request.getAuthDetail();
        //TODO 获取需要授权的资源id(当前节点和所有权限的下级节点)
        List<String> authSources = getAuthModels(request.getAuthSource(), request.getAuthSourceType());
        if (CollectionUtils.isEmpty(authSources)) {
            throw new RuntimeException(Translator.get("i18n_auth_source_be_canceled"));
        }
        //TODO 获取需要被授权的目标id(部门当前节点和所有权限的下级节点)
        List<String> authTargets = getAuthModels(request.getAuthTarget(), request.getAuthTargetType());

        if (CollectionUtils.isNotEmpty(authSources) && CollectionUtils.isNotEmpty(authTargets)) {
            List<String> authIdChange = new ArrayList<>();
            authTargets.stream().forEach(authTarget -> {
                authSources.forEach(authSource -> {
                    String authId = checkAuth(authSource, request.getAuthSourceType(), authTarget, request.getAuthTargetType());
                    authIdChange.add(authId);
                });
            });
            // 授权修改
            if (sysAuthDetail.getPrivilegeValue() == SystemConstants.PRIVILEGE_VALUE.ON) {
                //当前为开启1 >>> 关闭0 需要将权限级别（PrivilegeType）大于当前级别的全新都修改为关闭 0
                extSysAuthDetailMapper.authDetailsChange(SystemConstants.PRIVILEGE_VALUE.OFF, sysAuthDetail.getPrivilegeType(), authIdChange);
            } else {
                //当前为关闭0 >>> 开启1 需要将权限级别（PrivilegeType）小于当前级别的全新都修改为开启 1
                extSysAuthDetailMapper.authDetailsChange(SystemConstants.PRIVILEGE_VALUE.ON, sysAuthDetail.getPrivilegeType(), authIdChange);
            }
        }
    }

    private List<String> getAuthModels(String id, String type) {
        List<VAuthModelDTO> vAuthModelDTOS = searchAuthModelTree(new BaseTreeRequest(id, type, SystemConstants.WITH_EXTEND.CHILDREN));
        List<String> authSources = Optional.ofNullable(vAuthModelDTOS).orElse(new ArrayList<>()).stream().map(VAuthModelDTO::getId)
                .collect(Collectors.toList());
        return authSources;
    }

    /**
     * @Description: 查询当前target 是否有存在授权 不存在 增加权限 并复制权限模板
     **/
    private String checkAuth(String authSource, String authSourceType, String authTarget, String authTargetType) {
        String authId = extSysAuthMapper.findAuthId(authSource, authSourceType, authTarget, authTargetType);
        if (StringUtils.isEmpty(authId)) {
            authId = UUID.randomUUID().toString();
            //TODO 插入权限
            SysAuth sysAuthRecord = new SysAuth();
            sysAuthRecord.setId(authId);
            sysAuthRecord.setAuthSource(authSource);
            sysAuthRecord.setAuthSourceType(authSourceType);
            sysAuthRecord.setAuthTarget(authTarget);
            sysAuthRecord.setAuthTargetType(authTargetType);
            sysAuthRecord.setAuthTime(System.currentTimeMillis());
            sysAuthRecord.setAuthUser(AuthUtils.getUser().getUsername());
            sysAuthMapper.insertSelective(sysAuthRecord);

            //TODO 复制权限模板
            extSysAuthDetailMapper.copyAuthModel(authSourceType, authId, AuthUtils.getUser().getUsername());
        }

        return authId;
    }

    public void checkTreeNoManageCount(String modelType,String nodeId){
        if(extSysAuthMapper.checkTreeNoManageCount(AuthUtils.getUser().getUserId(),modelType,nodeId)){
            throw new RuntimeException(Translator.get("i18n_no_all_delete_privilege_folder"));
        }
    }

}
