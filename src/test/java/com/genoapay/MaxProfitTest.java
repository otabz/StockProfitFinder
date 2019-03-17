package com.genoapay;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MaxProfitTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testBuyStockAtMinimumPrice() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		int minBuyingPrice = profit.buy();
		
		assertEquals(5, minBuyingPrice);
	}
	
	@Test
	public void youCannotSellIfYouDontBuy() {
		exception.expect(IllegalStateException.class);
		exception.expectMessage("You didn't buy a stock yet!");
		
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		profit.sell();
	}
	
	@Test
	public void youCanSellOnlyIfYouBuyFirst() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		profit.buy();
		profit.sell();
	}
	
	@Test
	public void testSellStockAtMaximumPriceAfterBuying() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		profit.buy();
		int maxSellingPrice = profit.sell();
		
		assertEquals(11, maxSellingPrice);
	}

	@Test
	public void canOnlySellAtPriceWhosTimestampGreaterThanBuyingPrice() {
	}

}
