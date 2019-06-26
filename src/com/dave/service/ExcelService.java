package com.dave.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import com.dave.common.vo.JsonResult;
import com.dave.entity.Excel;
import com.dave.entity.ExcelAll;

import java.util.List;

/**
 * Excel业务层接口
 * @author Dave
 *
 */
public interface ExcelService {
	/**查询所有 Excel*/
	List<Excel> selectExcel();
	/**根据 ExcelID 查询所有 Excel内容*/
	List<ExcelAll> selectExcelAll(int excelId);
	/**根据 ExcelID 查询 Excel*/
	Excel selectExcelNameById(int excelId);
	/**导入Excel*/
	JsonResult batchImport(String fileName, MultipartFile file) throws Exception;
	/**导出Excel*/
	Workbook batchExport(int excelId);
	/**批量删除Excel*/
	int deleteExcel(Integer... excelIds);
	/**查询框根据 Excel名称、Excel日期查询 Excel*/
	List<Excel> searchExcel(String excelDate, String excelName, int isSearchMax);
}