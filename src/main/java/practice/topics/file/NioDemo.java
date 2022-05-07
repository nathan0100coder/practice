package practice.topics.file;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ListIterator;

/**
 * @author shiLong
 * @version 1.0
 * @desc NIO  练习
 * @date 2021/12/14
 */
@Slf4j
public class NioDemo {
    public static void main(String[] args) {
        ListIterator<String> iterator;
    }

    public static void nioCopy(String source, String target, int allocate) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(allocate);
        FileInputStream inputStream = new FileInputStream(source);
        FileChannel inChannel = inputStream.getChannel();
        FileOutputStream outputStream = new FileOutputStream(target);
        FileChannel outChannel = outputStream.getChannel();
        int length = inChannel.read(byteBuffer);
        while(length != -1){
            //读取模式转换写入模式
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            //清空缓存，等待下次写入
            byteBuffer.clear();
            // 再次读取文本内容
            length = inChannel.read(byteBuffer);
        }
        outputStream.close();
        outChannel.close();
        inputStream.close();
        inChannel.close();
    }
}
