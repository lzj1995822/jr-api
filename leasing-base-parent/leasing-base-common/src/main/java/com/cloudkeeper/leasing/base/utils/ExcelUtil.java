package com.cloudkeeper.leasing.base.utils;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel工具类
 * @author louis
 */
@Slf4j
public class ExcelUtil {


    private ExcelUtil() {
    }
    /** xls类型excel文件*/
    private static final String HSSF_WORKBOOK_SUFFIX = ".xls";

    /** xlsx类型excel文件*/
    private static final String XSSF_WORKBOOK_SUFFIX = ".xlsx";

    private static FormulaEvaluator FORMULA_EVALUATOR = null;


    /**
     *  获取文件数据
     * @param optionCellList 单元格集合
     * @param inputStream 文件inputStream
     * @param fileName 文件名
     * @return 数据集合
     */
    public static List<List<String>> getList(List<List<CellValue>> optionCellList, @NonNull InputStream inputStream, @NonNull String fileName){
        Workbook workbook =null;
        try {
            checkFile(fileName);
             workbook = getWorkbook(fileName, inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }

        if (workbook == null) {
            List<List<String>> result= new ArrayList<>();
            return result;
        }
        List<List<String>> result = new ArrayList<>();
        for (List<ExcelUtil.CellValue> cellValues : optionCellList) {
            List<String> resultRow = new ArrayList<>();
            //获得每行的数据
            for (ExcelUtil.CellValue cellValue : cellValues) {
                resultRow.add(getCellValue(workbook, cellValue));
            }
            result.add(resultRow);
        }
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询Cell对应的值
     * @param workbook 工作薄
     * @param cellValue 单元格属性
     * @return Cell对应的值
     */
    private static String getCellValue(Workbook workbook, @NonNull CellValue cellValue) {
        Sheet sheet = workbook.getSheetAt(cellValue.sheet);
        if (sheet == null) {
            return null;
        }
        Row row = sheet.getRow(cellValue.row);
        if (row == null) {
            return null;
        }
        Cell cell = row.getCell(cellValue.col);
        return getCellValue(cell, cellValue);
    }

    /**
     * 单元格属性类
     */
    @Getter
    @Setter
    public class CellValue {
        /**
         * 工作表的下标
         */
        private int sheet;
        /**
         * 所在行
         */
        private int row;
        /**
         * 所在列
         */
        private int col;
        /**
         * 单元格类型
         */
        private Type type;
        /**
         * 小数点后（保留）位数
         */
        private int point;
        /**
         * 时间格式
         */
        private String pattern;

        public CellValue() {
        }

        public CellValue(int sheet, int row, int col, ExcelUtil.Type type, int point, String pattern) {
            this.sheet = sheet;
            this.row = row;
            this.col = col;
            this.type = type;
            this.point = point;
            this.pattern = pattern;
        }
    }

    public static CellValue of(int sheet, int row, int col, ExcelUtil.Type type, int point, String pattern) {
        return new ExcelUtil().new CellValue(sheet, row, col, type, point, pattern);
    }


    /**
     * 单元格类型
     */
    public enum Type {
        /**
         * 数字
         */
        NUMERIC,
        /**
         * 字符串
         */
        STRING,
        /**
         * 公式
         */
        FORMULA,
        /**
         * boolean
         */
        BOOLEAN,
        /**
         * 时间
         */
        DATE,
        /**
         * 带%号数值
         */
        PERCENT,
        /**
         * 非法字符
         */
        ERROR
    }


    /**
     * 检查文件
     * @param fileName 文件名
     * @throws IOException
     */
    private static void checkFile(@NonNull String fileName) throws IOException {
        //判断文件是否是excel文件
        if (!fileName.endsWith(HSSF_WORKBOOK_SUFFIX) && !fileName.endsWith(XSSF_WORKBOOK_SUFFIX)) {
            log.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }


    /**
     * 获取excel工作簿
     * @param fileName 文件名称
     * @param inputStream 输入流
     * @return excel工作簿
     * @throws IOException 异常
     */
    private static Workbook getWorkbook(@Nonnull String fileName, @Nonnull InputStream inputStream) throws IOException {
        Workbook workbook;
        if (fileName.lastIndexOf(HSSF_WORKBOOK_SUFFIX) == (fileName.length() - HSSF_WORKBOOK_SUFFIX.length())) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileName.lastIndexOf(XSSF_WORKBOOK_SUFFIX) == (fileName.length() - XSSF_WORKBOOK_SUFFIX.length())) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            return null;
        }
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return null;
        }
        return workbook;
    }


    /**
     * 判断数据的类型 获取值
     * @param cell 单元格
     * @param value 单元格属性
     * @return 单元格数值
     */
    private static String getCellValue(Cell cell, CellValue value) {
        String cellValue = "";
        if (cell == null || CellType.BLANK.equals(cell.getCellTypeEnum())) {
            return cellValue;
        }else if (CellType.FORMULA.equals(cell.getCellTypeEnum()) && CellType.ERROR.equals(getFormulaEvaluator(cell).evaluate(cell).getCellTypeEnum())) {
            return cellValue;
        }else if (CellType.STRING.equals(cell.getCellTypeEnum())) {
            if (StringUtils.isEmpty(cell.getStringCellValue())) {
                return null;
            }
        }
        //判断数据的类型
        try {
            switch (value.type) {
                case NUMERIC:
                    cellValue = getPointFormat(cell, value);
                    break;
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case FORMULA:
                    cellValue = getPointFormat(cell, value);
                    break;
                case PERCENT:
                    cellValue = getPointFormat(cell, value);
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case DATE:
                    cellValue = String.valueOf(getDateFormat(cell, value));
                    break;
                case ERROR:
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "";
                    break;
            }
        } catch (Exception e) {
            log.error("获取单元格日期:工作簿名称:" + cell.getSheet().getSheetName() + "，" +
                    "行号：" + (cell.getRowIndex() + 1) + "，列号：" + (cell.getColumnIndex() + 1) + "，单元格值：" + cell, e);
        }
        return cellValue;
    }


    /**
     * 按小数点位数取值
     * @param cell 单元格
     * @param value 单元格属性
     * @return 单元格数值
     */
    private static String getPointFormat(@NonNull Cell cell, @NonNull CellValue value) {
        double cellValue = cell.getNumericCellValue();
        NumberFormat nf = null;
        String str = null;
        //带%符号
        if (value.type == ExcelUtil.Type.PERCENT) {
            nf = NumberFormat.getPercentInstance();
            // 小数点后保留几位
            nf.setMinimumFractionDigits(value.point);
            str = nf.format(cellValue);
        }
        //数字型取小数点
        if (value.type == ExcelUtil.Type.NUMERIC || value.type == ExcelUtil.Type.FORMULA) {
            nf = NumberFormat.getNumberInstance();
            // 小数点后保留几位
            nf.setMaximumFractionDigits(value.point);
            str = nf.format(cellValue);
        }
        return str;
    }


    /**
     * 时间格式
     * @param cell 单元格
     * @param value 单元格属性
     * @return 单元格数值
     */
    private static String getDateFormat(Cell cell, @NonNull CellValue value) {
        String pattern = value.pattern !=null ? value.pattern : "yyyy/MM/dd";
        return new SimpleDateFormat(pattern).format(cell.getDateCellValue());
    }

    private static FormulaEvaluator getFormulaEvaluator(Cell cell) {
        FORMULA_EVALUATOR = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
        return FORMULA_EVALUATOR;
    }

}
