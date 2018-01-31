package com.guigu.instructional.student.service;

import java.util.List;

import com.guigu.instructional.po.EvaluationInfo;
import com.guigu.instructional.po.EvaluationInfoCustom;

public interface EvaluationInfoCustomService {
	
	/**
	 * getEvaluationInfoCustom:����Ա����Ų�ѯѧԱ������Ϣ
	 * @param evaluationId
	 * @return
	 */
	public EvaluationInfoCustom getEvaluationInfoCustom(Integer evaluationId);
	
	/**
	 * getEvaluationInfoListCustom:����������ѯѧԱ������Ϣ
	 * @param evaluationInfo
	 * @return
	 */
	public List<EvaluationInfoCustom> getEvaluationInfoCustomList(EvaluationInfo evaluationInfo);
	
}
