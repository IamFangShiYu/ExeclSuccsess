package com.guigu.instructional.payment.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guigu.instructional.payment.service.StaffSalaryService;
import com.guigu.instructional.po.StaffInfo;
import com.guigu.instructional.po.StaffSalary;
import com.guigu.instructional.po.StaffSalaryCustom;
import com.guigu.instructional.po.StudentInfo;
import com.guigu.instructional.po.StudentPayment;
import com.guigu.instructional.system.service.StaffInfoService;

@Controller
@RequestMapping("/payment/staffsalary/")
public class StaffSalaryController {

	@Resource(name="StaffSalaryServiceImpl")
	private StaffSalaryService staffSalaryService;
	
	@Resource(name="staffInfoServiceImpl")
	private StaffInfoService staffInfoService;
	
//	@RequestMapping("list.action")
//    public String list(StaffSalaryCustom staffSalaryCustom,Model model) throws Exception {
//        List<StaffSalaryCustom> list =staffSalaryService.findStaffSalaryList(staffSalaryCustom);
//        model.addAttribute("list", list);
//        
//        return "payment/staffsalary/staffsalary_list";
//    }
	
	
	@RequestMapping("excel.action")
	public void export(HttpServletResponse response) throws Exception {
		//需要导入alibaba的fastjson包
		StaffSalary staffSalary=new StaffSalary();
		List<StaffSalary> list =staffSalaryService.getStaffSalary(staffSalary);
		
		
		
		ExportExcel<StaffSalary> ee= new ExportExcel<StaffSalary>();
		String[] headers = { "工资单号", "领取人员工号", "财务人员工号", "本月工资" , "扣除工资", "实际发放工资", "是否发放", "发放时间", "备注信息"};
		String fileName = "员工工资单统计表";
		ee.exportExcel(headers,list,fileName,response);
	}

	 @RequestMapping("add.action")
	    public String addStaffSalary(@Validated StaffSalary staffSalary,BindingResult bindingResult,Model model) throws Exception {
	       
		 
		  if(bindingResult.hasErrors()) {
			   List<ObjectError> allErrors=bindingResult.getAllErrors();
			   model.addAttribute("allErrors",allErrors);
			   
			     
		        List<StaffInfo> list3 =staffInfoService.getStaffInfoList(null);
		        model.addAttribute("stafflist", list3);
		        
		        StaffInfo staffInfo=new StaffInfo();
		        staffInfo.setRoleId(2);
		        
		        List<StaffInfo> list2 =staffInfoService.getStaffInfoList(staffInfo);
		        model.addAttribute("stastafflist", list2);
		        
		        model.addAttribute("staffSalary", staffSalary);
		        return "payment/staffsalary/staffsalary_add";
			   
		   }
		 
		 
	       boolean result= staffSalaryService.addStaffSalary(staffSalary);
	       if(result) {
	           model.addAttribute("info","-AddSuccess");
	       }else {
	           model.addAttribute("info","-AddFailure");
	       }
	       
	       return this.list(null, model);
	       
	    }
	   
	   @RequestMapping("delete.action")
	    public String deleteStaffSalary(Integer staffSalaryId,Model model) throws Exception {
	       
	       boolean result= staffSalaryService.deleteStaffSalary(staffSalaryId);
	       if(result) {
	           model.addAttribute("info","-DeleteSuccess");
	       }else {
	           model.addAttribute("info","-DeleteFailure");
	       }
	       
	       return this.list(null, model);
	       
	    }
	   
	   @RequestMapping("update.action")
	    public String updateStaffSalary(@Validated StaffSalary staffSalary,BindingResult bindingResult,Model model) throws Exception {
		   
			  if(bindingResult.hasErrors()) {
				  List<ObjectError> allErrors=bindingResult.getAllErrors();
				   model.addAttribute("allErrors",allErrors);
				   
				     
			        List<StaffInfo> list3 =staffInfoService.getStaffInfoList(null);
			        model.addAttribute("stafflist", list3);
			        
			        StaffInfo staffInfo=new StaffInfo();
			        staffInfo.setRoleId(2);
			        
			        List<StaffInfo> list2 =staffInfoService.getStaffInfoList(staffInfo);
			        model.addAttribute("stastafflist", list2);
			        
			        model.addAttribute("staffSalary", staffSalary);
			        return "payment/staffsalary/staffsalary_update";
				   
			   }
	       
	       boolean result= staffSalaryService.updateStaffSalary(staffSalary);
	       if(result) {
	           model.addAttribute("info","-UpdateSuccess");
	       }else {
	           model.addAttribute("info","-UpdateFailure");
	       }
	       
	       return this.list(null, model);
	       
	    }
	   
	   @RequestMapping("load.action")
	    public String loadUpate(Integer staffSalaryId,Model model) {
	       StaffSalary staffSalary = staffSalaryService.getStaffSalary(staffSalaryId);
	        model.addAttribute("staffSalary", staffSalary);
	           
	        List<StaffInfo> list3 =staffInfoService.getStaffInfoList(null);
	        model.addAttribute("stafflist", list3);
	        
	        StaffInfo staffInfo=new StaffInfo();
	        staffInfo.setRoleId(2);
	        List<StaffInfo> list2 =staffInfoService.getStaffInfoList(staffInfo);
	        model.addAttribute("stastafflist", list2);
	        
	        return "payment/staffsalary/staffsalary_update";
	    }
	   
	   @RequestMapping("addload.action")
	    public String addloadUpate(Integer staffSalaryId,Model model) {
	       StaffSalary staffSalary = staffSalaryService.getStaffSalary(staffSalaryId);
	        model.addAttribute("staffSalary", staffSalary);
	           
	        List<StaffInfo> list3 =staffInfoService.getStaffInfoList(null);
	        model.addAttribute("stafflist", list3);
	        
	        StaffInfo staffInfo=new StaffInfo();
	        staffInfo.setRoleId(2);
	        List<StaffInfo> list2 =staffInfoService.getStaffInfoList(staffInfo);
	        model.addAttribute("stastafflist", list2);
	        
	        return "payment/staffsalary/staffsalary_add";
	    }
	   
	   
	   @RequestMapping("list.action")
	    public String list(StaffSalaryCustom staffSalaryCustom,Model model) throws Exception {
	        List<StaffSalaryCustom> list =staffSalaryService.findStaffSalaryList(staffSalaryCustom);
	        model.addAttribute("list", list);
	        
	        return "payment/staffsalary/staffsalary_list";
	    }
	
	
}
