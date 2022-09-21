package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum AjstautsEnum {
    WKT(1,"待处理"),
    YJS(2,"已处理")
    ;

    private Integer code;
    private String msg;

    AjstautsEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(AjstautsEnum e: AjstautsEnum.values()){
            if(e.getMsg().equals(msg)){
                return e.getCode();
            }
        }
        return null;
    }
}
