package com.citi.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citi.bean.Trade;

public class TradeMapper implements RowMapper<Trade> {

	@Override
	public Trade mapRow(ResultSet rs, int rowNum) throws SQLException {
		Trade trade = new Trade();
		trade.setBuyerClearingMemberID(rs.getInt("buying_clearing_member"));
		trade.setPrice(rs.getDouble("price"));
		trade.setQuantity(rs.getInt("quantity"));
		trade.setSellerClearingMemberID(rs.getInt("selling_clearing_member"));
		trade.setStockID(rs.getInt("stock_id"));
		trade.setTradeID(rs.getInt("trade_id"));
		
		return trade;
	}
	
}
