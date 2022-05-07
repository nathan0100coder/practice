package practice.topics.file;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * shiLong
 *
 * @author cn.outofmemory
 * @date 2021-1-7
 */
public class FileUtils {



    /**
     * 将文本文件中的内容读入到buffer中
     *
     * @param buffer buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();
        while (line != null) {
            buffer.append(line);
            buffer.append("\n");
            line = reader.readLine();
        }
        reader.close();
        is.close();
    }

    /**
     * 读取文本文件内容
     *
     * @param filePath 文件所在路径
     * @return 文本内容
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static String readFile(String filePath) {
        StringBuffer sb = new StringBuffer();
        try {
            FileUtils.readToBuffer(sb, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
