package com.guigu.instructional.student.controller;

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
import com.guigu.instructional.po.EvaluationInfo;
import com.guigu.instructional.po.EvaluationInfoCustom;
import com.guigu.instructional.po.StaffInfo;
import com.guigu.instructional.po.StudentInfo;
import com.guigu.instructional.student.service.EvaluationInfoCustomService;
import com.guigu.instructional.student.service.EvaluationInfoService;
import com.guigu.instructional.student.service.StudentInfoService;
import com.guigu.instructional.system.service.StaffInfoService;

@Controller
@RequestMapping("/student/evaluationinfo/")
public class EvaluationInfoController {
	
	@Resource(name = "evaluationInfoServiceImpl")
	private EvaluationInfoService evaluationInfoService;
	
	@Resource(name = "evaluationInfoCustomServiceImpl")
	private EvaluationInfoCustomService evaluationInfoCustomService;
	
	@Resource(name = "studentInfoServiceImpl")
	private StudentInfoService studentInfoService;
	
	@Resource(name = "staffInfoServiceImpl")
	private StaffInfoService staffInfoService;
	
	@Resource(name = "disciplineInfoServiceImpl")
	private DisciplineInfoService disciplineInfoService;
	
	@RequestMapping("add.action")
	public String addEvaluationInfo(@Validated EvaluationInfo evaluationInfo, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			
			model.addAttribute("evaluationInfo", evaluationInfo);
			//��ѯ���е�ѧԱ��Ϣ
			List<StudentInfo> list =studentInfoService.getStudentInfoList(null);
			model.addAttribute("studentInfoList", list);
			//��ѯ���е�ְ����Ϣ
			List<StaffInfo> listOfStaff = staffInfoService.getStaffInfoList(null);
			model.addAttribute("staffInfoList", listOfStaff);
			//��ѯ���еĿ�Ŀ��Ϣ
			List<DisciplineInfo> listOfDisciplineInfo = disciplineInfoService.getDisciplineInfoList(null);
			model.addAttribute("disciplineInfoList", listOfDisciplineInfo);
			
			return "student/evaluationinfo/evaluationinfo_add";
		}
		boolean result = evaluationInfoService.addEvaluation(evaluationInfo);
		if(result) {
			model.addAttribute("info","��ӳɹ�");
		}else {
			model.addAttribute("info","���ʧ��");
		}
		return this.list(null, model);
	}

	@RequestMapping("delete.action")
    public String deletestudentInfo(Integer evaluationId, Model model) {
        
        boolean result =evaluationInfoService.deleteEvaluationInfo(evaluationId);
        if(result) {
            model.addAttribute("info", "ɾ���ɹ�");
        }else {
            model.addAttribute("info", "ɾ��ʧ��");
        }
        return this.list(null, model);
    }
	
	@RequestMapping("update.action")
    public String updatestudentInfo(@Validated EvaluationInfo evaluationInfo, BindingResult bindingResult, Model model) {
        
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			
			model.addAttribute("evaluationInfo", evaluationInfo);
			//��ѯ���е�ѧԱ��Ϣ
			List<StudentInfo> list =studentInfoService.getStudentInfoList(null);
			model.addAttribute("studentInfoList", list);
			//��ѯ���е�ְ����Ϣ
			List<StaffInfo> listOfStaff = staffInfoService.getStaffInfoList(null);
			model.addAttribute("staffInfoList", listOfStaff);
			//��ѯ���еĿ�Ŀ��Ϣ
			List<DisciplineInfo> listOfDisciplineInfo = disciplineInfoService.getDisciplineInfoList(null);
			model.addAttribute("disciplineInfoList", listOfDisciplineInfo);
			
			return "student/evaluationinfo/evaluationinfo_update";
		}
		
		boolean result=evaluationInfoService.updateEvaluation(evaluationInfo);
        if(result) {
            model.addAttribute("info", "�޸ĳɹ�");
        }else {
            model.addAttribute("info", "�޸�ʧ��");
        }
        return this.list(null, model);
    }
	
	@RequestMapping("list.action")
	private String list(EvaluationInfo evaluationInfo, Model model) {
		
		List<EvaluationInfoCustom> list = evaluationInfoCustomService.getEvaluationInfoCustomList(evaluationInfo);
		model.addAttribute("list",list);
		return "student/evaluationinfo/evaluationinfo_list";
	}
	
	@RequestMapping("show.action")
    public String showStudentInfo(Integer evaluationId, Model model) {
		EvaluationInfoCustom evaluationInfoCustom = evaluationInfoCustomService.getEvaluationInfoCustom(evaluationId);
        model.addAttribute("evaluationInfoCustom", evaluationInfoCustom);
        return "student/evaluationinfo/evaluationinfo_show";
        
    }
    
	@RequestMapping("load.action")
	public String loadUpate(Integer evaluationId, Model model) {
		
		EvaluationInfo evaluationInfo = evaluationInfoService.getEvaluationInfo(evaluationId);
		model.addAttribute("evaluationInfo", evaluationInfo);
		//��ѯ���е�ѧԱ��Ϣ
		List<StudentInfo> list =studentInfoService.getStudentInfoList(null);
		model.addAttribute("studentInfoList", list);
		//��ѯ���е�ְ����Ϣ
		List<StaffInfo> listOfStaff = staffInfoService.getStaffInfoList(null);
		model.addAttribute("staffInfoList", listOfStaff);
		//��ѯ���еĿ�Ŀ��Ϣ
		List<DisciplineInfo> listOfDisciplineInfo = disciplineInfoService.getDisciplineInfoList(null);
		model.addAttribute("disciplineInfoList", listOfDisciplineInfo);
		
		return "student/evaluationinfo/evaluationinfo_update";
	}
	
	@RequestMapping("addload.action")
	public String listaddEvaluationInfoInfo(Integer evaluationId, Model model) {
		
		EvaluationInfo evaluationInfo = evaluationInfoService.getEvaluationInfo(evaluationId);
		model.addAttribute("evaluationInfo", evaluationInfo);
		//��ѯ���е�ѧԱ��Ϣ
		List<StudentInfo> studentInfolist =studentInfoService.getStudentInfoList(null);
		model.addAttribute("studentInfoList", studentInfolist);
		//��ѯ���е�ְ����Ϣ
		List<StaffInfo> listOfStaff = staffInfoService.getStaffInfoList(null);
		model.addAttribute("staffInfoList", listOfStaff);
		//��ѯ���е�ѧ����Ϣ
		List<DisciplineInfo> listOfDisciplineInfo = disciplineInfoService.getDisciplineInfoList(null);
		model.addAttribute("disciplineInfoList", listOfDisciplineInfo);
		
		return "student/evaluationinfo/evaluationinfo_add";
	} 
    
    

}
