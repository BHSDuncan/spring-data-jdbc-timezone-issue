# How to use/reproduce

- Set a breakpoint on line 29 of Demonstrator.java, the point at which the Instant value has been persisted to the database but the application has
  not yet exited in order to allow examining the database in Testcontainers via Docker.
- Debug the Spring Boot application as a "Java Application" in your favourite IDE.
- Examine the console output and compare to the value in the 'some_db' database. In my case, I notice that the value in the console is my local time but treated as UTC (which is fine since I'm trying to emulate a production environment), but in the database the value is 4 hours behind the value output in the console. I've set the Clock
 to be UTC and also have told the database on startup to use UTC as well. The only way to see a correct value in the database is to set
 `-Duser.timezone=UTC` in the startup args, which shouldn't be necessary. 

I've tried using converters that write as `LocalDateTime`, `java.sql.Timestamp`, and `JdbcValue`, but none give the expected results (I do understand
that it's been said that JDBC does not support any kind of "timestamp" Java data types for MySQL DATETIME and is meant to be mapped via
`LocalDateTime`, but that doesn't seem to work, either).

What am I doing wrong?

