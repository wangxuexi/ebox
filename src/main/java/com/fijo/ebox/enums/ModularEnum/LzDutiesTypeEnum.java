package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum LzDutiesTypeEnum {
    LZ_RDHHY("LZRDHY","人代会会议"),
    LZ_LXXM("LZLXXM","联系选民和人民群众"),
    LZ_SMBG("LZSMBG","书面履职报告"),
    LZ_HYBG("LZHYBG","会议履职报告"),
    LZ_DBZHD("LZDBZHD","代表组活动"),
    LZ_LXHY("LZLXHY","列席会议"),
    LZ_PX("LZPX","培训"),
    LZ_QT("LZQT","其他"),
    LZ_LXTA("LZLXTA","领衔提案"),
    LZ_TJSMBG("LZTJSMBG","提交书面报告");

    private String code;
    private String msg;

    LzDutiesTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  String getEnumCodeByMsg(String msg){
        for(LzDutiesTypeEnum e: LzDutiesTypeEnum.values()){
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
    public static String getEnumMsgByCode(String code) {
        for (LzDutiesTypeEnum e : LzDutiesTypeEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
