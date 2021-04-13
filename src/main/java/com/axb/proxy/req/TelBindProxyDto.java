package com.axb.proxy.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 15:54
 */
@Data
@ApiModel(description = "AXB绑定入参")
public class TelBindProxyDto {

    @ApiModelProperty(value = "业务id")
    private String requestId;

    @ApiModelProperty(value = "真实号码")
    private String telA;

    @ApiModelProperty(value = "对端号码")
    private String telB;

    @ApiModelProperty(value = "主叫侧放音编码")
    private String anuCode;

    @ApiModelProperty(value = "区号")
    private String areaCode;

    @ApiModelProperty(value = "过期时间")
    private String expiration;

    @ApiModelProperty(value = "接入商自有字段")
    private String remake;

    @ApiModelProperty(value = "绑定时间")
    private String subTs;


}
