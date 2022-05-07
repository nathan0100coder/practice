package practice.topics.arr;
/**
 * @author Hephaest
 * @since 3/21/2019 8:41 PM
 * 这个接口有个静态方法通过给定的毫秒时间换算成相对应的时间表达。
 */
public interface TimeChecker
{

    static void main(String[] args) {
        long l = System.currentTimeMillis();
        String s = calculateTime(l);
        System.out.println("s = " + s);
    }



    /**
     * 根据程序给定的运行时间返回程序运行时间的标准表达。
     * @param time 在游戏开始和结束之间的时间。
     * @return 总用时的文本描述。
     */
    static String calculateTime(long time)
    {
        int CONVERT_TO_SEC = 1000;
        int CONVERT_TO_OTHERS = 60;

        int ms = (int) time;
        int sec = ms / CONVERT_TO_SEC;
        int min = sec / CONVERT_TO_OTHERS; // 把秒转换成分。
        int hr = min / CONVERT_TO_OTHERS; // 把分转化成小时。

        if (hr == 0)
        {
            if(min == 0)
            {
                if (sec == 0)
                    return ms + " ms";
                else
                    return sec + " sec " + ms % 1000 + " ms";
            } else
                return min + " min " + sec % CONVERT_TO_OTHERS + " sec " + ms % CONVERT_TO_SEC + " ms";
        } else
            return hr + " hour " + min % CONVERT_TO_OTHERS + " min " + sec % CONVERT_TO_OTHERS + " sec " + ms % CONVERT_TO_SEC + " ms";
    }
}