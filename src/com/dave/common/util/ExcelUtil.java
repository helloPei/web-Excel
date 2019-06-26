package com.dave.common.util;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.csvreader.CsvReader;

public class ExcelUtil {
	/**
	 * 读取csv格式
	 * 
	 * @param csvIs
	 * @return List<String[]>	List为行数据,String[]为一行里的每列数据
	 */
	public static List<String[]> readCsvByIs(InputStream csvIs) {
		List<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
		try {
			CsvReader reader = new CsvReader(csvIs, Charset.forName("GBK")); // 一般用这编码读就可以了
			// reader.readHeaders(); //跳过表头 如果需要表头的话，不要写这句。
			while (reader.readRecord()) { // 逐行读入除表头的数据
				csvList.add(reader.getValues());
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return csvList;
	}
	/**
	 * 读取xls和xlsx格式
	 * 
	 * @param is
	 * @return List<String[]>	List为行数据,String[]为一行里的每列数据
	 */
	public static List<String[]> readXlsAndXlsxByIs(InputStream is) {
		List<String[]> xlsList = new ArrayList<String[]>(); //用来保存数据
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(is);
			Sheet sheet = wb.getSheetAt(0);//工作表对象
			for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
				Row row = sheet.getRow(i);//通过sheet工作表对象得到"行对象"
				if (row == null)continue;//行对象为空跳出循环
				String[] val = new String[row.getLastCellNum()];
				for (int r = 0; r < row.getLastCellNum(); r++) {
					Cell cell = row.getCell(r);//获取指定的单元格
					if (cell != null) {
						String cellValue = null;
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC://数字
							if (cell.getCellStyle().getDataFormatString().indexOf("%") != -1) {
								//百分数转换字符串设置格式
								NumberFormat numFormat = NumberFormat.getPercentInstance();
								numFormat.setMaximumFractionDigits(2);//保留小数点后俩位数
								cellValue = numFormat.format(cell.getNumericCellValue());
							} else {
								//数字转换字符串设置格式
								cellValue = NumberFormat.getInstance().format(cell.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_STRING://字符串
							cellValue = String.valueOf(cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_BOOLEAN://Boolean
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA://公式
							cellValue = String.valueOf(cell.getCellFormula());
							break;
						case Cell.CELL_TYPE_BLANK://空值
							cellValue = "";
							break;
						case Cell.CELL_TYPE_ERROR://故障
							cellValue = "非法字符";
							break;
						default:
							cellValue = "未知类型";
							break;
						}
						val[r] = cellValue.trim();
					}
				}
				xlsList.add(val);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xlsList;
	}
}