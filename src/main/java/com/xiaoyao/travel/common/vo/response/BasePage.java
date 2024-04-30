package com.xiaoyao.travel.common.vo.response;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasePage {
    @ApiModelProperty("页码，默认1")
    private Integer pageNum = 1;
    @ApiModelProperty("每页数量，默认20")
    private Integer pageSize = 20;

    public Integer getPageNum() {
        if (null == pageNum) {
            return 1;
//            return PageConst.PAGE_NO;
        }
        return pageNum;
    }

    public void setPageNo(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        if (null == pageSize) {
            return 10;
//            return PageConst.PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
