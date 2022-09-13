package com.fijo.ebox.base.util.plat;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.util.*;

/**
 * Excel导入导出工具类
 */
public class ExcelUtil {

    final static String ENUM_PATH = " base.config.enumPath";



    /**
     * Excel导出初始化
     *
     * @param sheetName
     * @param nameRow
     * @param titleEN
     * @param titleCH
     * @return XSSFSheet
     * @throws Exception
     */
    public static XSSFWorkbook initExport(String sheetName, String nameRow, String[] titleEN, String[] titleCH, List<? extends Object> objList,Map<String ,String[]>  filedOptionConfig) {
        try {
            //1.在内存中创建一个excel文件
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            //2.创建工作簿
            XSSFSheet sheet = xssfWorkbook.createSheet(sheetName);
            //sheet.protectSheet("vpn123qwe");
            //3.创建名称行
            XSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue(nameRow);
            //4.创建隐藏行和标题行
            XSSFRow createHideTitleerRow = sheet.createRow(1);
            XSSFRow titlerRow = sheet.createRow(2);

            XSSFCellStyle lockStyle = xssfWorkbook.createCellStyle();
            lockStyle.setLocked(true);
            for (int cell = 0; cell < titleEN.length; cell++) {
                XSSFCell xssfCell1 = createHideTitleerRow.createCell(cell);
                xssfCell1.setCellValue(titleEN[cell]);
                xssfCell1.setCellStyle(lockStyle);
                XSSFCell xssfCell2 = titlerRow.createCell(cell);
                xssfCell2.setCellValue(titleCH[cell].toString());
                xssfCell2.setCellStyle(lockStyle);
            }
            //5.设置隐藏行
            sheet.getRow(1).setZeroHeight(true);
            //如果配置了下拉值，设置下拉值
            if(filedOptionConfig != null){
                for (int cell = 0; cell < titleEN.length; cell++) {
                    //判断是否有下拉框的配置
                    if(filedOptionConfig.get(titleEN[cell]) != null){
                        XSSFRow dataRow = sheet.createRow(3);//设置请选择
                        dataRow.createCell(cell).setCellValue("请选择");
                        String[] fielOption = filedOptionConfig.get(titleEN[cell]);
                        //判断选项长度
                        if(String.join(",",fielOption).length()>255){
                            int sheetTotal = xssfWorkbook.getNumberOfSheets();
                            String hiddenSheetName = "配置选项页" + sheetTotal;
                            XSSFSheet hiddenSheet = (XSSFSheet)xssfWorkbook.createSheet(hiddenSheetName);
                            XSSFRow row_hidden ;
                            //写入下拉数据到新的sheet页中
                            for (int i = 0; i < fielOption.length; i++) {
                                row_hidden = hiddenSheet.createRow(i);
                                Cell cell_hidden = row_hidden.createCell(0);
                                cell_hidden.setCellValue(fielOption[i]);
                            }
                            //获取新sheet页内容
                            String strFormula = hiddenSheetName + "!$"+"A"+"$1:$"+"A"+"$"+fielOption.length;
                            XSSFDataValidationConstraint constraint = new XSSFDataValidationConstraint(DataValidationConstraint.ValidationType.LIST,strFormula);
                            // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
                            CellRangeAddressList regions = new CellRangeAddressList(3, 3, cell, cell);
                            DataValidationHelper help = new XSSFDataValidationHelper((XSSFSheet) sheet);
                            DataValidation validation = help.createValidation(constraint, regions);
                            sheet.addValidationData(validation);
                            //将新建的sheet页隐藏掉
                            xssfWorkbook.setSheetHidden(sheetTotal, true);

                        }else{
                            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
                            XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                                    .createExplicitListConstraint(fielOption);
                            CellRangeAddressList addressList = null;
                            XSSFDataValidation validation = null;
                            addressList = new CellRangeAddressList(3, 3, cell, cell);
                            validation = (XSSFDataValidation) dvHelper.createValidation(
                                    dvConstraint, addressList);
                            sheet.addValidationData(validation);
                        }

                    }
                }
            }
            //6.遍历数据,创建数据行
            for (Object obj : objList) {
                //获取最后一行的行号,每次往sheet上面加数据的时候都从有数据的下一行加
                int lastRowNum = sheet.getLastRowNum() + 1;
                XSSFRow dataRow = sheet.createRow(lastRowNum);
                for (int cell = 0; cell < titleEN.length; cell++) {
                    String getValue = ReflectUtil.getFieldValueByFieldName(titleEN[cell], obj);
                    dataRow.createCell(cell).setCellValue(getValue);
                }
            }
            return xssfWorkbook;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Excel导出初始化
     *
     * @param sheetName
     * @param nameRow
     * @param titleEN
     * @param titleCH
     * @return XSSFSheet
     * @throws Exception
     */
    public static XSSFWorkbook initExport(String sheetName, String nameRow, String[] titleEN, String[] titleCH, List<? extends Object> objList,Map<String ,String[]>  filedOptionConfig,Map<String,Map<String,String>> outPutConfig) {
        try {
            //1.在内存中创建一个excel文件
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            //2.创建工作簿
            XSSFSheet sheet = xssfWorkbook.createSheet(sheetName);
            //sheet.protectSheet("vpn123qwe");
            //3.创建名称行
            XSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue(nameRow);
            //4.创建隐藏行和标题行
            XSSFRow createHideTitleerRow = sheet.createRow(1);
            XSSFRow titlerRow = sheet.createRow(2);

            XSSFCellStyle lockStyle = xssfWorkbook.createCellStyle();
            lockStyle.setLocked(true);
            for (int cell = 0; cell < titleEN.length; cell++) {
                XSSFCell xssfCell1 = createHideTitleerRow.createCell(cell);
                xssfCell1.setCellValue(titleEN[cell]);
                xssfCell1.setCellStyle(lockStyle);
                XSSFCell xssfCell2 = titlerRow.createCell(cell);
                xssfCell2.setCellValue(titleCH[cell].toString());
                xssfCell2.setCellStyle(lockStyle);
            }
            //5.设置隐藏行
            sheet.getRow(1).setZeroHeight(true);
            //如果配置了下拉值，设置下拉值
            if(filedOptionConfig != null){
                for (int cell = 0; cell < titleEN.length; cell++) {
                    //判断是否有下拉框的配置
                    if(filedOptionConfig.get(titleEN[cell]) != null){
                        XSSFRow dataRow = sheet.createRow(3);//设置请选择
                        dataRow.createCell(cell).setCellValue("请选择");
                        String[] fielOption = filedOptionConfig.get(titleEN[cell]);
                        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
                        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                                .createExplicitListConstraint(fielOption);
                        CellRangeAddressList addressList = null;
                        XSSFDataValidation validation = null;
                        addressList = new CellRangeAddressList(3, 3, cell, cell);
                        validation = (XSSFDataValidation) dvHelper.createValidation(
                                dvConstraint, addressList);
                        sheet.addValidationData(validation);
                    }
                }
            }
            if (outPutConfig != null){
                for (Object obj : objList) {
                    //获取最后一行的行号,每次往sheet上面加数据的时候都从有数据的下一行加
                    int lastRowNum = sheet.getLastRowNum() + 1;
                    XSSFRow dataRow = sheet.createRow(lastRowNum);
                    for (int cell = 0; cell < titleEN.length; cell++) {
                        String getValue = ReflectUtil.getFieldValueByFieldName(titleEN[cell], obj);
                        if(outPutConfig.get(titleEN[cell]) != null){
                            getValue = outPutConfig.get(titleEN[cell]).get(getValue);
                        }
                        dataRow.createCell(cell).setCellValue(getValue);
                    }
                }
            }else{
                //6.遍历数据,创建数据行
                for (Object obj : objList) {
                    //获取最后一行的行号,每次往sheet上面加数据的时候都从有数据的下一行加
                    int lastRowNum = sheet.getLastRowNum() + 1;
                    XSSFRow dataRow = sheet.createRow(lastRowNum);
                    for (int cell = 0; cell < titleEN.length; cell++) {
                        String getValue = ReflectUtil.getFieldValueByFieldName(titleEN[cell], obj);
                        dataRow.createCell(cell).setCellValue(getValue);
                    }
                }
            }

            return xssfWorkbook;
        } catch (Exception e) {
            throw e;
        }
    }





}
