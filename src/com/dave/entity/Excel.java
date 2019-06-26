package com.dave.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * Excel实体类
 * @author Dave
 *
 */
public class Excel {
	/**自增主键ID*/
	private Integer excelId;
	/**Excel名称*/
	private String excelName;
	/**Excel内容日期*/
	private String excelDate;
	/**Excel内容周期*/
	private String week;
	/**Excel上传日期*/
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createDate;
	/**Occupancy Rate Max*/
	private String occupancyRate;
	/**Excel样式*/
	private String type;
	
	public Integer getExcelId() {
		return excelId;
	}
	public void setExcelId(Integer excelId) {
		this.excelId = excelId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOccupancyRate() {
		return occupancyRate;
	}
	public void setOccupancyRate(String occupancyRate) {
		this.occupancyRate = occupancyRate;
	}
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getExcelDate() {
		return excelDate;
	}
	public void setExcelDate(String excelDate) {
		this.excelDate = excelDate;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
}