package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;
import com.cloudkeeper.leasing.identity.vo.CountRateVO;
import com.cloudkeeper.leasing.identity.vo.CountRecordVO;
import com.cloudkeeper.leasing.identity.vo.CountTimeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 活动记录 service
 * @author wj
 */
public interface RecordService extends BaseService<Record> {
    List<CountRecordVO> countRecordVOList(String codeNumber,String activityid);
    List<CountRecordVO> countRecordScoreVOList(String codeNumber,String activityid);
    List<CountRecordVO> countRecordBytown(String townid,String activityid);
    List<CountRecordVO> countRecordScoreBytown(String townid,String activityid);
   List<CountTimeVO> countRecordByYearorgCenter();
   List<CountTimeVO> countRecordScoreByYearorgCenter();
    List<CountTimeVO> countRecordByMonthorgCenter(String Year);
    List<CountTimeVO> countRecordScoreByMonthorgCenter(String Year);
    List<CountTimeVO> countRecordByYearTown();
    List<CountTimeVO> countRecordScoreByYearTown();
    List<CountTimeVO> countRecordByMonthTown(String Year);
    List<CountTimeVO> countRecordScoreByMonthTown(String Year);
    List<CountTimeVO> countRecordByMonthCountry(String Year);
    List<CountTimeVO> countRecordScoreByMonthCountry(String Year);
    List<CountTimeVO> countRecordByYearCountry();
    List<CountTimeVO> countRecordScoreByYearCountry();
    List<CountRateVO> CountRateVO();
    List<CountRateVO> CountScoreRateVO();
    List<Record> addAllReord(String countryId,List<String> artivityId);
    Page<Record> getSearchByRole(RecordSearchable searchable, Pageable pageable);
}