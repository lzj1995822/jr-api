package com.cloudkeeper.leasing.base.utils;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class BigDecimalUtil {

    /** 2位小数*/
    public static final int BIG_DECIMAL_CALC_TWO = 2;

    /**
     * 获取BigDecimal 给null赋0
     * @param source 源数据
     * @return 结果数据
     */
    @Nonnull
    public static BigDecimal getNotNull(BigDecimal source) {
        return getNotNull(source, null);
    }

    /**
     * 获取BigDecimal 给null赋0
     * @param source 源数据
     * @param defaultVal 默认值
     * @return 结果数据
     */
    @Nonnull
    public static BigDecimal getNotNull(BigDecimal source, BigDecimal defaultVal) {
        defaultVal = defaultVal == null ? BigDecimal.ZERO : defaultVal;
        return source == null ? defaultVal : source;
    }

    /**
     * 取最小值
     * @param first 第一个数据
     * @param second 第二个数据
     * @return 最小值
     */
    public static BigDecimal getMin(BigDecimal first, BigDecimal second) {
        first = getNotNull(first);
        second = getNotNull(second);
        return first.compareTo(second) > 0 ? second : first;
    }

    /**
     * 格式化百分比(默认取两位小数，例：0.1234->'12.34%')
     * @param bigDecimal 百分比
     * @return 格式化后字符串
     */
    @Nonnull
    public static String percentFormat(@Nonnull BigDecimal bigDecimal) {
        return percentFormat(bigDecimal, BIG_DECIMAL_CALC_TWO);
    }

    /**
     * 格式化百分比
     * @param bigDecimal 百分比
     * @param calc 小数位数 例:两位小数 0.123456->'12.34%'
     * @return 格式化后字符串
     */
    @Nonnull
    public static String percentFormat(@Nonnull BigDecimal bigDecimal, int calc) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(calc);
        return percent.format(bigDecimal);
    }
}
