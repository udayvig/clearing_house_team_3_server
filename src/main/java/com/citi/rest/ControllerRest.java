package com.citi.rest;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.bean.ClearingMember;
import com.citi.dao.ClearingMemberDAO;
import com.citi.dao.TradeDAO;
import com.citi.displaybeans.ClearingMemberCorporateActionReportPerStockDisplay;
import com.citi.displaybeans.CostSettlementDisplay;
import com.citi.displaybeans.FundObligationDisplay;
import com.citi.displaybeans.OpeningBalanceDisplay;
import com.citi.displaybeans.StockObligationDisplay;
import com.citi.displaybeans.TradeBookDisplay;
import com.citi.displaybeans.TradeDisplay;
import com.citi.service.ClearingHouseService;
import com.citi.service.ClearingMemberService;
import com.citi.servicebeans.CostSettlement;
import com.citi.servicebeans.FundObligation;
import com.citi.servicebeans.StockObligation;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*") 
public class ControllerRest {
	
	@Autowired
	private ClearingHouseService clearingHouseService;
	
	@Autowired
	private ClearingMemberService clearingMemberService;
	
	@Autowired
	private StockObligation stockObligation;

	@Autowired
	private FundObligation fundObligation;
	
	@Autowired
	private CostSettlement costSettlement;
	
	@Autowired
	private ClearingMemberDAO clearingMemberDAO;
	
	@Autowired
	private TradeDAO tradeDAO;
	
