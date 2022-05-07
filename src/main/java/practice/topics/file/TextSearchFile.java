package practice.topics.file;
 
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

//操作查找文件的类
public class TextSearchFile {
    static int countFiles = 0;// 声明统计文件个数的变量
    static int countFolders = 0;// 声明统计文件夹的变量
 
    public static File[] searchFile(File folder, final String[] keyWords) {
        List<File> result = new ArrayList<>();
        for (String keyWord:keyWords){
            // 运用内部匿名类获得文件
            File[] subFolders = folder.listFiles(pathname -> {
                if (pathname.isFile())// 如果是文件
                {
                    countFiles++;
                } else
                    // 如果是目录
                {
                    countFolders++;
                }
                // 目录或文件包含关键字
                return pathname.isDirectory() || (pathname.isFile() && pathname.getName().toLowerCase().contains(keyWord.toLowerCase()));
            });
            for (int i = 0; i < Objects.requireNonNull(subFolders).length; i++) {// 循环显示文件夹或文件
                if (subFolders[i].isFile()) {// 如果是文件则将文件添加到结果列表中
                    result.add(subFolders[i]);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    File[] foldResult = searchFile(subFolders[i], keyWords);
                    Collections.addAll(result, foldResult);
                }
            }
        }

        File[] files = new File[result.size()];// 声明文件数组，长度为集合的长度
        result.toArray(files);// 集合数组化
        return files;
    }
 
    public static void main(String[] args) {// java程序的主入口处
        File folder = new File("D:\\books\\theory\\maths\\algebra");// 默认目录
        String[] keywords = {"陈景润","华罗庚","数学女王","素数","算术","费马"};
        if (!folder.exists()) {// 如果文件夹不存在
            System.out.println("目录不存在：" + folder.getAbsolutePath());
        }
        File[] result = searchFile(folder, keywords);// 调用方法获得文件数组
        File endDirection = new File("D:\\books\\theory\\maths\\number_theory");
        for (File file:result){
            File endFile = new File(endDirection + File.separator + file.getName());
            System.out.println(file.renameTo(endFile));
        }


    }
}