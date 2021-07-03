package example;

import example.person.Person;
import example.person.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}

	@Bean
	public CommandLineRunner loadData(PersonRepository repository) {
		return (args) -> {
			if (repository.count() == 0) {
				// create a couple of people
				repository.save(new Person("Jack", "Bauer"));
				repository.save(new Person("Will", "Smith"));
				repository.save(new Person("Steve", "Miller"));
				repository.save(new Person("David", "Palmer"));
				repository.save(new Person("Michelle", "Dessler"));
			}
			System.out.println("######### WEATHER_API_KEY = " + System.getenv("WEATHER_API_KEY"));
		};
	}
}
