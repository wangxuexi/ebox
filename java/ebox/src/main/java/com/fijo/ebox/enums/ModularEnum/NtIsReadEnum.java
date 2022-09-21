package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum NtIsReadEnum {
    NT_IS_READ_YD(1,"是"),
    NT_IS_READ_WD(2,"否");

    private Integer code;
    private String msg;

    NtIsReadEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(NtIsReadEnum e: NtIsReadEnum.values()){
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
        for (NtIsReadEnum e : NtIsReadEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
