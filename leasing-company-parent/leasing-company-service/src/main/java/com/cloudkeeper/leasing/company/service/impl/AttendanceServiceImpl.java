package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.Attendance;
import com.cloudkeeper.leasing.company.domain.QAttendance;
import com.cloudkeeper.leasing.company.dto.attendance.AttendanceSearchable;
import com.cloudkeeper.leasing.company.repository.AttendanceRepository;
import com.cloudkeeper.leasing.company.service.AttendanceService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;

@Service
public class AttendanceServiceImpl extends BaseServiceImpl<Attendance> implements AttendanceService {


    /** 出勤Repository*/
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Override
    protected BaseRepository<Attendance> getBaseRepository() {
        return attendanceRepository;
    }

    @Override
    public BooleanBuilder defaultBooleanBuilder(@Nonnull BaseSearchable searchable) {
        AttendanceSearchable attendanceSearchable = (AttendanceSearchable) searchable;
        QAttendance qAttendance = QAttendance.attendance;
        BooleanBuilder booleanBuilder =  new BooleanBuilder();
        if (StringUtils.hasText(attendanceSearchable.getDriverName())) {
            booleanBuilder.and(qAttendance.driver.name.contains(attendanceSearchable.getDriverName()));
        }
        if (StringUtils.hasText(attendanceSearchable.getVehicleNumber())) {
            booleanBuilder.and(qAttendance.vehicle.licensePlate.contains(attendanceSearchable.getVehicleNumber()));
        }
        return booleanBuilder;
    }
}
