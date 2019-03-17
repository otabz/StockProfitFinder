package com.genoapay;

import static org.junit.Assert.*;
import org.junit.Test;

public class MaxProfitTest {
	
	@Test
	public void testBuyStockAtMinimumPrice() {
		int[] stockPrices = {10,7,5,8,11,9};
		MaxProfit profit = new MaxProfit(stockPrices);
		int minBuyingPrice = profit.buy();
		
		assertEquals(5, minBuyingPrice);
	}

}
