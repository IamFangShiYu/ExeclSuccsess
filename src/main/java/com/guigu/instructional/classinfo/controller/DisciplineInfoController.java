package com.guigu.instructional.classinfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guigu.instructional.classinfo.service.DisciplineInfoService;
import com.guigu.instructional.po.DisciplineInfo;

@Controller
@RequestMapping("/classinfo/disciplineinfo/")
public class DisciplineInfoController {

	@Resource(name = "disciplineInfoServiceImpl")
	private DisciplineInfoService disciplineInfoService;

	@RequestMapping("add.action")
	public String adddisciplineInfo(@Validated DisciplineInfo disciplineInfo, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			
			model.addAttribute("disciplineInfo", disciplineInfo);
			return "classinfo/disciplineinfo/disciplineinfo_add";
		}
		disciplineInfo.setDisciplineIsused("1");
		boolean result = disciplineInfoService.addDisciplineInfo(disciplineInfo);
		if (result) {
			model.addAttribute("info", "success");
		} else {
			model.addAttribute("info", "fault");
		}
		return this.list(null, model);

	}

	@RequestMapping("list.action")
	public String list(DisciplineInfo disciplineInfo, Model model) {
		List<DisciplineInfo> list = disciplineInfoService.getDisciplineInfoList(disciplineInfo);
		model.addAttribute("list", list);
		return "classinfo/disciplineinfo/disciplineinfo_list";
	}

	@RequestMapping("show.action")
	public String showdisciplineInfo(Integer disciplineId, Model model) {
		DisciplineInfo disciplineInfo = disciplineInfoService.getDisciplineInfo(disciplineId);
		model.addAttribute("disciplineInfo", disciplineInfo);
		return "classinfo/disciplineinfo/disciplineinfo_show";

	}

	@RequestMapping("load.action")
	public String loadUpate(Integer disciplineId, Model model) {
		DisciplineInfo disciplineInfo = disciplineInfoService.getDisciplineInfo(disciplineId);
		model.addAttribute("disciplineInfo", disciplineInfo);
		return "classinfo/disciplineinfo/disciplineinfo_update";
	}


	@RequestMapping("update.action")
	public String updatedisciplineInfo(@Validated DisciplineInfo disciplineInfo, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			
			model.addAttribute("studentInfo", disciplineInfo);
			return "classinfo/disciplineinfo/disciplineinfo_update";
		}
		
		boolean result = disciplineInfoService.updateDisciplineInfo(disciplineInfo);
		if (result) {
			model.addAttribute("info", "success");
		} else {
			model.addAttribute("info", "fault");
		}
		return this.list(null, model);
	}

	@RequestMapping("delete.action")
	public String deletedisciplineInfo(DisciplineInfo disciplineInfo, Model model) {

		disciplineInfo.setDisciplineIsused("0");

		boolean result = disciplineInfoService.updateDisciplineInfo(disciplineInfo);
		if (result) {
			model.addAttribute("info", "success");
		} else {
			model.addAttribute("info", "fault");
		}
		return this.list(null, model);
	}

}
