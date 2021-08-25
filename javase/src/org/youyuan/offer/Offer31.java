package org.youyuan.offer;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.PatternSyntaxException;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/7/15 18:36
 */
public class Offer31 {
    public boolean validateStackSequences(int[] push,int[] popped) {
        if (push.length == 0 && popped.length  == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int n = 0;
        for (int element : push) {
            stack.push(element);
            while (!stack.empty() && stack.peek() == popped[n]) {
                stack.pop();
                n++;
            }
        }
        return stack.empty();
    }
}
