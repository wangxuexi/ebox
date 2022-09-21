package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum LzSmallTypeEnum {
    LZ_SMBG(301,"书面履职报告"),
    LZ_HYBG(401,"会议履职报告");

    private Integer code;
    private String msg;

    LzSmallTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(LzSmallTypeEnum e: LzSmallTypeEnum.values()){
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
        for (LzSmallTypeEnum e : LzSmallTypeEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
