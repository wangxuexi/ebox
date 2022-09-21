package com.fijo.ebox.enums.SysEnum;

import lombok.Getter;

@Getter
public enum StateEnum {
    ENABLED_TRUE(1,"正常"),
    ENABLED_FALSE(0,"弃用"),
    REMOVED_TRUE(0,"正常"),
    REMOVED_FALSE(1,"弃用")
    ;

    private int code;
    private String msg;

    StateEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
