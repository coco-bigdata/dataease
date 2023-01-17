package io.tortoise.dto.panel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class PanelStoreDto {

    @ApiModelProperty("收藏ID")
    private Long storeId;
    @ApiModelProperty("仪表板名称")
    private String name;
    @ApiModelProperty("仪表板Id")
    private String panelGroupId;

}
