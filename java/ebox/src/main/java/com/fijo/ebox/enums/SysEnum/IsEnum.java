package com.fijo.ebox.enums.SysEnum;

import lombok.Getter;

@Getter
public enum IsEnum {
    IS(1, "是"),
    NO(0, "否");

    private int code;
    private String msg;

    IsEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     *
     * @param msg 枚举中文
     * @return code 枚举code
     */
    public static Integer getEnumCodeByMsg(String msg) {
        for (IsEnum isEnum : IsEnum.values()) {
            if (isEnum.getMsg().equals(msg)) {
                return isEnum.getCode();
            }
        }
        return null;
    }
}
