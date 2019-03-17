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
	public void youCanSellOnlyIfYouBuyAStockFirst() {
		exception.expect(IllegalStateException.class);
		exception.expectMessage("You didn't buy a stock yet!");
		
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		profit.sell();
	}

}
