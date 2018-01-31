package com.guigu.instructional.student.service;

import com.guigu.instructional.po.EvaluationInfo;

public interface EvaluationInfoService {

	/**
	 * addStudent:���ѧԱ������Ϣ
	 * @param evaluationInfo
	 * @return
	 */
	public boolean addEvaluation(EvaluationInfo evaluationInfo);
	
	/**
	 * deleteEvaluationInfo:ɾ��ѧԱ������Ϣ
	 * @param evaluationId
	 * @return
	 */
	public boolean deleteEvaluationInfo(Integer evaluationId);
	
	/**
	 * updateStudent:����ѧԱ������Ϣ
	 * @param evaluationInfo
	 * @return
	 */
	public boolean updateEvaluation(EvaluationInfo evaluationInfo);
	
	/**
	 * getStudentInfo:��ѯѧԱ������Ϣ
	 * @param evaluationId
	 * @return
	 */
	public EvaluationInfo getEvaluationInfo(Integer evaluationId);
	
	
}
