package liufeng.algo.tree.hfm;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HfmTest {
    @Test
    public void createTree() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 9);
        map.put("B", 5);
        map.put("C", 12);
        map.put("D", 11);
        map.put("G", 3);
        HaffmanTree<String> tree = new HaffmanTree<>(map);
        tree.print();
        System.out.println("3-code： " + tree.getCode(3));
        System.out.println("8-code： " + tree.getCode(8));
        System.out.println("12-code： " + tree.getCode(12));
        System.out.println("12-code： " + tree.getByCode("00"));
    }
}
