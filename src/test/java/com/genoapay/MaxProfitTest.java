package com.genoapay;

import static org.junit.Assert.*;
import java.security.InvalidParameterException;
import java.util.Date;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MaxProfitTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testBuyStockWorking() {
		int[] stockPrices = { 10, 7, 5, 8, 11, 9 };
		MaxProfit profit = new MaxProfit(stockPrices);

		int buyingPrice = profit.buyAt(2);

		assertEquals("Stock bought at incorrect price!", 5, buyingPrice);
	}

	@Test
	public void testMinimumStockPriceForGivenSample() {
		int[] stockPrices = { 10, 7, 5, 8, 11, 9 };
		MaxProfit profit = new MaxProfit(stockPrices);

		profit.findMaxProfitPricePair();
		int minPriceAt = profit.getMinStockPriceIndex();

		assertEquals("Found index of minimum stock price is incorrect!", 2,
				minPriceAt);
	}

	@Test
	public void testFirstOccurrenceMarkedAsMinWhenRepeatingMaxProfits() {
		int[] stockPrices = { 10, 7, 5, 4, 3, 2 };
		MaxProfit profit = new MaxProfit(stockPrices);

		profit.findMaxProfitPricePair();
		int minPriceAt = profit.getMinStockPriceIndex();

		assertEquals("Found index of minimum stock price is incorrect!", 2,
				minPriceAt);
	}

	@Test
	public void testFirstIndexMarkedAsMinWhenAcsendingPriceOrder() {
		int[] stockPrices = { 2, 3, 4, 5, 6, 7 };
		MaxProfit profit = new MaxProfit(stockPrices);

		profit.findMaxProfitPricePair();
		int minPriceAt = profit.getMinStockPriceIndex();

		assertEquals("Found index of minimum stock price is incorrect!", 0,
				minPriceAt);
	}

	@Test
	public void testBuyStockAtMinimumPrice() {
		int[] stockPrices = { 10, 7, 5, 8, 11, 9 };
		MaxProfit profit = new MaxProfit(stockPrices);

		profit.findMaxProfitPricePair();
		int minBuyingPriceAt = profit.getMinStockPriceIndex();
		int minBuyingPrice = profit.buyAt(minBuyingPriceAt);

		assertEquals("Stock isn't bought at minimum price!", 5, minBuyingPrice);
	}

	@Test
	public void testMaximumStockPriceForGivenSample() {
		int[] stockPrices = { 10, 7, 5, 8, 11, 9 };
		MaxProfit profit = new MaxProfit(stockPrices);

		profit.findMaxProfitPricePair();
		int maxPriceAt = profit.getMaxStockPriceIndex();

		assertEquals("Found index of maximum stock price is incorrect!", 4,
				maxPriceAt);
	}

	@Test
	public void testFirstOccurrenceMarkedAsMaxWhenRepeatingMaxProfits() {
		int[] stockPrices = { 10, 7, 5, 4, 3, 2 };
		MaxProfit profit = new MaxProfit(stockPrices);

		profit.findMaxProfitPricePair();
		int maxPriceAt = profit.getMaxStockPriceIndex();

		assertEquals("Found index of maximum stock price is incorrect!", 3,
				maxPriceAt);
	}

	@Test
	public void testLastIndexMarkedAsMaxWhenAcsendingPriceOrder() {
		int[] stockPrices = { 2, 3, 4, 5, 6, 7 };
		MaxProfit profit = new MaxProfit(stockPrices);

		profit.findMaxProfitPricePair();
		int maxPriceAt = profit.getMaxStockPriceIndex();

		assertEquals("Found index of maximum stock price is incorrect!", 5,
				maxPriceAt);
	}

	@Test
	public void maxAndMinPricesMustNotHaveSameTimeline() {
		int[] stockPrices = { 2, 3, 4, 5, 6, 7 };
		MaxProfit profit = new MaxProfit(stockPrices);

		profit.findMaxProfitPricePair();
		int maxPriceAt = profit.getMaxStockPriceIndex();
		int minPriceAt = profit.getMinStockPriceIndex();

		assertNotEquals(
				"Minimum and maximum prices must have 1 minute difference!",
				minPriceAt, maxPriceAt);
	}

	@Test
	public void mustBuyBeforeSell() {
		int[] stockPrices = { 10, 7, 5, 8, 11, 9 };
		MaxProfit profit = new MaxProfit(stockPrices);

		exception.expect(IllegalStateException.class);
		exception.expectMessage("You can't sell before buying a stock!");

		profit.sellAt(4);
	}

	@Test
	public void mustNotSellAtSameTimeStep() {
		int[] stockPrices = { 10, 7, 5, 8, 11, 9 };
		MaxProfit profit = new MaxProfit(stockPrices);

		exception.expect(IllegalStateException.class);
		exception
				.expectMessage("You can't sell stock within 1 minute of buying!");

		profit.getMaxProfit();
	}

	@Test
	public void mustSellAfter1Minute() {
		int[] stockPrices = { 10, 7, 5, 8, 11, 9 };
		MaxProfit profit = new MaxProfit(stockPrices,
				new com.genoapay.TimeUtils() {

					@Override
					public Date getCurrentTime() {
						return new Date();
					}

					@Override
					public boolean isMinuteElapsed(Date since) {
						return true;
					}

				});

		profit.getMaxProfit();
	}
	
	@Test
	public void testGetMaxProfitSample1() {
		int[] stockPrices = { 10, 7, 5, 8, 11, 9 };
		MaxProfit profit = new MaxProfit(stockPrices,
				new com.genoapay.TimeUtils() {

					@Override
					public Date getCurrentTime() {
						return new Date();
					}

					@Override
					public boolean isMinuteElapsed(Date since) {
						return true;
					}

				});

		int max = profit.getMaxProfit();
		assertEquals("Max profit is incorrect!", 6, max);
	}
	
	@Test
	public void testGetMaxProfitSample2() {
		int[] stockPrices = { 10, 1, 2, 3, 4, 5};
		MaxProfit profit = new MaxProfit(stockPrices,
				new com.genoapay.TimeUtils() {

					@Override
					public Date getCurrentTime() {
						return new Date();
					}

					@Override
					public boolean isMinuteElapsed(Date since) {
						return true;
					}

				});

		int max = profit.getMaxProfit();
		assertEquals("Max profit is incorrect!", 4, max);
	}
	
	@Test
	public void stockPricesMustNotBeEmpty() {
		int[] stockPrices = null;
		MaxProfit profit = new MaxProfit(stockPrices);
		
		exception.expect(InvalidParameterException.class);
		exception
				.expectMessage("Stock prices are empty!");
		
		profit.getMaxProfit();
	}
	
	@Test
	public void numberOfPricesMustBeGreaterThan2() {
		int[] stockPrices = new int[1];
		MaxProfit profit = new MaxProfit(stockPrices);
		
		exception.expect(InvalidParameterException.class);
		exception
				.expectMessage("Stock prices are empty!");
		
		profit.getMaxProfit();
	}
	
	@Test
	public void mustNotAcceptNegativePrices() {
		int[] stockPrices = { 10, 1, 2, 3, -4, 5};
		MaxProfit profit = new MaxProfit(stockPrices);
		
		exception.expect(InvalidParameterException.class);
		exception
				.expectMessage("Negative prices are not allowed!");
		
		profit.getMaxProfit();
	}

}
