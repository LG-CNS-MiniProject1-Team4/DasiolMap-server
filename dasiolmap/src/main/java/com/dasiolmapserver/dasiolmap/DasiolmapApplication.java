package com.dasiolmapserver.dasiolmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DasiolmapApplication {
// http://localhost:8088/swagger-ui/index.html#/ 
	public static void main(String[] args) {

		Dotenv env = Dotenv.configure()
							.directory("dasiolmap")
							.ignoreIfMissing()
							.load();
		env.entries().forEach(entry -> 
			System.setProperty(entry.getKey(), entry.getValue())
		);
		SpringApplication.run(DasiolmapApplication.class, args);
	}
}
