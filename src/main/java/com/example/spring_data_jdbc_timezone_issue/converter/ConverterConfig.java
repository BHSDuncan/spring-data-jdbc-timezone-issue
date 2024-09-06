package com.example.spring_data_jdbc_timezone_issue.converter;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

@Configuration
public class ConverterConfig extends AbstractJdbcConfiguration {
  @Override
  public JdbcCustomConversions jdbcCustomConversions() {
    return new JdbcCustomConversions(
        Arrays.asList(
//            new InstantWritingConverter()
        )
    );
  }
}
