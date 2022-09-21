package com.fijo.ebox.enums.SysEnum;

import lombok.Getter;

@Getter
public enum StatueEnum {
    RESULT_CODE_SUCCESS("1","success"),
    RESULT_CODE_ERROR("0","error"),
    ;

    private String code;
    private String msg;

    StatueEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
