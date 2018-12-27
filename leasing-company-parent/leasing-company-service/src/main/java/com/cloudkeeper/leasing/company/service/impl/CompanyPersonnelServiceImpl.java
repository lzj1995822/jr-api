package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.CompanyPersonnel;
import com.cloudkeeper.leasing.company.dto.companypersonnel.CompanyPersonnelDTO;
import com.cloudkeeper.leasing.company.repository.CompanyPersonnelRepository;
import com.cloudkeeper.leasing.company.service.CompanyPersonnelService;
import com.cloudkeeper.leasing.company.vo.CompanyPersonnelVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司相关人员 service
 *
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyPersonnelServiceImpl extends BaseServiceImpl<CompanyPersonnel> implements CompanyPersonnelService {

    /**
     * 公司相关人员 repository
     */
    private final CompanyPersonnelRepository companyPersonnelRepository;

    @Override
    protected BaseRepository<CompanyPersonnel> getBaseRepository() {
        return companyPersonnelRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("identity", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("officeNumber", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("mobilePhone", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("fax", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("post", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("position", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("postAddress", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("shareholderType", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("capitalContribution", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("contributionMethod", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("note", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public List<CompanyPersonnel> saveList(List<CompanyPersonnelDTO> contactList, BaseEntity baseEntity, Boolean isAdd) {
        List<CompanyPersonnel> companyPersonnelList = new ArrayList<>();
        contactList.forEach(companyPersonnelDTO -> {
            CompanyPersonnel companyPersonnel = companyPersonnelDTO.convert(CompanyPersonnel.class);
            companyPersonnel.setCompanyId(baseEntity.getId());
            if (StringUtils.isEmpty(companyPersonnel.getId()) && !isAdd) {
                companyPersonnel.setVersion(baseEntity.getVersion() + 1);
            }
            companyPersonnel = save(companyPersonnel);
            companyPersonnelList.add(companyPersonnel);
        });
        return companyPersonnelList;
    }

    @Override
    public List<CompanyPersonnel> findAllByAndCompanyIdAndType(String companyId, String type) {
        return companyPersonnelRepository.findAllByAndCompanyIdAndType(companyId, type);
    }

    @Override
    public List<CompanyPersonnel> findAllByAndCompanyId(String id) {
        return companyPersonnelRepository.findAllByAndCompanyId(id);
    }

    @Override
    public CompanyPersonnelVO getHis(String id, Integer version) {
        String sql = "SELECT * FROM l_cp_company_personnel_his where id = '" + id + "' And version = " + version + " ";
        return (CompanyPersonnelVO) transferUnique(CompanyPersonnelVO.class, sql);
    }


}