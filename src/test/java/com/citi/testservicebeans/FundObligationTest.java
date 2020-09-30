package com.citi.testservicebeans;

import static org.junit.Assert.assertEquals;
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
import com.citi.bean.Trade;
import com.citi.dao.ClearingMemberDAOImpl;
import com.citi.dao.TradeDAOImpl;
import com.citi.servicebeans.FundObligation;
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes=CitiClearingHouseApplication.class)
public class FundObligationTest {
	@Mock
	TradeDAOImpl tradeDAOImplMock;
	@Mock
	ClearingMemberDAOImpl clearingMemberDAOImplMock;
	
	@InjectMocks
	FundObligation fundObligationMock;
	
	@Before 
	public void setup() {
		List<Trade> mockListOfTrades= new ArrayList<Trade>();
		Trade mockTrade= new Trade();
		List<ClearingMember> mockListOfClearingMember=new ArrayList<ClearingMember>();
		ClearingMember mockClearingMember=new ClearingMember();
		mockTrade.setTradeID(1);
		mockTrade.setBuyerClearingMemberID(3);
		mockTrade.setSellerClearingMemberID(5);
		mockTrade.setStockID(1);
		mockTrade.setQuantity(2500);
		mockTrade.setPrice(100);
		mockListOfTrades.add(0,mockTrade);
		mockTrade= new Trade();
		mockTrade.setTradeID(2);
		mockTrade.setBuyerClearingMemberID(3);
		mockTrade.setSellerClearingMemberID(2);
		mockTrade.setStockID(5);
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
		mockClearingMember.setClearingMemberName("Morgan Stanley");
		mockClearingMember.setClearingMemberOpeningFundBalance(0);
		mockListOfClearingMember.add(3,mockClearingMember);
		mockClearingMember=new ClearingMember();
		mockClearingMember.setClearingMemberID(5);
		mockClearingMember.setClearingMemberName("Deutsche Bank");
		mockClearingMember.setClearingMemberOpeningFundBalance(0);
		mockListOfClearingMember.add(4,mockClearingMember);

		when(clearingMemberDAOImplMock.getAllClearingMembers()).thenReturn(mockListOfClearingMember);
		when(tradeDAOImplMock.getAllTrades()).thenReturn(mockListOfTrades);
		
	}
	@Test
	public void testGetFundObligation() {
		
		HashMap<Integer, Double> fundObligationReference = new HashMap<>();
		
		fundObligationReference.put(1, 1212000.0);
		fundObligationReference.put(2, -1077000.0);
		fundObligationReference.put(3, -385000.0);
		fundObligationReference.put(4, 0.0);
		fundObligationReference.put(5, 250000.0);
				
		fundObligationMock.setFundObligation();
		assertEquals(fundObligationReference, fundObligationMock.getFundObligation());
	}

	@Test
	public void testGetFundObligationDisplay() {
		
	}

}
