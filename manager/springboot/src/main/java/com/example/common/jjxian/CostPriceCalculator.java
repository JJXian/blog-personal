package com.example.common.jjxian;

public class CostPriceCalculator {
    public static double calculateCostPrice(double marketPrice, double rateOfReturn) {
        return marketPrice / (1 + rateOfReturn);
    }

    public static void main(String[] args) {
        double marketPrice = 32.895;
        double rateOfReturn = -0.2390;
        double costPrice = calculateCostPrice(marketPrice, rateOfReturn);
        System.out.println("price:" + costPrice);

    }
}

