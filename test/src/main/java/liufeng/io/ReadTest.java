package liufeng.io;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author Ailwyn
 * @Description: FileInputStream/BufferInputStream/FileChannel 速度比较
 * @Date 2021/8/23 16:42
 */
public class ReadTest {
    @Test
    public void fileInputStream() {
        InputStream inputStream = null;
        long startTime = System.currentTimeMillis();
        try {
            inputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\fsdownload\\test.zip"));
            byte[] temp = new byte[1024];
            while ((inputStream.read(temp)) != -1) {

            }
            long time = System.currentTimeMillis() - startTime;
            System.out.println("time: " + time);
        } catch (Exception e) {
            // 521421kb 2490ms
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void bufferInputStream() {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\fsdownload\\test.zip"));
            byte[] temp = new byte[1024];
            while ((inputStream.read(temp)) != -1) {

            }
            long time = System.currentTimeMillis() - startTime;
            System.out.println("time: " + time);
        } catch (Exception e) {
            // 521421kb 529ms
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void fileInputStreamIO() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        long startTime = System.currentTimeMillis();
        try {
            inputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\fsdownload\\test.zip"));
            outputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\fsdownload\\test1.zip"));
            byte[] temp = new byte[1024];
            while ((inputStream.read(temp)) != -1) {
                outputStream.write(temp);
            }
            long time = System.currentTimeMillis() - startTime;
            System.out.println("time: " + time);
        } catch (Exception e) {
            // 521421kb 6297ms
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void bufferInputStreamIO() {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\fsdownload\\test.zip"));
            outputStream = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\fsdownload\\test1.zip"));
            byte[] temp = new byte[1024];
            while ((inputStream.read(temp)) != -1) {
                outputStream.write(temp);
            }
            long time = System.currentTimeMillis() - startTime;
            System.out.println("time: " + time);
        } catch (Exception e) {
            // 521421kb 1629ms
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void fileChannel() {
        long startTime = System.currentTimeMillis();
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream("C:\\Users\\Administrator\\Desktop\\fsdownload\\test.zip").getChannel();
            outputChannel = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\fsdownload\\test1.zip").getChannel();

            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
            long time = System.currentTimeMillis() - startTime;
            System.out.println("time: " + time + ",size" + inputChannel.size());
        } catch (Exception e) {
            // 521421kb 389ms
        } finally {
            if (inputChannel != null) {
                try {
                    inputChannel.close();
                    if (outputChannel != null) {
                        outputChannel.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void randomAccess() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(new File("C:\\Users\\Administrator\\Desktop\\fsdownload\\test1.zip"), "rw");
            file.seek(10);
            byte[] bytes = new byte[12];
            long begin = file.getFilePointer();
            int read = file.read(bytes);
            long end = file.getFilePointer();
            System.out.println("begin: " + begin + ",end " + end + ",content " + new String(bytes));
            // 将位置移动到35，而不是移动35
            file.seek(35);
            file.write(bytes);
            System.out.println("begin: " + end + ",end " + file.getFilePointer() + ",content " + new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    //
                }
            }
        }
    }

    @Test
    public void byteBuffer() {
        long startTime = System.currentTimeMillis();
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream("C:\\Users\\Administrator\\Desktop\\fsdownload\\test.zip").getChannel();
            outputChannel = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\fsdownload\\test1.zip").getChannel();


            // 3. 创建缓冲区对象
            ByteBuffer buff = ByteBuffer.allocate(1024);
            while (true) {
                // 4. 从通道读取数据 & 写入到缓冲区
                // 注：若 以读取到该通道数据的末尾，则返回-1
                int r = inputChannel.read(buff);
                if (r == -1) {
                    break;
                }
                // 5. 传出数据准备：调用flip()方法
                buff.flip();

                // 6. 从 Buffer 中读取数据 & 传出数据到通道
                outputChannel.write(buff);

                // 7. 重置缓冲区
                buff.clear();
            }
            System.out.println("time: " + (System.currentTimeMillis() - startTime) + ",size" + inputChannel.size());
        } catch (Exception e) {
            //
        } finally {
            if (inputChannel != null) {
                try {
                    inputChannel.close();
                    if (outputChannel != null) {
                        outputChannel.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
