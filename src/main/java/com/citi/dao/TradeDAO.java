package com.citi.dao;

import java.util.List;

import com.citi.bean.Trade;

public interface TradeDAO {
	
	void addTrade(int buyerClearingMemberID, int sellerClearingMemberID, double price, int quantity, int stockID);
	
	void addTradeList(List<Trade> tradeList);
	
	Trade getTradeByTradeID(int tradeID);
	
	List<Trade> getTradesByStockID(int stockID);
	
	List<Trade> getTradesByBuyingClearingMemberID(int buyerClearingMemberID);
	
	List<Trade> getTradesBySellingClearingMemberID(int sellerClearingMemberID);
	
	List<Trade> getTradesByClearingMemberID(int clearingMemberID);
	
	List<Trade> getAllTrades();
	
	void deleteTrade(int tradeID);
	
	void deleteAllTrades();
}
