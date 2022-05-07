package practice.topics.str;

import java.util.StringTokenizer;

/**
 * @program: practice
 * @description: 去除掉所有的标点符号
 * @package_name: practice.topics.str
 * @author: shiLong
 * @date: 2021-08-28 17:23
 **/
public class SentenceSplit {
    public static void main(String[] args) {
        String fileWord = "Then Kung would flush, the veins on his forehead standing out as he remonstrated: Taking a book can't be considered stealing, . . . Taking a book, the affair of a scholar, can't be considered stealing! Then followed quotations from the classics, like A gentleman keeps his integrity even in poverty, and a jumble of archaic expressions till everybody was roaring with laughter and the whole tavern was gay.";
        StringTokenizer stringTokenizer = new StringTokenizer(fileWord, " \t\n\r\f,.:;?![]'");
        while (stringTokenizer.hasMoreTokens()){
            System.out.println("stringTokenizer.nextToken() = " + stringTokenizer.nextToken());
        }

    }
}
