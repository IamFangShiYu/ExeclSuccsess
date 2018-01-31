package com.guigu.instructional.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guigu.instructional.classinfo.mapper.DisciplineInfoMapper;
import com.guigu.instructional.po.DisciplineInfo;
import com.guigu.instructional.po.StaffInfo;
import com.guigu.instructional.po.StudentInfo;
import com.guigu.instructional.po.StudentInfoExample;
import com.guigu.instructional.po.StudentWriteGrade;
import com.guigu.instructional.po.StudentWriteGradeCustom;
import com.guigu.instructional.po.StudentWriteGradeExample;
import com.guigu.instructional.po.StudentWriteGradeExample.Criteria;
import com.guigu.instructional.student.mapper.StudentInfoMapper;
import com.guigu.instructional.student.mapper.StudentWriteGradeMapper;
import com.guigu.instructional.student.service.StudentInfoService;
import com.guigu.instructional.student.service.StudentWriteGradeCustomService;
import com.guigu.instructional.system.mapper.StaffInfoMapper;

@Service("studentWriteGradeCustomServiceImpl")
public class StudentWriteGradeCustomServiceImpl implements StudentWriteGradeCustomService{

	@Resource(name = "studentWriteGradeMapper")
	private StudentWriteGradeMapper studentWriteGradeMapper;
	
	@Resource(name = "studentInfoMapper")
	private StudentInfoMapper studentInfoMapper;
	
	@Resource(name = "staffInfoMapper")
	private StaffInfoMapper staffInfoMapper;
	
	@Resource(name = "disciplineInfoMapper")
	private DisciplineInfoMapper disciplineInfoMapper;
	
	@Resource(name="studentInfoServiceImpl")
	private StudentInfoService studentInfoService;
	
	@Override
	public StudentWriteGradeCustom getStudentWriteGradeCustom(Integer writeGradeId) {
		
		//��ѯ��ɼ���Ŷ�Ӧ�ĳɼ���Ϣ����װ
		StudentWriteGrade studentWriteGrade = studentWriteGradeMapper.selectByPrimaryKey(writeGradeId);
		StudentWriteGradeCustom studentWriteGradeCustom = new StudentWriteGradeCustom();
		studentWriteGradeCustom.setStudentWriteGrade(studentWriteGrade);
		
		//��ѯ��ѧ����Ŷ�Ӧ��ѧ����������װ
		Integer studentId = studentWriteGrade.getStudentId();
		StudentInfo studentInfo = studentInfoMapper.selectByPrimaryKey(studentId);
		String studentName = studentInfo.getStudentName();
		studentWriteGradeCustom.setStudentName(studentName);
		
		//��ѯ��Ա����Ŷ�Ӧ��Ա����������װ
		Integer staffId = studentWriteGrade.getStaffId();
		StaffInfo staffInfo = staffInfoMapper.selectByPrimaryKey(staffId);
		String staffName = staffInfo.getStaffName();
		studentWriteGradeCustom.setStaffName(staffName);
		
		//��ѯ���Ŀ��Ŷ�Ӧ�Ŀ�Ŀ���Ʋ���װ
		Integer disciplineId = studentWriteGrade.getDisciplineId();
		DisciplineInfo disciplineInfo = disciplineInfoMapper.selectByPrimaryKey(disciplineId);
		String disciplineName = disciplineInfo.getDisciplineName();
		studentWriteGradeCustom.setDisciplineName(disciplineName);
		
		return studentWriteGradeCustom;
	}
	
	@Override
	public List<StudentWriteGradeCustom> getStudentWriteGradeCustomList(StudentInfo studentInfo) {
		
		StudentWriteGradeExample studentWriteGradeExample = new StudentWriteGradeExample();
		Criteria criteriaOfStudentWriteGrade = studentWriteGradeExample.createCriteria();
		
		StudentInfoExample studentInfoExample = new StudentInfoExample();
		com.guigu.instructional.po.StudentInfoExample.Criteria criteriaStudentInfo = studentInfoExample.createCriteria();
		criteriaStudentInfo.andStudentStateEqualTo(1);
		
		if(studentInfo!=null) {
			if(studentInfo.getStudentName()!=null && !studentInfo.getStudentName().equals("")) {
				//ѧ������ģ����ѯ
				studentInfo.setStudentName("%"+studentInfo.getStudentName()+"%");
				criteriaStudentInfo.andStudentNameLike(studentInfo.getStudentName());
				//�Ѳ�ѯ����ѧ���������ݷ����б�
				List<StudentInfo> listOfAll = studentInfoMapper.selectByExample(studentInfoExample); 
				List<Integer> listOfStudentId = new ArrayList<>();
				for (StudentInfo student : listOfAll) {
					listOfStudentId.add(student.getStudentId());
				}
				if(listOfStudentId.isEmpty()) {
					return null;
				}else {
					criteriaOfStudentWriteGrade.andStudentIdIn(listOfStudentId);
				}
			}
			if(studentInfo.getStudentId()!=null && !studentInfo.getStudentId().equals("")) {
				criteriaOfStudentWriteGrade.andStudentIdEqualTo(studentInfo.getStudentId());
			}
		}
		
		
		List<StudentWriteGrade> listStudentWriteGrade=studentWriteGradeMapper.selectByExample(studentWriteGradeExample);
		if(listStudentWriteGrade!=null) {
			List<StudentWriteGradeCustom> listStudentWriteGradeCustom = new ArrayList<>();
			for(StudentWriteGrade grade:listStudentWriteGrade) {
				
				StudentWriteGradeCustom studentWriteGradeCustom = this.getStudentWriteGradeCustom(grade.getWriteGradeId());
				listStudentWriteGradeCustom.add(studentWriteGradeCustom);
			}
			return listStudentWriteGradeCustom;
		}
		return null;
	}

}
