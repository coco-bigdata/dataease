package io.tortoise.controller.request.panel;

import io.tortoise.dto.panel.PanelGroupDTO;
import lombok.Data;

/**
 * Author: wangjiahao
 * Date: 2021-03-05
 * Description:
 */
@Data
public class PanelGroupRequest extends PanelGroupDTO {
    private String sort;

    private String userId;

    private String optType;

    public PanelGroupRequest() {
    }

    public PanelGroupRequest(String pid,String userId) {
        super.setPid(pid);
        this.userId= userId;
    }
}
