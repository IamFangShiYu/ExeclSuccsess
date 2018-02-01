package com.guigu.instructional.classinfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guigu.instructional.classinfo.service.SyllabusInfoService;
import com.guigu.instructional.po.SyllabusInfo;

@Controller
@RequestMapping("/classinfo/syllabusinfo/")
public class SyllabusInfoController {
	
	@Resource(name="syllabusInfoServiceImpl")
	private SyllabusInfoService syllabusInfoService;
	
	@RequestMapping("add.action")
	public String addSyllabusInfo(@Validated SyllabusInfo syllabusInfo, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			
			model.addAttribute("syllabusInfo", syllabusInfo);
			return "classinfo/syllabusinfo/syllabusinfo_add";
		}
		
		syllabusInfo.setSyllabusIsused("1");
		boolean result = syllabusInfoService.addSyllabusInfo(syllabusInfo);
		if (result) {
			model.addAttribute("info", "successful");
		} else {
			model.addAttribute("info", "fault");
		}
		return this.list(null, model);

	}

	@RequestMapping("list.action")
	public String list(SyllabusInfo syllabusInfo, Model model) {
		List<SyllabusInfo> list = syllabusInfoService.getSyllabusInfoList(syllabusInfo);
		model.addAttribute("list", list);
		return "classinfo/syllabusinfo/syllabusinfo_list";
	}


	@RequestMapping("load.action")
	public String loadUpate(Integer syllabusId, Model model) {
		SyllabusInfo syllabusInfo = syllabusInfoService.getSyllabusInfo(syllabusId);
		model.addAttribute("syllabusInfo", syllabusInfo);
		return "classinfo/syllabusinfo/syllabusinfo_update";
	}

	@RequestMapping("update.action")
	public String updateSyllabusInfo(@Validated SyllabusInfo syllabusInfo, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			
			model.addAttribute("syllabusInfo", syllabusInfo);
			return "classinfo/syllabusinfo/syllabusinfo_update";
		}
		boolean result = syllabusInfoService.updateSyllabusInfo(syllabusInfo);
		if (result) {
			model.addAttribute("info", "success");
		} else {
			model.addAttribute("info", "fault");
		}
		return this.list(null, model);
	}

	@RequestMapping("delete.action")
	public String deleteSyllabusInfo(SyllabusInfo syllabusInfo, Model model) {
		System.out.println(syllabusInfo.getSyllabusId());
		syllabusInfo.setSyllabusIsused("0");
		System.out.println(syllabusInfo.getSyllabusIsused());
		boolean result = syllabusInfoService.updateSyllabusInfo(syllabusInfo);
		if (result) {
			model.addAttribute("info", "success");
		} else {
			model.addAttribute("info", "fault");
		}
		return this.list(null, model);
	}
}
