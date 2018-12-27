package com.cloudkeeper.leasing.base.constant;

/**
 * 基础常量类
 * @author jerry
 */
public class BaseConstants {

    private BaseConstants() {
    }

    /**
     * 布尔值 枚举类
     * @author jerry
     */
    public enum Boolean {
        /** 否*/
        FALSE,
        /** 是*/
        TRUE
    }

    /**
     * 案件流程状态 枚举类
     * @author lixin.shao
     */
    public enum subjectProcessStatus {
        /** 待送审*/
        UNCOMMIT,
        /** 审核中*/
        AUDITING,
        /** 审核完成*/
        COMPLETED
    }
}
