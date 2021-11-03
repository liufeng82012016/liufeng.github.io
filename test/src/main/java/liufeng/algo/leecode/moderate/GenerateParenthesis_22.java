package liufeng.algo.leecode.moderate;

import java.util.*;

public class GenerateParenthesis_22 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        Map<Integer, List<StringBuilder>> cache = new HashMap<>();
        cache.put(1, Collections.singletonList(new StringBuilder("(")));
        for (int i = 1; i < 2 * n - 1; i++) {
            StringBuilder sb = new StringBuilder("(");
            int sum = 1;
        }
        return result;
    }
}
