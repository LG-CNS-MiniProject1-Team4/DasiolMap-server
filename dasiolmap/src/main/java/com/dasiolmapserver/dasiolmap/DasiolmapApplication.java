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

        // build오류시 절대 경로 문제일 확률이 높습니다 운영체제가 달라 발생할수 있는 문제로 directory("./") -> directory("/dasiolmap") 변경해주시면 감사하겠습니다
		Dotenv env = Dotenv.configure().directory("./").ignoreIfMissing().load();
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
