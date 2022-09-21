package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum ReissueModeEnum {
    REISSUE_MODE_TWTZ(1,"图文通知"),
    REISSUE_MODE_MBTZ(2,"模板通知"),
    REISSUE_MODE_DXTZ(3,"短信通知");

    private Integer code;
    private String msg;

    ReissueModeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(ReissueModeEnum e: ReissueModeEnum.values()){
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
        for (ReissueModeEnum e : ReissueModeEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
