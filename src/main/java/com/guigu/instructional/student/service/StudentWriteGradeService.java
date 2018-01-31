package com.guigu.instructional.student.service;

import com.guigu.instructional.po.StudentWriteGrade;

public interface StudentWriteGradeService {

	/**
	 * addStudentWriteGrade:���ѧԱ�ɼ���Ϣ
	 * @param studentWriteGrade
	 * @return
	 */
	public boolean addStudentWriteGrade(StudentWriteGrade studentWriteGrade);
	
	/**
	 * deleteStudentWriteGrade:ɾ��ѧԱ�ɼ���Ϣ
	 * @param writeGradeId
	 * @return
	 */
	public boolean deleteStudentWriteGrade(Integer writeGradeId);
	
	/** 
	 * updateStudentWriteGrade:����ѧԱ�ɼ���Ϣ
	 * @param studentWriteGrade
	 * @return
	 */
	public boolean updateStudentWriteGrade(StudentWriteGrade studentWriteGrade);

	/**
	 * getStudenWriteGrade:����ѧԱ�ɼ���Ϣ
	 * @param writeGradeId
	 * @return
	 */
	public StudentWriteGrade getStudentWriteGrade(Integer writeGradeId);
}

