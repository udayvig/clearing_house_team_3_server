package com.citi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.citi.bean.OpeningStockBalance;
import com.citi.mappers.OpeningStockBalanceMapper;

@Repository
public class OpeningStockBalanceDAOImpl implements OpeningStockBalanceDAO {

	// TODO implement logger
	@Autowired
	private JdbcTemplate jdbcTemplateObject;

//	@Autowired
//	public void setDataSource(DataSource dataSource) {
//		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
//	}

	@Override
	public List<OpeningStockBalance> getAllOpeningStockBalances() {
		// TODO Auto-generated method stub
		String SQL = "select * from opening_stock_balance";
		List<OpeningStockBalance> openingStockBalances = jdbcTemplateObject.query(SQL, new OpeningStockBalanceMapper());

		return openingStockBalances;
	}
		
	@Override
	public OpeningStockBalance getOpeningStockBalance(int clearingMemberID, int stockID) {
		// TODO Auto-generated method stub
		String SQL = "select * from opening_stock_balance where clearing_member_id = ? and stock_id = ?";
		OpeningStockBalance openingStockBalance = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { clearingMemberID, stockID }, new OpeningStockBalanceMapper());

		System.out.println("openingBlanceID: " + openingStockBalance.getOpeningStockBalanceID());

		return openingStockBalance;
	}

	@Override
	public List<OpeningStockBalance> getOpeningStockBalanceByClearingMemberID(int clearingMemberID) {
		// TODO Auto-generated method stub

		String SQL = "select * from opening_stock_balance where clearing_member_id = ?";

		List<OpeningStockBalance> openingStockBalances = jdbcTemplateObject.query(SQL,
				new Object[] { clearingMemberID }, new OpeningStockBalanceMapper());

		System.out.println(openingStockBalances);

		return openingStockBalances;
	}

	@Override
	public void addOpeningStockBalance(int clearingMemberID, int stockID, int quantity) {
		// TODO Auto-generated method stub

		String SQL = "insert into opening_stock_balance (clearing_member_id, stock_id, quantity) values (?, ?, ?)";

		jdbcTemplateObject.update(SQL, clearingMemberID, stockID, quantity);
		System.out.println("Created openingStockBalnce record");

		return;

	}

	@Override
	public void updateStockBalanceQuantity(int openingStockBalanceID, int quantity) {
		// TODO Auto-generated method stub

		String SQl = "UPDATE opening_stock_balance " + "SET quantity = ? " + "WHERE opening_stock_balance_id = ?";

		jdbcTemplateObject.update(SQl, quantity, openingStockBalanceID);

		System.out.println("openingStockBalance updated");

		return;

	}

}
