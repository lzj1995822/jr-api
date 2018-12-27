package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.QVisitPlan;
import com.cloudkeeper.leasing.company.domain.VisitPlan;
import com.cloudkeeper.leasing.company.dto.visitPlan.VisitPlanSearchable;
import com.cloudkeeper.leasing.company.manager.PrincipalManager;
import com.cloudkeeper.leasing.company.repository.VisitPlanRepository;
import com.cloudkeeper.leasing.company.service.CustomerService;
import com.cloudkeeper.leasing.company.service.VisitPlanService;
import com.cloudkeeper.leasing.company.vo.VisitPlanVO;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VisitPlanServiceImpl extends BaseServiceImpl<VisitPlan> implements VisitPlanService {

    /** 拜访计划 Repository*/
    private final VisitPlanRepository visitPlanRepository;

    /** 用户接口*/
    private final PrincipalManager principalManager;

    /** 客户service*/
    private final CustomerService customerService;

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("itemId", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("purpose", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("createdBy", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("startTime", ExampleMatcher.GenericPropertyMatchers.startsWith());
    }

    @Override
    public BooleanBuilder defaultBooleanBuilder(@Nonnull BaseSearchable searchable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QVisitPlan qVisitPlan = QVisitPlan.visitPlan;
        VisitPlanSearchable visitPlanSearchable = (VisitPlanSearchable)searchable;
        if (!StringUtils.isEmpty(visitPlanSearchable.getStartTime()) && !StringUtils.isEmpty(visitPlanSearchable.getEndTime())) {
            booleanBuilder.and(qVisitPlan.startTime.after(visitPlanSearchable.getStartTime())
                    .and(qVisitPlan.endTime.before(visitPlanSearchable.getEndTime())));

        }
        if (StringUtils.hasText(visitPlanSearchable.getItemId())) {
            booleanBuilder.and(qVisitPlan.itemId.eq(visitPlanSearchable.getItemId()));
        }
        if (StringUtils.hasText(visitPlanSearchable.getPurpose())) {
            booleanBuilder.and(qVisitPlan.purpose.contains(visitPlanSearchable.getPurpose()));
        }
        if (StringUtils.hasText(visitPlanSearchable.getCreatorId())) {
            booleanBuilder.and(qVisitPlan.createdBy.eq(visitPlanSearchable.getCreatorId()));
        }
        return booleanBuilder;
    }

    @Override
    protected BaseRepository<VisitPlan> getBaseRepository() {
        return visitPlanRepository;
    }

    @Override
    public VisitPlanVO convertToVO(@Nonnull VisitPlan visitPlan) {
        VisitPlanVO convert = visitPlan.convert(VisitPlanVO.class);
        convert.setSaleName(principalManager.findOne(visitPlan.getCreatedBy()).getName());
        convert.setCustomerName(customerService.getOne(visitPlan.getItemId()).getCommonCompany().getName());
        return convert;
    }

    @Override
    public List<VisitPlanVO> convertToVOs(@Nonnull List<VisitPlan> visitPlans) {
        return visitPlans.stream().map(this::convertToVO).collect(Collectors.toList());
    }
}
