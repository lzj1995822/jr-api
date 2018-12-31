package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.vo.CountRateVO;
import com.cloudkeeper.leasing.identity.vo.CountRecordVO;
import com.cloudkeeper.leasing.identity.vo.CountTimeVO;

import java.util.List;

/**
 * 活动记录 service
 * @author wj
 */
public interface RecordService extends BaseService<Record> {
    List<CountRecordVO> countRecordVOList(String codeNumber,String activityid);
    List<CountRecordVO> countRecordBytown(String townid,String activityid);
   List<CountTimeVO> countRecordByYearorgCenter();
    List<CountTimeVO> countRecordByMonthorgCenter(String Year);
    List<CountTimeVO> countRecordByYearTown();
    List<CountTimeVO> countRecordByMonthTown(String Year);
    List<CountTimeVO> countRecordByMonthCountry(String Year);
    List<CountTimeVO> countRecordByYearCountry();
    List<CountRateVO> CountRateVO();
}