package com.citi.servicebeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citi.bean.ClearingMember;
import com.citi.bean.OpeningStockBalance;
import com.citi.bean.Stock;
import com.citi.bean.Trade;
import com.citi.dao.ClearingMemberDAO;
import com.citi.dao.OpeningStockBalanceDAO;
import com.citi.dao.StockDAO;
import com.citi.dao.TradeDAO;
import com.citi.displaybeans.ClearingMemberCorporateActionReportPerStockDisplay;
import com.citi.displaybeans.StockObligationDisplay;

@Component
public class StockObligation {
	private HashMap<Integer, HashMap<Integer, Integer>> stockObligation = new HashMap<>();
	private HashMap<String, HashMap<String, Integer>> stockObligationDisplay = new HashMap<>();
	private HashMap<Integer, HashMap<Integer, Integer>> stockShortage = new HashMap<>();
	private HashMap<String, HashMap<String, Integer>> stockShortageDisplay = new HashMap<>();
	private HashMap<Integer, HashMap<Integer, Double>> stockObligationPostCorporateAction = new HashMap<>();
	private HashMap<String, HashMap<String, Double>> stockObligationPostCorporateActionDisplay = new HashMap<>(); 

	@Autowired
	private ClearingMemberDAO clearingMemberDAO;

	@Autowired
	private StockDAO stockDAO;

	@Autowired
	private TradeDAO tradeDAO;

	@Autowired
	private OpeningStockBalanceDAO openingStockBalanceDAO;

	private List<Stock> stocks;
	private List<ClearingMember> clearingMembers;
	private List<Trade> trades;
	private List<OpeningStockBalance> openingStockBalances;
	
	private HashMap<Integer, ClearingMember> accessCMs;
	private HashMap<Integer, Stock> accessStocks;
	private HashMap<Pair, Integer> accessQty;

	public HashMap<Integer, HashMap<Integer, Integer>> getStockObligation() {
		return stockObligation;
	}

	public HashMap<String, HashMap<String, Integer>> getStockObligationDisplay() {
		return stockObligationDisplay;
	}

	public HashMap<Integer, HashMap<Integer, Integer>> getStockShortage() {
		return stockShortage;
	}

	public HashMap<String, HashMap<String, Integer>> getStockShortageDisplay() {
		return stockShortageDisplay;
	}
	
	

// Init all the lists
	public void initialise() {
		this.trades = tradeDAO.getAllTrades();
		this.stocks = stockDAO.getAllStocksList();
		this.openingStockBalances = openingStockBalanceDAO.getAllOpeningStockBalances();
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

		accessCMs = new HashMap<>();
		for(ClearingMember clearingMember : clearingMembers) {
			accessCMs.put(clearingMember.getClearingMemberID(), clearingMember);
		}
		
		accessStocks = new HashMap<>();
		for(Stock stock : stocks) {
			accessStocks.put(stock.getStockID(), stock);
		}
		
		accessQty = new HashMap<>();
		for(OpeningStockBalance openingStockBalance : openingStockBalances) {
			Pair pair = new Pair(openingStockBalance.getClearingMenberID(), openingStockBalance.getStockID());
			accessQty.put(pair, openingStockBalance.getQuantity());
		}
		
		return;
	}

	public void setStockObligation() {

		this.initialise();
		
		for (Trade trade : this.trades) {

			ClearingMember buyerCM = accessCMs.get(trade.getBuyerClearingMemberID());
			ClearingMember sellerCM = accessCMs.get(trade.getSellerClearingMemberID());
			Stock stock = accessStocks.get(trade.getStockID());

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

			HashMap<String, Integer> innerDisplay = new HashMap<>();
			innerDisplay = this.stockObligationDisplay.get(buyerCM.getClearingMemberName());
			innerDisplay.replace(stock.getStockName(), newBuyerStockObligationValue);
			this.stockObligationDisplay.replace(buyerCM.getClearingMemberName(), innerDisplay);

			HashMap<String, Integer> innerDisplay2 = new HashMap<>();
			innerDisplay2 = this.stockObligationDisplay.get(sellerCM.getClearingMemberName());
			innerDisplay2.replace(stock.getStockName(), newSellerStockObligationValue);
			this.stockObligationDisplay.replace(sellerCM.getClearingMemberName(), innerDisplay2);
		}
	}
	
