package com.citi.testservicebeans;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.citi.app.CitiClearingHouseApplication;
import com.citi.bean.ClearingMember;
import com.citi.bean.OpeningStockBalance;
import com.citi.bean.Stock;
import com.citi.bean.Trade;
import com.citi.dao.ClearingMemberDAOImpl;
import com.citi.dao.OpeningStockBalanceDAOImpl;
import com.citi.dao.StockDAOImpl;
import com.citi.dao.TradeDAOImpl;
import com.citi.servicebeans.StockObligation;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=CitiClearingHouseApplication.class)
public class StockObligationTest {

	@Mock
	TradeDAOImpl tradeDAOImplMock;
	@Mock
	ClearingMemberDAOImpl clearingMemberDAOImplMock;
	@Mock
	StockDAOImpl stockDAOImplMock;
	@Mock
	OpeningStockBalanceDAOImpl openingStockBalanceDAOImplMock;
	
	@InjectMocks
	StockObligation stockObligationMock;
	@Before
	public void setup() {

		List<Trade> mockListOfTrades= new ArrayList<Trade>();
		Trade mockTrade= new Trade();
		List<ClearingMember> mockListOfClearingMember=new ArrayList<ClearingMember>();
		ClearingMember mockClearingMember=new ClearingMember();
		List<Stock> mockListOfStock=new ArrayList<Stock>();
		Stock mockStock=new Stock();
		List<OpeningStockBalance> mockListOfOpeningStockBalance=new ArrayList<OpeningStockBalance>();
		OpeningStockBalance mockOpeningStockBalance=new OpeningStockBalance();
		
		mockTrade.setTradeID(1);
		mockTrade.setBuyerClearingMemberID(3);
		mockTrade.setSellerClearingMemberID(4);
		mockTrade.setStockID(1);
		mockTrade.setQuantity(2500);
		mockTrade.setPrice(100);
		mockListOfTrades.add(0,mockTrade);
		mockTrade= new Trade();
		mockTrade.setTradeID(2);
		mockTrade.setBuyerClearingMemberID(3);
		mockTrade.setSellerClearingMemberID(2);
		mockTrade.setStockID(2);
		mockTrade.setQuantity(3000);
		mockTrade.setPrice(45);
		mockListOfTrades.add(1,mockTrade);
		mockTrade= new Trade();
		mockTrade.setTradeID(3);
		mockTrade.setBuyerClearingMemberID(2);
		mockTrade.setSellerClearingMemberID(1);
		mockTrade.setStockID(1);
		mockTrade.setQuantity(12000);
		mockTrade.setPrice(101);
		mockListOfTrades.add(2,mockTrade);
		
		mockClearingMember.setClearingMemberID(1);
		mockClearingMember.setClearingMemberName("Citi");
		mockClearingMember.setClearingMemberOpeningFundBalance(0);
		mockListOfClearingMember.add(0,mockClearingMember);
		mockClearingMember=new ClearingMember();
		mockClearingMember.setClearingMemberID(2);
		mockClearingMember.setClearingMemberName("JP Morgan");
		mockClearingMember.setClearingMemberOpeningFundBalance(0);
		mockListOfClearingMember.add(1,mockClearingMember);
		mockClearingMember=new ClearingMember();
		mockClearingMember.setClearingMemberID(3);
		mockClearingMember.setClearingMemberName("Goldman Sachs");
		mockClearingMember.setClearingMemberOpeningFundBalance(0);
		mockListOfClearingMember.add(2,mockClearingMember);
		mockClearingMember=new ClearingMember();
		mockClearingMember.setClearingMemberID(4);
		mockClearingMember.setClearingMemberName("Deutsche Bank");
		mockClearingMember.setClearingMemberOpeningFundBalance(0);
		mockListOfClearingMember.add(3,mockClearingMember);
		
		mockStock.setStockID(1);
		mockStock.setStockName("Apple");
		mockStock.setBorrowingRate(0.5);
		mockStock.setCorporateAction("Bonus");
		mockStock.setCorporateActionFactor(2.0);
		mockListOfStock.add(mockStock);
		mockStock=new Stock();
		mockStock.setStockID(2);
		mockStock.setStockName("Facebook");
		mockStock.setBorrowingRate(0.3);
		mockStock.setCorporateAction("None");
		mockStock.setCorporateActionFactor(1.0);
		mockListOfStock.add(mockStock);
		mockStock=new Stock();
		
		mockOpeningStockBalance.setOpeningStockBalanceID(1);
		mockOpeningStockBalance.setClearingMenberID(1);
		mockOpeningStockBalance.setStockID(1);
		mockOpeningStockBalance.setQuantity(0);
		mockListOfOpeningStockBalance.add(mockOpeningStockBalance);
		mockOpeningStockBalance=new OpeningStockBalance();
		mockOpeningStockBalance.setOpeningStockBalanceID(2);
		mockOpeningStockBalance.setClearingMenberID(1);
		mockOpeningStockBalance.setStockID(2);
		mockOpeningStockBalance.setQuantity(0);
		mockListOfOpeningStockBalance.add(mockOpeningStockBalance);
		mockOpeningStockBalance=new OpeningStockBalance();
		
		mockOpeningStockBalance.setOpeningStockBalanceID(3);
		mockOpeningStockBalance.setClearingMenberID(2);
		mockOpeningStockBalance.setStockID(1);
		mockOpeningStockBalance.setQuantity(0);
		mockListOfOpeningStockBalance.add(mockOpeningStockBalance);
		mockOpeningStockBalance=new OpeningStockBalance();
		mockOpeningStockBalance.setOpeningStockBalanceID(4);
		mockOpeningStockBalance.setClearingMenberID(2);
		mockOpeningStockBalance.setStockID(2);
		mockOpeningStockBalance.setQuantity(0);
		mockListOfOpeningStockBalance.add(mockOpeningStockBalance);
		mockOpeningStockBalance=new OpeningStockBalance();
		
		mockOpeningStockBalance.setOpeningStockBalanceID(5);
		mockOpeningStockBalance.setClearingMenberID(3);
		mockOpeningStockBalance.setStockID(1);
		mockOpeningStockBalance.setQuantity(0);
		mockListOfOpeningStockBalance.add(mockOpeningStockBalance);
		mockOpeningStockBalance=new OpeningStockBalance();
		mockOpeningStockBalance.setOpeningStockBalanceID(6);
		mockOpeningStockBalance.setClearingMenberID(3);
		mockOpeningStockBalance.setStockID(2);
		mockOpeningStockBalance.setQuantity(0);
		mockListOfOpeningStockBalance.add(mockOpeningStockBalance);
		mockOpeningStockBalance=new OpeningStockBalance();
		
		mockOpeningStockBalance.setOpeningStockBalanceID(7);
		mockOpeningStockBalance.setClearingMenberID(4);
		mockOpeningStockBalance.setStockID(1);
		mockOpeningStockBalance.setQuantity(0);
		mockListOfOpeningStockBalance.add(mockOpeningStockBalance);
		mockOpeningStockBalance=new OpeningStockBalance();
		mockOpeningStockBalance.setOpeningStockBalanceID(8);
		mockOpeningStockBalance.setClearingMenberID(4);
		mockOpeningStockBalance.setStockID(2);
		mockOpeningStockBalance.setQuantity(0);
		mockListOfOpeningStockBalance.add(mockOpeningStockBalance);
		mockOpeningStockBalance=new OpeningStockBalance();
		
		when(stockDAOImplMock.getAllStocksList()).thenReturn(mockListOfStock);
		when(openingStockBalanceDAOImplMock.getAllOpeningStockBalances()).thenReturn(mockListOfOpeningStockBalance);
		when(tradeDAOImplMock.getAllTrades()).thenReturn(mockListOfTrades);
		when(clearingMemberDAOImplMock.getAllClearingMembers()).thenReturn(mockListOfClearingMember);
		
	}
	@Test
	public void testGetStockObligation() {
		HashMap<Integer, HashMap<Integer, Integer>> stockObligationReference = new HashMap<>();
		HashMap<Integer, Integer> inner = new HashMap<>();
		
		inner.put(1, -12000);
		inner.put(2, 0);
		stockObligationReference.put(1, inner);
		inner=new HashMap<>();
		
		inner.put(1, 12000);
		inner.put(2, -3000);
		stockObligationReference.put(2, inner);
		inner=new HashMap<>();
		
		inner.put(1, 2500);
		inner.put(2, 3000);
		stockObligationReference.put(3, inner);
		inner=new HashMap<>();
		
		inner.put(1, -2500);
		inner.put(2, 0);
		stockObligationReference.put(4, inner);
		inner=new HashMap<>();
		
		stockObligationMock.initialise();
		stockObligationMock.setStockObligation();
		assertEquals(stockObligationReference, stockObligationMock.getStockObligation());
	}

}
