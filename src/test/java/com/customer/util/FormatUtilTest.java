package com.customer.util;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(MockitoJUnitRunner.class)
public class FormatUtilTest {

    @Test
    public void getDateTest() throws Exception {

        Date date = FormatUtil.getDate("2022-06-24");
        assertNotNull(date);

    }

    @Test(expected = Exception.class)
    public void getDateTestException() throws Exception {

        FormatUtil.getDate(null);

    }

    @Test
    public void getDateTestException_2() throws Exception {

        Date date = FormatUtil.getDate("24-06-XXXX");
        assertNull(date);

    }

}