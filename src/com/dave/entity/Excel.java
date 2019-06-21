package com.dave.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Excel implements Serializable{
	private static final long serialVersionUID = 2651015555133679249L;
	private int excelId;
	private String excelName;
	private String excelDate;
	private String week;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	private String occupancyRate;
	
	public String getOccupancyRate() {
		return occupancyRate;
	}
	public void setOccupancyRate(String occupancyRate) {
		this.occupancyRate = occupancyRate;
	}
	public int getExcelId() {
		return excelId;
	}
	public void setExcelId(int excelId) {
		this.excelId = excelId;
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