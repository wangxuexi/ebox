package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum SexEnum {

    MAN(1,"男"),
    WOMEN(2,"女");


    private Integer code;
    private String msg;

    SexEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(SexEnum e: SexEnum.values()){
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
        for (SexEnum e : SexEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }

}
