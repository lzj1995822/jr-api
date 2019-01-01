package com.cloudkeeper.leasing.identity.constant;

public class ProcessConstants {

    /** activity流程*/
    public static final String ACTIVITY_COMMITED = "ACTIVITY_COMMITED";	//活动提交
    public static final String ACTIVITY_TOWN_PASSED = "ACTIVITY_TOWN_PASSED";	//活动镇通过
    public static final String ACTIVITY_CITY_PASSED = "ACTIVITY_CITY_PASSED";	//活动市通过
    public static final String ACTIVITY_REJECTED = "ACTIVITY_REJECTED";	//驳回

    /** record流程*/
    public static final String RECORD_UNFINSHED = "RECORD_UNFINSHED"; //记录提交
    public static final String RECORD_COMMITED = "RECORD_COMMITED"; //记录提交
    public static final String RECORD_TOWN_PASSED = "RECORD_TOWN_PASSED";	//记录镇通过
    public static final String RECORD_CITY_PASSED = "RECORD_CITY_PASSED";	//记录市通过

   
    public static final String ORG_TYPE_LLXJ = "ORG_TYPE_LLXJ"; //理论宣讲
    public static final String ORG_TYPE_JYFW = "ORG_TYPE_JYFW";	//教育服务
    public static final String ORG_TYPE_WTFW = "ORG_TYPE_WTFW";	//文体服务
    public static final String ORG_TYPE_KJKP = "ORG_TYPE_KJKP";	//科技科普
    public static final String ORG_TYPE_JKPJ = "ORG_TYPE_JKPJ";	//健康普及

    /** 组织类型*/
    public static final String ORG_CENTER = "ORG_CENTER"; //分中心
    public static final String ORG_ROOM = "ORG_ROOM";	//功能室
    public static final String ORG_COUNTRY = "ORG_COUNTRY";	//村
    public static final String ORG_CITY = "ORG_CITY"; //中心（市级）
    public static final String ORG_TOWN = "ORG_TOWN";	//镇

    /** 活动类型（是自选计划还是分中心发布的计划）*/
    public static final String ACT_TYPE_SELF = "ACT_TYPE_SELF";	//自选计划
    public static final String ACT_TYPE_CENTER = "ACT_TYPE_CENTER";	//分中心计划
}
