package com.springrecipes.database.beans;

public class Vehicle {
private String vehicleNo,color;
private int wheel,seat;

public Vehicle() {}
public Vehicle(String vehicleNo, String color, int wheel, int seat) {
	super();
	this.vehicleNo = vehicleNo;
	this.color = color;
	this.wheel = wheel;
	this.seat = seat;
}
public String getVehicleNo() {
	return vehicleNo;
}
public void setVehicleNo(String vehicleNo) {
	this.vehicleNo = vehicleNo;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public int getWheel() {
	return wheel;
}
public void setWheel(int wheel) {
	this.wheel = wheel;
}
public int getSeat() {
	return seat;
}
public void setSeat(int seat) {
	this.seat = seat;
}
}
