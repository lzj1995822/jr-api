package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.domain.CompanyPersonnel;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.dto.companypersonnel.CompanyPersonnelDTO;
import com.cloudkeeper.leasing.company.vo.CompanyPersonnelVO;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 公司相关人员 service
 * @author asher
 */
public interface CompanyPersonnelService extends BaseService<CompanyPersonnel> {

    /**
     * 批量保存并返回List
     * @param contactList 联系人集合
     * @param baseEntity
     * @return
     */
    List<CompanyPersonnel> saveList(@Nonnull List<CompanyPersonnelDTO> contactList, BaseEntity baseEntity, Boolean isAdd);

    /**
     * 根据公司id及人员类型查询相关类型人员
     * @param companyId 公司id
     * @param type 人员类型
     * @return
     */
    List<CompanyPersonnel> findAllByAndCompanyIdAndType(String companyId, String type);

    /**
     * 根据公司id查询相关公司人员
     * @param id 公司id
     * @return
     */
    List<CompanyPersonnel> findAllByAndCompanyId(String id);

    /**
     * 根据主表id和版本号找到对应的历史记录id
     * @param id 主表id
     * @param version 主表version
     * @return 历史记录
     */
    CompanyPersonnelVO getHis(String id,Integer version);

}