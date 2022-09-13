package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum NtFileTypeEnum {

    NT_FILE_TYPE_IMG(1,"image"),
    NT_FILE_TYPE_VOICE(2,"voice"),
    NT_FILE_TYPE_VIDEO(3,"video");

    private Integer code;
    private String msg;

    NtFileTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(NtFileTypeEnum e: NtFileTypeEnum.values()){
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
        for (NtFileTypeEnum e : NtFileTypeEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }

}
