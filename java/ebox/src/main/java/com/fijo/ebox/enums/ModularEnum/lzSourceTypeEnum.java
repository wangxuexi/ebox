package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum lzSourceTypeEnum {
    LZ_SOURCE_TYPE_SGLL(1,"手工录入"),
    LZ_SOURCE_TYPE_SMSC(2,"扫码生成");

    private Integer code;
    private String msg;

    lzSourceTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(lzSourceTypeEnum e: lzSourceTypeEnum.values()){
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
    public static String getEnumMsgByCode(int code) {
        for (lzSourceTypeEnum e : lzSourceTypeEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
