package com.xiaoyao.travel.common.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IntegerAndStringVo {
    private Integer id;
    private String text;
    public IntegerAndStringVo(Integer id,String text) {
        this.id = id;
        this.text = text;
    }
}
