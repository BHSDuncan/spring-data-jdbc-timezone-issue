package com.example.spring_data_jdbc_timezone_issue;

import java.time.Clock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.spring_data_jdbc_timezone_issue.persist.ExampleModel;
import com.example.spring_data_jdbc_timezone_issue.persist.ExampleModelRepository;

@Component
public class Demonstrator implements CommandLineRunner {
  @Autowired
  ExampleModelRepository exampleModelRepository;

  @Override
  public void run(String... args) throws Exception {
    ExampleModel exampleModel = new ExampleModel(Clock.systemUTC());

    System.out.println("Persisting exampleModel...");
    
    exampleModelRepository.save(exampleModel);
    
    // E.g. examine the console output and compare to the value in the 'some_db' database. In my case, I notice that the value in the console is my local time but treated as UTC
    // (which is fine since I'm trying to emulate a production environment), but in the database the value is 4 hours behind the value output in the console. I've set the Clock
    // to be UTC and also have told the database on startup to use UTC as well. The only way to see a correct value in the database is to set `-Duser.timezone=UTC` in the startup
    // args, which shouldn't be necessary. What am I doing wrong?
    System.out.println("exampleModel persisted. Set breakpoint here to check the dt_created field in the 'some_db' database in Testcontainers (u: test, p: test).");
  }

}