	@RequestMapping("*")
	@ResponseBody
	public String notFound() {
		return "Resource not found.";
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/trade-volume")
	@ResponseBody
	public HashMap<String, Integer> tradeVolume() {
		return clearingHouseService.getTradeVolume();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/interest-rate")
	@ResponseBody
	public HashMap<String, Double> interestRate() {
		return clearingHouseService.getFundInterestRate();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/opening-balance")
	@ResponseBody
	public List<OpeningBalanceDisplay> openingBalance() {
		return clearingHouseService.getOpeningBalance();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/stock-borrowing-rate")
	@ResponseBody
	public HashMap<String, Double> stockBorrowingRate() {
		return clearingHouseService.getStockBorrowingRate();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/tradebook")
	@ResponseBody
	public List<TradeDisplay> tradebook() {
		return clearingHouseService.getTradeBook();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/stock-obligation")
	@ResponseBody
	public List<StockObligationDisplay> stockObligation() {
		return stockObligation.generateStockObligationReport();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/stock-shortage")
	@ResponseBody
	public List<StockObligationDisplay> stockShortage() {
		return stockObligation.generateShortageReport();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/fund-obligation")
	@ResponseBody
	public List<FundObligationDisplay> fundObligation() {
		fundObligation.setFundObligationDisplayList();
		return fundObligation.getFundObligationDisplayList();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/post-corporate-action-stock-obligation")
	@ResponseBody
	public List<StockObligationDisplay> postCorporateActionStockObligation() {
		return stockObligation.generateStockObligationReportPostCorporateAction();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/add-trade")
	@ResponseBody
	public void addTrade(@RequestParam("bcm_id") int buyerClearingMemberID, @RequestParam("scm_id") int sellerClearingMemberID,
			@RequestParam("price") double price, @RequestParam("qty") int quantity, @RequestParam("stock_id") int stockID) {
		tradeDAO.addTrade(buyerClearingMemberID, sellerClearingMemberID, price, quantity, stockID);
	}
	
//	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-opening-fund-balance")
//	@ResponseBody
//	public ClearingMember cmOpeningFundBalance(@RequestParam("cmid") int id) {
//		return clearingMemberService.getOpeningFundBalance(id);
//	}
//	
//	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-opening-stock-balance")
//	@ResponseBody
//	public OpeningBalanceDisplay cmOpeningStockBalance(@RequestParam("cmid") int id) {
//		return clearingMemberService.getOpeningStockBalance(id);
//	}
//	
//	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-buy-tradebook")
//	@ResponseBody
//	public List<TradeDisplay> cmBuyTradebook(@RequestParam("cmid") int id) {
//		return clearingMemberService.getCmBuyTradebook(id) ;
//	}
//	
//	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-sell-tradebook")
//	@ResponseBody
//	public List<TradeDisplay> cmSellTradebook(@RequestParam("cmid") int id) {
//		return clearingMemberService.getCmSellTradebook(id) ;
//	}
//	
//	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-tradebook")
//	@ResponseBody
//	public List<TradeBookDisplay> cmTradebook(@RequestParam("cmid") int id) {
//		return clearingMemberService.getCmTradebook(id) ;
//	}
//	
//	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-stock-obligation")
//	@ResponseBody
//	public StockObligationDisplay cmStockObligation(@RequestParam("cmid") int id) {
//		return stockObligation.generateClearingMemberStockObligationReport(id);
//	}
//	
//	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-fund-obligation")
//	@ResponseBody
//	public double cmFundObligation(@RequestParam("cmid") int id) {
//		return fundObligation.getClearingMemberFundObligation(id);
//	}
//	
//	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-cost-of-settlement")
//	@ResponseBody
//	public CostSettlementDisplay cmCostOfSettlement(@RequestParam("cmid") int id) {
//		return costSettlement.generateCostSettlementReport(id);
//	}
//	
//	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-corporate-action-report")
//	@ResponseBody
//	public List<ClearingMemberCorporateActionReportPerStockDisplay> cmCorporateActionReport(@RequestParam("cmid") int id) {
//		return stockObligation.generateClearingMemberCorporateActionReport(id);
//	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-opening-fund-balance")
	@ResponseBody
	public ClearingMember cmOpeningFundBalance(@RequestParam("cmid") String token) {
		int id = clearingMemberDAO.getClearingMemberFromToken(token).getClearingMemberID();
		return clearingMemberService.getOpeningFundBalance(id);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-opening-stock-balance")
	@ResponseBody
	public OpeningBalanceDisplay cmOpeningStockBalance(@RequestParam("cmid") String token) {
		int id = clearingMemberDAO.getClearingMemberFromToken(token).getClearingMemberID();
		return clearingMemberService.getOpeningStockBalance(id);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-buy-tradebook")
	@ResponseBody
	public List<TradeDisplay> cmBuyTradebook(@RequestParam("cmid") String token) {
		int id = clearingMemberDAO.getClearingMemberFromToken(token).getClearingMemberID();
		return clearingMemberService.getCmBuyTradebook(id) ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-sell-tradebook")
	@ResponseBody
	public List<TradeDisplay> cmSellTradebook(@RequestParam("cmid") String token) {
		int id = clearingMemberDAO.getClearingMemberFromToken(token).getClearingMemberID();
		return clearingMemberService.getCmSellTradebook(id) ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-tradebook")
	@ResponseBody
	public List<TradeBookDisplay> cmTradebook(@RequestParam("cmid") String token) {
		int id = clearingMemberDAO.getClearingMemberFromToken(token).getClearingMemberID();
		return clearingMemberService.getCmTradebook(id) ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-stock-obligation")
	@ResponseBody
	public StockObligationDisplay cmStockObligation(@RequestParam("cmid") String token) {
		int id = clearingMemberDAO.getClearingMemberFromToken(token).getClearingMemberID();
		return stockObligation.generateClearingMemberStockObligationReport(id);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-fund-obligation")
	@ResponseBody
	public double cmFundObligation(@RequestParam("cmid") String token) {
		int id = clearingMemberDAO.getClearingMemberFromToken(token).getClearingMemberID();
		return fundObligation.getClearingMemberFundObligation(id);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-cost-of-settlement")
	@ResponseBody
	public CostSettlementDisplay cmCostOfSettlement(@RequestParam("cmid") String token) {
		int id = clearingMemberDAO.getClearingMemberFromToken(token).getClearingMemberID();
		return costSettlement.generateCostSettlementReport(id);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-corporate-action-report")
	@ResponseBody
	public List<ClearingMemberCorporateActionReportPerStockDisplay> cmCorporateActionReport(@RequestParam("cmid") String token) {
		int id = clearingMemberDAO.getClearingMemberFromToken(token).getClearingMemberID();
		return stockObligation.generateClearingMemberCorporateActionReport(id);
	}
}
