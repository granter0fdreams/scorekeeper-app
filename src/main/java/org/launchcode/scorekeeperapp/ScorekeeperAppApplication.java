package org.launchcode.scorekeeperapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ScorekeeperAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScorekeeperAppApplication.class, args);
	}
}