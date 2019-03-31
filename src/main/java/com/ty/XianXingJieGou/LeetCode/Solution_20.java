package com.ty.XianXingJieGou.LeetCode;


import java.util.Stack;

/**
 * leetCode: 20. 有效的括号
 * @ClassName Solution_20
 * @Author tianye
 * @Date 2019/3/9 23:24
 * @Version 1.0
 */
public class Solution_20 {

    /**
     * 有效字符串需满足：
     *	左括号必须用相同类型的右括号闭合。
     *	左括号必须以正确的顺序闭合。
     *	注意空字符串可被认为是有效字符串。
     *	示例 1:
     *	输入: "()"
     *	输出: true
     *	示例 2:
     *	输入: "()[]{}"
     *	输出: true
     *	示例 3:
     *	输入: "(]"
     *	输出: false
     *	示例 4:
     *	输入: "([)]"
     *	输出: false
     *	示例 5:
     *	输入: "{[]}"
     *	输出: true
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;
                Character topChar = stack.pop();
                if (c == ')' && topChar != '(')
                    return  false;
                if (c == ']' && topChar != '[')
                    return false;
                if (c == '}' && topChar != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println((new Solution_20()).isValid("()[]{}"));
        System.out.println((new Solution_20()).isValid("([)]"));
    }
}
