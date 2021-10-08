package liufeng.jdk.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author liufeng
 * @Description: todo
 * @since 2021/3/31 15:22
 */
public class ListTest {

    /**
     * 结论：iterator.remove()不会continue循环，但是结束后改元素被移除
     */
    @Test
    public void iteratorRemove() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 3 == 0) {
                iterator.remove();
                System.out.println(next + " remove");
            }
            System.out.println(next + " remain");
        }
        System.out.println(list.size());
    }

    /**
     * foreach 内部函数需要自己处理异常
     */
    @Test
    public void foreachInterrupt() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.forEach(in -> {
            if (in % 3 == 0) {
                throw new RuntimeException();
            }
            System.out.println(in + " am live ");
        });
    }
}
