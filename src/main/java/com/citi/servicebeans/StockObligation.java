package com.citi.servicebeans;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citi.bean.ClearingMember;
import com.citi.bean.Stock;
import com.citi.bean.Trade;
import com.citi.dao.ClearingMemberDAO;
import com.citi.dao.StockDAO;
import com.citi.dao.TradeDAO;

@Component
public class StockObligation {
	private HashMap<Integer, HashMap<Integer, Integer>> stockObligation = new HashMap<>();
	private HashMap<String, HashMap<String, Integer>> stockObligationDisplay = new HashMap<>();

	@Autowired
	private ClearingMemberDAO clearingMemberDAO;

	@Autowired
	private StockDAO stockDAO;

	@Autowired
	private TradeDAO tradeDAO;

	private List<Stock> stocks;
	private List<ClearingMember> clearingMembers;
	private List<Trade> trades;

	public HashMap<Integer, HashMap<Integer, Integer>> getStockObligation() {
		return stockObligation;
	}

	public HashMap<String, HashMap<String, Integer>> getStockObligationDisplay() {
		return stockObligationDisplay;
	}

//	Init all the lists
	public void initialise() {
		this.trades = tradeDAO.getAllTrades();
		this.stocks = stockDAO.getAllStocksList();
		this.clearingMembers = clearingMemberDAO.getAllClearingMembers();
		for (ClearingMember clearingMember : clearingMembers) {
			HashMap<Integer, Integer> inner = new HashMap<>();

			for (Stock stock : stocks) {
				inner.put(stock.getStockID(), 0);
			}

			this.stockObligation.put(clearingMember.getClearingMemberID(), inner);
		}

		for (ClearingMember clearingMember : clearingMembers) {
			HashMap<String, Integer> innerDisplay = new HashMap<>();

			for (Stock stock : stocks) {
				innerDisplay.put(stock.getStockName(), 0);
			}

			this.stockObligationDisplay.put(clearingMember.getClearingMemberName(), innerDisplay);
		}

//		System.out.println(this.stockObligation);
//		System.out.println(this.stockObligationDisplay);
//		System.out.println(this.clearingMembers);
//		System.out.println(this.stocks);
//		System.out.println(this.trades);

		return;
	}

	public void setStockObligation() {

		for (Trade trade : this.trades) {

			ClearingMember buyerCM = clearingMemberDAO.getClearingMember(trade.getBuyerClearingMemberID());
			//System.out.println(buyerCM.getClearingMemberID());
			ClearingMember sellerCM = clearingMemberDAO.getClearingMember(trade.getSellerClearingMemberID());
			//System.out.println(sellerCM.getClearingMemberID());
			Stock stock = stockDAO.getStock(trade.getStockID());
			//System.out.println(stock.getStockID());
			
			HashMap<Integer, Integer> inner = new HashMap<>();

			inner = this.stockObligation.get(buyerCM.getClearingMemberID());
			int oldBuyerStockObligationValue = inner.get(stock.getStockID());
			int newBuyerStockObligationValue = oldBuyerStockObligationValue + trade.getQuantity();
			inner.replace(stock.getStockID(), newBuyerStockObligationValue);
			this.stockObligation.replace(buyerCM.getClearingMemberID(), inner);
			
			HashMap<Integer, Integer> inner2 = new HashMap<>();
			inner2 = this.stockObligation.get(sellerCM.getClearingMemberID());
			int oldSellerStockObligationValue = inner2.get(stock.getStockID());
			int newSellerStockObligationValue = oldSellerStockObligationValue - trade.getQuantity();
			inner2.replace(stock.getStockID(), newSellerStockObligationValue);
			this.stockObligation.replace(sellerCM.getClearingMemberID(), inner2);
			
			
			HashMap<String, Integer> innerDisplay  = new HashMap<>();
			innerDisplay = this.stockObligationDisplay.get(buyerCM.getClearingMemberName());
			innerDisplay.replace(stock.getStockName(), newBuyerStockObligationValue);
			this.stockObligationDisplay.replace(buyerCM.getClearingMemberName(), innerDisplay);
			
			HashMap<String, Integer> innerDisplay2  = new HashMap<>();
			innerDisplay2 = this.stockObligationDisplay.get(sellerCM.getClearingMemberName());
			innerDisplay2.replace(stock.getStockName(), newSellerStockObligationValue);
			this.stockObligationDisplay.replace(sellerCM.getClearingMemberName(), innerDisplay2);
		}
		
//		System.out.println(this.stockObligation);
//		System.out.println(this.stockObligationDisplay);

	}
	
		

}
