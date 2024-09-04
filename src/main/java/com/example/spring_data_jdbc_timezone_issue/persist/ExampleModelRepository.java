package com.example.spring_data_jdbc_timezone_issue.persist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleModelRepository extends CrudRepository<ExampleModel, Long> {

}
