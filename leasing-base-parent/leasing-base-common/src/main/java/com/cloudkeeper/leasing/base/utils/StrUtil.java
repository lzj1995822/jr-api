package com.cloudkeeper.leasing.base.utils;

import org.springframework.util.StringUtils;

/**
 * 字符串工具
 * @author jerry
 */
public class StrUtil {

    private StrUtil() {
    }

    /**
     * 把一个字符串的第一个字母大写
     * @param fieldName 属性名称
     * @return 开头大写的字串
     * @author jerry.li
     */
    public static String upperCase(String fieldName) {
        if (!StringUtils.hasText(fieldName) || !Character.isLowerCase(fieldName.charAt(0))) {
            return fieldName;
        }
        char[] cs = fieldName.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /**
     * 把一个字符串的第一个字母小写
     * @param fieldName 属性名称
     * @return 开头小写的字串
     * @author jerry.li
     */
    public static String lowerCase(String fieldName) {
        if (!StringUtils.hasText(fieldName) || !Character.isUpperCase(fieldName.charAt(0))) {
            return fieldName;
        }
        char[] cs = fieldName.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);
    }
}
