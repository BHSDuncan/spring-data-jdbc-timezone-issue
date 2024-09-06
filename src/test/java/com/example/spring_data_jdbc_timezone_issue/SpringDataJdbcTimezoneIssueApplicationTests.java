package com.example.spring_data_jdbc_timezone_issue;

import com.example.spring_data_jdbc_timezone_issue.persist.ExampleModel;
import com.example.spring_data_jdbc_timezone_issue.persist.ExampleModelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringDataJdbcTimezoneIssueApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	ExampleModelRepository repository;

	@Test
	void persistInstant() {

		ExampleModel exampleModel = repository.save(new ExampleModel(Clock.systemUTC()));

		Instant persistedInstant = exampleModel.getDtCreated();

		Object selectedViaJDBC = jdbcTemplate.queryForObject("select dt_created from example_table where id = ?", Object.class, exampleModel.id);
		String instantAsString = jdbcTemplate.queryForObject("select date_format(dt_created,'%H:%i:%s') from example_table where id = ?", String.class, exampleModel.id);


		System.out.println(persistedInstant);
		System.out.println(selectedViaJDBC);
		System.out.println(instantAsString);

	}

}
