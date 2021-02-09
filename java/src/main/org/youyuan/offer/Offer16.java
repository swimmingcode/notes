package org.youyuan.offer;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/9 9:42
 * <p>
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 * <p>
 *  
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer16 {

    static public double myPow(double x, int n) {
        double xx = x;
        long nn = n;
        if (nn > 0) {
            while ( nn > 1) {
                xx = xx * x;
                nn--;
            }
        } else {
            nn = Math.abs(nn);
            // n 越界了 需要使用long 来表示
            while ( nn > 1) {
                xx = xx * x;
                nn--;
            }
            xx = 1.000 / xx;
        }
        return xx;
    }

    public static void main(String[] args) {
        // TODO: 2021/2/9 越界问题
        System.out.println(myPow(2.00000, -2147483648));
    }
}
