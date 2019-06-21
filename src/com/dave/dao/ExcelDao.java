package com.dave.dao;

import java.util.List;

import com.dave.entity.Excel;
import com.dave.entity.ExcelAll;
import com.dave.entity.ExcelTotal;

public interface ExcelDao {
	/**测试数据库连接*/
	int selectCount();
	
	/**查询所有 Excel 按照名称、日期排序*/
	List<Excel> selectExcel();
	/**根据 Excel ID 查询所有 Excel 内容*/
	List<ExcelAll> selectExcelAll(int excelId);
	/**根据 Excel ID 查询 Excel*/
	Excel selectExcelNameById(int excelId);
	/**添加 Excel*/
	int addExcel(Excel excel);
	/**更改 Excel, 添加 Occupancy Rate 的内容*/
	int updateExcel(Excel excel);
	/**根据 Excel 名称查询, 最新的自增 Excel ID*/
	int selectExcelByName(String excelName);
	/**添加 Excel 内容*/
	int addExcelAll(ExcelAll excelAll);
	/**根据 Excel ID 查询 Excel 内容*/
	List<ExcelAll> selectExcelAllById(int excelId);
	/**根据 Excel ID 删除 Excel 内容*/
	int deleteExcelAll(int excelId);
	/**根据 Excel ID 删除 Excel*/
	int deleteExcel(int excelId);
	/**根据 Excel ID 查询 Excel 内容, 获取需要的Total*/
	ExcelTotal selectExcelAllTotal(int excelId);
	/**根据名称查询所有 Excel*/
	List<Excel> searchExcel(String excelName);
	/**根据名称查询 Excel Occupancy Rate 的最大数*/
	List<Excel> searchExcelMax(String excelName);
	/**根据 Excel ID 查询 Excel Occupancy Rate 的最大数的记录*/
	ExcelAll selectExcelAllMax(int excelId);
}