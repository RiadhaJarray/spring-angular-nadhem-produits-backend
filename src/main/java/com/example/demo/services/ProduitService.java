package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;

public interface ProduitService {
	
	ProduitDTO saveProduit(ProduitDTO p);
	ProduitDTO updateProduit(ProduitDTO p);
	void deleteProduit(Produit p);
	void deleteProduitById(Long id);
	ProduitDTO getProduit(Long id);
	List<ProduitDTO> getAllProduits();

	//methode de l'interface repositoty du produit
	List<Produit> findByNomProduit(String nom);
	List<Produit> findByNomProduitContains(String nom);
	List<Produit> findByNomPrix (String nom, Double prix);
	List<Produit> findByCategorie (Categorie categorie);
	List<Produit> findByCategorieIdCat(Long id);
	List<Produit> findByOrderByNomProduitDesc();
	List<Produit> trierProduitsNomsPrix();
	
	
	ProduitDTO convertEntityToDto (Produit produit);
	Produit convertDtoToEntity(ProduitDTO produitDto);
}
