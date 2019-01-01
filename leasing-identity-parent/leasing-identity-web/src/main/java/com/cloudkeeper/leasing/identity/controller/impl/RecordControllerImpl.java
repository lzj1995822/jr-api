package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.RecordController;
import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.identity.dto.record.RecordDTO;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;
import com.cloudkeeper.leasing.identity.service.RecordService;
import com.cloudkeeper.leasing.identity.vo.CountRateVO;
import com.cloudkeeper.leasing.identity.vo.CountRecordVO;
import com.cloudkeeper.leasing.identity.vo.CountTimeVO;
import com.cloudkeeper.leasing.identity.vo.RecordVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 活动记录 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordControllerImpl extends BaseControllerImpl<Record, RecordDTO, RecordSearchable, RecordVO> implements RecordController {

    /** 活动记录 service */
    private final RecordService recordService;

    @Override
    protected BaseService<Record> getBaseService() {
    return recordService;
    }

    public List<CountRecordVO> countRecordVOList(String codeNumber,String activityid){
        return recordService.countRecordVOList(codeNumber, activityid);
    }
    
    public List<CountRecordVO> countRecordScoreVOList(String codeNumber,String activityid){
        return recordService.countRecordScoreVOList(codeNumber, activityid);
    }
    
    public List<CountRecordVO> countRecordBytown(String townid,String activityid){
        return recordService.countRecordBytown(townid, activityid);
    }
    
    public List<CountRecordVO> countRecordScoreBytown(String townid,String activityid){
    	return recordService.countRecordScoreBytown(townid, activityid);
    }
    
    public List<CountTimeVO> countRecordByYearorgCenter(){
        return recordService.countRecordByYearorgCenter();
    }
    
    public List<CountTimeVO> countRecordScoreByYearorgCenter(){
        return recordService.countRecordScoreByYearorgCenter();
    }
    
    public List<CountTimeVO> countRecordByMonthorgCenter(String Year){
        return recordService.countRecordByMonthorgCenter(Year);
    }
    
    public List<CountTimeVO> countRecordScoreByMonthorgCenter(String Year){
        return recordService.countRecordScoreByMonthorgCenter(Year);
    }
    
    public List<CountTimeVO> countRecordByYearTown(){
        return recordService.countRecordByYearTown();
    }
    
    public List<CountTimeVO> countRecordScoreByYearTown(){
        return recordService.countRecordScoreByYearTown();
    }
    
    public List<CountTimeVO> countRecordByMonthTown(String Year){
        return recordService.countRecordByMonthTown(Year);
    }
    
    public List<CountTimeVO> countRecordScoreByMonthTown(String Year){
        return recordService.countRecordScoreByMonthTown(Year);
    }
    
    public List<CountTimeVO> countRecordByMonthCountry(String Year){
        return recordService.countRecordByMonthCountry(Year);
    }
    
    public List<CountTimeVO> countRecordScoreByMonthCountry(String Year){
        return recordService.countRecordScoreByMonthCountry(Year);
    }
    
    public List<CountTimeVO> countRecordByYearCountry(){
        return recordService.countRecordByYearCountry();
    }
    
    public List<CountTimeVO> countRecordScoreByYearCountry(){
        return recordService.countRecordScoreByYearCountry();
    }
    
    public  List<CountRateVO> CountRateVO(){
        return recordService.CountRateVO();
    }
    
    public  List<CountRateVO> CountScoreRateVO(){
        return recordService.CountScoreRateVO();
    }
    public List<Record> addAllReord(String countryId,String [] artivityId){
        return recordService.addAllReord(countryId, artivityId);
    }
}