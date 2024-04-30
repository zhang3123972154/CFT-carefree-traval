package com.xiaoyao.travel.common.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("分页查询返回类")
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> implements Serializable {
    private static final long serialVersionUID = 6120919963247713959L;
    @ApiModelProperty("页码")
    private int pageNum;

    @ApiModelProperty("每页数量")
    private int pageSize;

    @ApiModelProperty("总数")
    private long total;

    @ApiModelProperty("查询内容")
    private List<T> records;

    public PageDTO(long total, List<T> records) {
        this.total = total;
        this.records = records;
    }
}
