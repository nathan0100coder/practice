package practice.topics.file;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteExample {
    public static void main(String[] args) throws IOException {
        // 构建写入内容
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append("ABCDEFGHIGKLMNOPQRSEUVWXYZ");
        }
        // 写入内容
        final String content = stringBuilder.toString();
        // 存放文件的目录
        final String filepath1 = "D:\\io_test\\write1.txt";
//        final String filepath2 = "D:\\io_test\\write2.txt";
//        final String filepath3 = "/Users/mac/Downloads/io_test/write3.txt";
//        final String filepath4 = "/Users/mac/Downloads/io_test/write4.txt";
//        final String filepath5 = "/Users/mac/Downloads/io_test/write5.txt";
//        final String filepath6 = "/Users/mac/Downloads/io_test/write6.txt";

        // 方法一:使用 FileWriter 写文件
        fileWriterTest(filepath1, content);

/*        // 方法二:使用 BufferedWriter 写文件
        long stime2 = System.currentTimeMillis();
        bufferedWriterTest(filepath2, content);
        long etime2 = System.currentTimeMillis();
        System.out.println("BufferedWriter 写入用时:" + (etime2 - stime2));

        // 方法三:使用 PrintWriter 写文件
        long stime3 = System.currentTimeMillis();
        printWriterTest(filepath3, content);
        long etime3 = System.currentTimeMillis();
        System.out.println("PrintWriterTest 写入用时:" + (etime3 - stime3));

        // 方法四:使用 FileOutputStream  写文件
        long stime4 = System.currentTimeMillis();
        fileOutputStreamTest(filepath4, content);
        long etime4 = System.currentTimeMillis();
        System.out.println("FileOutputStream 写入用时:" + (etime4 - stime4));

        // 方法五:使用 BufferedOutputStream 写文件
        long stime5 = System.currentTimeMillis();
        bufferedOutputStreamTest(filepath5, content);
        long etime5 = System.currentTimeMillis();
        System.out.println("BufferedOutputStream 写入用时:" + (etime5 - stime5));

        // 方法六:使用 Files 写文件
        long stime6 = System.currentTimeMillis();
        filesTest(filepath6, content);
        long etime6 = System.currentTimeMillis();
        System.out.println("Files 写入用时:" + (etime6 - stime6));*/

    }

    /**
     * 方法六:使用 Files 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void filesTest(String filepath, String content) throws IOException {
        Files.write(Paths.get(filepath), content.getBytes());
    }

    /**
     * 方法五:使用 BufferedOutputStream 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void bufferedOutputStreamTest(String filepath, String content) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(filepath))) {
            bufferedOutputStream.write(content.getBytes());
        }
    }

    /**
     * 方法四:使用 FileOutputStream  写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void fileOutputStreamTest(String filepath, String content) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filepath)) {
            byte[] bytes = content.getBytes();
            fileOutputStream.write(bytes);
        }
    }

    /**
     * 方法三:使用 PrintWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void printWriterTest(String filepath, String content) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filepath))) {
            printWriter.print(content);
        }
    }

    /**
     * 方法二:使用 BufferedWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void bufferedWriterTest(String filepath, String content) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
            bufferedWriter.write(content);
        }
    }

    /**
     * 方法一:使用 FileWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    private static void fileWriterTest(String filepath, String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.append(content);
        }
    }
}