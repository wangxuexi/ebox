package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum IsDistributeFaEnum {
    YES(true,"是"),
    NO(false,"否");

    private Boolean code;
    private String msg;

    IsDistributeFaEnum(Boolean code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Boolean getEnumCodeByMsg(String msg){
        for(IsDistributeFaEnum e: IsDistributeFaEnum.values()){
            if(e.getMsg().equals(msg)){
                return e.getCode();
            }
        }
        return null;
    }

    /**
     * 根据枚举值获取枚举msg
     * @param code
     * @return
     */
    public static String getEnumMsgByCode(Boolean code) {
        for (IsDistributeFaEnum e : IsDistributeFaEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
