package org.coffeeprojects.pmda.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTool {
    public static Date getDateWithTimezone(String timezone) {

        SimpleDateFormat startDateTZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        startDateTZ.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            if (timezone != null && !timezone.isBlank() && !"<null>".equals(timezone)) {
                date = startDateTZ.parse(timezone);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
