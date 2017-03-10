package dds.poi;

import dds.poi.services.InitService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(value = {"dds.poi.provider.repository"})
@SpringBootApplication
public class SpringPoiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringPoiApplication.class, args);
		context.getBean(InitService.class).inicializar();
	}
}
