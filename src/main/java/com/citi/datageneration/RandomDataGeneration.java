package com.citi.datageneration;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citi.bean.ClearingHouse;
import com.citi.bean.Trade;
import com.citi.dao.ClearingMemberDAO;
import com.citi.dao.OpeningStockBalanceDAO;
import com.citi.dao.StockDAO;
import com.citi.dao.TradeDAO;

@Component
public class RandomDataGeneration {
	
	private Random randomGenerator = new Random();
	DecimalFormat df = new DecimalFormat("#.##");
	
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
	
	//Trades
	int minForIDs = 1;
	int maxForStockIDs = 0;
	int maxForClearingMemberIDs = 0;
	int minForQuantity = 10000;
	int maxForQuantity = 100001;
	
	//Opening Stock Balances
	int minForStockQuantity = -10000;
	int maxForStockQuantity = 10001;
	
	//Corporate Actions
	int maxForCorporateActions = 3;
	
	public void initialise() {
		maxForStockIDs = stockDAO.getAllStocksList().size() + 1;
		maxForClearingMemberIDs = clearingMemberDAO.getAllClearingMembers().size() + 1;
	}
	
	public List<Trade> generateTrades(int numberOfTrades){
		List<Trade> trades = new ArrayList<>();
		for(int i = 0; i < numberOfTrades; i++) {
			Trade trade = generateTrade();
			trades.add(trade);
		}
		
		return trades;
	}
	
	private Trade generateTrade(){
		df.setRoundingMode(RoundingMode.DOWN);
		Trade trade = new Trade();
		
		int stockID = minForIDs + randomGenerator.nextInt(maxForStockIDs - minForIDs);
		int buyerClearingMemberID = minForIDs + randomGenerator.nextInt(maxForClearingMemberIDs - minForIDs);
		int sellerClearingMemberID = minForIDs + randomGenerator.nextInt(maxForClearingMemberIDs - minForIDs);
		int quantity = minForQuantity + randomGenerator.nextInt(maxForQuantity - minForQuantity);
		double generatedPrice = ThreadLocalRandom.current().nextDouble(0, 1000);
		
		double price = new Double(df.format(generatedPrice));
		
		trade.setBuyerClearingMemberID(buyerClearingMemberID);
		trade.setSellerClearingMemberID(sellerClearingMemberID);
		trade.setStockID(stockID);
		trade.setQuantity(quantity);
		trade.setPrice(price);
		
		tradeDAO.addTrade(buyerClearingMemberID, sellerClearingMemberID, price, quantity, stockID);
		return trade;
	}
	
	public void generateOpeningStockBalances(){
		int balance = 0;
		
		for(int i = 1; i < maxForClearingMemberIDs; i++) {
			for(int j = 1; j < maxForStockIDs; j++) {
				balance = generateOpeningStockBalance();
				openingStockBalanceDAO.addOpeningStockBalance(i, j, balance);
			}
		}
	}
	
	private int generateOpeningStockBalance(){
		return ThreadLocalRandom.current().nextInt(minForStockQuantity, maxForStockQuantity);
	}
	
	public void generateOpeningFundBalances() {
		double balance = 0, generatedBalance = 0;
		
		for(int i = 1; i < maxForClearingMemberIDs; i++) {
			df.setRoundingMode(RoundingMode.DOWN);
			generatedBalance = generateOpeningFundBalance(i);
			
			balance = new Double(df.format(generatedBalance));
			clearingMemberDAO.updateClearingMemberFundBalance(i, balance);
		}
	}
	
	private double generateOpeningFundBalance(int clearingMemberID) {
		return ThreadLocalRandom.current().nextDouble(-10000000, 10000000);
	}
	
	public void generateStockBorrowingRates() {
		df.setRoundingMode(RoundingMode.DOWN);
		double borrowingRate = 0, generatedBorrowingRate = 0;
		
		for(int i = 1; i < maxForStockIDs; i++) {
			generatedBorrowingRate = generateStockBorrowingRate();
			
			borrowingRate = new Double(df.format(generatedBorrowingRate));
			stockDAO.updateStockBorrowingRate(i, borrowingRate);
		}
	}
	
	private double generateStockBorrowingRate() {
		return ThreadLocalRandom.current().nextDouble(1, 10);
	}
	
	public void generateCorporateActions() {
		int corporateAction = 0;
		
		for(int i = 1; i < maxForStockIDs; i++) {
			corporateAction = generateCorporateAction();
			//stockDAO.updateStockCorporateAction(i, corporateAction);
		}
	}
	
	private int generateCorporateAction() {
		return randomGenerator.nextInt(maxForCorporateActions);
	}
	
	public void generateInterestRateForFunds() {
		df.setRoundingMode(RoundingMode.DOWN);
		double generatedFundsBorrowingRate = ThreadLocalRandom.current().nextDouble(1, 20);
		double fundsBorrowingRate = new Double(df.format(generatedFundsBorrowingRate));
		clearingHouse.setFundBorrowingRate(fundsBorrowingRate/100);
	}
}
