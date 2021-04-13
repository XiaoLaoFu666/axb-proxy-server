package com.axb.proxy.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 16:10
 */
@Data
@ApiModel(description = "AXB返回参数")
public class TelBindRespDto{

    @ApiModelProperty(value = "绑定ID")
    private String subId;

    @ApiModelProperty(value = "X号码")
    private String telX;

}
