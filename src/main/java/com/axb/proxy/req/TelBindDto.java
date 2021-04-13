package com.axb.proxy.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 16:03
 */
@Data
@ApiModel(description = "AXB获取X号码入参")
public class TelBindDto {

    @ApiModelProperty(value = "真实号码")
    private String telA;

    @ApiModelProperty(value = "对端号码")
    private String telB;

}
