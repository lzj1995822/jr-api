package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.CommonCompany;
import com.cloudkeeper.leasing.company.repository.CommonCompanyRepository;
import com.cloudkeeper.leasing.company.service.CommonCompanyService;
import com.cloudkeeper.leasing.company.vo.CommonCompanyVO;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 客户、担保公司、供应商父表 service
 * @author asher
 */
@Service
public class CommonCompanyServiceImpl extends BaseServiceImpl<CommonCompany> implements CommonCompanyService {

    /** 客户、担保公司、供应商父表 repository */
    @Autowired
    private CommonCompanyRepository commonCompanyRepository;

    @Override
    protected BaseRepository<CommonCompany> getBaseRepository() {
        return commonCompanyRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public void deleteById(@Nonnull String id) {
        super.deleteHis(id);
        super.deleteById(id);
    }

    @Override
    public CommonCompanyVO getHis(String id) {
        String sql = "SELECT * FROM l_cp_common_company_history where pkId = '"+ id +"' ";
        return (CommonCompanyVO)transferUnique(CommonCompanyVO.class, sql);
    }

    @Override
    public CommonCompanyVO getOne(String commonCompanyId, Integer version) {
        String sql = "SELECT * FROM l_cp_common_company_his where id = '"+ commonCompanyId +"' And version = "+ version +" ";
        return (CommonCompanyVO)transferUnique(CommonCompanyVO.class, sql);
    }

    @Override
    public <D> D getChildren(@Nonnull Class<D> clazz, @Nonnull String childHisId, @Nonnull String tableName) {
        String sql = String.format("SELECT ch.*, cch.* FROM %s_his ch LEFT JOIN l_cp_common_company_his cch ON " +
                "ch.parentId = cch.id AND ch.version = cch.version WHERE ch.pkId = '%s'", tableName, childHisId);
        return (D) transferUnique(clazz, sql);
    }

    @Override
    public <D> D getChildrenByBusinessId(@Nonnull Class<D> clazz, @Nonnull String businessId, @Nonnull String tableName) {
        String sql = String.format("SELECT ch.*, cch.* FROM %s c LEFT JOIN %s_his ch ON c.id = ch.id AND c.version = ch.version " +
                "LEFT JOIN l_cp_common_company_his cch ON c.parentId = cch.id AND c.version = cch.version WHERE c.id = '%s'",
                tableName, tableName, businessId);
        return (D) transferUnique(clazz, sql);
    }

    @Override
    public <D> D getLatestHisByHisId(@Nonnull Class<D> clazz, @Nonnull String hisId, @Nonnull String tableName) {
        String sql = String.format("SELECT h.*, ch.* FROM %s_his h LEFT JOIN %s e ON e.id = h.id AND e.version = h.version " +
                        "LEFT JOIN  l_cp_common_company_his ch ON ch.id = e.parentId AND ch.version = e.version" +
                        " WHERE e.id = (SELECT id FROM %s_his WHERE pkId = '%s')",
                tableName, tableName, tableName, hisId);
        return (D) transferUnique(clazz, sql);
    }

    @Override
    public Map<String, String> getHisIdAndNameMap(@Nonnull String tableName) {
        String sql = String.format("SELECT ch.pkId, cp.name FROM %s c LEFT JOIN l_cp_common_company cp ON c.parentId = cp.id " +
                "LEFT JOIN %s_his ch ON c.id = ch.id AND c.version = ch.version", tableName, tableName);
        return getValueMap(sql);
    }

    @Override
    public <D> List<D> getLatestHisListByHisId(@Nonnull Class<D> clazz, @Nonnull List<String> hisIdList, @Nonnull String tableName) {
        String sql = String.format("SELECT h.*, ch.* FROM %s_his h LEFT JOIN %s e ON e.id = h.id AND e.version = h.version " +
                "LEFT JOIN  l_cp_common_company_his ch ON ch.id = e.parentId AND ch.version = e.version" +
                " WHERE e.id in (SELECT id FROM %s_his WHERE pkId in (:hisIdList))", tableName, tableName, tableName);
        NativeQuery nativeQuery = getQuery(clazz, sql);
        nativeQuery.setParameter("hisIdList", hisIdList);
        List<D> list = nativeQuery.getResultList();
        return list;
    }

    @Override
    public <D> List<D> getHisListByHisId(@Nonnull Class<D> clazz, @Nonnull List<String> hisIdList, @Nonnull String tableName) {
        String sql = String.format("SELECT h.*, ch.* FROM %s_his h LEFT JOIN %s e ON e.id = h.id AND e.version = h.version " +
                "LEFT JOIN  l_cp_common_company_his ch ON ch.id = e.parentId AND ch.version = e.version" +
                " WHERE h.pkId in (:hisIdList)", tableName, tableName);
        NativeQuery nativeQuery = getQuery(clazz, sql);
        nativeQuery.setParameter("hisIdList", hisIdList);
        List<D> list = nativeQuery.getResultList();
        return list;
    }

}