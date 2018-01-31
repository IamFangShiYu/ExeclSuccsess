package com.guigu.instructional.student.service;

import java.util.List;

import com.guigu.instructional.po.CommunicationInfo;
import com.guigu.instructional.po.CommunicationInfoCustom;
import com.guigu.instructional.po.StudentInfo;


public interface CommunicationInfoCustomService {

	/**
	 * addCommunication:���ѧԱ��ͨ��¼
	 * @param communicationInfo
	 * @return
	 */
	public boolean addCommunication(CommunicationInfo communicationInfo);
	
	/**
	 * deleteCommunication:ɾ��ѧԱ��ͨ��¼
	 * @param communicationId
	 * @return
	 */
	public boolean deleteCommunication(Integer communicationId);
	
	/**
	 * updateCommunication:����ѧԱ��ͨ��¼
	 * @param communicationInfo
	 * @return
	 */
	public boolean updateCommunication(CommunicationInfo communicationInfo);
	
	/**
	 * getCommunicationInfoCustom:���ݹ�ͨ��Ų�ѯѧԱ��ͨ��¼
	 * @param communicationId
	 * @return
	 */
	public CommunicationInfoCustom getCommunicationInfoCustom(Integer communicationId);
	
	/**
	 * getCommunicationInfoList:����������ѯѧԱ��ͨ��¼
	 * @param studentInfo
	 * @return
	 */
	public List<CommunicationInfoCustom> getCommunicationInfoCustomList(StudentInfo studentInfo);

}
