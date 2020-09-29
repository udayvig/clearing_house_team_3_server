package com.citi.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citi.bean.Stock;

public class StockMapper implements RowMapper<Stock> {

	@Override
	public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
// TODO Auto-generated method stub
		Stock stock = new Stock();
		stock.setStockID(rs.getInt("stock_id"));
		stock.setStockName(rs.getString("stock_name"));
		stock.setBorrowingRate(rs.getDouble("borrowing_rate"));
		stock.setCorporateAction(rs.getString("corporate_action"));
		stock.setCorporateActionFactor(rs.getDouble("corporate_action_factor"));

		return stock;
	}

}
