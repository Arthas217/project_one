package org.com.zlk.leedcode;

import org.junit.Test;

import static org.com.zlk.leedcode.StrSolution.*;
import static org.junit.Assert.*;

/**
 * @Author zc217
 * @Date 2020/8/5
 */
public class StrSolutionTest {

    @Test
    public void testReplaceSpace() {
        String s = "abc de ";
        System.out.println(replaceSpace(s));
    }

    @Test
    public void testLengthOfLongestSubstring() {
        String str = "abcbcbb";
        System.out.println(lengthOfLongestSubstring(str));
    }

    @Test
    public void testIsPalindrome() {
        String st = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(st));
    }

    @Test
    public void testLongestPalindrome() {
        String str = "amanaplanacanalpanama";
        System.out.println(longestPalindrome(str));
    }

    @Test
    public void testLongestPalindrome2() {
        String str = "amanaplanacanalpanama";
        System.out.println(longestPalindrome2(str));
    }
}