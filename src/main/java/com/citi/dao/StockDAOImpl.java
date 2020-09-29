package com.citi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.citi.bean.Stock;
import com.citi.mappers.StockMapper;

@Repository
public class StockDAOImpl implements StockDAO {

// TODO Implement Logger
	@Autowired
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void addStock(String stockName, double borrowingRate, String corporateAction, double corporateActionFactor) {

		String SQL = "insert into stock (stock_name, borrowing_rate, corporate_action, corporate_action_factor) values (?, ?, ?, ?)";

		jdbcTemplateObject.update(SQL, stockName, borrowingRate, corporateAction, corporateActionFactor);
		System.out.println("Created new stock record");

		return;
	}

	@Override
	public List<Stock> getAllStocksList() {
		String SQL = "select * from stock";

		List<Stock> stockList = jdbcTemplateObject.query(SQL, new StockMapper());

		return stockList;

	}

	@Override
	public Stock getStock(int stockID) {
// TODO Auto-generated method stub
		String SQL = "select * from stock where stock_id = ?";

		Stock stock = jdbcTemplateObject.queryForObject(SQL, new Object[] { stockID }, new StockMapper());

		return stock;
	}

	@Override
	public void updateStockBorrowingRate(int stockID, double borrowingRate) {
// TODO Auto-generated method stub
		String SQl = "UPDATE stock " + "SET borrowing_rate = ? " + "WHERE stock_id = ?";

		jdbcTemplateObject.update(SQl, borrowingRate, stockID);

		System.out.println("Borrowing rate for stock updated");

		return;

	}

// @Override
// public void updateStockCorporateAction(int stockID, String corporateAction, double corporateActionFactor) {
// // TODO Auto-generated method stub
// String SQl = "UPDATE stock " + "SET corporate_action = ? " + "WHERE stock_id = ?";
//
// jdbcTemplateObject.update(SQl, corporateAction, stockID);
//
// System.out.println("Corporate Action for stock updated");
//
// }

	@Override
	public void updateStockName(int stockID, String stockName) {
// TODO Auto-generated method stub
		String SQl = "UPDATE stock " + "SET stock_name = ? " + "WHERE stock_id = ?";

		jdbcTemplateObject.update(SQl, stockName, stockID);

		System.out.println("Stock Name for stock updated");

	}

	@Override
	public void deleteStock(int stockID) {
// TODO Auto-generated method stub

		String SQl = "DELETE FROM stock WHERE stock_id = ? ";

		jdbcTemplateObject.update(SQl, stockID);

		System.out.println("Stock Name for stock deleted");

		String autoIncrementCurrentQuery = "SELECT MAX(stock_id) AS max FROM stock";
		int resetID = jdbcTemplateObject.queryForObject(autoIncrementCurrentQuery, Integer.class) + 1;
		String autoIncrementResetQuery = "ALTER TABLE stock AUTO_INCREMENT = ?";
		jdbcTemplateObject.update(autoIncrementResetQuery, resetID);
		System.out.println("Auto-increment reset complete: " + resetID);
	}

	@Override
	public void updateStockCorporateActionFactor(int stockID, double corporateActionFactor) {
// TODO Auto-generated method stub

		String SQl = "UPDATE stock " + "SET corporate_action_factor = ? " + "WHERE stock_id = ?";

		jdbcTemplateObject.update(SQl, corporateActionFactor, stockID);

		System.out.println("Corporate Action Factor for stock updated");

	}

	@Override
	public void updateStockCorporateAction(int stockID, String corporateAction, Double corporateActionFactor) {
// TODO Auto-generated method stub

		String SQl = "UPDATE stock " + "SET corporate_action = ?, corporate_action_factor = ? " + "WHERE stock_id = ?";

		jdbcTemplateObject.update(SQl, corporateAction, corporateActionFactor, stockID);

		System.out.println("Corporate Action for stock updated");

	}

}
