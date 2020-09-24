package com.citi.dao;

import java.util.List;

import com.citi.bean.Trade;

public interface TradeDAO {
	
	Trade getTradeByTradeID (int tradeID);
	
	List<Trade> getTradeByStockID (int stockID);
	
	List<Trade> getTradeBySellerID (int sellerID);
	
	List<Trade> getTradeByBuyerID (int buyerID);
	
	List<Trade> getAllTrades ();
}
