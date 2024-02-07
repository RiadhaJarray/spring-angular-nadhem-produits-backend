package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;

@SpringBootApplication
public class ProduitsApplication implements CommandLineRunner{

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	
	public static void main(String[] args) {
		SpringApplication.run(ProduitsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Par défaut Spring Data REST ne retourne pas la propriéte ID. Or on peut avoir 
			besoin de l’ID dans le résultat JSON si on utilise des frontend tels que Angular ou 
			ReactJS. Pour retourner l’ID, on doit faire la configuration suivante :
		 */
		repositoryRestConfiguration.exposeIdsFor(Produit.class, Categorie.class);
	}
	
	//en mode singleton
	//bean creer au demaarrage : en uite spring va cherche ce bean a chaque appel ou injection du bean MadeMapper
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}


}
