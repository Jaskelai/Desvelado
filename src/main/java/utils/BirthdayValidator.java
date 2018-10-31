package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthdayValidator {
    public boolean validate(String bDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Date parsed = null;
        try {
            parsed = format.parse(bDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (currentDate.after(parsed)) {
            return true;
        } else {
            return false;
        }
    }
}
