package com.dave.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dave.common.vo.JsonResult;
import com.dave.entity.Excel;
import com.dave.entity.ExcelAll;
import com.dave.service.ExcelService;

/**
 * Excel控制层
 * @author davewpw
 *
 */
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
	
	@RequestMapping("doSearch")
	public String searchExcel(String excelDate, String excelName, int isSearchMax, Model model) {
		List<Excel> excels = excelService.searchExcel(excelDate, excelName, isSearchMax);
		model.addAttribute("excel", excels);
		return "index";
	}
	/**
	 * 导入Excel
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("doImport")
	public String exImport(HttpServletRequest request, Model model) {
		JsonResult jsonResult = null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			if (multipartRequest != null) {
				Iterator<String> iterator = multipartRequest.getFileNames();
				while (iterator.hasNext()) {
					//单文件上传 。
//	                MultipartFile file = multipartRequest.getFile(iterator.next());//一次传一个文件
//	                if (StringUtils.hasText(file.getOriginalFilename())) {
//	                	jsonResult = excelService.batchImport(file.getOriginalFilename(), file);
//						request.setAttribute("result", jsonResult);
//						if(jsonResult.getState() == 0){
//							List<Excel> excels = excelService.selectExcel();
//							model.addAttribute("excel", excels);
//							return "index";
//						}
//	                }
					//多文件上传
					List<MultipartFile> fileList = multipartRequest.getFiles(iterator.next()); //一次选多个文件上传
					for (MultipartFile file : fileList) {
						if (StringUtils.hasText(file.getOriginalFilename())) {
							jsonResult = excelService.batchImport(file.getOriginalFilename(), file);
							request.setAttribute("result", jsonResult);
							if(jsonResult.getState() == 0){
								List<Excel> excels = excelService.selectExcel();
								model.addAttribute("excel", excels);
								return "index";
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:doIndexUI.do";
	}
	/**
	 * 导出Excel
	 * @param response
	 * @param excelId
	 */
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
	
	@RequestMapping("doDeleteExcel")
	@ResponseBody
	public JsonResult doDeleteExcel(Integer... excelIds) {
		int row = excelService.deleteExcel(excelIds);
		if(row != 1)new JsonResult("删除失败");
		return new JsonResult("删除成功", row);
	}
}