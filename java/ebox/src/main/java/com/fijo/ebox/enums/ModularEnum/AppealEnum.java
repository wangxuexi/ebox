package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum AppealEnum {
    DCL(1,"待处理"),
    YCL(2,"已处理");

    private Integer code;
    private String msg;

    AppealEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(AppealEnum e: AppealEnum.values()){
            if(e.getMsg().equals(msg)){
                return e.getCode();
            }
        }
        return null;
    }
}