	public void setStockObligationPostCorporateAction() {
		setStockObligation();
		
		for(Integer cmID : this.stockObligation.keySet()) {
			HashMap<Integer, Double> map = new HashMap<>();
			HashMap<String, Double> mapDisplay = new HashMap<>();
			
			for(Integer stockID : this.stockObligation.get(cmID).keySet()) {
				/**/if(this.stockObligation.get(cmID).get(stockID) > 0) {
					map.put(stockID, this.stockObligation.get(cmID).get(stockID) * accessStocks.get(stockID).getCorporateActionFactor());
					mapDisplay.put(accessStocks.get(stockID).getStockName(), 
							this.stockObligation.get(cmID).get(stockID) * accessStocks.get(stockID).getCorporateActionFactor());
				}else{
					map.put(stockID, (double)(this.stockObligation.get(cmID).get(stockID)));
					mapDisplay.put(accessStocks.get(stockID).getStockName(), (double)(this.stockObligation.get(cmID).get(stockID)));
				}/**/
			}
			
			this.stockObligationPostCorporateAction.put(cmID, map);
			this.stockObligationPostCorporateActionDisplay.put(accessCMs.get(cmID).getClearingMemberName(), mapDisplay);
		}
	}
	
	public List<StockObligationDisplay> generateStockObligationReportPostCorporateAction() {
		this.setStockObligationPostCorporateAction();

		List<StockObligationDisplay> stockObligationPostCorporateActionDisplays = new ArrayList<>();

		for (String cmName : this.stockObligationPostCorporateActionDisplay.keySet()) {

			StockObligationDisplay temp = new StockObligationDisplay();
			temp.setName(cmName);

			temp.setApple(this.stockObligationPostCorporateActionDisplay.get(temp.getName()).get("Apple"));
			temp.setGoogle(this.stockObligationPostCorporateActionDisplay.get(temp.getName()).get("Google"));
			temp.setAmazon(this.stockObligationPostCorporateActionDisplay.get(temp.getName()).get("Amazon"));
			temp.setNetflix(this.stockObligationPostCorporateActionDisplay.get(temp.getName()).get("Netfilx"));
			temp.setFacebook(this.stockObligationPostCorporateActionDisplay.get(temp.getName()).get("Facebook"));

			stockObligationPostCorporateActionDisplays.add(temp);

		}

		return stockObligationPostCorporateActionDisplays;
	}
	
	
	public List<ClearingMemberCorporateActionReportPerStockDisplay> generateClearingMemberCorporateActionReport(int cmid) {
		this.setStockObligation();
		this.setStockObligationPostCorporateAction();
		
		List<ClearingMemberCorporateActionReportPerStockDisplay> clearingMemberCorporateActionReportPerStockDisplays = new ArrayList<>();
		
		HashMap<Integer, Integer> inner = this.stockObligation.get(cmid);
		for(Integer stockid : inner.keySet()) {
			ClearingMemberCorporateActionReportPerStockDisplay temp = new ClearingMemberCorporateActionReportPerStockDisplay();
			temp.setClearingMemberID(cmid);
			temp.setClearingMemberName(accessCMs.get(cmid).getClearingMemberName());
			temp.setStockid(stockid);
			temp.setStockName(accessStocks.get(stockid).getStockName());
			/**/temp.setCorporateActionName(accessStocks.get(stockid).getCorporateAction());
			
			temp.setOpeningBalance(accessQty.get(new Pair(cmid, stockid)));
			temp.setDailyObligation(this.stockObligation.get(cmid).get(stockid));
			temp.setNetTotal(temp.getOpeningBalance() + temp.getDailyObligation());
			/**/if(temp.getNetTotal() > 0) {
				temp.setCorporateAction((accessStocks.get(stockid).getCorporateActionFactor() - 1) * temp.getNetTotal());
			}else {
				temp.setCorporateAction(0);
			}/**/
			temp.setClosingBalance(temp.getNetTotal()+temp.getCorporateAction());
			
			clearingMemberCorporateActionReportPerStockDisplays.add(temp);
			
			
		}
		
		return clearingMemberCorporateActionReportPerStockDisplays;
	}

