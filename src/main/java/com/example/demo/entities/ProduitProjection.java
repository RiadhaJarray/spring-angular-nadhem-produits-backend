package com.example.demo.entities;

import org.springframework.data.rest.core.config.Projection;


/*
 * L’objectif des projections est de limiter le résultat JSON retourné à un certain nombre 
d’attributs. Par exemple on peut avoir besoin seulement de l’attribut nomProduit et de l’attribut prixProduit :
 */
@Projection(name = "nomProd", types = { Produit.class })
public interface ProduitProjection {
	public String getNomProduit();
	public Double getPrixProduit();
}
