package io.tortoise.dto.panel;

import io.tortoise.base.domain.PanelGroupWithBLOBs;
import io.tortoise.commons.model.ITreeBase;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wangjiahao
 * Date: 2021-03-05
 * Description:
 */
@Data
public class PanelGroupDTO extends PanelGroupWithBLOBs implements ITreeBase<PanelGroupDTO> {

    private String label;

    private Boolean leaf;

    private String privileges;

    private String defaultPanelId;

    private String defaultPanelName;

    private Boolean isDefault;

    private String sourcePanelName;

    private List<PanelGroupDTO> children = new ArrayList<>();


}
