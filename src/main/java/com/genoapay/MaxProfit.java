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
	private Integer maxStockPriceIndex;

	// external dependency must be very clear, i.e. stock prices
	public MaxProfit(int[] stockPrices) {
		this.stockPrices = stockPrices;
	}
	
	public int buyAt(int price) {
		this.minStockPriceIndex = price;
		return this.stockPrices[this.minStockPriceIndex];
	}
	
	public void findMaxProfitPricePair() {
		int maxProfit = Integer.MIN_VALUE;
		// lets start with naive approach
		for(int i=0; i<this.stockPrices.length; i++){
			for(int j=i+1; j<this.stockPrices.length; j++) {
				int left = this.stockPrices[i];
				int right = this.stockPrices[j];
				int diff =  right - left;
				if(diff > maxProfit) {
					maxProfit = diff;
					this.minStockPriceIndex = i;
					this.maxStockPriceIndex = j;
				}
			}
		}
	}
	
	public int sellAt(int price) {
		return this.stockPrices[price];
	}
	
	public int getMinStockPriceIndex() {
		return this.minStockPriceIndex;
	}

	public int getMaxStockPriceIndex() {
		return this.maxStockPriceIndex;
	}
	
}
