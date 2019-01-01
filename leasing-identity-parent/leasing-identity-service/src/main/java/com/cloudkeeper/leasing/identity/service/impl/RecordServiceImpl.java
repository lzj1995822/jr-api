package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.constant.ProcessConstants;
import com.cloudkeeper.leasing.identity.domain.*;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;
import com.cloudkeeper.leasing.identity.repository.RecordRepository;
import com.cloudkeeper.leasing.identity.service.*;
import com.cloudkeeper.leasing.identity.vo.CountRateVO;
import com.cloudkeeper.leasing.identity.vo.CountRecordVO;
import com.cloudkeeper.leasing.identity.vo.CountTimeVO;
import com.cloudkeeper.leasing.identity.vo.CountVO;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 活动记录 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordServiceImpl extends BaseServiceImpl<Record> implements RecordService {

    /** 活动记录 repository */
    private final RecordRepository recordRepository;
    
    private final RecordHistoryService recordHistoryService;

    private final PrincipalService principalService;

    private final OrgRoomService orgRoomService;

    private final OrgCenterService orgCenterService;

    private final CountryService countryService;

    @Override
    protected BaseRepository<Record> getBaseRepository() {
        return recordRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Nonnull
	@Override
	public Record save(Record entity) {
		RecordHistory recordHistory = new RecordHistory();
		BeanUtils.copyProperties(entity, recordHistory, "id");
		entity = super.save(entity);
		recordHistory.setRecordId(entity.getId());
		recordHistoryService.save(recordHistory);
		return entity;
	}

	public List<CountRecordVO> countRecordVOList(String codeNumber,String activityid){
        String sql="SELECT\n" +
                "\tjr.`status`,\n" +
                "\tCOUNT(jr.id) count\n" +
                "FROM\n" +
                "\tjr_record jr,\n" +
                "\tjr_org_center joc,\n" +
                "\tjr_activity ja\n" +
                "WHERE\n" +
                "\tja.activityType = joc.type\n" +
                "AND ja.id = jr.activityId\n" +
                "AND joc.codeNumber=" +"'"+codeNumber+"'"+
                " AND ja.id=" +"'"+activityid+"'"+
                " GROUP BY\n" +
                "\tjr.`status`";
        return super.findAllListBySql(CountRecordVO.class,sql);

    }
	
	public List<CountRecordVO> countRecordScoreVOList(String codeNumber,String activityid){
        String sql="SELECT\n" +
                "\tjr.`status`,\n" +
                "\tSUM(jr.score) count\n" +
                "FROM\n" +
                "\tjr_record jr,\n" +
                "\tjr_org_center joc,\n" +
                "\tjr_activity ja\n" +
                "WHERE\n" +
                "\tja.activityType = joc.type\n" +
                "AND ja.id = jr.activityId\n" +
                "AND joc.codeNumber=" +"'"+codeNumber+"'"+
                " AND ja.id=" +"'"+activityid+"'"+
                " GROUP BY\n" +
                "\tjr.`score`";
        return super.findAllListBySql(CountRecordVO.class,sql);

    }

    public List<CountRecordVO> countRecordBytown(String townid,String activityid){
        String sql="SELECT\n" +
                "\tjr.`status`,\n" +
                "\tCOUNT(jr.id) count\n" +
                "FROM\n" +
                "\tjr_record jr,\n" +
                "\tjr_town jt,\n" +
                "\tjr_country jc,\n" +
                "\tjr_activity ja\n" +
                "WHERE\n" +
                "\tjc.id=jr.countryId\n" +
                "AND  jt.id=jc.townid\n" +
                "AND ja.id = jr.activityId\n" +
                "AND jt.id=" +"'"+townid+"'"+
                " AND ja.id=" +"'"+activityid+"'"+
                " GROUP BY\n" +
                "\tjr.`status`";
        return super.findAllListBySql(CountRecordVO.class,sql);

    }
    
    public List<CountRecordVO> countRecordScoreBytown(String townid,String activityid){
        String sql="SELECT\n" +
                "\tjr.`status`,\n" +
                "\tSUM(jr.score) count\n" +
                "FROM\n" +
                "\tjr_record jr,\n" +
                "\tjr_town jt,\n" +
                "\tjr_country jc,\n" +
                "\tjr_activity ja\n" +
                "WHERE\n" +
                "\tjc.id=jr.countryId\n" +
                "AND  jt.id=jc.townid\n" +
                "AND ja.id = jr.activityId\n" +
                "AND jt.id=" +"'"+townid+"'"+
                " AND ja.id=" +"'"+activityid+"'"+
                " GROUP BY\n" +
                "\tjr.`score`";
        return super.findAllListBySql(CountRecordVO.class,sql);

    }

    public List<CountTimeVO> countRecordByYearorgCenter(){
        String sql="SELECT\n" +
                "\tYEAR (jr.createdAt) time,\n" +
                "\tCOUNT(jr.id) count\n" +
                "FROM\n" +
                "\tjr_org_center joc,\n" +
                "\tjr_activity ja,\n" +
                "\tjr_record jr\n" +
                "WHERE\n" +
                "\tjoc.type = ja.activityType\n" +
                "AND ja.id = jr.activityId\n" +
                "GROUP BY\n" +
                "\tYEAR (jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }
    
    public List<CountTimeVO> countRecordScoreByYearorgCenter(){
        String sql="SELECT\n" +
                "\tYEAR (jr.createdAt) time,\n" +
                "\tSUM(jr.score) count\n" +
                "FROM\n" +
                "\tjr_org_center joc,\n" +
                "\tjr_activity ja,\n" +
                "\tjr_record jr\n" +
                "WHERE\n" +
                "\tjoc.type = ja.activityType\n" +
                "AND ja.id = jr.activityId\n" +
                "GROUP BY\n" +
                "\tYEAR (jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }

    public List<CountTimeVO> countRecordByMonthorgCenter(String Year){
        String sql="SELECT\n" +
                "\tMONTH (jr.createdAt) time,\n" +
                "\tCOUNT(jr.id) count\n" +
                "FROM\n" +
                "\tjr_org_center joc,\n" +
                "\tjr_activity ja,\n" +
                "\tjr_record jr\n" +
                "WHERE\n" +
                "\tjoc.type = ja.activityType\n" +
                "AND ja.id = jr.activityId\n" +
                "AND Year(jr.createdAt)=" +Year+
                " GROUP BY\n" +
                "\tMONTH (jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }
    
    public List<CountTimeVO> countRecordScoreByMonthorgCenter(String Year){
        String sql="SELECT\n" +
                "\tMONTH (jr.createdAt) time,\n" +
                "\tSUM(jr.score) count\n" +
                "FROM\n" +
                "\tjr_org_center joc,\n" +
                "\tjr_activity ja,\n" +
                "\tjr_record jr\n" +
                "WHERE\n" +
                "\tjoc.type = ja.activityType\n" +
                "AND ja.id = jr.activityId\n" +
                "AND Year(jr.createdAt)=" +Year+
                " GROUP BY\n" +
                "\tMONTH (jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }


    public List<CountTimeVO> countRecordByYearTown(){
        String sql="SELECT\n" +
                "\tYEAR(jr.createdAt) time,\n" +
                "\tCOUNT(jr.id) count\n" +
                "FROM\n" +
                "\tjr_record jr,\n" +
                "\tjr_town jt,\n" +
                "\tjr_country jc,\n" +
                "\tjr_activity ja\n" +
                "WHERE\n" +
                "\tjc.id=jr.countryId\n" +
                "AND  jt.id=jc.townid\n" +
                "AND ja.id = jr.activityId\n" +
                "\n" +
                "GROUP BY\n" +
                "\tYEAR(jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }
    
    public List<CountTimeVO> countRecordScoreByYearTown(){
        String sql="SELECT\n" +
                "\tYEAR(jr.createdAt) time,\n" +
                "\tSUM(jr.score) count\n" +
                "FROM\n" +
                "\tjr_record jr,\n" +
                "\tjr_town jt,\n" +
                "\tjr_country jc,\n" +
                "\tjr_activity ja\n" +
                "WHERE\n" +
                "\tjc.id=jr.countryId\n" +
                "AND  jt.id=jc.townid\n" +
                "AND ja.id = jr.activityId\n" +
                "\n" +
                "GROUP BY\n" +
                "\tYEAR(jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }

    public List<CountTimeVO> countRecordByMonthTown(String Year){
        String sql="SELECT\n" +
                "\tMONTH(jr.createdAt) time,\n" +
                "\tCOUNT(jr.id) count\n" +
                "FROM\n" +
                "\tjr_record jr,\n" +
                "\tjr_town jt,\n" +
                "\tjr_country jc,\n" +
                "\tjr_activity ja\n" +
                "WHERE\n" +
                "\tjc.id=jr.countryId\n" +
                "AND  jt.id=jc.townid\n" +
                "AND ja.id = jr.activityId\n" +
                "AND YEAR(jr.createdAt)=" +Year+
                " GROUP BY\n" +
                "\tMONTH(jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }
    
    public List<CountTimeVO> countRecordScoreByMonthTown(String Year){
        String sql="SELECT\n" +
                "\tMONTH(jr.createdAt) time,\n" +
                "\tSUM(jr.score) count\n" +
                "FROM\n" +
                "\tjr_record jr,\n" +
                "\tjr_town jt,\n" +
                "\tjr_country jc,\n" +
                "\tjr_activity ja\n" +
                "WHERE\n" +
                "\tjc.id=jr.countryId\n" +
                "AND  jt.id=jc.townid\n" +
                "AND ja.id = jr.activityId\n" +
                "AND YEAR(jr.createdAt)=" +Year+
                " GROUP BY\n" +
                "\tMONTH(jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }

    public List<CountTimeVO> countRecordByMonthCountry(String Year){
        String sql="SELECT\n" +
                "\tMONTH (jr.createdAt) time,\n" +
                "\tCOUNT(jr.id) count\n" +
                "FROM\n" +
                "\tjr_country jc,\n" +
                "\tjr_activity ja,\n" +
                "\tjr_record jr\n" +
                "WHERE\n" +
                "\tjc.id=jr.countryId\n" +
                "AND ja.id = jr.activityId\n" +
                "AND Year(jr.createdAt)=" +Year+
                " GROUP BY\n" +
                "\tMONTH (jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }
    
    public List<CountTimeVO> countRecordScoreByMonthCountry(String Year){
        String sql="SELECT\n" +
                "\tMONTH (jr.createdAt) time,\n" +
                "\tSUM(jr.score) count\n" +
                "FROM\n" +
                "\tjr_country jc,\n" +
                "\tjr_activity ja,\n" +
                "\tjr_record jr\n" +
                "WHERE\n" +
                "\tjc.id=jr.countryId\n" +
                "AND ja.id = jr.activityId\n" +
                "AND Year(jr.createdAt)=" +Year+
                " GROUP BY\n" +
                "\tMONTH (jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }

    public List<CountTimeVO> countRecordByYearCountry(){
        String sql="SELECT\n" +
                "\tYEAR (jr.createdAt) time,\n" +
                "\tCOUNT(jr.id) count\n" +
                "FROM\n" +
                "\tjr_country jc,\n" +
                "\tjr_activity ja,\n" +
                "\tjr_record jr\n" +
                "WHERE\n" +
                "\tjc.id=jr.countryId\n" +
                "AND ja.id = jr.activityId\n" +
                "GROUP BY\n" +
                "\tYEAR (jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }
    
    public List<CountTimeVO> countRecordScoreByYearCountry(){
        String sql="SELECT\n" +
                "\tYEAR (jr.createdAt) time,\n" +
                "\tSUM(jr.score) count\n" +
                "FROM\n" +
                "\tjr_country jc,\n" +
                "\tjr_activity ja,\n" +
                "\tjr_record jr\n" +
                "WHERE\n" +
                "\tjc.id=jr.countryId\n" +
                "AND ja.id = jr.activityId\n" +
                "GROUP BY\n" +
                "\tYEAR (jr.createdAt)";
        return super.findAllListBySql(CountTimeVO.class,sql);

    }

    public List<CountRateVO> CountRateVO(){
        String sql="SELECT  \n" +
                "ja.activityType activitytype,\n" +
                "COUNT(id) rate\n" +
                "FROM\n" +
                "jr_activity ja\n" +
                "GROUP BY\n" +
                "ja.activityType";

        String sql1="SELECT  \n" +
                "COUNT(id) count \n" +
                "FROM\n" +
                "jr_activity ja\n";

        List<CountRateVO> countRateVOList=super.findAllListBySql(CountRateVO.class,sql);
        Long total=super.findOneBySql(CountVO.class, sql1).getCount();;
        for (CountRateVO countRateVO1:countRateVOList){
            countRateVO1.setRate(countRateVO1.getRate()/total);
        }
        return countRateVOList;

    }
    
    public List<CountRateVO> CountScoreRateVO(){
        String sql="SELECT  \n" +
                "ja.activityType activitytype,\n" +
                "SUM(score) rate\n" +
                "FROM\n" +
                "jr_activity ja\n" +
                "GROUP BY\n" +
                "ja.activityType";

        String sql1="SELECT  \n" +
                "SUM(score) count \n" +
                "FROM\n" +
                "jr_activity ja\n";

        List<CountRateVO> countRateVOList=super.findAllListBySql(CountRateVO.class,sql);
        Long total=super.findOneBySql(CountVO.class, sql1).getCount();
        for (CountRateVO countRateVO1:countRateVOList){
            countRateVO1.setRate(countRateVO1.getRate()/total);
        }
        return countRateVOList;

    }
    public List<Record> addAllReord(String countryId,List<String> artivityId){
        List<Record> recordList=new ArrayList<>();

        for (String s:artivityId){
            Record record=new Record();
            record.setCountryId(countryId);
            record.setActivityId(s);
            record.setStatus(ProcessConstants.RECORD_UNFINSHED);
            recordList.add(record);
        }
        return super.saveList(recordList);
    }

    @Override
    public Page<Record> getSearchByRole(RecordSearchable searchable, Pageable pageable) {
        Principal principal = (Principal)((Optional) principalService.getCurrentPrincipal()).get();
        QRecord qRecord = QRecord.record;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        String orgId = principal.getOrgId();
        if (ProcessConstants.ORG_CENTER.equals(principal.getType())){
            OrgCenter orgCenter = orgCenterService.findById(orgId);
            booleanBuilder.and(qRecord.activity.activityType.eq(orgCenter.getType()));
            booleanBuilder.and(qRecord.status.eq(ProcessConstants.RECORD_TOWN_PASSED).or(qRecord.status.eq(ProcessConstants.RECORD_CITY_PASSED)));
        } else if (ProcessConstants.ORG_ROOM.equals(principal.getType())) {
            OrgRoom orgRoom = orgRoomService.findById(orgId);
            List<Country> allByTownId = countryService.findAllByTownId(orgRoom.getTownId());
            List<Principal> allByOrgIdIn = principalService.findAllByOrgIdIn(allByTownId.stream().map(Country::getId).collect(Collectors.toList()));
            booleanBuilder.and(qRecord.activity.activityType.eq(orgRoom.getType()));
            booleanBuilder.and(qRecord.status.eq(ProcessConstants.RECORD_COMMITED));
            booleanBuilder.and(qRecord.createdBy.in(allByOrgIdIn.stream().map(Principal::getId).collect(Collectors.toList())));
        } else if  (ProcessConstants.ORG_COUNTRY.equals(principal.getType())){
            booleanBuilder.and(qRecord.createdBy.eq(principal.getId()));
            booleanBuilder.and(qRecord.status.eq(ProcessConstants.RECORD_UNFINSHED));
        }
        return super.findAll(booleanBuilder, pageable);
    }
}