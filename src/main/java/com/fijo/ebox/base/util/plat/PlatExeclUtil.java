package com.fijo.ebox.base.util.plat;

import com.fijo.ebox.base.constant.ExcelConstant;
import com.mysql.jdbc.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class PlatExeclUtil<T> {
    private static final String FILE_PREFIX = ".xlsx";
    private static final String ENUM_GET_CODE_METHOD = "getEnumCodeByMsg";



    /**
     * 读取exel文件
     * @param file 文件对象
     * @param clazz 反射类
     * @param sheetNumber sheet页数
     * @param filedRowLine 属性所在行
     * @param dataBeginLine 数据开始行
     * @param enumConfig 枚举字段配置
     * @return
     * @throws Exception
     */
    public static List<Object> readExecl(MultipartFile file, Class clazz, int sheetNumber, int filedRowLine, int dataBeginLine,Map<String,Class> enumConfig ) throws Exception{
        List resultList = new ArrayList();
        String fileName = file.getOriginalFilename();//获取文件名
        String prefix = fileName.substring(fileName.lastIndexOf("."));//获取文件后缀名
        if(!prefix.equals(FILE_PREFIX)){
            throw new RuntimeException("请确认上传的文件类型为："+ FILE_PREFIX);
        }else{
            String newFileName = UUID.randomUUID().toString().concat(prefix);//新文件名
            String savePathName = ExcelConstant.FILE_PATH;//保存路径
            File saveFile = new File(savePathName + newFileName);//生成新的文件
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(),saveFile);//复制文件
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream is = new FileInputStream(saveFile);//获取文件流
            Workbook workbook = new XSSFWorkbook(is);
            //读取第一个sheet页
            Sheet sheet = workbook.getSheetAt(sheetNumber);
            //不做校验
            //读取第二行，需要处理的字段属性名
            Row filedrow = sheet.getRow(filedRowLine);//属性行
            boolean isPassFieldCheck = false;
            //校验属性
            try {
                isPassFieldCheck = checkSheet(filedrow,clazz);
            } catch (Exception e) {
                log.error("导入表格字段校验失败，失败原因：{}",e.toString());
                throw e;
            }
            if(isPassFieldCheck){//如果通过字段校验，读取所有的数据
                resultList = readExelAllRowValue(sheet,clazz,filedrow,dataBeginLine,enumConfig);//读取行数据
            }
            return resultList;
        }

    }

    /**
     * 校验exel导入的对应字段在实体类中是否存在
     * @param filedRow
     * @param clazz
     * @return
     * @throws Exception
     */
    private static boolean checkSheet(Row filedRow,Class clazz) throws Exception{
        boolean flag = true;
        for(int i =0; i<filedRow.getLastCellNum();i++){
            String fieldName = filedRow.getCell(i).getStringCellValue();//获取字段属性名
            //校验属性
            Field field = null;
            try {
                field = clazz.getDeclaredField(fieldName);//判断是否存在属性
            } catch (Exception e) {
                Class uperClazz = clazz.getSuperclass();//若不存在属性则查找父类属性
                try {
                    field = uperClazz.getDeclaredField(fieldName);
                } catch (NoSuchFieldException noSuchFieldException) {
                    flag = false;
                    log.error("不匹配的字段名：{}",fieldName);
                    throw new Exception("【字段属性校验】属性校验不匹配，请检查迁入文件是否正确");
                }
            }
        }
        return flag;
    }

    /**
     * 读取sheet页中的所有数据
     * @param sheet sheet对象
     * @param clazz 反射类
     * @param filedRow 字段行
     * @param dataBeginLine 数据开始行
     * @return
     * @throws Exception
     */
    private static List<Object> readExelAllRowValue(Sheet sheet,Class clazz,Row filedRow ,int dataBeginLine,Map<String,Class> enumConfig) throws Exception{
        List resultList= new ArrayList();
        for (int i = dataBeginLine; i < sheet.getLastRowNum() + 1; i++){
            Row row = sheet.getRow(i);
            //实例化对象
            Object obj = clazz.newInstance();
            //构建数据
            for(int j = 0 ; j < row.getLastCellNum(); j++){
                Field objField = null;
                //获取字段属性名
                String fieldName = filedRow.getCell(j).getStringCellValue();//获取字段属性名
                try {
                    objField = obj.getClass().getDeclaredField(fieldName);//实例化对象的属性
                } catch (Exception e) {
                    Class uperClazz = clazz.getSuperclass();//若不存在属性则查找父类属性
                    try {
                        objField = obj.getClass().getSuperclass().getDeclaredField(fieldName);//实例化对象的属性
                    } catch (NoSuchFieldException noSuchFieldException) {
                        throw new Exception("【字段属性校验】属性校验不匹配，请检查迁入文件是否正确");
                    }
                }
                Class fieldType = objField.getType();
                //2.构建实体对象
                //set方法
                objField.setAccessible(true);
                String cellValue = checkValue(i,j,row);
                cellValue = StringUtils.isNullOrEmpty(cellValue) ? null : cellValue;
                //判断当前字段是否是枚举类
                //判断当前字段是否有配置枚举
                if(enumConfig != null && enumConfig.containsKey(fieldName)){
                    Class enumClazz = enumConfig.get(fieldName);
                    Method getMeaning = enumClazz.getDeclaredMethod(ENUM_GET_CODE_METHOD,String.class);
                    Object[] oo = enumClazz.getEnumConstants();
                    Object invoke = getMeaning.invoke(oo[0],cellValue);
                    if(invoke == null){
                        cellValue = null;
                    }else{
                        cellValue = ((Integer) invoke).toString();
                    }

                }
                if(fieldType.isAssignableFrom(Byte.class) || fieldType.isAssignableFrom(byte.class)){
                    if(StringUtils.isNullOrEmpty(cellValue)){
                        objField.set(obj,null);
                    }else{
                        Byte value = Byte.valueOf(cellValue);
                        objField.set(obj,value);
                    }
                }else if(fieldType.isAssignableFrom(Short.class) || fieldType.isAssignableFrom(short.class)){
                    if(StringUtils.isNullOrEmpty(cellValue)){
                        objField.set(obj,null);
                    }else{
                        Short value = Short.valueOf(cellValue);
                        objField.set(obj,value);
                    }
                }else if(fieldType.isAssignableFrom(Integer.class) || fieldType.isAssignableFrom(int.class)){
                    if(StringUtils.isNullOrEmpty(cellValue)){
                        objField.set(obj,null);
                    }else{
                        Integer value = Integer.valueOf(cellValue) ;
                        objField.set(obj,value);
                    }
                }else if(fieldType.isAssignableFrom(Long.class) || fieldType.isAssignableFrom(long.class)){
                    if(StringUtils.isNullOrEmpty(cellValue)){
                        objField.set(obj,null);
                    }else{
                        Long value = Long.valueOf(cellValue);
                        objField.set(obj,value);
                    }
                }else if(fieldType.isAssignableFrom(Float.class) || fieldType.isAssignableFrom(float.class)){
                    if(StringUtils.isNullOrEmpty(cellValue)){
                        objField.set(obj,null);
                    }else{
                        Float value = Float.valueOf(cellValue);
                        objField.set(obj,value);
                    }
                }else if(fieldType.isAssignableFrom(Double.class) || fieldType.isAssignableFrom(double.class)){
                    if(StringUtils.isNullOrEmpty(cellValue)){
                        objField.set(obj,null);
                    }else{
                        Double value = Double.valueOf(cellValue);
                        objField.set(obj,value);
                    }
                }else if(fieldType.isAssignableFrom(char.class)){
                    String value = cellValue ;
                    objField.set(obj,value);
                }else if(fieldType.isAssignableFrom(Boolean.class) || fieldType.isAssignableFrom(boolean.class)){
                    if(StringUtils.isNullOrEmpty(cellValue)) {
                        objField.set(obj,null);
                    }else{
                        Boolean value = cellValue.equals("true") ? true : false ;
                        objField.set(obj,value);
                    }
                }else if(fieldType.isAssignableFrom(String.class)){
                    String value = cellValue ;
                    objField.set(obj,value);
                } else{

                    throw new Exception("数据类型为非八大基本数据类型以及String类型，请检查"+fieldName+"属性");
                }
            }
            resultList.add(obj);
        }
        return resultList;
    }


    public static String checkValue(int r, int j, Row row) {
        Cell cell = row.getCell(j);
        String value = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (cell == null) {
            cell = row.createCell(0);
            cell.setCellValue("");
        }
        try {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    //处理数字和公式
                    value = String.valueOf(Double.valueOf(cell.getNumericCellValue()));
                    value = value.replaceAll("0+?$", "");
                    value = value.replaceAll("[.]$", "");
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    //处理日期格式的Excel数据
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        value = sdf.format(date);
                    } else {
                        //处理科学计数法
                        double temp = cell.getNumericCellValue();
                        DecimalFormat df = new DecimalFormat("0");
                        value = df.format(temp);


                    }
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("导入失败!第" + (r + 1) + "行第" + (j + 1) + "列,格式有误\n");
        }
        return value;
    }


}


