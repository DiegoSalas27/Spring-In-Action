package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import tacos.domain.Ingredient;
import tacos.domain.Ingredient.Type;
import tacos.repository.IngredientRepository;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}
}
