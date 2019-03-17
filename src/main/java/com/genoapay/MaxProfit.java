package com.genoapay;

import java.util.Date;

public class MaxProfit {
	/**
	 * Assumptions: As it is an array of primitive integers and indices
	 * represent minutes past so, order of time will be ascending, time will
	 * never be in negative numbers, and there is always an interval of 1 minute
	 * between times
	 */
	int[] stockPrices;
	private Integer minStockPriceIndex;
	private Integer maxStockPriceIndex;
	private Date buyingTime;
	private TimeUtils time;

	// external dependency must be very clear, i.e. stock prices
	public MaxProfit(int[] stockPrices) {
		this.stockPrices = stockPrices;
		this.time = new TimeUtils.TimeUtilsJavaSystem();
	}

	public MaxProfit(int[] stockPrices, TimeUtils mockedUtils) {
		this.stockPrices = stockPrices;
		this.time = mockedUtils;
	}

	public int buyAt(int index) {
		this.buyingTime = this.time.getCurrentTime();
		return this.stockPrices[index];
	}

	public void findMaxProfitPricePair() {
		int maxProfit = Integer.MIN_VALUE;
		// lets start with naive approach
		for (int i = 0; i < this.stockPrices.length; i++) {
			for (int j = i + 1; j < this.stockPrices.length; j++) {
				int left = this.stockPrices[i];
				int right = this.stockPrices[j];
				int diff = right - left;
				if (diff > maxProfit) {
					maxProfit = diff;
					this.minStockPriceIndex = i;
					this.maxStockPriceIndex = j;
				}
			}
		}
	}

	public int sellAt(int price) {
		if (null == this.minStockPriceIndex || null == this.maxStockPriceIndex
				|| null == this.buyingTime) {
			throw new IllegalStateException(
					"You can't sell before buying a stock!");
		}
		boolean elapsed = this.time.isMinuteElapsed(this.buyingTime);
		if (!elapsed) {
			throw new IllegalStateException(
					"You can't sell stock within 1 minute of buying!");
		}
		return this.stockPrices[price];
	}

	public int getMaxProfit() {
		findMaxProfitPricePair();
		buyAt(getMinStockPriceIndex());
		sellAt(getMaxStockPriceIndex());
		int max = this.stockPrices[getMaxStockPriceIndex()]
				- this.stockPrices[getMinStockPriceIndex()];
		return max;
	}

	public int getMinStockPriceIndex() {
		return this.minStockPriceIndex;
	}

	public int getMaxStockPriceIndex() {
		return this.maxStockPriceIndex;
	}

}
