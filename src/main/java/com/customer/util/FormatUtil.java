package com.customer.util;

import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public final class FormatUtil {

    public static final String BASE_DATE_FORMAT = "yyyy-MM-dd";

    public static Date getDate(String stringDate) {

        Date dateInput = null;
        try {
            dateInput = new SimpleDateFormat(BASE_DATE_FORMAT).parse(stringDate);
        } catch (ParseException e) {
            log.debug("Error during convert dateInput - input value " + stringDate);
        }
        return dateInput;

    }
}
