package com.genoapay;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MaxProfitTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testBuyingStockCorrectly() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		int buyingPrice = profit.buyAt(2);
		
		assertEquals("Stock bought at incorrect price!", 5, buyingPrice);
	}
	
	@Test
	public void testMinimumStockPriceForGivenSample() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		profit.findMaxProfitPricePair();
		int minPriceAt = profit.getMinStockPriceIndex();
		
		assertEquals("Found index of minimum stock price is incorrect!", 2, minPriceAt);
	}
	
	@Test
	public void testFirstIndexMarkedAsMinWhenRepeatingMaxProfits() {
		int[] stockPrices = {10,7,5,4,3,2};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		profit.findMaxProfitPricePair();
		int minPriceAt = profit.getMinStockPriceIndex();
		
		assertEquals("Found index of minimum stock price is incorrect!", 2, minPriceAt);
	}
	
	@Test
	public void testFirstIndexMarkedAsMinWhenAcsendingPriceOrder() {
		int[] stockPrices = {2,3,4,5,6,7};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		profit.findMaxProfitPricePair();
		int minPriceAt = profit.getMinStockPriceIndex();
		
		assertEquals("Found index of minimum stock price is incorrect!", 0, minPriceAt);
	}
	
	@Test
	public void testBuyingStockAtMinimumPrice() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		profit.findMaxProfitPricePair();
		int minBuyingPriceAt = profit.getMinStockPriceIndex();
		int minBuyingPrice = profit.buyAt(minBuyingPriceAt);
		
		assertEquals("Stock isn't bought at minimum price!", 5, minBuyingPrice);
	}
	
	@Test
	public void testSellingStockCorrectly() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		int sellingPrice = profit.sellAt(1);
		
		assertEquals("Stock sold at incorrect price!", 7, sellingPrice);
	}
	
	@Test
	public void testMaximumStockPriceForGivenSample() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		profit.findMaxProfitPricePair();
		int maxPriceAt = profit.getMaxStockPriceIndex();
		
		assertEquals("Found index of maximum stock price is incorrect!", 4, maxPriceAt);
	}
	
	@Test
	public void testFirstIndexMarkedAsMaxWhenRepeatingMaxProfits() {
		int[] stockPrices = {10,7,5,4,3,2};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		profit.findMaxProfitPricePair();
		int maxPriceAt = profit.getMaxStockPriceIndex();
		
		assertEquals("Found index of maximum stock price is incorrect!", 3, maxPriceAt);
	}
	
	@Test
	public void testLastIndexMarkedAsMaxWhenAcsendingPriceOrder() {
		int[] stockPrices = {2,3,4,5,6,7};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		profit.findMaxProfitPricePair();
		int maxPriceAt = profit.getMaxStockPriceIndex();
		
		assertEquals("Found index of maximum stock price is incorrect!", 5, maxPriceAt);
	}
	
	@Test
	public void maxAndMinPricesMustNotHaveSameTimeline() {
		int[] stockPrices = {2,3,4,5,6,7};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		profit.findMaxProfitPricePair();
		int maxPriceAt = profit.getMaxStockPriceIndex();
		int minPriceAt = profit.getMinStockPriceIndex();
		
		assertNotEquals("Minimum and maximum prices must have 1 minute difference!", minPriceAt, maxPriceAt);
	}

}
