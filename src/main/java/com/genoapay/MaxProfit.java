package com.genoapay;

public class MaxProfit {
	/**
	 * Assumptions: As it is an array of primitive integers and indices
	 * represent minutes past so, order of time will be ascending, time will
	 * never be in negative numbers, and there is always an interval of 1 minute
	 * between times
	 */
	int[] stockPrices;
	private Integer minStockPriceIndex;

	// external dependency must be very clear, i.e. stock prices
	public MaxProfit(int[] stockPrices) {
		this.stockPrices = stockPrices;
	}
	
	public int buyAt(int price) {
		this.minStockPriceIndex = price;
		return this.stockPrices[this.minStockPriceIndex];
	}
	
	public int findMinimumStockPriceAt() {
		int minPriceIndex = 0;
		for(int i=1; i<this.stockPrices.length; i++) {
			minPriceIndex = this.stockPrices[minPriceIndex] < 
					this.stockPrices[i] ? minPriceIndex : i;
		}
		return minPriceIndex;
	}
	
	
	public int sellAt(int price) {
		return this.stockPrices[price];
	}
	
	public int findMaximumStockPriceAfter(int time) {
		int maxPriceIndex = time;
		for(int i=++time; i<this.stockPrices.length; i++) {
			maxPriceIndex = this.stockPrices[maxPriceIndex] > 
					this.stockPrices[i] ? maxPriceIndex : i;
		}
		return maxPriceIndex;
	}

	
}
