package com.citi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.bean.ClearingHouse;
import com.citi.bean.ClearingMember;
import com.citi.bean.OpeningStockBalance;
import com.citi.bean.Stock;
import com.citi.bean.Trade;
import com.citi.dao.ClearingMemberDAO;
import com.citi.dao.OpeningStockBalanceDAO;
import com.citi.dao.StockDAO;
import com.citi.dao.TradeDAO;
import com.citi.displaybeans.OpeningBalanceDisplay;
import com.citi.displaybeans.StockBorrowingRateDisplay;
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
	
	@Autowired
	private ClearingHouse clearingHouse;
	
	private List<Trade> tradeList;
	private List<ClearingMember> clearingMembers;
	private List<Stock> stocks;
	
	private HashMap<Integer, String> clearingMemberNames = new HashMap<>();
	private HashMap<Integer, String> stockNames = new HashMap<>();
	
	public void initialise() {
		System.out.println("Initialising CHS");
		tradeList = tradeDAO.getAllTrades();
		clearingMembers = clearingMemberDAO.getAllClearingMembers();
		stocks = stockDAO.getAllStocksList();
		
		for(ClearingMember clearingMember : clearingMembers) {
			clearingMemberNames.put(clearingMember.getClearingMemberID(), clearingMember.getClearingMemberName());
		}
		
		for(Stock stock : stocks) {
			stockNames.put(stock.getStockID(), stock.getStockName());
		}
	}
	
	public List<TradeDisplay> getTradeBook(){
		List<TradeDisplay> tradeBook = new ArrayList<>();
		
		for(Trade trade : tradeList) {
			TradeDisplay tradeDisplay = new TradeDisplay();
			int buyerClearingMemberID = trade.getBuyerClearingMemberID();
			int sellerClearingMemberID = trade.getSellerClearingMemberID();
			int stockID = trade.getStockID();
			
			String buyerName = clearingMemberNames.get(buyerClearingMemberID);
			String sellerName = clearingMemberNames.get(sellerClearingMemberID);
			String stockName = stockNames.get(stockID);
			
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
	
	public HashMap<String, Integer> getTradeVolume() {
		HashMap<String, Integer> map = new HashMap<>();
		
		int volume = 0;
		for(Trade trade : tradeList) {
			volume += trade.getQuantity();
		}
		
		map.put("TradeVolume", volume);
		return map;
	}
	
	public List<OpeningBalanceDisplay> getOpeningBalance(){
		HashMap<String, HashMap<String, Double>> openingBalances = new HashMap<>();
		
		for(ClearingMember cm : clearingMembers) {
			HashMap<String, Double> map = new HashMap<>();
			String clearingMemberName = cm.getClearingMemberName();
			
			map.put("Funds", cm.getClearingMemberOpeningFundBalance());
			
			List<OpeningStockBalance> balanceList = openingStockBalanceDAO
					.getOpeningStockBalanceByClearingMemberID(cm.getClearingMemberID());
			
			for(OpeningStockBalance balance : balanceList) {
				String stockName = stockNames.get(balance.getStockID());
				map.put(stockName, (double)(balance.getQuantity()));
			}
			
			openingBalances.put(clearingMemberName, map);
		}
		
		System.out.println(openingBalances);
		
		List<OpeningBalanceDisplay> openingBalanceDisplayList = new ArrayList<>();
		for(String cmName : openingBalances.keySet()) {
			OpeningBalanceDisplay openingBalanceDisplay = new OpeningBalanceDisplay();
			openingBalanceDisplay.setClearingMemberName(cmName);
			
			openingBalanceDisplay.setAmazon(openingBalances.get(cmName).get("Amazon"));
			openingBalanceDisplay.setApple(openingBalances.get(cmName).get("Apple"));
			openingBalanceDisplay.setGoogle(openingBalances.get(cmName).get("Google"));
			openingBalanceDisplay.setNetflix(openingBalances.get(cmName).get("Netfilx"));
			openingBalanceDisplay.setFacebook(openingBalances.get(cmName).get("Facebook"));
			openingBalanceDisplay.setCash(openingBalances.get(cmName).get("Funds"));
			
			openingBalanceDisplayList.add(openingBalanceDisplay);
		}
		
		return openingBalanceDisplayList;
	}
	
	public List<StockBorrowingRateDisplay> getStockBorrowingRate(){
//		HashMap<String, Double> map = new HashMap<>();
//		
//		for(Stock stock : stocks) {
//			map.put(stock.getStockName(), stock.getBorrowingRate());
//		}
//		
//		return map;
		
		List<StockBorrowingRateDisplay> list = new ArrayList<>();
		
		for(Stock stock : stocks) {
			StockBorrowingRateDisplay stockBorrowingRateDisplay = new StockBorrowingRateDisplay();
			stockBorrowingRateDisplay.setBorrowingcost(stock.getBorrowingRate());
			stockBorrowingRateDisplay.setStock(stock.getStockName());
			
			list.add(stockBorrowingRateDisplay);
		}
		
		return list;
	}
	
	public HashMap<String, Double> getFundInterestRate(){
		HashMap<String, Double> map = new HashMap<>();
		map.put("interest_rate", clearingHouse.getFundBorrowingRate());
		
		return map;
	}
}
