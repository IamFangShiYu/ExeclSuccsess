package com.guigu.instructional.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guigu.instructional.classinfo.mapper.DisciplineInfoMapper;
import com.guigu.instructional.po.DisciplineInfo;
import com.guigu.instructional.po.EvaluationInfo;
import com.guigu.instructional.po.EvaluationInfoCustom;
import com.guigu.instructional.po.EvaluationInfoExample;
import com.guigu.instructional.po.EvaluationInfoExample.Criteria;
import com.guigu.instructional.po.StaffInfo;
import com.guigu.instructional.po.StudentInfo;
import com.guigu.instructional.student.mapper.EvaluationInfoMapper;
import com.guigu.instructional.student.mapper.StudentInfoMapper;
import com.guigu.instructional.student.service.EvaluationInfoCustomService;
import com.guigu.instructional.system.mapper.StaffInfoMapper;

@Service("evaluationInfoCustomServiceImpl")
public class EvaluationInfoCustomServiceImpl implements EvaluationInfoCustomService {

	@Resource(name = "evaluationInfoMapper")
	private EvaluationInfoMapper evaluationInfoMapper;
	
	@Resource(name = "studentInfoMapper")
	private StudentInfoMapper studentInfoMapper;
	
	@Resource(name = "staffInfoMapper")
	private StaffInfoMapper staffInfoMapper;
	
	@Resource(name = "disciplineInfoMapper")
	private DisciplineInfoMapper disciplineInfoMapper;

	@Override
	public EvaluationInfoCustom getEvaluationInfoCustom(Integer evaluationId) {
		
		//��ѯ��ɼ���Ŷ�Ӧ�Ĺ�ͨ��Ϣ����װ
		EvaluationInfoCustom evaluationInfoCustom = new EvaluationInfoCustom();
		EvaluationInfo evaluationInfo = evaluationInfoMapper.selectByPrimaryKey(evaluationId);
		evaluationInfoCustom.setEvaluationInfo(evaluationInfo);
		
		//��ѯ��ѧ����Ŷ�Ӧ��ѧ����������װ
		Integer studentId = evaluationInfo.getStudentId();
		StudentInfo studentInfo = studentInfoMapper.selectByPrimaryKey(studentId);
		String studentName = studentInfo.getStudentName();
		evaluationInfoCustom.setStudentName(studentName);
		
		//��ѯ��Ա����Ŷ�Ӧ��Ա����������װ
		Integer staffId = evaluationInfo.getStaffId();
		StaffInfo staffInfo = staffInfoMapper.selectByPrimaryKey(staffId);
		String staffName = staffInfo.getStaffName();
		evaluationInfoCustom.setStaffName(staffName);
		
		//��ѯ���Ŀ��Ŷ�Ӧ�Ŀ�Ŀ���Ʋ���װ
		Integer disciplineId = evaluationInfo.getDisciplineId();
		DisciplineInfo disciplineInfo = disciplineInfoMapper.selectByPrimaryKey(disciplineId);
		String disciplineName = disciplineInfo.getDisciplineName();
		evaluationInfoCustom.setDisciplineName(disciplineName);
		
		return evaluationInfoCustom;
	}


	@Override
	public List<EvaluationInfoCustom> getEvaluationInfoCustomList(EvaluationInfo evaluationInfo) {
		
		EvaluationInfoExample evaluationInfoExample = new EvaluationInfoExample();
		Criteria criteria = evaluationInfoExample.createCriteria();
		
		if(evaluationInfo!=null) {
			//id
			if(evaluationInfo.getEvaluationId()!=null) {
				criteria.andStudentIdEqualTo(evaluationInfo.getStudentId());
			}
			//name
			if(evaluationInfo.getEvaluationTile()!=null) {
				evaluationInfo.setEvaluationTile("%"+evaluationInfo.getEvaluationTile()+"%");
				criteria.andEvaluationTileLike(evaluationInfo.getEvaluationTile());
			}
		}
		List<EvaluationInfo> listEvaluationInfo = evaluationInfoMapper.selectByExample(evaluationInfoExample);
		if(listEvaluationInfo!=null) {
			List<EvaluationInfoCustom> listEvaluationInfoCustom = new ArrayList<>();
			for(EvaluationInfo evaluation:listEvaluationInfo) {
				EvaluationInfoCustom evaluationInfoCustom = this.getEvaluationInfoCustom(evaluation.getEvaluationId());
				listEvaluationInfoCustom.add(evaluationInfoCustom);
			}
			return listEvaluationInfoCustom;
		}
		return null;
	}

}
