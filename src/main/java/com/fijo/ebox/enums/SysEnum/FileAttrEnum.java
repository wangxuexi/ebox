package com.fijo.ebox.enums.SysEnum;

import lombok.Getter;

@Getter
public enum FileAttrEnum {
    ONRMAL("1","正常"),
    DISCARD("2","无效")
    ;

    private String code;
    private String msg;

    FileAttrEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
