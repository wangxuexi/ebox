package com.fijo.ebox.base.util.common;

import com.mysql.jdbc.StringUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.ArrayUtils;

public class StringUtil {
    public static String subRangeString(String body, String str1, String str2) {
        while (true) {
            int index1 = body.indexOf(str1);
            if (index1 != -1) {
                int index2 = body.indexOf(str2, index1);
                if (index2 != -1) {
                    String str3 = body.substring(0, index1) + body.substring(index2 + str2.length(), body.length());
                    body = str3;
                } else {
                    return body;
                }
            } else {
                return body;
            }
        }
    }

    /**
     * 传入组织字符串，处理成可以在mybatis中使用的字符串格式
     *
     * @param orgCodes 组织编码
     * @return
     */
    public static String orgCodesToStr(String orgCodes) {
        StringBuffer sb = new StringBuffer();
        String[] orgCodeArray = (orgCodes.split(","));
        for (int i = 0; i < orgCodeArray.length; i++) {
            sb.append("\'" + orgCodeArray[i] + "\'" + ",");
        }
        String orgCode = sb.toString();
        orgCode = orgCode.substring(0, orgCode.length() - 1);
        return orgCode;
    }



    public static boolean isNull(String param) {
        if (param == null || param.isEmpty() || param.trim().equals("")) return true;
        return false;
    }



    /**
     * 汉字转为拼音
     *
     * @param chinese
     * @return
     */
    public static String toPinyin(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr.toUpperCase();
    }


    /**
     * 逗号分隔类型的字符串转sql可执行字符串
     *
     * @param str
     * @return
     */
    public static String str2SqlStr(String str) {
        if(StringUtils.isNullOrEmpty(str)) return "('')";
        String resultStr = "(";
        String [] strs = str.split(",");
        for(String s : strs){
            resultStr += "'"+s+"',";
        }
        resultStr = resultStr.substring(0,resultStr.length()-1);
        resultStr += ")";
        return resultStr;
    }

    /**
     * 英文括号转中文括号
     *
     * @param str 字符串
     */
    public static String enBracketToCnBracket(String str) {
        if (str.contains("(") || str.contains(")")) {
            str.replace("(", "（").replace(")", "）");
            str = str.replace("(", "（").replace(")", "）");
            return str;
        }
        return str;
    }

    /**
     * 过滤非数字和小数点
     *
     * @param str
     * @return
     */
    public static String getNumericPoints(String str) {

        str = str.trim();
        String str2 = "";

        if (str != null && !"".equals(str)) {

            for (int i = 0; i < str.length(); i++) {

                if ((str.charAt(i) >= 48 && str.charAt(i) <= 57) || str.charAt(i) == 46) {

                    str2 += str.charAt(i);

                }

            }
        }

        return str2;
    }

    public static int getNumberFirst(String str) {

        str = str.trim();

        int j = 0;

        if (str != null && !"".equals(str)) {

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    j = i;
                    return j;
                }
            }
        }
        return j;
    }

    public static boolean isAnyBlank(final CharSequence... css) {
        if (ArrayUtils.isEmpty(css)) {
            return false;
        }
        for (final CharSequence cs : css){
            if (isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}
