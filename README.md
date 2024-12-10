# Optimal Game Strategy using Dynamic Programming

## Problem Description

The Optimal Game Strategy problem involves a two-player game where an even number of coins are arranged in a row. Players take alternate turns, and on each turn, a player can either select the first coin or the last coin in the row. The objective is to determine the maximum amount of money a player can guarantee to win if they make the first move, assuming both players play optimally.

## Solution Approach

To solve this problem, we use **Dynamic Programming (DP)**. The key idea is to break the problem into smaller subproblems, where we calculate the optimal choice for each player, considering the opponent's future optimal moves. By storing the maximum values that a player can secure in a DP table, we can avoid redundant calculations.

- Let `dp[i][j]` represent the maximum value a player can guarantee to win from the subarray of coins between indices `i` and `j`.
- The recurrence relation is as follows:
  - If the player picks the first coin, the remaining subarray is from `i+1` to `j`, and the opponent plays optimally.
  - If the player picks the last coin, the remaining subarray is from `i` to `j-1`, and the opponent plays optimally.
  - The player aims to maximize their total, while the opponent minimizes the player's winnings.
  
The base case occurs when there is only one coin, in which case the player simply takes it.

The final solution is stored in `dp[0][n-1]`, which gives the maximum value the first player can guarantee.

## Time and Space Complexity

- **Time Complexity**: \(O(n^2)\), where \(n\) is the number of coins, as we are filling a DP table of size \(n \times n\).
- **Space Complexity**: \(O(n^2)\) due to the DP table used to store intermediate results.
