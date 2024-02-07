package com.example.demo;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;
import com.example.demo.repositories.ProduitRepository;

@SpringBootTest
class ProduitsApplicationTests {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@Test
	public void testCreateProduit() {
		Produit prod = new Produit("TV ",700.600,new Date());
		produitRepository.save(prod);
	}
	
	@Test
	public void testFindProduit()
	{
		Produit p = produitRepository.findById(1L).get(); 
		System.out.println(p);
	}
	
	@Test
	public void testUpdateProduit()
	{
		Produit p = produitRepository.findById(1L).get();
		p.setPrixProduit(2000.0);
		produitRepository.save(p);
		System.out.println(p);
	}

	@Test
	public void testDeleteProduit()
	{
		produitRepository.deleteById(1L);;
	}
	 
	@Test
	public void testListerTousProduits()
	{
		List<Produit> prods = produitRepository.findAll();
		for (Produit p : prods)
		{
			System.out.println(p);
		}
	}
	
	@Test
	public void testFindByNomProduit()
	{
		List<Produit> prods = produitRepository.findByNomProduit("PC HP");
		for (Produit p : prods)
		{
			System.out.println(p);
		}
	}

	@Test
	public void testFindByNomProduitContains()
	{
		List<Produit> prods = produitRepository.findByNomProduitContains("PC");
		for (Produit p : prods)
		{
			System.out.println(p);
		}
	}
	
	@Test
	public void testfindByNomPrix()
	{
		List<Produit> prods = produitRepository.findByNomPrix("PC HP", 1000.0);
		for (Produit p : prods)
		{
			System.out.println(p);
		}
	}
	
	@Test
	public void testfindByCategorie()
	{
		Categorie cat = new Categorie();
		cat.setIdCat(2L);
		List<Produit> prods = produitRepository.findByCategorie(cat);
		for (Produit p : prods)
		{
			System.out.println(p);
		}
	}
	
	@Test
	public void findByCategorieIdCat()
	{
		List<Produit> prods = produitRepository.findByCategorieIdCat(1L);
		for (Produit p : prods)
		{
			System.out.println(p);
		}
	}
	
	@Test
	public void testfindByOrderByNomProduitDesc()
	{
		List<Produit> prods = 
		produitRepository.findByOrderByNomProduitDesc();
		for (Produit p : prods)
		{
			System.out.println(p);
		}
	}

	@Test
	public void testTrierProduitsNomsPrix()
	{
		List<Produit> prods = produitRepository.trierProduitsNomsPrix();
		for (Produit p : prods)
		{
			System.out.println(p);
		}
	}
}
