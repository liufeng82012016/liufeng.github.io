package liufeng.jdk.comparetor;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2021/3/31 17:37
 */
public class SortTest {

    public static void main(String[] args) {
        sort1();
    }

    public static void sort1() {
        Random random = new Random();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println(list);
        list.sort((a, b) -> {
            if (a % 2 == 0) {
                return 1;
            }
            return a.compareTo(b);
        });
        System.out.println(list);
    }
}
