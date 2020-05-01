package org.eco.vegalize;

import org.eco.vegalize.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;

@SpringBootApplication(exclude = ContextStackAutoConfiguration.class)
public class VegalizeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VegalizeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
