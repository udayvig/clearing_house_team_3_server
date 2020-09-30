package com.citi.servicebeans;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citi.bean.ClearingHouse;
import com.citi.bean.ClearingMember;
import com.citi.dao.ClearingMemberDAOImpl;
import com.citi.dao.StockDAOImpl;
import com.citi.displaybeans.CostSettlementDisplay;

@Component
public class CostSettlement {
	CostSettlementDisplay costSettlementDisplay;
	
	@Autowired
	ClearingMemberDAOImpl clearingMemberDAOImpl;
	
	@Autowired
	StockDAOImpl stockDAOImpl;
	
	@Autowired
	FundObligation fundObligation;
	
	@Autowired
	StockObligation stockObligation;
	
	@Autowired
	ClearingHouse clearingHouse;
	
	double fundBorrowingRate = 0.0;
	
	public CostSettlementDisplay generateCostSettlementReport(int clearingMemberID) {
		//Set fund borrowing interest rate
		this.fundBorrowingRate = clearingHouse.getFundBorrowingRate();
		CostSettlementDisplay costSettlementDisplay = new CostSettlementDisplay();
		ClearingMember clearingMember = clearingMemberDAOImpl.getClearingMember(clearingMemberID);
		costSettlementDisplay.setOpeningBalance(clearingMember.getClearingMemberOpeningFundBalance());
		costSettlementDisplay.setDailyObligation(fundObligation.getFundObligation().get(clearingMemberID));
		costSettlementDisplay.setCostOfFunds(fundObligation.getFundShortage().get(clearingMemberID) * fundBorrowingRate	* (double)(2d/365d));
		double costOfSecurities = 0;
		for(Map.Entry entry : stockObligation.getStockShortage().get(clearingMemberID).entrySet()) {
			double stockBorrowingRate = stockDAOImpl.getStock((Integer)entry.getKey()).getBorrowingRate();
			costOfSecurities += stockBorrowingRate * (Integer)entry.getValue();
		}
		costSettlementDisplay.setCostOfSecurities(costOfSecurities);
		costSettlementDisplay.setTotal();
		return costSettlementDisplay;
	}
}
