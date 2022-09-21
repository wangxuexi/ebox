package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum TimeSlotEnum {
    TIME_SLOT_SW(1,"全天"),
    TIME_SLOT_XW(2,"上午"),
    TIME_SLOT_QT(3,"下午");

    private Integer code;
    private String msg;

    TimeSlotEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(TimeSlotEnum e: TimeSlotEnum.values()){
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
        for (TimeSlotEnum e : TimeSlotEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
