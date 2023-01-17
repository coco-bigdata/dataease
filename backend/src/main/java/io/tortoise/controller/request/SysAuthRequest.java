package io.tortoise.controller.request;

import io.tortoise.base.domain.SysAuth;
import io.tortoise.base.domain.SysAuthDetail;
import lombok.Data;

import java.util.List;

/**
 * Author: wangjiahao
 * Date: 2021-05-11
 * Description:
 */
@Data
public class SysAuthRequest extends SysAuth {

    private List<String> authSources;

    private List<String> authTargets;

    private SysAuthDetail authDetail;


}
