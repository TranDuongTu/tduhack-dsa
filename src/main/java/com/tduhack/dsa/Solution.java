package com.tduhack.dsa;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

  public static void main(final String[] args) throws Exception {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    final int T = Integer.valueOf(br.readLine());
    for (int t = 0; t < T; t++) {
      final int[] stocks = readInput(br);
      System.out.println(maximizeStock(stocks));
    }
    
    br.close();
  }

  private static int[] readInput(final BufferedReader br) throws Exception {
    final int N = Integer.valueOf(br.readLine());
    final int[] stocks = new int[N];
    final String[] buff = br.readLine().split(" ");
    for (int i = 0; i < stocks.length; i++) stocks[i] = Integer.valueOf(buff[i]);
    return stocks;
  }

  private static int maximizeStock(final int[] stocks) {
    final boolean[] decisions = new boolean[stocks.length]; // Buy or not
    for (int i = 0; i < decisions.length; i++) decisions[i] = true;
    
    int largest = 0;
    for (int i = stocks.length - 1; i >= 0; i--) {
      if (largest < stocks[i]) {
        largest = stocks[i];
        decisions[i] = false;
      }
    }

    int totalProfit = 0, noStocks = 0;
    for (int i = 0; i < decisions.length; i++) {
      if (decisions[i]) {
        totalProfit -= stocks[i];
        noStocks++;
      } else {
        totalProfit += stocks[i]*noStocks;
        noStocks--;
      }
    }
    return totalProfit < 0 ? 0 : totalProfit;
  }
}
