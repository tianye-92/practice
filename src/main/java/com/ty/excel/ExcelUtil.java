package com.ty.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 读取Excel文件内容
 *
 * @ClassName ExcelUtil
 * @Author tianye
 * @Date 2019/4/23 16:13
 * @Version 1.0
 */
public class ExcelUtil {

    private XSSFSheet sheet;

    private HSSFWorkbook workbook;

    /**
     * 构造函数，初始化excel数据
     *
     * @param filePath  excel路径
     * @param sheetName sheet表名
     */
    ExcelUtil(String filePath, String sheetName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            sheet = sheets.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据行和列的索引获取单元格的数据
     *
     * @param row
     * @param column
     * @return
     */
    public String getExcelDateByIndex(int row, int column) {
        XSSFRow row1 = sheet.getRow(row - 1);
        String cell = row1.getCell(column - 1).toString();
        return cell;
    }

    /**
     * 获取Excel文件总行数
     * @return
     */
    public int getRowNbr(){
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * 获取当前这一行总列数
     * @param row
     * @return
     */
    public int getCellNbr(XSSFRow row){
        return row.getPhysicalNumberOfCells();
    }

    public List<String> getCells(String cellName){
        // 获取第一行所有列名称
        XSSFRow row = sheet.getRow(0);
        // 获取第一行有多少列
        int cellNbr = getCellNbr(row);
        // 遍历，获取cellName列的索引
        int cellNameNbr = -1;
        for (int i = 0; i < cellNbr; i++) {
            if (StringUtils.equals(row.getCell(i).toString(),cellName))
                cellNameNbr = i;
        }
        if (cellNameNbr == -1)
            throw new NullPointerException("没有该列 -> "+cellName);
        // 获取总行数
        int rowNbr = getRowNbr();
        List<String> result = new ArrayList<>();
        // 遍历所有行，获取cellName列的数据
        for (int i = 1; i < rowNbr; i++) {
            XSSFRow sheetRow = sheet.getRow(i);
            result.add(sheetRow.getCell(cellNameNbr).toString());
        }
        return result;
    }


    /**
     * 根据某一列值为“******”的这一行，来获取该行第x列的值
     *
     * @param caseName
     * @param currentColumn 当前单元格列的索引
     * @param targetColumn  目标单元格列的索引
     * @return
     */
    public String getCellByCaseName(String caseName, int currentColumn, int targetColumn) {
        String operateSteps = "";
        //获取行数
        CellStyle columnStyle = sheet.getColumnStyle(1);
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(currentColumn).toString();
            if (cell.equals(caseName)) {
                operateSteps = row.getCell(targetColumn).toString();
                break;
            }
        }
        return operateSteps;
    }

    //测试方法
    public static void main(String[] args) {
        ExcelUtil sheet1 = new ExcelUtil("C:\\Users\\Administrator\\Desktop\\test1.xlsx", "username");
//        //获取第二行第4列
//        String cell2 = sheet1.getExcelDateByIndex(1, 3);
//        //根据第3列值为“2行，3列”的这一行，来获取该行第2列的值
//        String cell3 = sheet1.getCellByCaseName("2行，3列", 2, 1);
//        System.out.println(cell2);
//        System.out.println(cell3);

        List<String> cells = sheet1.getCells("1行，4列");
        cells.forEach(System.out::println);
    }


}
