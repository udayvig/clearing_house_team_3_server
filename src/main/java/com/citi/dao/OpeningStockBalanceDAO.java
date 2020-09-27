package com.citi.dao;

import java.util.List;

import com.citi.bean.OpeningStockBalance;

public interface OpeningStockBalanceDAO {
	
	OpeningStockBalance getOpeningStockBalance(int clearingMemberID, int stockID);
	
	List<OpeningStockBalance> getOpeningStockBalanceByClearingMemberID(int clearingMemberID);
	
	void addOpeningStockBalance(int clearingMemberID, int stockID, int quantity);
	
	void updateStockBalanceQuantity(int openingStockBalanceID, int quantity);

}
