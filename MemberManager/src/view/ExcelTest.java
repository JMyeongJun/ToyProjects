package view;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.MemberDao;
import model.MemberVo;

public class ExcelTest {
	public static void main(String[] args) {
		
		String path = System.getProperty("user.home") + "\\desktop\\";
		String fName = "excel_test2.xlsx";
		System.out.println(path + fName);
		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		
		int rowCount = 0, cellCount = 0;
		
		Row row = sheet.createRow(rowCount++);
		Cell cell = row.createCell(0);
		cell.setCellValue("아이디");
		cell = row.createCell(1);
		cell.setCellValue("이름");
		cell = row.createCell(2);
		cell.setCellValue("직업");
		cell = row.createCell(3);
		cell.setCellValue("성별");
		cell = row.createCell(4);
		cell.setCellValue("가입일");
		
		MemberDao dao = new MemberDao();
		for(MemberVo vo : dao.getMemberList()) {
			row = sheet.createRow(rowCount++);
			
			cell = row.createCell(0);
			cell.setCellValue(vo.getUserid());
			cell = row.createCell(1);
			cell.setCellValue(vo.getUsername());
			cell = row.createCell(2);
			cell.setCellValue(vo.getJob());
			cell = row.createCell(3);
			cell.setCellValue(vo.getGender());
			cell = row.createCell(4);
			cell.setCellValue(vo.getIndate());
		}
		
		try {
			File file = new File(path + fName);
			FileOutputStream fos = new FileOutputStream(file);
			
			workbook.write(fos);
			
			fos.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setCell(int index, Object object) {
		
	}
	
}
