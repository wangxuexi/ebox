package com.fijo.ebox.base.pojo;

import lombok.Data;

@Data
public class ComparePojo<T> {
    private String fieldType;//字段类型
    private String fieldName;//字段名称
    private String filedNameCN;//字段中文名
    private T oldValue;//旧值
    private T newValue;//新值
}
