package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum LzTypeEntityTypeCode {
    LZ_RDHHY(1,"LZRDHY"),
    LZ_LXXM(2,"LZLXXM"),
    LZ_SMBG(3,"LZSMBG"),
    LZ_HYBG(4,"LZHYBG"),
    LZ_DBZHD(5,"LZDBZHD"),
    LZ_LXHY(6,"LZLXHY"),
    LZ_PX(7,"LZPX"),
    LZ_QT(8,"LZQT"),
    LZ_LXTA(9,"LZLXTA"),
    LZ_TJSMBG(10,"LZTJSMBG");

    private Integer code;
    private String msg;

    LzTypeEntityTypeCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(LzTypeEntityTypeCode e: LzTypeEntityTypeCode.values()){
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
        for (LzTypeEntityTypeCode e : LzTypeEntityTypeCode.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
