package com.guigu.instructional.payment.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.naming.Binding;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guigu.instructional.payment.service.StudentPaymentService;
import com.guigu.instructional.po.RoleInfo;
import com.guigu.instructional.po.StaffInfo;
import com.guigu.instructional.po.StaffSalary;
import com.guigu.instructional.po.StudentInfo;
import com.guigu.instructional.po.StudentPayment;
import com.guigu.instructional.po.StudentPaymentCustom;
import com.guigu.instructional.student.service.StudentInfoService;
import com.guigu.instructional.system.service.StaffInfoService;


@Controller
@RequestMapping("/payment/studentpayment/")
public class StudentPaymentController {
	
	
	@Resource(name="studentInfoServiceImpl")
	private StudentInfoService  studentInfoService;
	
	@Resource(name="staffInfoServiceImpl")
	private StaffInfoService staffInfoService;
	
	
	@RequestMapping("loadchange.action")
    public String StudentInfo(Integer studentId,Model model) {
		StudentInfo studentInfo =studentInfoService.getStudentInfo(studentId);
        model.addAttribute("studentInfo", studentInfo);
        List<StudentInfo> list =studentInfoService.getStudentInfoList(null);
        model.addAttribute("studentlist", list);
        return "system/roleinfo/role_change";
    }
	
	@RequestMapping("excel.action")
	public void export(HttpServletResponse response) throws Exception {
		StudentPayment studentPayment=new StudentPayment();
		List<StudentPayment> list =studentPaymentService.getStudentPayment(studentPayment);			
		ExportExcel<StudentPayment> ee= new ExportExcel<StudentPayment>();
		
		String[] headers = { "payment_id", "student_id", "staff_id", "payment_situtation" , "payment_menthod", "payment_time", "payment_discount_amount", "payment_should_amount", "payment_real_amount", "payment_debt_amount", "payment_remark"};
		String fileName = "StudentPaymentTable";
		ee.exportExcel(headers,list,fileName,response);
	}

	@Resource(name="StudentPaymentServiceImpl")
	private StudentPaymentService studentPaymentService;
	
	   @RequestMapping("add.action")
	    public String addStudentPayment(@Validated StudentPayment studentPayment,BindingResult bindingResult,Model model) throws Exception {
	       
		   if(bindingResult.hasErrors()) {
			   List<ObjectError> allErrors=bindingResult.getAllErrors();
			   model.addAttribute("allErrors",allErrors);
			   
			   List<StudentInfo> list =studentInfoService.getStudentInfoList(null);
		        model.addAttribute("studentlist", list);
		        
		        StaffInfo staffInfo=new StaffInfo();
		        staffInfo.setRoleId(2);
		        List<StaffInfo> list2 =staffInfoService.getStaffInfoList(staffInfo);
		        model.addAttribute("stafflist", list2);
			   
			   model.addAttribute("studentPayment", studentPayment);
			   
			   return "payment/studentpayment/studentpayment_add";
			   
		   }
		   
		   
		  
		   
	       boolean result= studentPaymentService.addStudentPayment(studentPayment);
	       if(result) {
	           model.addAttribute("info","-AddSuccess");
	       }else {
	           model.addAttribute("info","-AddFailure");
	       }
	       
	       return this.list(null, model);
	       
	    }
	   
	   @RequestMapping("delete.action")
	    public String deleteStudentPayment(Integer paymentId,Model model) throws Exception {
	       
		   
		   
	       boolean result= studentPaymentService.deleteStudentPayment(paymentId);
	       if(result) {
	           model.addAttribute("info","-DeleteSuccess");
	       }else {
	           model.addAttribute("info","-DeleteFailure");
	       }
	       
	       return this.list(null, model);
	       
	    }
	   
	   @RequestMapping("update.action")
	    public String updateStudentPayment(@Validated StudentPayment studentPayment,BindingResult bindingResult,Model model) throws Exception {
		   
		   if(bindingResult.hasErrors()) {
			   List<ObjectError> allErrors=bindingResult.getAllErrors();
			   model.addAttribute("allErrors",allErrors);
			   
			   List<StudentInfo> list =studentInfoService.getStudentInfoList(null);
		        model.addAttribute("studentlist", list);
		        
		        StaffInfo staffInfo=new StaffInfo();
		        staffInfo.setRoleId(2);
		        List<StaffInfo> list2 =staffInfoService.getStaffInfoList(staffInfo);
		        model.addAttribute("stafflist", list2);
			   
			   model.addAttribute("studentPayment", studentPayment);

		        return "payment/studentpayment/studentpayment_update";
			   
		   }
	       
	       boolean result= studentPaymentService.updateStudentPayment(studentPayment);
	       if(result) {
	           model.addAttribute("info","-UpdateSuccess");
	       }else {
	           model.addAttribute("info","-UpdateFailure");
	       }
	       
	       return this.list(null, model);
	       
	    }
	   
	   @RequestMapping("load.action")
	    public String loadUpate(Integer paymentId,Model model) {
	      
	        List<StudentInfo> list =studentInfoService.getStudentInfoList(null);
	        model.addAttribute("studentlist", list);
	        
	        StudentPayment studentPayment = studentPaymentService.getStudentPayment(paymentId);
	        model.addAttribute("studentPayment", studentPayment);
	        
	        StaffInfo staffInfo=new StaffInfo();
	        staffInfo.setRoleId(2);
	        
	        List<StaffInfo> list2 =staffInfoService.getStaffInfoList(staffInfo);
	        model.addAttribute("stafflist", list2);
	
	        
	        return "payment/studentpayment/studentpayment_update";
	    }
	   
	   @RequestMapping("addload.action")
	    public String addloadUpate(Integer paymentId,Model model) {
	      
	    
	        StudentPayment studentPayment = studentPaymentService.getStudentPayment(paymentId);
	        model.addAttribute("studentPayment", studentPayment);
	        
	        List<StudentInfo> list =studentInfoService.getStudentInfoList(null);
	        model.addAttribute("studentlist", list);
	        
	        StaffInfo staffInfo=new StaffInfo();
	        staffInfo.setRoleId(2);
	        List<StaffInfo> list2 =staffInfoService.getStaffInfoList(staffInfo);
	        model.addAttribute("stafflist", list2);
	    
	        return "payment/studentpayment/studentpayment_add";
	    }
	   
	   @RequestMapping("list.action")
	    public String list(StudentPaymentCustom studentPaymentCustom,Model model) throws Exception {
	        List<StudentPaymentCustom> list =studentPaymentService.findStudentPaymentList(studentPaymentCustom);
	        
	        for (StudentPaymentCustom studentPaymentCustom2 : list) {
	        	if(studentPaymentCustom2.getPaymentMenthod()==1) {
	        		studentPaymentCustom2.setStateName("�ֽ�");
		        }
		        if(studentPaymentCustom2.getPaymentMenthod()==2) {
		        	studentPaymentCustom2.setStateName("����");
		        }
		        if(studentPaymentCustom2.getPaymentMenthod()==3) {
		        	studentPaymentCustom2.setStateName("֧����");
		        }
			}
	       
	        
	        model.addAttribute("list", list);        
	        return "payment/studentpayment/studentpayment_list";
	    }

}
