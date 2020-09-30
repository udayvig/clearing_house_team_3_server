package com.citi.servicebeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citi.bean.ClearingMember;
import com.citi.bean.Trade;
import com.citi.dao.ClearingMemberDAOImpl;
import com.citi.dao.TradeDAOImpl;
import com.citi.displaybeans.FundObligationDisplay;

@Component
public class FundObligation {
	private HashMap<Integer, Double> openingBalance;
	private HashMap<Integer, Double> fundObligation;
	private List<FundObligationDisplay> fundObligationDisplayList;
	private HashMap<Integer, Double> fundShortage;
	private List<FundObligationDisplay> fundShortageDisplayList;

	@Autowired
	ClearingMemberDAOImpl clearingMemberDAOImpl;
	
	@Autowired
	TradeDAOImpl tradeDAOImpl;
	
	List<ClearingMember> clearingMembers;
	
	private HashMap<Integer, ClearingMember> accessCMs;
	
	public void initFundObligation() {
		clearingMembers = clearingMemberDAOImpl.getAllClearingMembers();
		fundObligation = new HashMap<>();
		for(ClearingMember clearingMember : clearingMembers) {
			fundObligation.put(clearingMember.getClearingMemberID(), 0.0);
		}
		
		accessCMs = new HashMap<>();
		for(ClearingMember clearingMember : clearingMembers) {
			accessCMs.put(clearingMember.getClearingMemberID(), clearingMember);
		}
	}
	
	public void initFundShortage() {
		clearingMembers = clearingMemberDAOImpl.getAllClearingMembers();
		openingBalance = new HashMap<>();
		for(ClearingMember clearingMember : clearingMembers) {
			openingBalance.put(clearingMember.getClearingMemberID(), clearingMembers.get(clearingMember.getClearingMemberID() - 1).getClearingMemberOpeningFundBalance());
		}
		
		accessCMs = new HashMap<>();
		for(ClearingMember clearingMember : clearingMembers) {
			accessCMs.put(clearingMember.getClearingMemberID(), clearingMember);
		}
	}
	public HashMap<Integer, Double> getFundObligation() {
		return fundObligation;
	}
	public List<FundObligationDisplay> getFundShortageDisplayList() {
		return fundShortageDisplayList;
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
	
	public void setFundObligationDisplayList() {
		setFundObligation();
		fundObligationDisplayList = new ArrayList<>();
		for(Map.Entry<Integer, Double> entry : fundObligation.entrySet()) {
			FundObligationDisplay fundObligationDisplay = new FundObligationDisplay();
			String clearingMemberName = accessCMs.get((Integer)(entry.getKey())).getClearingMemberName();
			fundObligationDisplay.setClearingMemberName(clearingMemberName);
			fundObligationDisplay.setFundObligationAmount((Double)entry.getValue());
			fundObligationDisplayList.add(fundObligationDisplay);
		}
	}

	public List<FundObligationDisplay> getFundObligationDisplayList() {
		return fundObligationDisplayList;
	}

	public HashMap<Integer, Double> getFundShortage() {
		return fundShortage;
	}	
	
	public void setFundShortage() {
		initFundShortage();
		setFundObligation();
		fundShortage = new HashMap<>();
		for(Map.Entry<Integer, Double> entry : fundObligation.entrySet()) {
			double shortage = (openingBalance.get(entry.getKey()) + (Double)entry.getValue()) * -1;
			if(shortage > 0)
				fundShortage.put((Integer)entry.getKey(), shortage);
			else
				fundShortage.put((Integer)entry.getKey(), 0.0);
		}
	}
	
	public void setFundShortageDisplayList() {
		setFundObligation();
		setFundShortage();
		fundShortageDisplayList = new ArrayList<>();
		for(Map.Entry<Integer, Double> entry : fundShortage.entrySet()) {
			FundObligationDisplay fundShortageDisplay = new FundObligationDisplay();
			String clearingMemberName = accessCMs.get((Integer)(entry.getKey())).getClearingMemberName();
			fundShortageDisplay.setClearingMemberName(clearingMemberName);
			fundShortageDisplay.setFundObligationAmount((Double)entry.getValue());
			fundShortageDisplayList.add(fundShortageDisplay);
		}
	}
	
	public double getClearingMemberFundObligation(int cmid) {
		setFundObligation();
		return this.fundObligation.get(cmid);
	}
}
