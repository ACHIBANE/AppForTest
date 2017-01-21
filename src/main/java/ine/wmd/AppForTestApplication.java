package ine.wmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ine.wmd.dao.ProduitRepository;
import ine.wmd.entities.Produit;


@SpringBootApplication
@EnableAutoConfiguration
public class AppForTestApplication {

	public static void main(String[] args) {
		System.setProperty("java.security.auth.login.config", "jaas.config");
		ApplicationContext ctx = SpringApplication.run(AppForTestApplication.class, args);
		ProduitRepository produitRepository = ctx.getBean(ProduitRepository.class);
		
		}
}
