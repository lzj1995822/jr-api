package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.identity.dto.record.RecordDTO;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;
import com.cloudkeeper.leasing.identity.vo.CountRateVO;
import com.cloudkeeper.leasing.identity.vo.CountRecordVO;
import com.cloudkeeper.leasing.identity.vo.CountTimeVO;
import com.cloudkeeper.leasing.identity.vo.RecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 活动记录 controller
 * @author wj
 */
@Api(value = "活动记录", tags = "活动记录")
@RequestMapping("/record")
public interface RecordController extends BaseController<RecordDTO, RecordSearchable, RecordVO> {

    @ApiModelProperty(value = "按照中心具体活动统计", position = 10, required = true)
    @GetMapping("/countRecordByorgCenter")
    List<CountRecordVO> countRecordVOList(String codeNumber,String activityid);
    
    @ApiModelProperty(value = "按照中心具体活动统计积分", position = 11, required = true)
    @GetMapping("/countRecordScoreByorgCenter")
    List<CountRecordVO> countRecordScoreVOList(String codeNumber,String activityid);
    
    @ApiModelProperty(value = "按照所具体活动统计", position = 12, required = true)
    @GetMapping("/countRecordBytown")
    List<CountRecordVO> countRecordBytown(String townid,String activityid);
    
    @ApiModelProperty(value = "按照所具体活动统计积分", position = 13, required = true)
    @GetMapping("/countRecordScoreBytown")
    List<CountRecordVO> countRecordScoreBytown(String townid,String activityid);
    
    @ApiModelProperty(value = "按照年、中心具体活动统计", position = 14, required = true)
    @GetMapping("/countRecordByYearorgCenter")
     List<CountTimeVO> countRecordByYearorgCenter();
    
    @ApiModelProperty(value = "按照年、中心具体活动统计积分", position = 15, required = true)
    @GetMapping("/countRecordScoreByYearorgCenter")
     List<CountTimeVO> countRecordScoreByYearorgCenter();

    @ApiModelProperty(value = "按照月、中心具体活动统计", position = 16, required = true)
    @GetMapping("/countRecordByMonthorgCenter")
    List<CountTimeVO> countRecordByMonthorgCenter(String Year);
    
    @ApiModelProperty(value = "按照月、中心具体活动统计积分", position = 17, required = true)
    @GetMapping("/countRecordScoreByMonthorgCenter")
    List<CountTimeVO> countRecordScoreByMonthorgCenter(String Year);

     @ApiModelProperty(value = "按照年、所具体活动统计", position = 18, required = true)
     @GetMapping("/countRecordByYearTown")
     List<CountTimeVO> countRecordByYearTown();
     
     @ApiModelProperty(value = "按照年、所具体活动统计积分", position = 19, required = true)
     @GetMapping("/countRecordScoreByYearTown")
     List<CountTimeVO> countRecordScoreByYearTown();


    @ApiModelProperty(value = "按照月、所具体活动统计", position = 20, required = true)
    @GetMapping("/countRecordByMonthTown")
    List<CountTimeVO> countRecordByMonthTown(String Year);
    
    @ApiModelProperty(value = "按照月、所具体活动统计积分", position = 21, required = true)
    @GetMapping("/countRecordScoreByMonthTown")
    List<CountTimeVO> countRecordScoreByMonthTown(String Year);

    @ApiModelProperty(value = "按照月、站具体活动统计", position = 22, required = true)
    @GetMapping("/countRecordByMonthCountry")
    List<CountTimeVO> countRecordByMonthCountry(String Year);
    
    @ApiModelProperty(value = "按照月、站具体活动统计积分", position = 23, required = true)
    @GetMapping("/countRecordScoreByMonthCountry")
    List<CountTimeVO> countRecordScoreByMonthCountry(String Year);

    @ApiModelProperty(value = "按照年、站具体活动统计", position = 24, required = true)
    @GetMapping("/countRecordByYearCountry")
    List<CountTimeVO> countRecordByYearCountry();
    
    @ApiModelProperty(value = "按照年、站具体活动统计积分", position = 25, required = true)
    @GetMapping("/countRecordScoreByYearCountry")
    List<CountTimeVO> countRecordScoreByYearCountry();


    @ApiModelProperty(value = "统计不同类型的活动占比", position = 26, required = true)
    @GetMapping("/CountRateVO")
    List<CountRateVO> CountRateVO();
    
    @ApiModelProperty(value = "统计不同类型的活动占比积分", position = 27, required = true)
    @GetMapping("/CountScoreRateVO")
    List<CountRateVO> CountScoreRateVO();


    @ApiModelProperty(value = "批量插入执行活动", position = 28, required = true)
    @PostMapping("/addAllReord")
    List<Record> addAllReord(String countryId, String [] artivityId);

}