package org.youyuan.offer;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/3 19:33
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *  
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer17 {

    public int[] printNumbers1(int n) {
        int i=0;
        int max =0;
        // TODO: 2021/2/3 获取最大值
        while(i++ <n){
            max = max * 10 + 9;
        }

        int [] res = new int[max];
        i =0;
        for(;i<max;i++){
            res[i]=i+1;
        }
        return res;

    }

    public int[] printNumbers(int n) {
        StringBuilder max = new StringBuilder();
        for (int i = 0; i < n; i++) {
            max.append('9');
        }
        Integer integer = Integer.valueOf(max.toString());
        int[] ints = new int[integer];
        int count = 0;
        for (int i = 1; i <=integer; i++) {
            ints[count++] = i;
        }
        return ints;
    }
}
