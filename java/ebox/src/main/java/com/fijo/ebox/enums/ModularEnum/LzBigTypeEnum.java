package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum LzBigTypeEnum {
    LZ_RDHHY(1,"人代会会议"),
    LZ_LXXM(2,"联系选民和人民群众"),
    LZ_SMBG(3,"书面履职报告"),
    LZ_HYBG(4,"会议履职报告"),
    LZ_DBZHD(5,"代表组活动"),
    LZ_LXHY(6,"列席会议"),
    LZ_PX(7,"培训"),
    LZ_QT(8,"其他"),
    LZ_LXTA(9,"领衔提案"),
    LZ_TJSMBG(10,"提交书面报告");

    private Integer code;
    private String msg;

    LzBigTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(LzBigTypeEnum e: LzBigTypeEnum.values()){
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
        for (LzBigTypeEnum e : LzBigTypeEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
