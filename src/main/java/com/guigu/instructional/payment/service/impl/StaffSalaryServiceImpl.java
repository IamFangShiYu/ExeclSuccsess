package com.guigu.instructional.payment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guigu.instructional.payment.mapper.StaffSalaryCustomMapper;
import com.guigu.instructional.payment.mapper.StaffSalaryMapper;
import com.guigu.instructional.payment.service.StaffSalaryService;
import com.guigu.instructional.po.StaffSalary;
import com.guigu.instructional.po.StaffSalaryCustom;
import com.guigu.instructional.po.StaffSalaryExample;
import com.guigu.instructional.po.StaffSalaryExample.Criteria;

@Service("StaffSalaryServiceImpl")
public class StaffSalaryServiceImpl implements StaffSalaryService {

	@Resource(name = "staffSalaryMapper")
	private StaffSalaryMapper staffSalaryMapper;

	@Resource(name = "staffSalaryCustomMapper")
	private StaffSalaryCustomMapper staffSalaryCustomMapper;
	
	@Override
	public boolean addStaffSalary(StaffSalary staffSalary) {
		try {
			int i = staffSalaryMapper.insertSelective(staffSalary);
			if (i > 0) {
				return true;
			}
		} catch (Exception e) {

		}

		return false;
	}

	@Override
	public boolean updateStaffSalary(StaffSalary staffSalary) {
		try {
			int i = staffSalaryMapper.updateByPrimaryKey(staffSalary);
			if (i > 0) {
				return true;
			}
		} catch (Exception e) {

		}

		return false;
	}

	@Override
	public List<StaffSalary> getStaffSalary(StaffSalary staffSalary) {
		StaffSalaryExample staffSalaryExample = new StaffSalaryExample();

		Criteria criteria = staffSalaryExample.createCriteria();
		if (staffSalary != null) {

			if (staffSalary.getStaffSalaryId() != null) {
				criteria.andStaffSalaryIdEqualTo(staffSalary.getStaffSalaryId());
			}

			if (staffSalary.getStaffId() != null) {
				criteria.andStaffIdEqualTo(staffSalary.getStaffId());
			}

			if (staffSalary.getStaStaffId()!= null) {
				criteria.andStaStaffIdEqualTo(staffSalary.getStaStaffId());
			}

		}else {
			return staffSalaryMapper.selectByExample(null);
		}

		return staffSalaryMapper.selectByExample(staffSalaryExample);
	}

	@Override
	public StaffSalary getStaffSalary(Integer staffSalaryId) {
		return staffSalaryMapper.selectByPrimaryKey(staffSalaryId);
	}

	@Override
	public boolean deleteStaffSalary(Integer staffSalaryId) {
		try {
			int i = staffSalaryMapper.deleteByPrimaryKey(staffSalaryId);
			if (i > 0) {
				return true;
			}
		} catch (Exception e) {

		}

		return false;
	}



	@Override
	public List<StaffSalaryCustom> findStaffSalaryList(StaffSalaryCustom staffSalaryCustom) throws Exception {
		return staffSalaryCustomMapper.findStaffSalaryList(staffSalaryCustom);
	}

}
