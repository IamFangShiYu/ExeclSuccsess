package com.guigu.instructional.payment.controller;

import java.io.BufferedOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExportExcel<T> {
	public void exportExcel(String[] headers,Collection<T> dataset, String fileName,HttpServletResponse response) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(fileName);
		sheet.setDefaultColumnWidth((short) 200);
		XSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			XSSFCell cell = row.createCell(i);
			XSSFRichTextString text = new XSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		try {
			Iterator<T> it = dataset.iterator();
			int index = 0;
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				T t = (T) it.next();
				Field[] fields = t.getClass().getDeclaredFields();
				for (short i = 0; i<headers.length; i++) {
					
					XSSFCell cell = row.createCell(i);
					Field field = fields[i];
					String fieldName = field.getName();
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = null;
					if(value != null && value != ""){
						textValue = value.toString();
					}
					if (textValue != null) {
						XSSFRichTextString richString = new XSSFRichTextString(textValue);
						cell.setCellValue(richString);
					}
				}
			}
			getExportedFile(workbook, fileName,response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public void getExportedFile(XSSFWorkbook workbook, String name,HttpServletResponse response) throws Exception {
		BufferedOutputStream fos = null;
		try {
			String fileName = name + ".xlsx";
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
			fos = new BufferedOutputStream(response.getOutputStream());
			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

}
