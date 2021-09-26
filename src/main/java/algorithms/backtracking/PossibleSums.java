package algorithms.backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 */
public class PossibleSums {

    Map<Integer, Integer> nCoins = new HashMap<>();

    public int possibleSums(int[] coins, int[] quantity) {
        for (int i = 0; i < coins.length; i++) {
            nCoins.put(coins[i], nCoins.getOrDefault(coins[i], 0) + quantity[i]);
        }
        Set<Integer> sums = new HashSet<>();
        possibleSumsHelper(0, sums);
        return sums.size();
    }

    private void possibleSumsHelper(int currentSum, Set<Integer> sums) {
        for (Map.Entry<Integer, Integer> entry : nCoins.entrySet()) {
            int newSum = currentSum + entry.getKey();
            if (!sums.contains(newSum) && entry.getValue() > 0) {
                nCoins.put(entry.getKey(), entry.getValue() - 1);
                sums.add(newSum);
                possibleSumsHelper(newSum, sums);
                nCoins.put(entry.getKey(), entry.getValue() + 1);
            }
        }

    }
}
