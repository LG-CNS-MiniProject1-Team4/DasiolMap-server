package com.dasiolmapserver.dasiolmap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dasiolmapserver.dasiolmap.dasiolstore.service.DasiolStoreService;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DasiolmapApplication {
	public static void main(String[] args) {

		Dotenv env = Dotenv.configure().ignoreIfMissing().load();
		env.entries().forEach(entry -> System.setProperty(entry.getKey(),
				entry.getValue()));

		SpringApplication.run(DasiolmapApplication.class, args);
	}

	@Bean
    public CommandLineRunner dataLoader(DasiolStoreService storeService) {
        return args -> {
            System.out.println("MariaDB 데이터를 Elasticsearch로 동기화 시작...");
            storeService.indexDasiolStores();
            System.out.println("데이터 동기화 완료!");
        };
    }
}
