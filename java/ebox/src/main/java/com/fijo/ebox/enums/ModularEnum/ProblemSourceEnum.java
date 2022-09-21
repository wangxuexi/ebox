package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum ProblemSourceEnum {
    PROBLEM_SOURCE_SPXC(1,"商铺巡查"),
    PROBLEM_SOURCE_SSXC(2,"设施巡查"),
    PROBLEM_SOURCE_ZZTJ(3,"自主提交"),
    PROBLEM_SOURCE_WBWT(4,"外部问题");

    private Integer code;
    private String msg;

    ProblemSourceEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举值获取枚举code
     * @param msg
     * @return
     */
    public static  Integer getEnumCodeByMsg(String msg){
        for(ProblemSourceEnum e: ProblemSourceEnum.values()){
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
        for (ProblemSourceEnum e : ProblemSourceEnum.values()) {
            if (e.getCode()==code) {
                return e.getMsg();
            }
        }
        return null;
    }
}
