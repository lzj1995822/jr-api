package com.cloudkeeper.leasing.bean.company.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GuarantorAllTO extends GuarantorTO {

    /** 担保资产 */
    private List<SecuredAssetsTO> assetsTOList;
}
