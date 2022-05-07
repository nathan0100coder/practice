package practice.tmp;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Test {
public static void main(String[] args) throws ParseException {
//    String format = "yyyy-MM-dd HH:mm:ss";
//    Date date = new SimpleDateFormat(format).parse("2021-09-09 09:18:31");
//    DateFormat dateFormat = DateFormat.getTimeInstance();
//    String dateStr = dateFormat.format(date);
//    System.out.println("dateStr = " + dateStr);


//    Date nowTime = new SimpleDateFormat(format).parse(nowStr);Date startTime = new SimpleDateFormat(format).parse("09:28:00");
//    Date endTime = new SimpleDateFormat(format).parse("09:32:00");
//    System.out.println(isEffectiveDate(nowTime, startTime, endTime));

    if (Float.parseFloat("3.9") < Float.parseFloat("5.2")) {
        //爆红
        System.out.println("true");
    }
//



}

        /**
        * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
        *
        * @param nowTime 当前时间
        * @param startTime 开始时间
        * @param endTime 结束时间
        * @return
        * @author jqlin
        */
        public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
                           return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
                   return true;
        } else {
                   return false;

        }



        }
}
