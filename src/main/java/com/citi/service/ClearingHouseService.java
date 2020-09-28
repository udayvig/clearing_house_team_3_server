package com.citi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.bean.ClearingMember;
import com.citi.bean.OpeningStockBalance;
import com.citi.bean.Stock;
import com.citi.bean.Trade;
import com.citi.dao.ClearingMemberDAO;
import com.citi.dao.OpeningStockBalanceDAO;
import com.citi.dao.StockDAO;
import com.citi.dao.TradeDAO;
import com.citi.displaybeans.TradeDisplay;

@Service
public class ClearingHouseService {
	
	@Autowired
	private TradeDAO tradeDAO;
	
	@Autowired
	private ClearingMemberDAO clearingMemberDAO;
	
	@Autowired
	private StockDAO stockDAO;
	
	@Autowired
	private OpeningStockBalanceDAO openingStockBalanceDAO;
	
	public List<TradeDisplay> getTradeBook(){
		List<Trade> tradeList = tradeDAO.getAllTrades();
		List<TradeDisplay> tradeBook = new ArrayList<>();
		
		for(Trade trade : tradeList) {
			TradeDisplay tradeDisplay = new TradeDisplay();
			int buyerClearingMemberID = trade.getBuyerClearingMemberID();
			int sellerClearingMemberID = trade.getSellerClearingMemberID();
			int stockID = trade.getStockID();
			
			String buyerName = clearingMemberDAO.getClearingMember(buyerClearingMemberID).getClearingMemberName();
			String sellerName = clearingMemberDAO.getClearingMember(sellerClearingMemberID).getClearingMemberName();
			String stockName = stockDAO.getStock(stockID).getStockName();
			
			tradeDisplay.setBuyerName(buyerName);
			tradeDisplay.setPrice(trade.getPrice());
			tradeDisplay.setQuantity(trade.getQuantity());
			tradeDisplay.setSellerName(sellerName);
			tradeDisplay.setStockName(stockName);
			tradeDisplay.setTradeID(trade.getTradeID());
			tradeDisplay.setTradeValue(trade.getTradeValue());
			
			tradeBook.add(tradeDisplay);
		}
		
		return tradeBook;
	}
	
	public int getTradeVolume() {
		List<Trade> tradeList = tradeDAO.getAllTrades();
		int volume = 0;
		for(Trade trade : tradeList) {
			volume += trade.getQuantity();
		}
		
		return volume;
	}
	
	public HashMap<String, HashMap<String, Double>> getOpeningBalance(){
		HashMap<String, HashMap<String, Double>> openingBalances = new HashMap<>();
		
		List<ClearingMember> clearingMembers = clearingMemberDAO.getAllClearingMembers();
		
		for(ClearingMember cm : clearingMembers) {
			HashMap<String, Double> map = new HashMap<>();
			String clearingMemberName = cm.getClearingMemberName();
			
			map.put("Funds", cm.getClearingMemberOpeningFundBalance());
			
			List<OpeningStockBalance> balanceList = openingStockBalanceDAO
					.getOpeningStockBalanceByClearingMemberID(cm.getClearingMemberID());
			
			for(OpeningStockBalance balance : balanceList) {
				String stockName = stockDAO.getStock(balance.getStockID()).getStockName();
				map.put(stockName, (double)(balance.getQuantity()));
			}
			
			openingBalances.put(clearingMemberName, map);
		}
		
		return openingBalances;
	}
	
	public HashMap<String, Double> getStockBorrowingRate(){
		HashMap<String, Double> map = new HashMap<>();
		List<Stock> stocks = stockDAO.getAllStocksList();
		
		for(Stock stock : stocks) {
			map.put(stock.getStockName(), stock.getBorrowingRate());
		}
		
		return map;
	}
}
