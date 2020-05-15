package org.com.zlk.leedcode.backtrack;


import java.util.HashSet;
import java.util.Set;

public class PermutationString {

    public static void main(String[] args) {
        String[] result = permutation("abc");
        for (String ss : result) {
            System.out.println(ss);
        }
    }


    public static String[] permutation(String s) {
        if (s == null) {
            return new String[]{};// todo
        }
        Set<String> result = new HashSet<>();
        boolean[] visited = new boolean[s.length()];
        dfs(s, "", visited, result);
        return result.toArray(new String[result.size()]);//todo
    }


    private static void dfs(String s, String letter, boolean[] visted, Set<String> result) {
        if (s.length() == letter.length()) {
            result.add(letter);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (visted[i]) {
                continue;
            }
            visted[i] = true;
            dfs(s, letter + temp, visted, result);
            visted[i] = false;
        }
    }

}

