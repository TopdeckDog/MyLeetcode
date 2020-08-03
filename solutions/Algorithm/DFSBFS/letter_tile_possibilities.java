package Algorithm.DFSBFS;

class letter_tile_possibilities {
    // 思路:回溯法,遍历所有可能的结果,
    // 细节优化:
    // 1.将遍历哪个字模退化成字模个数,可以去重复(因为有的字模可能是相同的,[A,A,B]对于123和213的排列结果是重复的)
    public int numTilePossibilities(String tiles) {

        int[] counter = new int[26];
        for (int i = 0; i < tiles.length(); i++)
            counter[tiles.charAt(i) - 'A']++;
        return dfs(counter);
    }

    public int dfs(int[] counter) {

        int sum = 0;
        for (int i = 0; i < 26; i++) {

            if (counter[i] > 0) {
                // 当前情况算一个
                sum++;
                counter[i]--;
                sum += dfs(counter);
                counter[i]++;
            }
        }
        return sum;
    }
}
