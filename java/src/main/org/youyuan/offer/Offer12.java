package org.youyuan.offer;

import javax.swing.*;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 23:00
 *
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 *
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 *
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer12 {

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board,word,0,i,j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param board 二维数组
     * @param word 匹配的字符
     * @param i 字符串的下标
     * @param x x轴
     * @param y y轴
     * @return
     */
    private boolean dfs(char[][] board, String word, int i, int x, int y) {
        //判断数组是否越界
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word.charAt(i)) {
            return false;
        }
        if (i == board.length - 1) {
            return true;
        }
        //防止重复遍历，设置一个标志字符
        char temp = board[x][y];
        board[x][y] = '\0';
        Boolean res = dfs(board, word, i + 1, x, y - 1) //向上
                   || dfs(board, word, i + 1, x, y + 1) //向下
                   || dfs(board, word, i + 1, x + 1, y)//向右
                   || dfs(board, word, i + 1, x - 1, y); //向左
        board[x][y] = temp;
        return res;
    }

    //  public boolean exist(char[][] board, String word) {
    //        char[] words = word.toCharArray();
    //        for(int i = 0; i < board.length; i++) {
    //            for(int j = 0; j < board[0].length; j++) {
    //                if(dfs(board, words, i, j, 0)) return true;
    //            }
    //        }
    //        return false;
    //    }
    //    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
    //        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
    //        if(k == word.length - 1) return true;
    //        board[i][j] = '\0';
    //        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
    //                      dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
    //        board[i][j] = word[k];
    //        return res;
    //    }
    //
}

