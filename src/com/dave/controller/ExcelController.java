package com.dave.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dave.common.vo.JsonResult;
import com.dave.entity.Excel;
import com.dave.entity.ExcelAll;
import com.dave.service.ExcelService;

@Controller
@RequestMapping("/")
public class ExcelController {
	
	@Autowired
	private ExcelService excelService;
	
	@RequestMapping("doExcelAllUI")
	public String showExcel(Model model, int excelId) {
		List<ExcelAll> excels = excelService.selectExcelAll(excelId);
		Excel excel = excelService.selectExcelNameById(excelId);
		model.addAttribute("excelAll", excels);
		model.addAttribute("excel", excel);
		return "excelAll";
	}
	
	@RequestMapping("doIndexUI")
	public String showExcelAll(Model model) {
		List<Excel> excels = excelService.selectExcel();
		model.addAttribute("excel", excels);
		return "index";
	}
	
	@RequestMapping("doImport")
	public String exImport(HttpServletRequest request, Model model) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("filename");
		String fileName = file.getOriginalFilename();
		JsonResult jsonResult = null;
		try {
			jsonResult = excelService.batchImport(fileName, file);
			request.setAttribute("result", jsonResult);
			if(jsonResult.getState() == 0){
				List<Excel> excels = excelService.selectExcel();
				model.addAttribute("excel", excels);
				return "index";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:doIndexUI.do";
	}
	
	@RequestMapping("doExport")
	@ResponseBody
	public void export(HttpServletResponse response, int excelId) {
		try {
			Workbook wb = excelService.batchExport(excelId);
			Excel excel = excelService.selectExcelNameById(excelId);
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			OutputStream os = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment;filename="+excel.getExcelName()+" - "+excel.getExcelDate()+".xls");// 默认Excel名称
			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("doSearch")
	public String searchExcel(String excelName, int isSearchMax, Model model) {
		List<Excel> excels = excelService.searchExcel(excelName, isSearchMax);
		model.addAttribute("excel", excels);
		return "index";
	}
	
	@RequestMapping("doDeleteExcel")
	@ResponseBody
	public JsonResult doDeleteExcel(Integer... excelIds) {
		int row = excelService.deleteExcel(excelIds);
		if(row != 1)new JsonResult("删除失败");
		return new JsonResult("删除成功", row);
	}
}