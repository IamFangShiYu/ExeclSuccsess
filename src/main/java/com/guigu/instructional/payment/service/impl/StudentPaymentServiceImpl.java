package com.guigu.instructional.payment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guigu.instructional.payment.mapper.StudentPaymentCustomMapper;
import com.guigu.instructional.payment.mapper.StudentPaymentMapper;
import com.guigu.instructional.payment.service.StudentPaymentService;
import com.guigu.instructional.po.StaffInfo;
import com.guigu.instructional.po.StaffInfoExample;
import com.guigu.instructional.po.StudentPayment;
import com.guigu.instructional.po.StudentPaymentCustom;
import com.guigu.instructional.po.StudentPaymentExample;
import com.guigu.instructional.po.StudentPaymentExample.Criteria;


@Service("StudentPaymentServiceImpl")
public class StudentPaymentServiceImpl implements StudentPaymentService {

	@Resource(name = "studentPaymentMapper")
	private StudentPaymentMapper studentPaymentMapper;
	
	@Resource(name="studentPaymentCustomMapper")
	private StudentPaymentCustomMapper studentPaymentCustomMapper;
	

	@Override
	public boolean addStudentPayment(StudentPayment studentPayment) {
		try {
			int i = studentPaymentMapper.insertSelective(studentPayment);
			if (i > 0) {
				return true;
			}
		} catch (Exception e) {

		}

		return false;
	}

	@Override
	public boolean updateStudentPayment(StudentPayment studentPayment) {
		  try {
	            int i = studentPaymentMapper.updateByPrimaryKey(studentPayment);
	            if (i > 0) {
	                return true;
	            }
	        } catch (Exception e) {

	        }

	        return false;
	}
	
	

	@Override
	public List<StudentPayment> getStudentPayment(StudentPayment studentPayment) {

		StudentPaymentExample studentPaymentExample = new StudentPaymentExample();
		
		Criteria criteria = studentPaymentExample.createCriteria();
		if (studentPayment != null) {
			
			if (studentPayment.getPaymentId()!= null) {
				criteria.andPaymentIdEqualTo(studentPayment.getPaymentId());
			}
			
			if (studentPayment.getStudentId()!= null) {
				criteria.andStudentIdEqualTo(studentPayment.getStudentId());
			}
			
			if (studentPayment.getStaffId()!= null) {
				criteria.andStaffIdEqualTo(studentPayment.getStaffId());
			}
		

		}else {
			
			return studentPaymentMapper.selectByExample(null);
		}

		return studentPaymentMapper.selectByExample(studentPaymentExample);
	}
	
	
	

	@Override
	public StudentPayment getStudentPayment(Integer paymentId) {
		return studentPaymentMapper.selectByPrimaryKey(paymentId);
	}

	@Override
	public boolean deleteStudentPayment(Integer paymentId) {
		try {
			int i = studentPaymentMapper.deleteByPrimaryKey(paymentId);
			if (i > 0) {
				return true;
			}
		} catch (Exception e) {

		}

		return false;
	}

	@Override
	public List<StudentPaymentCustom> findStudentPaymentList(StudentPaymentCustom studentPaymentCustom)
			throws Exception {
		if(studentPaymentCustom==null) {
			return studentPaymentCustomMapper.findStudentPaymentList(null);
		}else {
			return studentPaymentCustomMapper.findStudentPaymentList(studentPaymentCustom);
		}
		
	}

}
