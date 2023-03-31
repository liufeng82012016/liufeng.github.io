package liufeng.algo.str;

import java.util.ArrayList;
import java.util.List;

public class StrMatchTest {
    public static void main(String[] args) {
        List<StrMatch> algos = new ArrayList<>();
        algos.add(new BruteForce());
        algos.add(new KMP());
        algos.add(new KMP2());
        String pat = "ababa";
        String txt = "abacababa";
        for (StrMatch strMatch : algos) {
            System.out.println(strMatch.search(pat, txt));
        }
    }
}
