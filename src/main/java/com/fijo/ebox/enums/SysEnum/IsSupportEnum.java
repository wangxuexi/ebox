package com.fijo.ebox.enums.SysEnum;

import lombok.Getter;
//是否扶持
@Getter
public enum IsSupportEnum {
    RESULT_YEAS(1,"是"),
    RESULT_NO(0,"否")
    ;

    private int code;
    private String msg;

    IsSupportEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
