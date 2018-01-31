package com.guigu.instructional.student.service;

import java.util.List;

import com.guigu.instructional.po.StudentInfo;
import com.guigu.instructional.po.StudentWriteGradeCustom;

public interface StudentWriteGradeCustomService {

	/**
	 * getStudentWriteGradeCustom:��ѯѧԱ�ɼ���Ϣ
	 * @param writeGradeId
	 * @return
	 */
	public StudentWriteGradeCustom getStudentWriteGradeCustom(Integer writeGradeId);

	/**
	 * getStudentWriteGradeCustomList:��ѯѧԱ�ɼ���Ϣ
	 * @param studentInfo
	 * @return
	 */
	public List<StudentWriteGradeCustom> getStudentWriteGradeCustomList(StudentInfo studentInfo);
	 
}
