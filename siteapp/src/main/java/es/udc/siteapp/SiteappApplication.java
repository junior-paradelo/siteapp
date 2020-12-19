package es.udc.siteapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.udc.siteapp.model.Site;

@SpringBootApplication
public class SiteappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteappApplication.class, args);
	}

	@Bean
	public CommandLineRunner lombokTest() {
		return args -> {
			Site site = new Site();
			site.setName("Sitio");
			site.setProvince("Provincia");
			System.out.println("Site: " + site.getName() + " - Province: " + site.getProvince());
		};
	}
	
}
