package io.tortoise.dto.panel;

import io.tortoise.base.domain.PanelTemplateWithBLOBs;
import lombok.Data;

import java.util.List;

/**
 * Author: wangjiahao
 * Date: 2021-03-05
 * Description:
 */
@Data
public class PanelTemplateDTO extends PanelTemplateWithBLOBs {

    private String label;

    private Integer childrenCount;

    private List<PanelTemplateDTO> children;


}
