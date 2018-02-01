package com.guigu.instructional.classinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guigu.instructional.classinfo.mapper.ClassroomInfoMapper;
import com.guigu.instructional.classinfo.service.ClassroomInfoService;
import com.guigu.instructional.po.ClassroomInfo;
import com.guigu.instructional.po.ClassroomInfoExample;
import com.guigu.instructional.po.ClassroomInfoExample.Criteria;

@Service("classroomInfoServiceImpl")
public class ClassrommInfoServiceImpl implements ClassroomInfoService{
	
	@Resource(name="classroomInfoMapper")
	private ClassroomInfoMapper classroomInfoMapper;
	
	@Override
	public boolean addClassroomInfo(ClassroomInfo classroomInfo) {
		if (classroomInfo != null) {
			classroomInfo.setClassroomRemark("1");
		}
		try {
			int i = classroomInfoMapper.insertSelective(classroomInfo);
			if (i > 0) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	@Override
	public boolean updateClassroomInfo(ClassroomInfo classroomInfo) {
		try {
			int i = classroomInfoMapper.updateByPrimaryKeySelective(classroomInfo);
			if (i > 0) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	@Override
	public List<ClassroomInfo> getClassroomInfoList(ClassroomInfo classroomInfo) {
		ClassroomInfoExample classroomInfoExample=new ClassroomInfoExample();
		Criteria criteria=classroomInfoExample.createCriteria();
		
		if(classroomInfo !=null) {
			if(classroomInfo.getClassroomId()!=null) {
				criteria.andClassroomIdLessThanOrEqualTo(classroomInfo.getClassroomId());
			}
			if(classroomInfo.getClassroomName()!=null) {
				classroomInfo.setClassroomName("%" + classroomInfo.getClassroomName() + "%");
				criteria.andClassroomNameLike(classroomInfo.getClassroomName());
			}
			if(classroomInfo.getClassroomMax()!=null) {
				criteria.andClassroomMaxLessThanOrEqualTo(classroomInfo.getClassroomMax());
			}
		}
		criteria.andClassroomMarkEqualTo("1");
		return classroomInfoMapper.selectByExample(classroomInfoExample);
	}

	@Override
	public ClassroomInfo getClassroomInfo(Integer classroomId) {
		return classroomInfoMapper.selectByPrimaryKey(classroomId);
	}

}
