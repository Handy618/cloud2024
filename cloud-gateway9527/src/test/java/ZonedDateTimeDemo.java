/**
 * @program: cloud2024
 * @author: Handy
 * @create: 2024-05-14 17:32
 */

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeDemo {

    public static void main(String[] args)
    {
        //获取当前ZonedDateTimeDemo时间
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);
    }
}