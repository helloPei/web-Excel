package test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dave.dao.ExcelDao;
import com.dave.entity.Excel;
import com.dave.entity.ExcelAll;

public class SpringTest extends SpringTestBase {
	
	@Autowired
	private ExcelDao excelDao;
	
	@Test
	public void insertTest() {
		ExcelAll excelAll = new ExcelAll();
		excelAll.setTime("Time");
		excelAll.setExcelId(1);
		excelAll.setOutCallAnswer("10");
		excelAll.setOutAverageHoldingTPC("10");
		excelAll.setOutTotalHour("0.11");
		excelAll.setServiceCapacity("24");
		excelAll.setCapacityNeeded("2");
		excelAll.setOccupancyHour("0.02");
		excelAll.setOccupancyRate("3.09");
		int rows = excelDao.addExcelAll(excelAll);
		System.out.println(rows);
	}
	
	@Test
	public void pojoTest() {
		List<Excel> selectExcel = excelDao.selectExcel();
		System.out.println(selectExcel);
	}
}