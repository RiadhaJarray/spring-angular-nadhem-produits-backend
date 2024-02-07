package com.example.demo.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Categorie;
import com.example.demo.repositories.CategorieRepository;

@RestController
@RequestMapping("/api/cat")
@CrossOrigin("*")
public class CategorieRESTController {
	
	@Autowired
	CategorieRepository categorieRepository;
	
	@GetMapping
	public List<Categorie> getAllCategories()
	{
		return categorieRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Categorie getCategorieById(@PathVariable("id") Long id) {
		return categorieRepository.findById(id).get();
	}
}

