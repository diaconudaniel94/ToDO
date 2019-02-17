package daniel.diaconu.ToDo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages="daniel.diaconu.ToDo")
@ComponentScan(basePackages = "daniel.diaconu.ToDo")
@EnableJpaRepositories(basePackages = "daniel.diaconu.ToDo")
public class ToDoApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ToDoApplication.class);
    }


	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class, args);
	}
}

