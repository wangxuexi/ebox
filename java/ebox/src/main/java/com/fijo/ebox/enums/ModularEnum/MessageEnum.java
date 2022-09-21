package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum MessageEnum {
    YHL(0,"已忽略"),
    DY(1,"待阅"),
    YY(2,"已阅");

    private Integer code;
    private String msg;

    MessageEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(MessageEnum e: MessageEnum.values()){
            if(e.getMsg().equals(msg)){
                return e.getCode();
            }
        }
        return null;
    }

}
