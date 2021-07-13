package org.youyuan.offer;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/3 19:43
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 1 2  3  4
 * 5 6  7  8
 * 9 10 11 12
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer29 {

    public static void main(String[] args) {
        //[[1,11],[2,12],[3,13],[4,14],[5,15],[6,16],[7,17],[8,18],[9,19],[10,20]]
        System.out.println(new Offer29().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}).toString());
//        System.out.println(new Offer29().spiralOrder(null));
    }


    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int[] res = new int[matrix.length * matrix[0].length];
        int n = 0;
        //定义左右（0，length-1）、上下(0,length-1)
        int left = 0,right = matrix[0].length-1, top = 0 ,bottom = matrix.length-1;
        //判断 当触碰到任何一端时  直接break
        while (true) {
            //从左向右
            for (int i = left; i <= right ; i++) {
                res[n++] = matrix[top][i];
            }
            if (++top > bottom) {
                break;
            }
            //从上到下
            for (int i = top; i <= bottom; i++) {
                res[n++] = matrix[i][right];
            }
            if (--right < left) {
                break;
            }
            //从右到左
            for (int i = right ; i >= left ; i--) {
                res[n++] = matrix[bottom][i];
            }
            if (--bottom < top) {
                break;
            }
            //从下到上
            for (int i = bottom; i >= top ; i--) {
                res[n++] = matrix[i][left];
            }
            if (++left > right) {
                break;
            }
        }
        return res;
    }

}
