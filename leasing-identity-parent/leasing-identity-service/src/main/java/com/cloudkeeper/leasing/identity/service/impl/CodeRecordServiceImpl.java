package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.constant.SerialNumberCycleConstants;
import com.cloudkeeper.leasing.identity.domain.CodeConfig;
import com.cloudkeeper.leasing.identity.domain.CodeRecord;
import com.cloudkeeper.leasing.identity.domain.QCodeRecord;
import com.cloudkeeper.leasing.identity.repository.CodeRecordRepository;
import com.cloudkeeper.leasing.identity.service.CodeConfigService;
import com.cloudkeeper.leasing.identity.service.CodeRecordService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * 编码生成记录 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CodeRecordServiceImpl extends BaseServiceImpl<CodeRecord> implements CodeRecordService {

    /** 编码生成记录 repository */
    private final CodeRecordRepository codeRecordRepository;

    /** 编码配置 service */
    private final CodeConfigService codeConfigService;

    @Override
    protected BaseRepository<CodeRecord> getBaseRepository() {
        return codeRecordRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("businessType", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("branchCompany", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("leasingType", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("separator", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("serialNumber", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public CodeRecord init(CodeConfig codeConfig) {
        CodeRecord codeRecord = new CodeRecord();
        codeRecord.setConfigId(codeConfig.getId());
        codeRecord.setBusinessType(codeConfig.getBusinessType());
        if (codeConfig.getHasBranchCompany() == 1) {
            codeRecord.setBranchCompany(codeConfig.getBranchCompany());
        }
        if (codeConfig.getHasLeasingType() == 1) {
            codeRecord.setLeasingType(codeConfig.getLeasingType());
        }
        if (codeConfig.getHasDate() == 1) {
            codeRecord.setDate(LocalDateTime.now());
        }
        if (codeConfig.getHasSeparator() == 1) {
            codeRecord.setCodeSeparator(codeConfig.getCodeSeparator());
        }
        return codeRecord;
    }

    @Override
    public String generateCode(CodeRecord codeRecord, CodeConfig codeConfig) {
        String code = codeRecord.getBusinessType();
        String separator;
        if (codeRecord.getCodeSeparator() == null) {
            separator = "";
        } else {
            separator = codeRecord.getCodeSeparator();
        }
        code = concat(code, codeRecord.getBranchCompany(), separator);
        code = concat(code, codeRecord.getLeasingType(), separator);
        if (codeRecord.getDate() != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(codeConfig.getDateTemplate());
            code = concat(code, dateTimeFormatter.format(codeRecord.getDate()), separator);
        }
        Integer maxSerialNumber = getMaxSerialNumber(codeConfig.getId(), codeConfig.getSerialNumberCycle());
        codeRecord.setSerialNumber(String.valueOf(++maxSerialNumber));
        code = concat(code, String.format("%0" + codeConfig.getSerialNumberLength() + "d", maxSerialNumber), separator);
        save(codeRecord);
        return code;
    }

    /**
     * 判断值是否为null，不为null则拼接
     * @param code 编码
     * @param temp 拼接内容
     * @param separator 分隔符
     * @return 编码
     */
    private String concat(String code, String temp, String separator) {
        if (temp != null) {
            code += separator;
            code += temp;
        }
        return code;
    }

    /**
     * 根据流水号周期获取库中最大流水号
     * @param configId 编码配置id
     * @param serialNumberCycle 流水号生成周期
     * @return 最大流水号
     */
    private synchronized Integer getMaxSerialNumber(@Nonnull String configId, @Nonnull String serialNumberCycle) {
        QCodeRecord qCodeRecord = QCodeRecord.codeRecord;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qCodeRecord.configId.eq(configId));
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(now.getYear(), 1, 1, 0, 0, 0, 0);
        if (SerialNumberCycleConstants.YEAR.equals(serialNumberCycle)) {
            booleanBuilder.and(qCodeRecord.createdAt.between(start, start.plusYears(1)));
        } else if (SerialNumberCycleConstants.MONTH.equals(serialNumberCycle)) {
            start = start.withMonth(now.getMonth().getValue());
            booleanBuilder.and(qCodeRecord.createdAt.between(start, start.plusMonths(1)));
        } else if (SerialNumberCycleConstants.DAY.equals(serialNumberCycle)) {
            start = start.withMonth(now.getMonth().getValue()).withDayOfMonth(now.getDayOfMonth());
            booleanBuilder.and(qCodeRecord.createdAt.between(start, start.plusDays(1)));
        }
        String max = queryFactory.select(qCodeRecord.serialNumber.max()).from(qCodeRecord).where(booleanBuilder).fetchOne();
        if (max == null) {
            return 0;
        }
        return Integer.valueOf(max);
    }

    @Override
    public String getCode(String configId) {
        Optional<CodeConfig> optional = codeConfigService.findOptionalById(configId);
        if (!optional.isPresent()) {
            return null;
        }
        CodeConfig codeConfig = optional.get();
        return generateCode(init(codeConfig), codeConfig);
    }
}