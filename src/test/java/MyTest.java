import com.dzc.springboot.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: 董政辰
 * @date: 2020/9/21 22:11
 * @description:
 * @email：532587041@qq.com
 */

public class MyTest {

    @Test
    public void One() throws ParseException {
        Date date = new Date();
        System.out.println(date);
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.DATE, 10);
        System.out.println(cl.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.format(date);
        Date parse = format.parse("2020-07-01");
        System.out.println(parse);
        System.out.println(date.getTime() > parse.getTime());
    }

    @Test
    public void Two() {
        System.out.println(DateUtil.nowDateToStr(10));
    }

    @Test
    public void Temp(){
         DateUtil.isLate("2020年-09月-23日");
    }

}
