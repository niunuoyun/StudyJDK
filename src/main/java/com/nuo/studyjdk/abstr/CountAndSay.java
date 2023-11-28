package com.nuo.studyjdk.abstr;

public class CountAndSay {
    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        return transfer(countAndSay(n - 1));
    }

    public static String transfer(String s) {
        StringBuffer sb = new StringBuffer();
        int count = 1;
        int length = s.length();
        int i;
        char temp = s.charAt(0);
        for (i = 1; i <= length; i++) {
            while (i < length && temp == s.charAt(i)) {
                count++;
                i++;
            }
            if (i < length) {
                temp = s.charAt(i);
            }
            sb.append(count);
            sb.append(s.charAt(i - 1));
            count = 1;
        }
        return sb.toString();
    }

}