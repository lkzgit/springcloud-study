package com.demo.gateway;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @author lkz
 * @date 2021/10/15 16:29
 * @description
 */
public class Test {
    public static void main(String[] args) {
        //AccessTime 时间格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        String nowTime = dateTimeFormatter.format(ZonedDateTime.now());
        System.out.println(nowTime);


        LocalTime now = LocalTime.now();
        //System.out.println(now);
        //System.out.println(now.plusHours(12));
    }
}
