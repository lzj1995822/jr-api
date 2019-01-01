package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.PointController;
import com.cloudkeeper.leasing.identity.domain.Point;
import com.cloudkeeper.leasing.identity.dto.point.PointDTO;
import com.cloudkeeper.leasing.identity.dto.point.PointSearchable;
import com.cloudkeeper.leasing.identity.service.PointService;
import com.cloudkeeper.leasing.identity.vo.PointVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实践点 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PointControllerImpl extends BaseControllerImpl<Point, PointDTO, PointSearchable, PointVO> implements PointController {

    /** 实践点 service */
    private final PointService pointService;

    @Override
    protected BaseService<Point> getBaseService() {
    return pointService;
    }

    @Override
    public Result<Page<PointVO>> page(PointSearchable searchable, Pageable pageable) {
        return super.page(searchable, pageable);
    }
}