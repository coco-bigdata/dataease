package io.tortoise.controller.request.panel;

import io.tortoise.base.domain.PanelTemplateWithBLOBs;
import lombok.Data;

/**
 * Author: wangjiahao
 * Date: 2021-03-05
 * Description:
 */
@Data
public class PanelTemplateRequest extends PanelTemplateWithBLOBs {
    private String sort;

    private String withBlobs="Y";

    private String optType;

    private Boolean withChildren = false;

    public PanelTemplateRequest() {
    }

    public PanelTemplateRequest(String pid) {
        super.setPid(pid);
        withBlobs="N";
    }
}
