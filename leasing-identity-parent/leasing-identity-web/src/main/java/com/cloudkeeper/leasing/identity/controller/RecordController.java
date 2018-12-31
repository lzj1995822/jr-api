package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.record.RecordDTO;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;
import com.cloudkeeper.leasing.identity.vo.CountRateVO;
import com.cloudkeeper.leasing.identity.vo.CountRecordVO;
import com.cloudkeeper.leasing.identity.vo.CountTimeVO;
import com.cloudkeeper.leasing.identity.vo.RecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.GetMapping;
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
    @ApiModelProperty(value = "按照所具体活动统计", position = 12, required = true)
    @GetMapping("/countRecordBytown")
    List<CountRecordVO> countRecordBytown(String townid,String activityid);
    @ApiModelProperty(value = "按照年、中心具体活动统计", position = 14, required = true)
    @GetMapping("/countRecordByYearorgCenter")
     List<CountTimeVO> countRecordByYearorgCenter();

    @ApiModelProperty(value = "按照月、中心具体活动统计", position = 16, required = true)
    @GetMapping("/countRecordByMonthorgCenter")
    List<CountTimeVO> countRecordByMonthorgCenter(String Year);

     @ApiModelProperty(value = "按照年、所具体活动统计", position = 18, required = true)
     @GetMapping("/countRecordByYearTown")
     List<CountTimeVO> countRecordByYearTown();


    @ApiModelProperty(value = "按照月、所具体活动统计", position = 20, required = true)
    @GetMapping("/countRecordByMonthTown")
    List<CountTimeVO> countRecordByMonthTown(String Year);



    @ApiModelProperty(value = "按照月、站具体活动统计", position = 22, required = true)
    @GetMapping("/countRecordByMonthCountry")
    List<CountTimeVO> countRecordByMonthCountry(String Year);

    @ApiModelProperty(value = "按照年、站具体活动统计", position = 24, required = true)
    @GetMapping("/countRecordByYearCountry")
    List<CountTimeVO> countRecordByYearCountry();


    @ApiModelProperty(value = "统计不同类型的活动占比", position = 26, required = true)
    @GetMapping("/CountRateVO")
    List<CountRateVO> CountRateVO();

}