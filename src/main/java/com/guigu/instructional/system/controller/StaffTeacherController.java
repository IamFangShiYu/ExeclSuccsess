package com.guigu.instructional.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guigu.instructional.po.StaffInfo;
import com.guigu.instructional.po.StaffTeachers;
import com.guigu.instructional.system.service.StaffInfoService;
import com.guigu.instructional.system.service.StaffTeacherService;

@Controller
@RequestMapping("/system/staffteacher/")

public class StaffTeacherController {

	@Resource(name ="staffTeacherServiceImpl")
	private StaffTeacherService staffTeacherService;

	@Resource(name = "staffInfoServiceImpl")
	private StaffInfoService staffInfoService;
	
	@RequestMapping("teacherlist.action")
	public String teacherlist(StaffInfo staffInfo, Model model) throws Exception {
		List<StaffInfo> list = staffInfoService.getteacherList(staffInfo);
		model.addAttribute("list", list);
		return "classinfo/teacher/teacher_list";
	}
	

	@RequestMapping("show.action")
	public String showStaffInfo(Integer staffId, Model model) {
		StaffInfo staffInfo = staffInfoService.getStaffInfo(staffId);
		model.addAttribute("staffInfo", staffInfo);
		return "classinfo/teacher/teacher_show";

	}

	@RequestMapping("load.action")
	public String loadUpate(Integer staffId, Model model) {
		StaffInfo staffInfo = staffInfoService.getStaffInfo(staffId);
		model.addAttribute("staffInfo", staffInfo);
		return "classinfo/teacher/teacher_update";
	}
	
	@RequestMapping("teacherload.action")
	public String teacherloadUpate(Integer staffId, Model model) {
		StaffInfo staffInfo = staffInfoService.getStaffInfo(staffId);
		model.addAttribute("staffInfo", staffInfo);
		return "classinfo/teacher/teacher_update";
	}

	@RequestMapping("add.action")
	public String addStaffInfo(StaffInfo staffInfo, Model model) throws Exception {
		staffInfo.setStaffState("1");
		staffInfo.setRoleId(3);
		boolean result = staffInfoService.addStaff(staffInfo);
		if (result) {
			model.addAttribute("info", "-addsuccess");
		} else {
			model.addAttribute("info", "-adddefult");
		}
		return this.teacherlist(null, model);

	}
	
	@RequestMapping("update.action")
	public String updateStaffInfo(StaffInfo staffInfo, Model model) throws Exception {
		boolean result = staffInfoService.updateStaff(staffInfo);
		if (result) {
			model.addAttribute("info", "-updatesuccess");
		} else {
			model.addAttribute("info", "-updatedefult");
		}
		return this.teacherlist(null, model);
	}
	
	@RequestMapping("delete.action")
	public String deleteStaffInfo(StaffInfo staffInfo, Model model) throws Exception {

		staffInfo.setStaffState("0");

		boolean result = staffInfoService.updateStaff(staffInfo);
		if (result) {
			model.addAttribute("info", "-deletesuccess");
		} else {
			model.addAttribute("info", "-deletedefult");
		}
		return this.teacherlist(null, model);
	}
	
	
	
	
}
