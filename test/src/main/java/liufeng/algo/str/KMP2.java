package liufeng.algo.str;

import java.util.Arrays;

public class KMP2 extends StrMatch {

    int[] next;

    private void init(String pat) {
        next = new int[pat.length()];
        System.out.println(pat);
        for (int i = 1; i <= pat.length(); i++) {
            // 遍历截取不同的字段，截取左闭右开，所以使用等于判断
            String str = pat.substring(0, i);
            // todo 这里改成倒序，匹配到第一个就break
            for (int j = 1; j < i; j++) {
                // 得到截取字段前缀；至少一个字符，所以从1开始；不能等于原字符串，所以j<i
                String prefix = str.substring(0, j);
                //得到截取字段后缀
                String suffix = str.substring(str.length() - prefix.length(), str.length());
                //如果前后缀匹配成功，则保存该处错误之后回溯的位数
                if (suffix.equals(prefix) && !suffix.equals(str)) {
                    next[i - 1] = suffix.length();
                }

            }
        }
        System.out.println(Arrays.toString(next));
    }

    @Override
    public int search(String pat, String txt) {
        System.out.println(txt);
        init(pat);
        for (int i = 0; i < txt.length(); i++) {
            System.out.println();
            System.out.printf("i:%s \t", i);
            int index = 0;
            for (int j = index; j < pat.length(); j++) {
                System.out.printf("txt[%s]:%s pat[%s]:%s match:%s \t", i, txt.charAt(i), j, pat.charAt(j), (txt.charAt(i) == pat.charAt(j)));
                if (txt.charAt(i) == pat.charAt(j)) {
                    if (j == pat.length() - 1) {
                        System.out.println("success return");
                        return i - j;
                    }
                    i++;
                    if (i == txt.length()) {
                        System.out.printf("fail return %s,%s", i, txt.length());
                        return -1;
                    }
                } else {
                    if (index != j) {
                        // 匹配到了一部分，修改索引，重新匹配
                        index = j - next[j];
                        i--;
                        System.out.printf("fail: j:%s i:%s index:%s", j, i, index);
                    }
                    break;
                }
            }
        }
        return -1;
    }
}
