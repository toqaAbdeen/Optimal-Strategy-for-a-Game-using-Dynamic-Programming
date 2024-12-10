package application;

/**
 * This class was created to collect the functions for coins that the user will
 * enter. In this class, we defined private instance variable (numberOfCoins
 * (which expresses the number of coins entered by user(EVEN
 * NUMBER)),coins(which expresses the number of these coins that the user will
 * enter), dp (through which reach the optimal soluution and some), and methods
 * that needed to implement them
 * 
 */

public class Coins {
	private int[] coins;
	private int n;
	private int[][] dp;

	public Coins() {
	}

	// Constructor to initialize the coins array, the DP table and Calculate the DP
	// table
	public Coins(int[] coins) {
		this.coins = coins;
		this.n = coins.length;
		this.dp = new int[n][n];
		calculateDP();
	}

	/**
	 * constructor to get the number of coins, which must be greater than 0, using
	 * this size, the coins array will implemented
	 * 
	 **/
	public Coins(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size must be greater than 0.");
		}
		coins = new int[size];
		n = size;
	}

	/**
	 * This method was implemented to initialize the array which will contain the
	 * coins values, the size of the array will be the size entered by the user
	 * through the constructor
	 */
	public void initalizationCoins() {
		for (int i = 0; i < n; i++) {
			coins[i] = i + 1;
		}
	}

	/**
	 * This method is designed to remove a specific coin from the coins array so
	 * that if the coin is found, all coins will move to the left, thus removing the
	 * specific coin and thus reducing the size of the array by one. However, if the
	 * coin is not found, the method will return false, and if successful, it
	 * will return a true.
	 */

	public boolean removeCoin(int value) {
		int index = -1;
		for (int i = 0; i < n; i++) {
			if (coins[i] == value) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			return false;
		}

		for (int i = index; i < n - 1; i++) {
			coins[i] = coins[i + 1];
		}

		n--;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < n; i++) {
			sb.append(coins[i]);
			if (i < n - 1)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	public int[] getCoins() {
		return coins;
	}

	public void setCoins(int[] coins) {
		this.coins = coins;
		this.n = coins.length;
		this.dp = new int[n][n]; // Reinitialize the DP table if coins array changes
		calculateDP(); // Recalculate the DP table whenever coins change
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int[][] getDp() {
		return dp;
	}

	public void setDp(int[][] dp) {
		this.dp = dp;
	}


	/**
	 * 
	 * Imagine you have a row of coins. You and your opponent take turns picking
	 * coins from either end. You want to pick coins to get the highest total value.
	 * 
	 * To figure out the best strategy, we can break down the problem into smaller
	 * subproblems. For each smaller section of coins, we calculate the best
	 * possible score you can get. We consider two choices:
	 * 
	 * Pick the left coin: Your opponent will then pick the best coin remaining
	 * (either the next one or the last one). Pick the right coin: Your opponent
	 * will again pick the best remaining coin (either the next one or the
	 * second-to-last one).
	 * 
	 * We store the best score for each subproblem in a table. By working our way
	 * through the table, we can eventually find the best overall strategy for the
	 * whole row of coins.
	 * 
	 * 
	 * This method was implemented exactly as explained above.
	 * 
	 * 
	 * The initial value in this game is the subsets that consist of one element,
	 * where the first player will have to take the only available coin. In this
	 * case, the DP table will be directly initialized with the value of the coin
	 * (dp[i][j] = coins[i]). As for the subsets that consist of more than one coin,
	 * the DP table will be filled by specifying the maximum score that the first
	 * player can achieve through the two options: take the coin on the far right
	 * or the far left.
	 * 
	 */
	public int calculateDP() {

		for (int length = 1; length <= n; length++) {
			for (int i = 0; i <= n - length; i++) {
				int j = i + length - 1;

				if (i == j) {
					dp[i][j] = coins[i];
				} else {

					int pickLeft = coins[i]
							+ Math.min((i + 2 <= j ? dp[i + 2][j] : 0), (i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0));

					int pickRight = coins[j]
							+ Math.min((i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0), (i <= j - 2 ? dp[i][j - 2] : 0));

					dp[i][j] = Math.max(pickLeft, pickRight);
				}
			}
		}

		return dp[0][n - 1];
	}
}
