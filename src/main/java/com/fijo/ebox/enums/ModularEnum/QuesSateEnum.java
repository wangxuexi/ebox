package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum QuesSateEnum {
    QUES_SATE_DCL(1,"待处理"),
    QUES_SATE_YCL(2,"已处理");

    private Integer code;
    private String msg;

    QuesSateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(QuesSateEnum e: QuesSateEnum.values()){
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
        for (QuesSateEnum e : QuesSateEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
