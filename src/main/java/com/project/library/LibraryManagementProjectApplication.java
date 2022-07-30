package com.project.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//(exclude={SecurityAutoConfiguration.class}) Bỏ Spring Security
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class LibraryManagementProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementProjectApplication.class, args);
	}

}
