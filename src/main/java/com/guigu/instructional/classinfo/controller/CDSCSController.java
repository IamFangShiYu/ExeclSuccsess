package com.guigu.instructional.classinfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guigu.instructional.classinfo.service.CDSCSService;
import com.guigu.instructional.classinfo.service.ClassroomInfoService;
import com.guigu.instructional.classinfo.service.DisciplineInfoService;
import com.guigu.instructional.classinfo.service.SyllabusInfoService;
import com.guigu.instructional.po.CDSCS;
import com.guigu.instructional.po.ClassInfo;
import com.guigu.instructional.po.ClassroomInfo;
import com.guigu.instructional.po.DisciplineInfo;
import com.guigu.instructional.po.StaffInfo;
import com.guigu.instructional.po.StaffTeachers;
import com.guigu.instructional.po.SyllabusInfo;
import com.guigu.instructional.system.service.StaffInfoService;
import com.guigu.instructional.system.service.StaffTeacherService;

@Controller
@RequestMapping("/classinfo/classinfo/")
public class CDSCSController {

	@Resource(name = "cDSCSServiceImpl")
	private CDSCSService cDSCSService;

	@Resource(name = "disciplineInfoServiceImpl")
	private DisciplineInfoService disciplineInfoService;

	@Resource(name = "classroomInfoServiceImpl")
	private ClassroomInfoService classroomInfoService;

	@Resource(name = "syllabusInfoServiceImpl")
	private SyllabusInfoService syllabusInfoService;

	@Resource(name = "staffInfoServiceImpl")
	private StaffInfoService staffInfoService;

	@RequestMapping("add.action")
	public String addClassInfo(@Validated ClassInfo classInfo,BindingResult bindingResult, Model model) throws Exception {
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			List<DisciplineInfo> disciplineInfoList = disciplineInfoService.getDisciplineInfoList(null);
			List<ClassroomInfo> classroomInfoList = classroomInfoService.getClassroomInfoList(null);
			List<SyllabusInfo> syllabusInfoList = syllabusInfoService.getSyllabusInfoList(null);
			List<StaffInfo> teacherList = staffInfoService.getteacherList(null);
			model.addAttribute("disciplineInfoList", disciplineInfoList);
			model.addAttribute("classroomInfoList", classroomInfoList);
			model.addAttribute("syllabusInfoList", syllabusInfoList);
			model.addAttribute("teacherList", teacherList);
			model.addAttribute("classInfo", classInfo);
			return "classinfo/classinfo/classinfo_add";
			
		}
		
		classInfo.setClassIsused("1");
		classInfo.setClassState("1");
		boolean result = cDSCSService.addCDSCS(classInfo);
		if (result) {
			model.addAttribute("info", "添加成功");
		} else {
			model.addAttribute("info", "添加失败");
		}
		return this.listCDSCS(null, model);

	}

	@RequestMapping("loadCDSCS.action")
	public String loadCSSD(@Validated Integer classId, Model model) throws Exception {
		
		List<DisciplineInfo> disciplineInfoList = disciplineInfoService.getDisciplineInfoList(null);
		List<ClassroomInfo> classroomInfoList = classroomInfoService.getClassroomInfoList(null);
		List<SyllabusInfo> syllabusInfoList = syllabusInfoService.getSyllabusInfoList(null);
		List<StaffInfo> teacherList = staffInfoService.getteacherList(null);
		model.addAttribute("disciplineInfoList", disciplineInfoList);
		model.addAttribute("classroomInfoList", classroomInfoList);
		model.addAttribute("syllabusInfoList", syllabusInfoList);
		model.addAttribute("teacherList", teacherList);
		return "classinfo/classinfo/classinfo_add";
	}

	@RequestMapping("loadCDSCS1.action")
	public String loadCSSD1(@Validated ClassInfo classInfo,BindingResult bindingResult,Integer classId, Model model) throws Exception {
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			List<DisciplineInfo> disciplineInfoList = disciplineInfoService.getDisciplineInfoList(null);
			List<ClassroomInfo> classroomInfoList = classroomInfoService.getClassroomInfoList(null);
			List<SyllabusInfo> syllabusInfoList = syllabusInfoService.getSyllabusInfoList(null);
			List<StaffInfo> teacherList = staffInfoService.getteacherList(null);
			model.addAttribute("disciplineInfoList", disciplineInfoList);
			model.addAttribute("classroomInfoList", classroomInfoList);
			model.addAttribute("syllabusInfoList", syllabusInfoList);
			model.addAttribute("teacherList", teacherList);
			model.addAttribute("classInfo", classInfo);
			return "classinfo/classinfo/classinfo_update";
			
		}
		classInfo = cDSCSService.getClassInfo(classId);
		List<DisciplineInfo> disciplineInfoList = disciplineInfoService.getDisciplineInfoList(null);
		List<ClassroomInfo> classroomInfoList = classroomInfoService.getClassroomInfoList(null);
		List<SyllabusInfo> syllabusInfoList = syllabusInfoService.getSyllabusInfoList(null);
		List<StaffInfo> teacherList = staffInfoService.getteacherList(null);
		
		model.addAttribute("classInfo", classInfo);
		model.addAttribute("disciplineInfoList", disciplineInfoList);
		model.addAttribute("classroomInfoList", classroomInfoList);
		model.addAttribute("syllabusInfoList", syllabusInfoList);
		model.addAttribute("teacherList", teacherList);
		return "classinfo/classinfo/classinfo_update";
	}

	@RequestMapping("update.action")
	public String updateClassInfo(@Validated ClassInfo classInfo,BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			
			model.addAttribute("classInfo", classInfo);
			return "classinfo/classinfo/classinfo_update";
			
		}
		
		boolean result = cDSCSService.updateCDSCS(classInfo);
		if (result) {
			model.addAttribute("info", "变更成功");
		} else {
			model.addAttribute("info", "变更失败");
		}
		return this.listCDSCS(null, model);
	}

	@RequestMapping("listCDSCS.action")
	public String listCDSCS(ClassInfo classInfo, Model model) {
		List<CDSCS> list = cDSCSService.getCDSCSList(classInfo);
		model.addAttribute("list", list);
		return "classinfo/classinfo/classinfo_list";
	}

	@RequestMapping("delete.action")
	public String deleteClassInfo(ClassInfo classInfo, Model model) {
		classInfo.setClassIsused("0");
		classInfo.setClassState("0");
		boolean reuslt = cDSCSService.updateCDSCS(classInfo);
		if (reuslt) {
			model.addAttribute("info", "删除成功");
		} else {
			model.addAttribute("info", "删除失败");
		}
		return this.listCDSCS(null, model);
	}
}
