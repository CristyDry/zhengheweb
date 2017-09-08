/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheFriend;
import com.fullteem.modules.zhenghe.dao.ZhengheFriendDao;

/**
 * 好友关系Service
 * @author 陈协
 * @version 2015-11-12
 */
@Service
@Transactional(readOnly = true)
public class ZhengheFriendService extends CrudService<ZhengheFriendDao, ZhengheFriend> {
	
	public List<ZhengheFriend> findFriendByPatientIdAndGroupId (String patientId,String groupingId){
		ZhengheFriend friend = new ZhengheFriend();
		friend.setPatientId(patientId);
		friend.setGroupingId(groupingId);
		return findFriendsByKY(friend);
	}
	
	public List<ZhengheFriend> findFriendByDoctorIdAndStatus (String doctorId,String status){
		ZhengheFriend friend = new ZhengheFriend();
		friend.setDoctorId(doctorId);
		friend.setStatus(status);
		return findFriendsByKY(friend);
	}
	
	public List<ZhengheFriend> findFriendByDoctorIdAndPatientId(String doctorId,String patientId){
		ZhengheFriend friend = new ZhengheFriend();
		friend.setDoctorId(doctorId);
		friend.setPatientId(patientId);
		return findFriendsByKY(friend);
	}
	
	public List<ZhengheFriend> findFriendByPatientIdAndStatus (String patientId,String status){
		ZhengheFriend friend = new ZhengheFriend();
		friend.setPatientId(patientId);
		friend.setStatus(status);
		return findFriendsByKY(friend);
	}
	
	public List<ZhengheFriend> findFriendByGroupIdAndStatus(String groupId,String status){
		ZhengheFriend friend = new ZhengheFriend();
		friend.setGroupingId(groupId);
		friend.setStatus(status);
		return findFriendsByKY(friend);
	}

	public List<ZhengheFriend> findFriendByPatientIdAndPatientStatus(String patientId,String patientStatus){
		ZhengheFriend friend = new ZhengheFriend();
		friend.setPatientId(patientId);
		friend.setPatientStatus(patientStatus);
		return findFriendsByKY(friend);
	}
	
	public List<ZhengheFriend> findFriendByDoctorIdAndDoctorStatus(String doctorId,String doctorStatus){
		ZhengheFriend friend = new ZhengheFriend();
		friend.setDoctorId(doctorId);
		friend.setDoctorStatus(doctorStatus);
		return findFriendsByKY(friend);
	}
	
	private List<ZhengheFriend> findFriendsByKY(ZhengheFriend zhengheFriend){
		return dao.findFriendsByKY(zhengheFriend);
	}
	
	public ZhengheFriend get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheFriend> findList(ZhengheFriend zhengheFriend) {
		return super.findList(zhengheFriend);
	}
	
	public Page<ZhengheFriend> findPage(Page<ZhengheFriend> page, ZhengheFriend zhengheFriend) {
		return super.findPage(page, zhengheFriend);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheFriend zhengheFriend) {
		super.save(zhengheFriend);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheFriend zhengheFriend) {
		super.delete(zhengheFriend);
	}
	
}