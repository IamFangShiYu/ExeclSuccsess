package com.guigu.instructional.system.mapper;

import java.util.List;

import com.guigu.instructional.po.StaffInfo;
import com.guigu.instructional.po.StaffTeachers;


public interface StaffTeachersMapper {

	public List<StaffInfo> findStaffTeachers(StaffInfo staffInfo)throws Exception;
	
}
