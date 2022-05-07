package practice.topics.concurrent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * @author nathan
 */
public class ThreadsDownload {
    private static  Long eachSize;
    private static  Long totalLength;
    private static  File sourceFile;
    private static  File targetFile;
    private static final int PARALLELISM = 8;

    public static void main(String[] args) throws IOException {
        String source = "D:\\CloudMusic\\虞兮叹.mp3";
        String target = "D:\\tmp\\虞兮叹.mp3";
        download(source,target);
    }

    /**
     * 多线程读写文件
     *
     * @param source  源文件路径
     * @param target  目标路径
     * @throws IOException
     */
    private static void download(String source,String target) throws IOException {
        sourceFile = new File(source);
        targetFile = new File(target);
        totalLength = sourceFile.length();
        RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
        raf.close();
        eachSize = totalLength / PARALLELISM;
        CompletableFuture[] completableFutures = IntStream.range(0, PARALLELISM)
                        .boxed()
                        .map(i -> CompletableFuture.runAsync(() -> downloadHandler(i))
                        .whenComplete((result, e) -> System.out.println(i + "-->> over...")))
                        .toArray(CompletableFuture[]::new);
        
        CompletableFuture.allOf(completableFutures).join();
    }

    private static void downloadHandler(Integer index) {
        System.out.println(index);
        try (FileInputStream is = new FileInputStream(sourceFile);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             RandomAccessFile accessFile = new RandomAccessFile(targetFile, "rw")) {
            //每次读取1024
            byte[] buff = new byte[1024];
            //todo 待优化
            //获取当前线程读取区间,最后一个读取剩余部分
            int start = (int) (index * eachSize);
            int end = (int) (index == PARALLELISM - 1 ? totalLength - 1 : (index + 1) * eachSize - 1);
            int length = end - start + 1;
            int readLength = 0;
            is.skip(start);
            int len;
            //下载文件并写入本地
            while ((len = is.read(buff)) != -1 && readLength <= length) {
                byteArrayOutputStream.write(buff, 0, len);
                readLength += len;
            }
            byte[] readData = byteArrayOutputStream.toByteArray();
            byte[] result = byteArrayOutputStream.size() > length ? Arrays.copyOf(readData, length) : readData;
            System.out.println(result.length);
            accessFile.seek(start);
            accessFile.write(result);
            System.out.println(start + "-" + end + " over.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
