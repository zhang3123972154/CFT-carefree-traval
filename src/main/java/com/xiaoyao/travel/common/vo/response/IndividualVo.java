package com.xiaoyao.travel.common.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("返回个性标签")
public class IndividualVo extends BasePage{

    @ApiModelProperty("个性标签数据")
    private List<IntegerAndStringVo> data;

}
