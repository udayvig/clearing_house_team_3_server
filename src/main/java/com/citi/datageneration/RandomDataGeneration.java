package com.citi.datageneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citi.bean.Trade;
import com.citi.dao.ClearingMemberDAO;
import com.citi.dao.StockDAO;
import com.citi.dao.TradeDAOImpl;

@Component
public class RandomDataGeneration {
	
	private Random randomGenerator = new Random();
	
	//DAOObj Stock and CM
	
	@Autowired
	private TradeDAOImpl tradeDAO;
	
	@Autowired
	private ClearingMemberDAO clearingMemberDAO;
	
	@Autowired
	private StockDAO stockDAO;
	
	//Trades
	int minForIDs = 1;
	int maxForStockIDs = /*stockDAO.getAllStocksList().size() + 1;*/ 6;
	int maxForClearingMemberIDs = /*clearingMemberDAO.getAllClearingMembers().size() + 1;*/ 6;
	int minForQuantity = 10000;
	int maxForQuantity = 500001;
	
	//Stock
	int minForStockQuantity = 10000;
	int maxForStockQuantity = 500001;
	
	public List<Trade> generateTrades(int numberOfTrades){
		List<Trade> trades = new ArrayList<>();
		for(int i = 0; i < numberOfTrades; i++) {
			Trade trade = generateTrade();
			trades.add(trade);
//			tradeDAO.addTrade(trade.getBuyerClearingMemberID(), trade.getSellerClearingMemberID(), 
//					trade.getPrice(), trade.getQuantity(), trade.getStockID());
		}
		
		return trades;
	}
	
	private Trade generateTrade(){
		Trade trade = new Trade();
		
		int stockID = minForIDs + randomGenerator.nextInt(maxForStockIDs - minForIDs);
		int buyerClearingMemberID = minForIDs + randomGenerator.nextInt(maxForClearingMemberIDs - minForIDs);
		int sellerClearingMemberID = minForIDs + randomGenerator.nextInt(maxForClearingMemberIDs - minForIDs);
		int quantity = minForQuantity + randomGenerator.nextInt(maxForQuantity - minForQuantity);
		double price = ThreadLocalRandom.current().nextDouble(0, 1000);
		
		trade.setBuyerClearingMemberID(buyerClearingMemberID);
		trade.setSellerClearingMemberID(sellerClearingMemberID);
		trade.setStockID(stockID);
		trade.setQuantity(quantity);
		trade.setPrice(price);
		
		tradeDAO.addTrade(buyerClearingMemberID, sellerClearingMemberID, price, quantity, stockID);
		return trade;
	}
	
	private void generateOpeningStockBalances(){
		int balance = 0;
		//List<OpeningStockBalance> openingStockBalanceList;
		
		for(int i = 0; i < maxForClearingMemberIDs; i++) {
			for(int j = 0; j < maxForStockIDs; j++) {
				balance = generateOpeningStockBalance(i, j);
				//OpeningStockBalance temp = new OpeningStockBalance()
				//temp.setCMID, .setStockID, .setBalance
				//openingStockBalanceList.add(temp);
			}
		}
		
		//DAO.add in for loop
	}
	
	private int generateOpeningStockBalance(int clearingMemberID, int stockID){
		return minForStockQuantity + (randomGenerator.nextInt() * (maxForStockQuantity - minForStockQuantity));
	}
	
	public void generateOpeningFundBalances() {
		double balance = 0;
		
		for(int i = 0; i < maxForClearingMemberIDs; i++) {
			balance = generateOpeningFundBalance(i);
			/*
			 * list.get(i).setOpeningBalance(balance); 
			 */
		}
		
		
	}
	
	private double generateOpeningFundBalance(int clearingMemberID) {
		return ThreadLocalRandom.current().nextDouble(-10000000, 10000000);
	}
}
