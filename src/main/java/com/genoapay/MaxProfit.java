package com.genoapay;

import java.security.InvalidParameterException;
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
		int diff = 0;
		// ~ O(n)
		int j = this.stockPrices.length-2;
		for(int i = this.stockPrices.length-1; i>=0; i--) {
			notNegative(this.stockPrices[i]);
			if(i > j)
				diff = this.stockPrices[i] - this.stockPrices[j];
			else if(i < j)
				diff = this.stockPrices[j] - this.stockPrices[i];
			else 
				continue;
			
			if(diff >= maxProfit) {
				maxProfit = diff;
				this.maxStockPriceIndex = j;
				this.minStockPriceIndex = i;
			}
			
			if(this.stockPrices[i] > this.stockPrices[j])
				j = i;
		}
// ~ O(n^2)		
//		for (int i = 0; i < this.stockPrices.length; i++) {
//			for (int j = i + 1; j < this.stockPrices.length; j++) {
//				int left = this.stockPrices[i];
//				int right = this.stockPrices[j];
//				int diff = right - left;
//				if (diff > maxProfit) {
//					maxProfit = diff;
//					this.minStockPriceIndex = i;
//					this.maxStockPriceIndex = j;
//				}
//			}
//		}
	}
	
	private void notNegative(int value) {
		if(value < 0) {
			throw new InvalidParameterException("Negative prices are not allowed!");
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
		notEmpty();
		findMaxProfitPricePair();
		buyAt(getMinStockPriceIndex());
		sellAt(getMaxStockPriceIndex());
		int max = this.stockPrices[getMaxStockPriceIndex()]
				- this.stockPrices[getMinStockPriceIndex()];
		return max;
	}
	
	private void notEmpty() {
		if(null == this.stockPrices || this.stockPrices.length < 2) {
			throw new InvalidParameterException("Stock prices are empty!");
		}
	}

	public int getMinStockPriceIndex() {
		return this.minStockPriceIndex;
	}

	public int getMaxStockPriceIndex() {
		return this.maxStockPriceIndex;
	}

}
