package com.example.spring_data_jdbc_timezone_issue.converter;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;
import org.springframework.stereotype.Component;

/*
 *  I have tried converting to: LocalDateTime, which doesn't seem to work, and converting to java.sql.Timestamp, and JDBCType.TIMESTAMP doesn't work either,
 *  but that's somewhat expected given that JDBC does not map java.sql.Timestamp to DATETIME fields in MySQL.
 * 
 * 
 */

//@Component
//@WritingConverter
public class InstantWritingConverter implements Converter<Instant, JdbcValue> {

  @Override
  public JdbcValue convert(Instant source) {
    System.err.println("Converting to database timestamp:");
//    System.err.println(source.toString());
//    System.err.println(Timestamp.from(source).toString());
//    
//    // hack for now since Spring Data JDBC seems to always make use of the system's/JVM's default timezone
    source = source.plusMillis(TimeZone.getDefault().getRawOffset());
    System.err.println(ZoneId.systemDefault().getDisplayName(TextStyle.FULL, Locale.CANADA));
    System.err.println(source.toString());
    System.err.println(JdbcValue.of(source, JDBCType.TIMESTAMP).getValue());
    System.err.println("============");
   
    return JdbcValue.of(source, JDBCType.TIMESTAMP);
    

    //return Timestamp.from(source);
    
//    System.out.println(ZoneId.systemDefault().getDisplayName(TextStyle.FULL, Locale.CANADA));
//    System.out.println(LocalDateTime.ofInstant(source, ZoneId.systemDefault()));
//    System.err.println("============");
//    
//    return LocalDateTime.ofInstant(source, ZoneId.systemDefault());
  }

}
