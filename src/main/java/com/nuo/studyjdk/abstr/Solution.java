package com.nuo.studyjdk.abstr;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
    public static int romanToInt(String s) {
        Integer[] data = new Integer[]{100, 500, 0, 0, 0, 0, 1, 0, 0, 50, 1000, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10};
        int start = 67;
        int sum = 0, lastData = 0, i = 0;
        for (; i < s.length(); i++) {
            int index = Integer.valueOf(s.charAt(i)) - start;
            if (i == 0) {
                lastData = data[index];
                continue;
            }
            if (lastData >= data[index]) {
                sum = sum + lastData;
            } else {
                sum = sum - lastData;
            }
            lastData = data[index];
        }
        int index = s.charAt(i - 1) - start;
        if (lastData >= data[index]) {
            sum = sum + data[index];
        } else {
            sum = sum - data[index];
        }
        return sum;
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        // 初始化首行
        for (int j = 2; j < n; j += 2)
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (dp[i][j - 2]) dp[i][j] = true;                                            // 1.
                    else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true; // 2.
                    else if (dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;             // 3.
                } else {
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;  // 1.
                    else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;         // 2.
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static boolean isMatch1(String s, String p) {
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        // 初始化首行
        for (int j = 2; j < n; j += 2)
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (dp[i][j - 2]) dp[i][j] = true;                                            // 1.
                    else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true; // 2.
                    else if (dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;             // 3.
                } else {
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;  // 1.
                    else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;         // 2.
                }
            }
        }
        return dp[m - 1][n - 1];
    }

  /*  public static void main(String[] args) {
        System.out.println(isMatch1("mississippi", "c*a*mis*is*p*."));

    }*/

    public static void main(String[] args) {
        String baseUrl = "http://example.com/api?";
        List<String> myArray = Arrays.asList("item1", "item2", "item3");

        // 将数组转换为URL编码的字符串
        StringBuilder params = new StringBuilder();
        for (String item : myArray) {
            try {
                params.append(URLEncoder.encode("param", "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(item, "UTF-8"))
                        .append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // 构建完整的URL
        String fullUrl = baseUrl + params.toString();

        System.out.println(fullUrl);
    }
}