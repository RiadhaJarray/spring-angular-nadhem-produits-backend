package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;

@RepositoryRestResource(path = "rest")
/*
 * avec cet annotation on peut 
 * Méthode GET :
• http://localhost:8080/produits/rest
• http://localhost:8080/produits/rest/2
• http://localhost:8080/produits/rest?size=2&page=0
• http://localhost:8080/produits/rest?size=2&page=1
• http://localhost:8080/produits/rest?sort=nomProduit,desc
• http://localhost:8080/produits/rest?size=2&page=0&sort=prixProduit,desc
• http://localhost:8080/produits/rest/search
• http://localhost:8080/produits/rest/search/findByNomProduitContains?nom=PC
• http://localhost:8080/produits/rest/search/findByCategorieIdCat?id=1
 * Méthode POST :
• http://localhost:8080/produits/rest
 * Méthode PATCH :
• http://localhost:8080/produits/rest/2
 */
public interface ProduitRepository extends JpaRepository<Produit, Long> {

	List<Produit> findByNomProduit(String nom);
	List<Produit> findByNomProduitContains(String nom);
	
	/*@Query("select p from Produit p where p.nomProduit like %?1 and p.prixProduit > ?2") //?1 veut dire premier parametre
	List<Produit> findByNomPrix (String nom, Double prix);*/
	
	@Query("select p from Produit p where p.nomProduit like %:nom and p.prixProduit > :prix") //ici les parametre avec nom avec @param
	List<Produit> findByNomPrix (@Param("nom") String nom,@Param("prix") Double prix);

	@Query("select p from Produit p where p.categorie = ?1")
	List<Produit> findByCategorie (Categorie categorie);
	
	List<Produit> findByCategorieIdCat(Long id); //on suit toujours les regle de nommage
	
	List<Produit> findByOrderByNomProduitDesc();
	
	@Query("select p from Produit p order by p.nomProduit DESC, p.prixProduit DESC")
	List<Produit> trierProduitsNomsPrix ();

}
