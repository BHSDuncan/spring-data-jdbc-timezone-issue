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
import java.util.TimeZone;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringDataJdbcTimezoneIssueApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	ExampleModelRepository repository;

	@Test
	void persistInstant() {

		ExampleModel exampleModel = repository.save(new ExampleModel());

		Instant persistedInstant = exampleModel.getDtCreated();

		Instant reloaded = repository.findById(exampleModel.id).orElseThrow().getDtCreated();

		Object selectedViaJDBC = jdbcTemplate.queryForObject("select dt_created from example_table where id = ?", Object.class, exampleModel.id);
		String instantAsString = jdbcTemplate.queryForObject("select date_format(dt_created,'%H:%i:%s') from example_table where id = ?", String.class, exampleModel.id);

		System.out.println("----------------------------------------------");
		System.out.println("Instant.now(Clock.systemUTC())):\t"+ Instant.now(Clock.systemUTC()));
		System.out.println("Instant.now():\t" + Instant.now());
		System.out.println("persisted: \t\t" + persistedInstant);
		System.out.println("selected via JDBC:\t" + selectedViaJDBC + " (" + selectedViaJDBC.getClass().getName() + ")");
		System.out.println("selected as String:\t"+ instantAsString);
		System.out.println("reloaded:\t\t"+ reloaded);
		System.out.println("----------------------------------------------");

	}

}
