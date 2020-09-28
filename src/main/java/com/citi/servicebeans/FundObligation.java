package com.citi.servicebeans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citi.bean.ClearingMember;
import com.citi.bean.Trade;
import com.citi.dao.ClearingMemberDAOImpl;
import com.citi.dao.TradeDAOImpl;

@Component
public class FundObligation {
	private HashMap<Integer, Double> fundObligation;
	private HashMap<String, Double> fundObligationDisplay;
	
	@Autowired
	ClearingMemberDAOImpl clearingMemberDAOImpl;
	
	@Autowired
	TradeDAOImpl tradeDAOImpl;
	
	List<ClearingMember> clearingMembers;
	
	public void initFundObligation() {
		clearingMembers = clearingMemberDAOImpl.getAllClearingMembers();
		fundObligation = new HashMap<>();
		for(ClearingMember clearingMember : clearingMembers) {
			fundObligation.put(clearingMember.getClearingMemberID(), clearingMembers.get(clearingMember.getClearingMemberID() - 1).getClearingMemberOpeningFundBalance());
		}
	}
	public HashMap<Integer, Double> getFundObligation() {
		return fundObligation;
	}
	public HashMap<String, Double> getFundObligationDisplay() {
		return fundObligationDisplay;
	}
	public void setFundObligation() {
		initFundObligation();
		List<Trade> allTrades = tradeDAOImpl.getAllTrades();
		for(Trade trade : allTrades) {
			int buyerClearingMemberID = trade.getBuyerClearingMemberID();
			int sellerClearingMemberID = trade.getSellerClearingMemberID();
			double tradeValue = trade.getTradeValue();
			fundObligation.replace(buyerClearingMemberID, fundObligation.get(buyerClearingMemberID) - tradeValue);
			fundObligation.replace(sellerClearingMemberID, fundObligation.get(sellerClearingMemberID) + tradeValue);
		}
	}
	public void setFundObligationDisplay() {
		fundObligationDisplay = new HashMap<>();
		setFundObligation();
		for(Map.Entry entry : fundObligation.entrySet()) {
			String clearingMemberName = clearingMemberDAOImpl.getClearingMember((Integer)(entry.getKey())).getClearingMemberName();
			fundObligationDisplay.put(clearingMemberName, (Double)entry.getValue());
		}
	}
}