	public List<StockObligationDisplay> generateStockObligationReport() {
		this.setStockObligation();

		List<StockObligationDisplay> stockObligationDisplays = new ArrayList<>();

		for (String cmName : this.stockObligationDisplay.keySet()) {

			StockObligationDisplay temp = new StockObligationDisplay();
			temp.setName(cmName);

			temp.setApple(this.stockObligationDisplay.get(temp.getName()).get("Apple"));
			temp.setGoogle(this.stockObligationDisplay.get(temp.getName()).get("Google"));
			temp.setAmazon(this.stockObligationDisplay.get(temp.getName()).get("Amazon"));
			temp.setNetflix(this.stockObligationDisplay.get(temp.getName()).get("Netfilx"));
			temp.setFacebook(this.stockObligationDisplay.get(temp.getName()).get("Facebook"));

			stockObligationDisplays.add(temp);

		}

		return stockObligationDisplays;
	}

	public void setShortage() {
		
		this.initialise();
		this.setStockObligation();

		for (Integer cmID : this.stockObligation.keySet()) {

			HashMap<Integer, Integer> innerShortage = new HashMap<>();
			HashMap<String, Integer> innerShortageDisplay = new HashMap<>();

			for (Integer stockID : this.stockObligation.get(cmID).keySet()) {

				int shortage = this.stockObligation.get(cmID).get(stockID)
						+ accessQty.get(new Pair(cmID, stockID));

				if (shortage < 0) {
					shortage = Math.abs(shortage);
				} else {
					shortage = 0;
				}

				innerShortage.put(stockID, shortage);
				innerShortageDisplay.put(accessStocks.get(stockID).getStockName(), shortage);

			}

			this.stockShortage.put(cmID, innerShortage);
			this.stockShortageDisplay.put(accessCMs.get(cmID).getClearingMemberName(),
					innerShortageDisplay);

		}

		return;
	}
	
	public List<StockObligationDisplay> generateShortageReport(){
		this.setShortage();

		List<StockObligationDisplay> shortageDisplay = new ArrayList<>();

		for (String cmName : this.stockShortageDisplay.keySet()) {

			StockObligationDisplay temp = new StockObligationDisplay();
			temp.setName(cmName);

			temp.setApple(this.stockShortageDisplay.get(temp.getName()).get("Apple"));
			temp.setGoogle(this.stockShortageDisplay.get(temp.getName()).get("Google"));
			temp.setAmazon(this.stockShortageDisplay.get(temp.getName()).get("Amazon"));
			temp.setNetflix(this.stockShortageDisplay.get(temp.getName()).get("Netfilx"));
			temp.setFacebook(this.stockShortageDisplay.get(temp.getName()).get("Facebook"));

			shortageDisplay.add(temp);

		}

		return shortageDisplay;
	}
	
	public StockObligationDisplay generateClearingMemberStockObligationReport(int cmid) {
		this.setStockObligation();
		StockObligationDisplay temp = new StockObligationDisplay();
		
		temp.setName(accessCMs.get(cmid).getClearingMemberName());
		
		temp.setApple(this.stockObligationDisplay.get(temp.getName()).get("Apple"));
		temp.setGoogle(this.stockObligationDisplay.get(temp.getName()).get("Google"));
		temp.setAmazon(this.stockObligationDisplay.get(temp.getName()).get("Amazon"));
		temp.setNetflix(this.stockObligationDisplay.get(temp.getName()).get("Netfilx"));
		temp.setFacebook(this.stockObligationDisplay.get(temp.getName()).get("Facebook"));

		return temp;
	}

	public class Pair{
		private int cmID;
		private int stockID;
		
		Pair(int cmID, int stockID){
			this.cmID = cmID;
			this.stockID = stockID;
		}

		public void setCmID(int cmID) {
			this.cmID = cmID;
		}

		public void setStockID(int stockID) {
			this.stockID = stockID;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + cmID;
			result = prime * result + stockID;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (cmID != other.cmID)
				return false;
			if (stockID != other.stockID)
				return false;
			return true;
		}

		private StockObligation getEnclosingInstance() {
			return StockObligation.this;
		}
	}
}
