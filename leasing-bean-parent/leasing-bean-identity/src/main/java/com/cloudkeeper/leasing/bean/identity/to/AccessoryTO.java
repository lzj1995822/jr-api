package com.cloudkeeper.leasing.bean.identity.to;

import com.cloudkeeper.leasing.bean.base.to.BaseTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class AccessoryTO extends BaseTO {

    /** 名称 */
    private String name;

    /** 附件所对应主表id */
    private String masterTableId;

    /** 主表对象 */
    private String masterObject;

    /** 类别 */
    private String type;

    /** 描述 */
    private String description;

    /** 存储路径 */
    private String path;

}
