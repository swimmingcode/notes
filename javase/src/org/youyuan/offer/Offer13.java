package org.youyuan.offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/19 15:09
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 */
public class Offer13 {

    public static int res = 0;

    public boolean[][] visited;

    public static void main(String[] args) {
        new Offer13().movingCount1(3,3,2);
        System.out.println(res);
    }

    /**
     * 第二种解法：BFS
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        int res = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int i = poll[0];
            int j = poll[1];
            if (i >= m || j>=n || (sum(i)+sum(j)>k) || visited[i][j]) {
                continue;
            }
            visited[i][j] = true;
            res++;
            queue.add(new int[]{i+1,j});
            queue.add(new int[]{i,j+1});
        }
        return res;
    }

    /**
     * 第一种解法：DFS
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount1(int m, int n, int k) {
        if (k == 0 || (m == 0 && n == 0)) {
            return 1;
        }
        visited = new boolean[m][n];
        return dfs(0, 0, m, n, k);
    }

    public int dfs(int i, int j, int m, int n, int k) {
        res++;
        //越界
        if (i > m - 1 || j > n - 1 || (sum(i)+sum(j)) > k) {
            return 0;
        }

        //已经遍历过了
        if (visited[i][j] ) {
            return 0;
        }

        visited[i][j] = true;
        return 1 + dfs(i + 1,j,m,n,k) + dfs(i,j+1,m,n,k);
    }

    /**
     * @param n
     * @return
     */
    public int sum(int n) {
        int sum = 0;
        while (n!=0) {
            sum += n%10;
            n /= 10;
        }
        return sum;
    }

}
