package com.guigu.instructional.system.service;

import java.util.List;

import com.guigu.instructional.po.StaffInfo;
import com.guigu.instructional.po.StaffTeachers;

public interface StaffTeacherService {
	public List<StaffInfo> findStaffTeachers(StaffInfo staffInfo)throws Exception;

	 public boolean addStaff(StaffInfo staffInfo);
	    
	    public boolean updateStaff(StaffInfo staffInfo);

	    public StaffInfo getStaffInfo(Integer staffId);
	    
	    

}
