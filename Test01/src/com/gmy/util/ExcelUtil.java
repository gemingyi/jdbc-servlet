package com.gmy.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtil{
	public static void exportExcelData(ResultSet rs, Workbook wb, String[] headers) throws SQLException {
		int indexRow = 0;
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(indexRow++);
		for(int i = 0; i < headers.length; i++) {
			row.createCell(i).setCellValue(headers[i]);
		}
		while(rs.next()) {
			row = sheet.createRow(indexRow++);
			for(int i = 0; i < headers.length; i++) {
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());
			}
		}
	}
	
	public static String formatCell(HSSFCell hssfCell) {
		if(hssfCell==null){
			return "";
		}else{
			if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
				return String.valueOf(hssfCell.getBooleanCellValue());
			}else if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				return String.valueOf(hssfCell.getNumericCellValue()).split("\\.")[0];
			}else{
				return String.valueOf(hssfCell.getStringCellValue());
			}
		}
	}
}
