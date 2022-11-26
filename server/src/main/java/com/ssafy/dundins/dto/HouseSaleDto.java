package com.ssafy.dundins.dto;

public class HouseSaleDto {
	private int house_sale_id;
	private String aptCode;
	private String title;
	private String content;
	private String dealAmount;
	private String floor;
	private String area;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private int room;
	private String userId;

	public int getHouse_sale_id() {
		return house_sale_id;
	}

	public void setHouse_sale_id(int house_sale_id) {
		this.house_sale_id = house_sale_id;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "HouseSaleDto [houseSaleId=" + house_sale_id + ", aptCode=" + aptCode + ", title=" + title + ", content="
				+ content + ", dealAmount=" + dealAmount + ", floor=" + floor + ", area=" + area + ", dealYear="
				+ dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", room=" + room + ", userId="
				+ userId + "]";
	}
}
