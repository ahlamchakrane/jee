package ma.enset.Seance8;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ma.enset.Seance8.entities.Category;
import ma.enset.Seance8.entities.Product;
import ma.enset.Seance8.repositories.CategoryRepository;
import ma.enset.Seance8.repositories.ProductRepository;

@SpringBootApplication
public class Seance8Application {

	public static void main(String[] args) {
		SpringApplication.run(Seance8Application.class, args);
	}
	@Bean
	public CommandLineRunner start(ProductRepository productRepository,
								   CategoryRepository categoryRepository) {
		return args -> {
			Stream.of("Computers","Printers","Spart Phones").forEach(name->{
				Category category = new Category();
				category.setName(name);
				categoryRepository.save(category);
			});
			categoryRepository.findAll().forEach(cat->{
				for(int i = 1; i<=5; i++) {
					Product product = new Product();
					product.setId(UUID.randomUUID().toString());
					product.setPrice(100+Math.random()*9000);
					product.setQuantity(1+Math.random()*50);
					product.setName(cat.getName()+"_"+i);
					product.setCategory(cat);
					productRepository.save(product);
				}
			});
		};
	}

}
