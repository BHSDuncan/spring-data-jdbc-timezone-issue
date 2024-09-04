package com.example.spring_data_jdbc_timezone_issue;

import org.springframework.boot.SpringApplication;

import com.example.spring_data_jdbc_timezone_issue.SpringDataJdbcTimezoneIssueApplication;

public class TestSpringDataJdbcTimezoneIssueApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringDataJdbcTimezoneIssueApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
