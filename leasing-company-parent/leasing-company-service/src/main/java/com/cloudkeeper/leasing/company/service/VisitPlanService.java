package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.domain.VisitPlan;
import com.cloudkeeper.leasing.company.vo.VisitPlanVO;

import javax.annotation.Nonnull;
import java.util.List;

public interface VisitPlanService extends BaseService<VisitPlan> {

    /**
     * 转换拜访计划VO
     * @param visitPlan 拜访计划
     * @return
     */
    VisitPlanVO convertToVO(VisitPlan visitPlan);

    /**
     * 转换拜访计划VOs
     * @param visitPlans 拜访计划s
     * @return
     */
    List<VisitPlanVO> convertToVOs(@Nonnull List<VisitPlan> visitPlans);
}
