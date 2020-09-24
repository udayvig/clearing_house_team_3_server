package com.citi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.bean.ClearingMember;

@Repository
public class ClearingMemberDAOImpl implements ClearingMemberDAO {
	
	@Autowired
	private ClearingMember clearingMember;
	
	public ClearingMember getMemberByID(int id) {
		return clearingMember;
	}
}
