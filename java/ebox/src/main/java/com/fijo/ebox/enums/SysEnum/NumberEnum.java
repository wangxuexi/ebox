package com.fijo.ebox.enums.SysEnum;

import lombok.Getter;

@Getter
public enum NumberEnum {
    ZERO(0),
    ONE(1)
    ;

    private int code;

    NumberEnum(int code) {
        this.code = code;
    }
}
