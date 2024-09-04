package com.example.spring_data_jdbc_timezone_issue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringDataJdbcTimezoneIssueApplicationTests {

	@Test
	void contextLoads() {
	}

}
