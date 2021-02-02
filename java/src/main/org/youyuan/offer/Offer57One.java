package org.youyuan.offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/1 23:09
 *
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 *
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *  
 *
 * 限制：
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer57One {

    /**
     * 解法一：Hash
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        Set<Integer> sets = new HashSet<>();
        for (Integer num : nums) {
            sets.add(num);
        }
        for (int num : nums) {
            if (sets.contains(target - num)) {
                return new int[]{num,target-num};
            }
        }
        return null;
    }

    /**
     * 解法二：双指针（递增）
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int i = 0,j = nums.length-1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum > target) {
              j--;
            } else if (sum < target) {
                i++;
            } else {
                return new int[]{nums[i],nums[j]};
            }
        }
        return null;
    }
}
