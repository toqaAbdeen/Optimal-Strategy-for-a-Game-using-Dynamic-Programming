package application;

import java.util.Arrays;
import java.util.Random;

/*
 * This class was created to collect the functions for coins that the user will enter.
 * In this class, we defined private instance variable (numberOfCoins (which expresses the number of coins
 * entered by user(EVEN NUMBER)),coins(which expresses the number of these coins that the user will enter)
 * 
 * */

public class Coins {
	/*
	 * numberOfCoins: private instance variable of type integer. coins: private
	 * instance variable that's an array of integers.
	 * 
	 */
	private int numberOfCoins;
	private int[] coins;

	/*
	 * Constructor for the class. Takes two parameters: numberOfCoins for the array
	 * size and coins for the input array. Creates a new array of size
	 * numberOfCoins.
	 * 
	 */

	public Coins() {
	}

	public Coins(int numberOfCoins, int[] coins) {
		super();
		this.numberOfCoins = numberOfCoins;
		this.coins = new int[numberOfCoins];
	}

	/*
	 * Generate getter and setter for the variables In setter for numberOfCoins
	 * (setNumberOfCoins) we added the condition that the number must be even.
	 * 
	 */

	/*
	 * Getter method for numberOfCoins:
	 * 
	 * type int (as previously defined)
	 * 
	 * @param NO parameter
	 * 
	 * @return the value of the numberOfCoins previously entered
	 * 
	 */
	public int getNumberOfCoins() {
		return numberOfCoins;
	}

	/*
	 * Setter method for numberOfCoins:
	 * 
	 * type Void
	 * 
	 * @param numberOfCoins (type int)
	 * 
	 * @return NO return value
	 * 
	 * this method implement to set the value of numberOfCoins after making sure the
	 * number is even using IF STATEMENT that check this condition (numberOfCoins %
	 * 2 == 0),the the number isn't even the system will show
	 * "The number of coins must be an even number."
	 * 
	 */
	public void setNumberOfCoins(int numberOfCoins) {
		if (numberOfCoins % 2 == 0)
			this.numberOfCoins = numberOfCoins;
		else
			System.out.println("The number of coins must be an even number.");
	}

	/*
	 * Getter method for coins:
	 * 
	 * type array of integers (as previously defined)
	 * 
	 * @param NO parameter
	 * 
	 * @return the value of the coins that entered by the user
	 * 
	 */
	public int[] getCoins() {
		return coins;
	}

