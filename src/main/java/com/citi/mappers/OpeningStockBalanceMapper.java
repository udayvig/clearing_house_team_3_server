package com.citi.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citi.bean.OpeningStockBalance;

public class OpeningStockBalanceMapper implements RowMapper<OpeningStockBalance> {

	@Override
	public OpeningStockBalance mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		OpeningStockBalance openingStockBalance = new OpeningStockBalance();
		openingStockBalance.setClearingMenberID(rs.getInt("clearing_member_id"));
		openingStockBalance.setOpeningStockBalanceID(rs.getInt("opening_stock_balance_id"));
		openingStockBalance.setQuantity(rs.getInt("quantity"));
		openingStockBalance.setStockID(rs.getInt("stock_id"));

		return openingStockBalance;

	}

}
