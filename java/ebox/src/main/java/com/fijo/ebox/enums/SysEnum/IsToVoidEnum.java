package com.fijo.ebox.enums.SysEnum;

import lombok.Getter;

@Getter
public enum IsToVoidEnum {
    TO_VOID(1, "已作废"),
    UN_TO_VOID(0, "未作废");

    private Integer code;
    private String msg;

    IsToVoidEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
