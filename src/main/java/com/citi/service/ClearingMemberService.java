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
import com.citi.displaybeans.OpeningBalanceDisplay;
import com.citi.displaybeans.TradeBookDisplay;
import com.citi.displaybeans.TradeDisplay;

@Service
public class ClearingMemberService {
	
	@Autowired
	private TradeDAO tradeDAO;
	
	@Autowired
	private ClearingMemberDAO clearingMemberDAO;
	
	@Autowired
	private StockDAO stockDAO;
	
	@Autowired
	private OpeningStockBalanceDAO openingStockBalanceDAO;
	
	private List<ClearingMember> clearingMembers;
	private List<Stock> stocks;
	
	private HashMap<Integer, String> clearingMemberNames = new HashMap<>();
	private HashMap<Integer, String> stockNames = new HashMap<>();
	
	public void initialise() {
		System.out.println("Initialising CMS");
		clearingMembers = clearingMemberDAO.getAllClearingMembers();
		stocks = stockDAO.getAllStocksList();
		
		for(ClearingMember clearingMember : clearingMembers) {
			clearingMemberNames.put(clearingMember.getClearingMemberID(), clearingMember.getClearingMemberName());
		}
		
		for(Stock stock : stocks) {
			stockNames.put(stock.getStockID(), stock.getStockName());
		}
	}
	
	public List<TradeDisplay> getCmBuyTradebook(int id) {
		List<Trade> tradeList = tradeDAO.getTradesByBuyingClearingMemberID(id);
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
	
	public List<TradeDisplay> getCmSellTradebook(int id) {
		List<Trade> tradeList = tradeDAO.getTradesBySellingClearingMemberID(id);
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
	
	public List<TradeBookDisplay> getCmTradebook(int id){
		List<Trade> tradeList = tradeDAO.getTradesByClearingMemberID(id);
		List<TradeBookDisplay> tradeBook = new ArrayList<>();
		
		for(Trade trade : tradeList) {
			TradeBookDisplay tradeBookDisplay = new TradeBookDisplay();
			tradeBookDisplay.setCmName(clearingMemberNames.get(id));
			tradeBookDisplay.setPrice(trade.getPrice());
			tradeBookDisplay.setQuantity(trade.getQuantity());
			tradeBookDisplay.setTradeID(trade.getTradeID());
			tradeBookDisplay.setStockName(stockNames.get(trade.getStockID()));
			
			if(trade.getBuyerClearingMemberID() == id) {
				tradeBookDisplay.setOrderType("buy");
			}else if(trade.getSellerClearingMemberID() == id) {
				tradeBookDisplay.setOrderType("sell");
			}else {
				tradeBookDisplay.setOrderType("error");
			}
			
			tradeBook.add(tradeBookDisplay);
		}
		
		return tradeBook;
	}
	
	public ClearingMember getOpeningFundBalance(int id) {
		return clearingMemberDAO.getClearingMember(id);
	}
	
	public OpeningBalanceDisplay getOpeningStockBalance(int id) {
		OpeningBalanceDisplay openingStockDisplay = new OpeningBalanceDisplay();
		List<OpeningStockBalance> openingStockBalances = openingStockBalanceDAO.getOpeningStockBalanceByClearingMemberID(id);
		
		openingStockDisplay.setClearingMemberName(clearingMemberNames.get(id));
		
		for(OpeningStockBalance balance : openingStockBalances) {
			switch(balance.getStockID()) {
			case 1: 
				openingStockDisplay.setApple(balance.getQuantity());
				break;
			
			case 2:
				openingStockDisplay.setFacebook(balance.getQuantity());
				break;
				
			case 3:
				openingStockDisplay.setNetflix(balance.getQuantity());
				break;
			
			case 4:
				openingStockDisplay.setAmazon(balance.getQuantity());
				break;
				
			case 5:
				openingStockDisplay.setGoogle(balance.getQuantity());
			}
		}
		
		return openingStockDisplay;
	}
	
	
}
