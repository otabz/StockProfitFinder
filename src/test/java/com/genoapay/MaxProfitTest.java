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
	public void testMinimumStockPriceFoundAt() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		int minPriceAt = profit.findMinimumStockPriceAt();
		
		assertEquals("Found index of minimum stock price is incorrect!", 2, minPriceAt);
	}
	
	@Test
	public void testBuyingStockAtMinimumPrice() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		int minBuyingPriceAt = profit.findMinimumStockPriceAt();
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
	public void testMaximumStockPriceFoundAfterMinimunStockPriceTimestamp() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		int minPriceAt = profit.findMinimumStockPriceAt();
		int maxPriceAt = profit.findMaximumStockPriceAfter(minPriceAt);
		
		assertEquals("Found index of maximum stock price is incorrect!", 4, maxPriceAt);
	}
	
	@Test
	public void maximumAndMinimumStockPriceMustBeSameIfNoGreaterPriceFound() {
		int[] stockPrices = {10,7,5,3,4,2};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		int minPriceAt = profit.findMinimumStockPriceAt();
		int maxPriceAt = profit.findMaximumStockPriceAfter(minPriceAt);
		
		assertEquals("Found index of maximum stock price is incorrect!", 4, maxPriceAt);
	}
	
//	@Test
//	public void youCannotSellIfYouDontBuy() {
//		exception.expect(IllegalStateException.class);
//		exception.expectMessage("You didn't buy a stock yet!");
//		
//		int[] stockPrices = {10,7,5,8,11,9};
//		MaxProfit profit = new MaxProfit(stockPrices);
//		profit.sell();
//	}
//	
//	@Test
//	public void youCanSellOnlyIfYouBuyFirst() {
//		int[] stockPrices = {10,7,5,8,11,9};
//		MaxProfit profit = new MaxProfit(stockPrices);
//		
//		profit.buy();
//		profit.sell();
//	}
//	
//	@Test
//	public void testSellStockAtMaximumPriceAfterBuying() {
//		int[] stockPrices = {10,7,5,8,11,9};
//		MaxProfit profit = new MaxProfit(stockPrices);
//		
//		profit.buy();
//		int maxSellingPrice = profit.sell();
//		
//		assertEquals(11, maxSellingPrice);
//	}
//
//	@Test
//	public void canOnlySellAtPriceWhosTimestampGreaterThanBuyingPrice() {
//	}

}
