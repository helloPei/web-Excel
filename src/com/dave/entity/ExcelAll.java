package com.dave.entity;

public class ExcelAll {
	private int excelAllId;
	private int excelId;
	
	/**--Time*/
	private String time;
	/**--Incoming Call Answer*/
	private String inCallAnswer;
	/**--Incoming Average Holding Time Per Call (sec)*/
	private String inAverageHoldingTPC;
	/**--Outgoing Call Answer*/
	private String outCallAnswer;
	/**--Outgoing Average Holding Time Per Call (sec)*/
	private String outAverageHoldingTPC;
	/**--Service Capacity*/
	private String serviceCapacity;
	/**--Capacity Needed*/
	private String capacityNeeded;
	/**Incoming Call Answer * Incoming Average Holding Time Per Call (sec)
	 * --Incoming total seconds in the hour*/
	private String inTotalHour;
	/**Outgoing Call Answer * Outgoing Average Holding Time Per Call (sec)
	 * --Outgoing total seconds in the hour*/
	private String outTotalHour;
	/**--Occupancy Hour(hour)*/
	private String occupancyHour;
	/**--Occupancy Rate(%)*/
	private String occupancyRate;
	
//	/**Incoming Line Occupancy (hour)*/
//	private String inLineOccupancy;
//	/**Incoming Line Occupancy Rate (%)*/
//	private String inLineOccupancyRate;
//	/**Incoming Call Attempt*/
//	private String inCallAttempt;
//	/**Incoming Call Delivered*/
//	private String inCallDelivered;
//	/**Incoming Call Rejected*/
//	private String inCallRejected;
//	/**Incoming Call Delivered Rate (%)*/
//	private String inCallDeliveredRate;
//	/**Incoming Call Answer Rate (%)*/
//	private String inCallAnswerRate;
//	/**Outgoing Line Occupancy (hour)*/
//	private String outLineOccupancy;
//	/**Outgoing Line Occupancy Rate (%)*/
//	private String outLineOccupancyRate;
//	/**Outgoing Call Attempt*/
//	private String outCallAttempt;
//	/**Outgoing Call Delivered*/
//	private String outCallDelivered;
//	/**Outgoing Call Rejected*/
//	private String outCallRejected;
//	/**Outgoing Call Delivered Rate (%)*/
//	private String outCallDeliveredRate;
//	/**Outgoing Call Answer Rate (%)*/
//	private String outCallAnswerRate;
//	/**Capacity Margin*/
//	private String capacityMargin;
	
	public String getOccupancyHour() {
		return occupancyHour;
	}
	public void setOccupancyHour(String occupancyHour) {
		this.occupancyHour = occupancyHour;
	}
	public String getOccupancyRate() {
		return occupancyRate;
	}
	public void setOccupancyRate(String occupancyRate) {
		this.occupancyRate = occupancyRate;
	}
	public int getExcelAllId() {
		return excelAllId;
	}
	public void setExcelAllId(int excelAllId) {
		this.excelAllId = excelAllId;
	}
	public int getExcelId() {
		return excelId;
	}
	public void setExcelId(int excelId) {
		this.excelId = excelId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getInCallAnswer() {
		return inCallAnswer;
	}
	public void setInCallAnswer(String inCallAnswer) {
		this.inCallAnswer = inCallAnswer;
	}
	public String getInAverageHoldingTPC() {
		return inAverageHoldingTPC;
	}
	public void setInAverageHoldingTPC(String inAverageHoldingTPC) {
		this.inAverageHoldingTPC = inAverageHoldingTPC;
	}
	public String getOutCallAnswer() {
		return outCallAnswer;
	}
	public void setOutCallAnswer(String outCallAnswer) {
		this.outCallAnswer = outCallAnswer;
	}
	public String getOutAverageHoldingTPC() {
		return outAverageHoldingTPC;
	}
	public void setOutAverageHoldingTPC(String outAverageHoldingTPC) {
		this.outAverageHoldingTPC = outAverageHoldingTPC;
	}
	public String getServiceCapacity() {
		return serviceCapacity;
	}
	public void setServiceCapacity(String serviceCapacity) {
		this.serviceCapacity = serviceCapacity;
	}
	public String getCapacityNeeded() {
		return capacityNeeded;
	}
	public void setCapacityNeeded(String capacityNeeded) {
		this.capacityNeeded = capacityNeeded;
	}
	public String getInTotalHour() {
		return inTotalHour;
	}
	public void setInTotalHour(String inTotalHour) {
		this.inTotalHour = inTotalHour;
	}
	public String getOutTotalHour() {
		return outTotalHour;
	}
	public void setOutTotalHour(String outTotalHour) {
		this.outTotalHour = outTotalHour;
	}
}