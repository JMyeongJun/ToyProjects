package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.SalesDao;
import model.SalesVo;

public class SaveExcel {
   
   static Vector<?> title;
   
   public SaveExcel() {
      
   }
   
   public SaveExcel(File file) {
      title = SalesPanel.getColumnList();

      Workbook workbook = createWorkbook(file);
      if(workbook == null) {
         writeCsvOrTxt(file);
         return;
      }
      Sheet sheet = workbook.createSheet();

      int rowCount = 0, cellCount = 0;
      
      Row row = sheet.createRow(rowCount++);
      Cell cell = null;
      for(Object str : title) {
         cell = row.createCell(cellCount++);
         cell.setCellValue((String)str);
      }
      cellCount = 0;
      
      SalesDao dao = new SalesDao();
      for(SalesVo vo : dao.getSalesList(SalesPanel.getSelectedDate(SalesPanel.date1), SalesPanel.getSelectedDate(SalesPanel.date2))) {
         row = sheet.createRow(rowCount++);
         String[] dataList = vo.toString2().split(",");
         for(String data : dataList) {
            cell = row.createCell(cellCount++);
            cell.setCellValue(data);
         }
         cellCount = 0;
      }
      
      try {
         FileOutputStream fos = new FileOutputStream(file);
         
         workbook.write(fos);
         
         fos.close();
         workbook.close();
      } catch (Exception e) {
         System.out.println("저장 실패");
         e.printStackTrace();
      }
   }

   private Workbook createWorkbook(File file) {
      String name = file.getName().substring(file.getName().indexOf(".") + 1);
      switch (name) {
      case "xlsx":
         return new XSSFWorkbook();
      case "xls":
         return new HSSFWorkbook();
      }
      return null;
   }
   
   private void writeCsvOrTxt(File file) {
      try {
         FileWriter fw = new FileWriter(file);
         BufferedWriter bw = new BufferedWriter(fw);
         
         for(int i = 0; i < title.size(); i++) {
            bw.write((String)title.get(i));
            if(i == title.size() - 1) {
               bw.write("\n");
               break;
            }
            bw.write(",");
         }
         
         SalesDao dao = new SalesDao();
         for(SalesVo vo : dao.getSalesList()) {
            bw.write(vo.toString2() + "\n");
         }
         
         bw.close();
         fw.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}