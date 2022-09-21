package com.fijo.ebox.enums.SysEnum;

import lombok.Getter;

@Getter
public enum ResultEnum {
    RESULT_CODE_SUCCESS(1,"success"),
    RESULT_CODE_ERROR(0,"error"),
    RESULT_CODE_ERROR_DUP_KEY_EXCEPTION(2,"duplicateKeyException");
    ;

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
