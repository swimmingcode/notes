package org.youyuan.test;

import com.sun.jmx.snmp.SnmpNull;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/8 15:48
 */
public class Sort {

    public static int[] numberSort(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }

            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,2,99,4,13,8,1,7};
        quickSort(nums,0,nums.length-1);
    }

    /**
     * 快速排序
     **/
    static public void quickSort(int[] nums, int start, int end) {
        if (end > start) {
            int index = getIndex(nums, start, end);
            quickSort(nums, start, index - 1);
            quickSort(nums, index + 1, end);
        }
    }

    /**
     * 1. 将基准数挖出形成第一个坑。 * 2. 由后向前找他小的数，找到后挖出此数填到前一个坑中。 * 3. 由前向后找比他大或者等于的数，找到后也挖出此数填到前一个坑中。 * 4. 在重复执行2，3两步骤。
     */
    private static int getIndex(int[] nums, int start, int end) {
        int i = start;
        int j = end;
        int value = nums[start];
        while (i < j) {
            while (i < j && nums[j] >= value) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }
            while (i < j && nums[i] < value) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = value;
        return i;
    }
}
