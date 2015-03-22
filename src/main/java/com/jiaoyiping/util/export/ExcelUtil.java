package com.jiaoyiping.util.export;

import com.fasterxml.jackson.databind.JsonNode;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import sun.text.resources.uk.CollationData_uk;

import java.io.File;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: 焦一平
 * Date: 2015/3/19
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class ExcelUtil {
    /**
     * 根据数据源来下载文件
     * @param url 数据源URL
     * @param destFile 目标Excel文件
     */
    public void download(String url,String destFile){

    }

    public static void writeDateToFile(JsonNode node,File file){

        try   {
            //  打开文件
            WritableWorkbook book  =  Workbook.createWorkbook(new File("d:\\test.xls"));
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet  =  book.createSheet( " 第一页 " ,  0 );
            //  在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
            //  以及单元格内容为test

            Iterator<String> iterator = node.get(0).fieldNames();
            int column=0;
            while(iterator.hasNext()){
                String name = iterator.next();
                Label label  =   new Label( column ,  0 ,name);
                sheet.addCell(label);
                column ++;
            }
            int row = 1;
            for(JsonNode nodeItem:node){
                int innerColumn = 0;
                Iterator<String> iterator1 = nodeItem.fieldNames();
                row ++;
            }

            //  将定义好的单元格添加到工作表中

             /*
             * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
              */
            jxl.write.Number number  =   new  jxl.write.Number( 1 ,  0 ,  555.12541 );
            sheet.addCell(number);

            //  写入数据并关闭文件
            book.write();
            book.close();
        }   catch  (Exception e)  {
            e.printStackTrace();
        }



    }

    public static void main(String[] args) {
        writeDateToFile(null,null);
    }
}
