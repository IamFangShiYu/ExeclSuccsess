package com.guigu.instructional.po;

public class StaffSalaryCustom extends StaffSalary{

	private String staffName;
	private String staStaffName;
	
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	@Override
	public String toString() {
		return "StaffSalaryCustom [staffName=" + staffName + ", staStaffName=" + staStaffName + "]";
	}
	public String getStaStaffName() {
		return staStaffName;
	}
	public void setStaStaffName(String staStaffName) {
		this.staStaffName = staStaffName;
	}
	public StaffSalaryCustom() {
		super();
	}
	public StaffSalaryCustom(String staffName, String staStaffName) {
		super();
		this.staffName = staffName;
		this.staStaffName = staStaffName;
	}
}
