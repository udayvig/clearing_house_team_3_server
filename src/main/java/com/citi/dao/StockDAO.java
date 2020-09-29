package com.citi.dao;

import java.util.List;

import com.citi.bean.Stock;

public interface StockDAO {

// Lets the user add a new stock to the pre-existing stock list.
	public void addStock(String stockName, double borrowingRate, String corporateAction, double corporateActionFactor);

// Gives a list of all stocks traded in the clearing house.
	public List<Stock> getAllStocksList();

// Returns a particular stock identified by specified ID that is traded in the
// clearing house.
	public Stock getStock(int stockID);

// Updates the borrowing rate for the stock identified by the ID passed to the
// function.
	public void updateStockBorrowingRate(int stockID, double borrowingRate);

// Updates the corporate action for the stock identified by the ID passed to the
// function.
	public void updateStockCorporateAction(int stockID, String corporateAction, Double corporateActionFactor);

// Updates the corporate action factor
	public void updateStockCorporateActionFactor(int stockID, double corporateActionFactor);

// Updates the name of the stock (in case of mergers) identified by the ID
// passed to the function.
	public void updateStockName(int stockID, String stockName);

// Deletes the record of a particular stock identified by the ID passed to the
// function.
	public void deleteStock(int stockID);

}