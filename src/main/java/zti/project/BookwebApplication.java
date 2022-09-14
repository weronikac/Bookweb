package zti.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BookwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookwebApplication.class, args);
	}

}
