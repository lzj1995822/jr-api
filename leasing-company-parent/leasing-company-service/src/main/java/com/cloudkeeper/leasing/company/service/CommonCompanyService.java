package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.CommonCompany;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.vo.CommonCompanyVO;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 客户、担保公司、供应商父表 service
 * @author asher
 */
public interface CommonCompanyService extends BaseService<CommonCompany> {

    /**
     * 根据id获取历史表的一条记录
     * @param id
     * @return
     */
    CommonCompanyVO getHis(String id);

    /**
     * 根据主表id和版本号找到对应的历史记录id
     * @param commonCompanyId 主表id
     * @param version 主表version
     * @return 历史记录
     */
    CommonCompanyVO getOne(String commonCompanyId, Integer version);

    /**
     * 获取子表vo对象
     * @param clazz 子表vo类型
     * @param childHisId 子表记录idl
     * @return
     */
    <D> D getChildren(@Nonnull Class<D> clazz, @Nonnull String childHisId, @Nonnull String tableName);

    /**
     * 根据业务id获取子表vo对象
     * @param clazz 子表vo类型
     * @param businessId 子表业务记录id
     * @return
     */
    <D> D getChildrenByBusinessId(@Nonnull Class<D> clazz, @Nonnull String businessId, @Nonnull String tableName);

    /**
     * 根据历史记录id拿最新的历史记录
     * @param clazz 接受类
     * @param hisId 历史记录id
     * @param tableName 表名
     * @param <D> 返回类
     * @return
     */
    <D> D getLatestHisByHisId(@Nonnull Class<D> clazz, @Nonnull String hisId, @Nonnull String tableName);

    /**
     * 获取最新历史记录id与name的Map集合（下拉框用）
     * @param tableName 子表名
     * @return
     */
    Map<String, String> getHisIdAndNameMap(@Nonnull String tableName);

    /**
     * 获取最新历史记录的id，name集合
     * @param hisIdList
     * @param tableName
     * @return
     */
    <D> List<D> getLatestHisListByHisId(@Nonnull Class<D> clazz, @Nonnull List<String> hisIdList, @Nonnull String tableName);

    /**
     * 获取对应历史记录的id，name集合
     * @param hisIdList
     * @param tableName
     * @return
     */
    <D> List<D> getHisListByHisId(@Nonnull Class<D> clazz, @Nonnull List<String> hisIdList, @Nonnull String tableName);
}