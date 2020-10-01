package com.citi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.citi.bean.Trade;
import com.citi.mappers.TradeMapper;

@Repository
public class TradeDAOImpl implements TradeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void addTrade(int buyerClearingMemberID, int sellerClearingMemberID, double price, int quantity,
			int stockID) {
		String SQL = "insert into trade (buying_clearing_member, selling_clearing_member, stock_id, quantity, price) "
				+ "values (?, ?, ?, ?, ?)";

		jdbcTemplateObject.update(SQL, buyerClearingMemberID, sellerClearingMemberID, stockID, quantity, price);
	}
	
	@Override
	public Trade getTradeByTradeID(int tradeID) {
		String SQL = "select * from trade where trade_id = ?";

		Trade trade = jdbcTemplateObject.queryForObject(SQL, new Object[] { tradeID }, new TradeMapper());
		return trade;
	}

	@Override
	public List<Trade> getTradesByStockID(int stockID) {
		String SQL = "select * from trade where stock_id = ?";

		List<Trade> trades = jdbcTemplateObject.query(SQL, new Object[] { stockID }, new TradeMapper());
		return trades;
	}

	@Override
	public List<Trade> getTradesByBuyingClearingMemberID(int buyerClearingMemberID) {
		String SQL = "select * from trade where buying_clearing_member = ?";

		List<Trade> trades = jdbcTemplateObject.query(SQL, new Object[] { buyerClearingMemberID }, new TradeMapper());
		return trades;
	}

	@Override
	public List<Trade> getTradesBySellingClearingMemberID(int sellerClearingMemberID) {
		String SQL = "select * from trade where selling_clearing_member = ?";

		List<Trade> trades = jdbcTemplateObject.query(SQL, new Object[] { sellerClearingMemberID }, new TradeMapper());
		return trades;	
	}
	
	@Override
	public List<Trade> getTradesByClearingMemberID(int clearingMemberID){
		String SQL = "select * from trade where buying_clearing_member = ? or selling_clearing_member = ?";
		List<Trade> trades = jdbcTemplateObject.query(SQL, new Object[] {clearingMemberID, clearingMemberID}, new TradeMapper());
		return trades;
	}

	@Override
	public List<Trade> getAllTrades() {
		String SQL = "select * from trade";

		List<Trade> trades = jdbcTemplateObject.query(SQL, new TradeMapper());
		return trades;
	}

	@Override
	public void deleteTrade(int tradeID) {
		// TODO Auto-generated method stub
		String SQL = "delete from trade where trade_id = ?";
		jdbcTemplateObject.update(SQL, tradeID);
	}
	
	@Override
	public void deleteAllTrades() {
		String SQL = "truncate trade";
		jdbcTemplateObject.update(SQL);
	}
}
