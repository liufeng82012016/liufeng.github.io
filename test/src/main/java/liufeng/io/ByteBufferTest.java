package liufeng.io;

import org.junit.Test;

import java.nio.ByteBuffer;

public class ByteBufferTest {
    @Test
    public void slice() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        buffer.position(3);
        buffer.limit(6);
        ByteBuffer slice = buffer.slice();
        for (int i = 0; i < slice.capacity(); i++) {
            slice.put((byte) (i*3));
        }
        // 必须重置起始位置和limit，否则无法读取超过限制的内容
        buffer.position(0);
        buffer.limit(buffer.capacity());
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.get());
        }
    }
}