	/*
	 * Setter method for coins:
	 * 
	 * type Void
	 * 
	 * @param coins (type array of integers)
	 * 
	 * @return NO return value
	 * 
	 * this method implement to set the value of numbers that user will enter it and
	 * store in this array after checking that the coin value is positive.
	 * 
	 */
	public void setCoins(int[] coins) {
		for (int i = 0; i < coins.length; i++) {
			if (coins[i] < 0) {
				throw new IllegalArgumentException("Coins must be positive.");
			}
		}
		this.coins = coins;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Coins [numberOfCoins=" + numberOfCoins + ", coins=[");
		for (int i = 0; i < coins.length; i++) {
			sb.append(coins[i]);
			if (i < coins.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]]");
		return sb.toString();
	}

	public void game(int numberOfCoins, int[] coins) {
		int[][] player1Table = new int[numberOfCoins][numberOfCoins];
		int[][] player2Table = new int[numberOfCoins][numberOfCoins];

		for (int k = 0; k < numberOfCoins; k++) {
			for (int i = 0, j = k; j < numberOfCoins; i++, j++) {
				if (k == 0) {
					player1Table[i][j] = coins[i];
					player2Table[i][j] = 0; // Player 2 gets nothing if Player 1 picks the only coin
				} else if (k == 1) {
					player1Table[i][j] = Math.max(coins[i], coins[j]);
					player2Table[i][j] = Math.min(coins[i], coins[j]);
				} else {
					int pickLeft = coins[i] + player2Table[i + 1][j]; // Player 1 picks left coin
					int pickRight = coins[j] + player2Table[i][j - 1]; // Player 1 picks right coin

					if (pickLeft > pickRight) {
						player1Table[i][j] = pickLeft;
						player2Table[i][j] = player1Table[i + 1][j]; // Player 2's best choice after Player 1's pick
					} else {
						player1Table[i][j] = pickRight;
						player2Table[i][j] = player1Table[i][j - 1]; // Player 2's best choice after Player 1's pick
					}
				}
			}
		}

		// Display the results for both players
		System.out.println("Player 1's scores:");
		for (int i = 0; i < player1Table.length; i++) {
			for (int j = 0; j < player1Table[i].length; j++) {
				System.out.print(player1Table[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("Player 2's scores:");
		for (int i = 0; i < player2Table.length; i++) {
			for (int j = 0; j < player2Table[i].length; j++) {
				System.out.print(player2Table[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("Maximum coins Player 1 can collect: " + player1Table[0][numberOfCoins - 1]);
		System.out.println("Maximum coins Player 2 can collect: " + player2Table[0][numberOfCoins - 1]);
	}

	public String gameComputer(int numberOfCoins, int[] coins) {
		int[][] player1Table = new int[numberOfCoins][numberOfCoins];
		int[][] player2Table = new int[numberOfCoins][numberOfCoins];

		// Fill the tables using dynamic programming
		// ... (same as before)

		StringBuilder result = new StringBuilder();
		int player1Score = 0, player2Score = 0;
		int i = 0, j = numberOfCoins - 1;

		while (i <= j) {
			// Computer 1's (Player 1's) turn
			if (player1Table[i][j] == coins[i] + player2Table[i + 1][j]) {
				result.append("Computer 1 picks coin " + coins[i] + " at index " + i + "\n");
				player1Score += coins[i];
				i++;
			} else {
				result.append("Computer 1 picks coin " + coins[j] + " at index " + j + "\n");
				player1Score += coins[j];
				j--;
			}

			// Fictional Player 2's turn (Computer plays randomly)
			if (i <= j) {
				Random random = new Random();
				int choice = random.nextInt(2);
				if (choice == 0) {
					result.append("Computer 2 picks coin " + coins[i] + " at index " + i + "\n");
					player2Score += coins[i];
					i++;
				} else {
					result.append("Computer 2 picks coin " + coins[j] + " at index " + j + "\n");
					player2Score += coins[j];
					j--;
				}
			}
		}

		result.append("\nFinal Scores:\n");
		result.append("Computer 1: " + player1Score + "\n");
		result.append("Computer 2: " + player2Score + "\n");

		// Determine the winner
		if (player1Score > player2Score) {
			result.append("Computer 1 wins!");
		} else if (player2Score > player1Score) {
			result.append("Computer 2 wins!");
		} else {
			result.append("It's a tie!");
		}

		return result.toString();
	}

	public int maxValueInCoins(int[] coins) {
		int[][] dp = new int[numberOfCoins][numberOfCoins];
		for (int i = 0; i < numberOfCoins; i++) { //
			dp[i][i] = coins[i];
		}
		for (int len = 2; len <= numberOfCoins; len++) {
			for (int i = 0; i <= numberOfCoins - len; i++) {
				int j = i + len - 1;
				if (i == j) {
					dp[i][j] = coins[i];
					continue;
				}

				if (i > j) {
					continue;
				}
				int first = coins[i] + (i + 2 <= j ? Math.min(dp[i + 2][j], dp[i + 1][j - 1]) : 0);
				int second = coins[j] + (j - 2 >= i ? Math.min(dp[i + 1][j - 1], dp[i][j - 2]) : 0);

				dp[i][j] = Math.max(first, second);
			}
		}

		printDp(dp);
		return dp[0][numberOfCoins - 1];
	}

//	public void printDP(int[][] dp) {
//		int maxWidth = String.valueOf(dp[0][0]).length();
//		for (int i = 0; i < dp.length; i++) {
//			for (int j = 0; j < dp.length; j++) {
//				System.out.printf("%"+(maxWidth+1)+"d",dp[i][j]);
//			}
//			System.out.println();
//		}
//
//	}
	public void printDp(int[][] dp) {
		// Find the maximum width for formatting
		int maxWidth = 0;
		for (int[] row : dp) {
			for (int num : row) {
				maxWidth = Math.max(maxWidth, String.valueOf(num).length());
			}
		}

		// Print the dp table with aligned columns
		for (int[] row : dp) {
			for (int num : row) {
				String formattedNumber = String.valueOf(num);
				// Pad the number with spaces
				while (formattedNumber.length() < maxWidth) {
					formattedNumber = " " + formattedNumber;
				}
				System.out.print(formattedNumber + " ");
			}
			System.out.println();
		}
	}

	public void printDpTable(int[][] dp) {
		// Find the maximum width for formatting
		int maxWidth = 0;
		for (int[] row : dp) {
			for (int num : row) {
				maxWidth = Math.max(maxWidth, String.valueOf(num).length());
			}
		}

		// Print the dp table with aligned columns
		for (int[] row : dp) {
			for (int i = 0; i < row.length; i++) {
				String formattedNumber = String.valueOf(row[i]);
				// Pad the number with spaces
				while (formattedNumber.length() < maxWidth) {
					formattedNumber = " " + formattedNumber;
				}
				// Indicate which player's value is being printed
				if (i == 0) {
					System.out.print("Player 1: " + formattedNumber + " ");
				} else {
					System.out.print("Player 2: " + formattedNumber + " ");
				}
			}
			System.out.println();
		}
	}

}
