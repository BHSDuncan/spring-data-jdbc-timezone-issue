create table `example_table` (
	 `id` integer auto_increment not null,
	 `version` integer,
	 `dt_created` datetime(6) not null, 
	 primary key (`id`)) engine=InnoDB;

