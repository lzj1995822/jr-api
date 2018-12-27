package com.cloudkeeper.leasing.bean.base.to;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * base TO
 * 各业务系统之间数据传输使用
 * @author jerry
 */
@Getter
@Setter
public abstract class BaseTO implements Serializable {

    /** id */
    private String id;
}
