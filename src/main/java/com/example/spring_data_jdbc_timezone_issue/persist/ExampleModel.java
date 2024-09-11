package com.example.spring_data_jdbc_timezone_issue.persist;

import java.time.Clock;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Table("example_table")
public class ExampleModel {
  @Id
  public Long id;

  @Version
  private Integer version;

  private Instant dtCreated;

  public ExampleModel() {
    dtCreated = Instant.now();
  }
  
  public Instant getDtCreated() {
    return dtCreated;
  }
}
