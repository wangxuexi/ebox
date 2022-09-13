package com.fijo.ebox.enums.SysEnum;

import lombok.Getter;

@Getter
public enum MsgCategoryEnum {
    NOTE(1,"短信"),
    EMAIL(2,"邮件"),
    WX_NEWS(3,"微信消息")
    ;

    private int code;
    private String msg;

    MsgCategoryEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
