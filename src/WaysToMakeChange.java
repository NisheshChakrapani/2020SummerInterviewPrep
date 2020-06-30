import java.util.Arrays;

public class WaysToMakeChange {
    public static void main(String[] args) {
        System.out.println(changeCombosDynamic(5));
        System.out.println(changeCombosDynamic(10));
        System.out.println(changeCombosDynamic(86));
    }

    public static int changeCombosDynamic(int total) {
        if (total < 0) return 0;

        int[] coins = {5, 10, 25};
        int[] ways = new int[total+1];
        Arrays.fill(ways, 1);

        for (int coin : coins)
            for (int j = coin; j <= total; j++)
                ways[j] += ways[j - coin];

        return ways[total];
    }
}
