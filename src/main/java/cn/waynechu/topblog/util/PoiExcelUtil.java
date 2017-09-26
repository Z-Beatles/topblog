package cn.waynechu.topblog.util;

import entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xiach on 2017/9/19.
 */
public class PoiExcelUtil {

    /**
     * 获得Excel的方法
     * @param response  response请求
     * @param counters   待导出的信息
     * @param ExcelName  文件名
     */
     public static void getExcel(HttpServletResponse response, List counters,String ExcelName){
         try {
             //创建工作空间
             Workbook workbook=new HSSFWorkbook();
             //创建工作薄
             Sheet sheet = workbook.createSheet(ExcelName);
             //准备导出的实体类的属性数组
             Field[] declaredFields = User.class.getDeclaredFields();
             //实体类属性值的个数
             Integer  attributeNum=declaredFields.length;
             //合并单元格
             CellRangeAddress address=new CellRangeAddress(0, 1, 0, attributeNum-1);
             sheet.addMergedRegion(address);
             //设置单元格格式
             sheet.setDefaultColumnWidth(20);
             //创建一级标题行
             Row row = sheet.createRow(0);
             Cell cell = row.createCell(0);
             //设置内容格式
             CellStyle cellStyle = workbook.createCellStyle();
             cellStyle.setAlignment(HorizontalAlignment.CENTER);
             cell.setCellStyle(cellStyle);
             cell.setCellValue(ExcelName);
             //创建二级标题行
             Row titleRow=sheet.createRow(2);
             for (int i = 0; i < attributeNum; i++) {
                 Cell colCell = titleRow.createCell(i);
                 //实体类上加自定义注解类的属性名作为二级标题
                 String name=declaredFields[i].getAnnotation(annotations.Comment.class).value();
                 colCell.setCellValue(name);
             }
             //导出的信息
             for (int i = 0; i < counters.size(); i++) {
                 //数据行
                 Row dataRow = sheet.createRow(i+3);
                 //创建数据列
                 for (int j = 0; j < attributeNum; j++) {
                     Cell dataCell = dataRow.createCell(j);
                     //通过反射调用类中的方法
                     String getMethodName = "get" +
                             declaredFields[j].getName().substring(0, 1).toUpperCase()
                             +declaredFields[j].getName().substring(1);
                     //返回方法对象
                     Method getMethod = User.class.getDeclaredMethod(getMethodName, new Class[]{});
                     //执行方法
                     Object invoke = getMethod.invoke(counters.get(i), new Object[]{});
                     if(declaredFields[j].getType()  == String.class) {
                         dataCell.setCellValue(invoke.toString());
                     }
                     //考虑日期类型
                     if(declaredFields[j].getType()  == Date.class){
                         dataCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(invoke));
                     }
                     //考虑数字类型
                     if(declaredFields[j].getType()  == Integer.class){
                         dataCell.setCellValue(invoke.toString());
                     }
                     //考虑关系属性   对象|集合
                 }

             }
             //写出Excel
             //设置以附件的形式下载  attachment
             response.setHeader("content-disposition", "attachment; fileName="+ URLEncoder.encode(ExcelName+".xls","UTF-8"));
             ServletOutputStream os = response.getOutputStream();
             workbook.write(os);
             os.close();
             workbook.close();
         } catch (Exception e) {
             e.printStackTrace();
         }

     }

}
