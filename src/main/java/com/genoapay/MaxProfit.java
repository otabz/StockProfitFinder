package com.genoapay;

public class MaxProfit {
	/**
	 * Assumptions: As it is an array of primitive integers and indices
	 * represent minutes past so, order of time will be ascending, time will
	 * never be in negative numbers, and there is always an interval of 1 minute
	 * between times
	 */
	int[] stockPrices;
	private Integer minStockPrice;

	// external dependency must be very clear, i.e. stock prices
	public MaxProfit(int[] stockPrices) {
		this.stockPrices = stockPrices;
	}
	
	public int buy() {
		int min = this.stockPrices[0];
		for(int i=0; i<this.stockPrices.length; i++) {
			min = this.stockPrices[i] < min ? this.stockPrices[i] : min;
		}
		this.minStockPrice = min;
		return min;
	}
	
	public int sell() {
		// use this expression to avoid typo of =, leading to serious logical bugs
		if(null == minStockPrice) {
			throw new IllegalStateException("You didn't buy a stock yet!");
		}
		return 0;
	}

	
}
