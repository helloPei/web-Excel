package com.dave.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import com.dave.common.vo.JsonResult;
import com.dave.entity.Excel;
import com.dave.entity.ExcelAll;

import java.util.List;

public interface ExcelService {
	
	List<Excel> selectExcel();
	
	List<ExcelAll> selectExcelAll(int excelId);
	
	Excel selectExcelNameById(int excelId);
	
	JsonResult batchImport(String fileName, MultipartFile file) throws Exception;
	
	Workbook batchExport(int excelId);
	
	List<Excel> searchExcel(String excelName, int isSearchMax);
	
	int deleteExcel(Integer... excelIds);
}