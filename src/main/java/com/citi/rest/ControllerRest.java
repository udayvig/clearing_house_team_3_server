package com.citi.rest;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.displaybeans.FundObligationDisplay;
import com.citi.displaybeans.TradeDisplay;
import com.citi.service.ClearingHouseService;
import com.citi.servicebeans.FundObligation;


@RestController
public class ControllerRest {
	
	@Autowired
	private ClearingHouseService clearingHouseService;
	
	@Autowired
	private FundObligation fundObligation;

	@RequestMapping(produces = MediaType.TEXT_HTML, method = RequestMethod.GET, value = "")
	@ResponseBody
	public String index() {
		return "Hello World!";
	}
	
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
	public String interestRate() {
		return "Interest Rate is: 500";
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/opening-balance")
	@ResponseBody
	public HashMap<String, HashMap<String, Double>> openingBalance() {
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
	public String stockObligation() {
		return "Stock Obligations are: 50 USD, 25 USD, ....";
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/fund-obligation")
	@ResponseBody
	public List<FundObligationDisplay> fundObligation() {
		return fundObligation.getFundObligationDisplayList();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/post-corporate-action-stock-obligation")
	@ResponseBody
	public String postCorporateActionStockObligation() {
		return "After Corporate Actions, Stock Obligations are: 150 USD, 125 USD, ....";
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-opening-fund-balance")
	@ResponseBody
	public String cmOpeningFundBalance(@RequestParam("cmid") int id) {
		return "CM with id = " + id + " has opening funds: 500k USD, 125k USD, ...." ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-closing-fund-balance")
	@ResponseBody
	public String cmClosingFundBalance(@RequestParam("cmid") int id) {
		return "CM with id = " + id + " has closing funds: 5M USD, 12k USD, ...." ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-opening-stock-balance")
	@ResponseBody
	public String cmOpeningStockBalance(@RequestParam("cmid") int id) {
		return "CM with id = " + id + " has opening stocks: 5k USD, 15k USD, ...." ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-buy-tradebook")
	@ResponseBody
	public String cmBuyTradebook(@RequestParam("cmid") int id) {
		return "CM with id = " + id + " 's buy tradebook: ...." ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-sell-tradebook")
	@ResponseBody
	public String cmSellTradebook(@RequestParam("cmid") int id) {
		return "CM id = " + id + " 's sell tradebook: ...." ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-stock-obligation")
	@ResponseBody
	public String cmStockObligation(@RequestParam("cmid") int id) {
		return "CM with id = " + id + " 's stock obligations: 125k USD, 50k USD, ...." ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-fund-obligation")
	@ResponseBody
	public String cmFundObligation(@RequestParam("cmid") int id) {
		return "CM with id = " + id + " 's fund obligation: 225k USD" ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-cost-of-settlement")
	@ResponseBody
	public String cmCostOfSettlement(@RequestParam("cmid") int id) {
		return "CM with id = " + id + " 's cost of settlement: 0 USD" ;
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/cm-corporate-action-report")
	@ResponseBody
	public String cmCorporateActionReport(@RequestParam("cmid") int id) {
		return "CM with id = " + id + " 's post corporate action report: 625k USD, 70k USD, ...." ;
	}
}
