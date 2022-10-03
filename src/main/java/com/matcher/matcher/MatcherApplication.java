package com.matcher.matcher;

import com.matcher.matcher.entity.Account;
import com.matcher.matcher.entity.AccountRepository;
import org.h2.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatcherApplication.class, args);
		System.out.println("Ready to go");


	}

}
