package com.fijo.ebox.enums.ModularEnum;

import lombok.Getter;

@Getter
public enum WechatOfficialAccountIdEnums {

    MHZS_WECHAT_OFFICIAL_ACCOUNT_ID_FZCS("wx62e6f5250ffae700", "dfe9db59c971e0dccc45f4445c90e455",null,null),//斐卓测试号
    MHZS_WECHAT_OFFICIAL_ACCOUNT_ID_MHZS("wx07f5fb67545b9ad4", "b08a773119c5b4699b5f2745a166ae19","WA6V9vF2X9AjXz3ipsOsl12","55Mk3oWA6V9vF2X9yEjnIO2BTAjXz3ipsOsl12o5FAo"),//斐卓企业通
    MHZS_WECHAT_OFFICIAL_ACCOUNT_ID_MQT("wx9e1056cda48ed6b7","bcd2a40f4444f7b7856f0374e5b8d441",null,null),//闵企通
    MHZS_WECHAT_OFFICIAL_ACCOUNT_ID_JSHC("wx77d2f929c06760dd", "22a503a206ff938109289b4f796ddea4",null,null),//聚商华漕,华漕
    FZKJ_WECHAT_OFFICIAL_ACCOUNT_ID("wx14f20a51f1253f07","fc02f9a829306dc565f02522535ef42e",null,null),//斐卓科技公众号

    ;

    private String code;
    private String secret;
    private String token;
    private String aeskey;

    WechatOfficialAccountIdEnums(String code, String secret,String token,String aeskey) {
        this.code = code;
        this.secret = secret;
        this.token = token;
        this.aeskey = aeskey;
    }

    public static WechatOfficialAccountIdEnums getEnum(String code) {
        for (WechatOfficialAccountIdEnums entEnum : WechatOfficialAccountIdEnums.values()) {
            if (entEnum.getCode().equals(code)) {
                return entEnum;
            }
        }
        return null;
    }


}
