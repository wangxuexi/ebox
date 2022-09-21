package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum LzStateEnum {
    LZ_DQR(1,"待确认"),
    LZ_YQR(2,"已确认");

    private Integer code;
    private String msg;

    LzStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(LzStateEnum e: LzStateEnum.values()){
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
        for (LzStateEnum e : LzStateEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
