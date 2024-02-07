package com.example.demo.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entities.Produit;
import com.example.demo.services.ProduitService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProduitRESTController {
	
	@Autowired 
	ProduitService produitService;
	
	@GetMapping
	List<ProduitDTO> getAllProduits(){
		return produitService.getAllProduits();
	}
	
	@GetMapping("/{id}")
	public ProduitDTO getProduitById(@PathVariable("id") Long id) {
		return produitService.getProduit(id);
	 }
	
	@PostMapping
	public ProduitDTO createProduit(@RequestBody ProduitDTO produitDTO) {
		return produitService.saveProduit(produitDTO);
	}
	
	@PutMapping
	public ProduitDTO updateProduit(@RequestBody ProduitDTO produitDTO) {
		return produitService.updateProduit(produitDTO);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteProduit(@PathVariable("id") Long id)
	{
		produitService.deleteProduitById(id);
	}

	@RequestMapping(value="/prodscat/{idCat}",method = RequestMethod.GET)
	public List<Produit> getProduitsByCatId(@PathVariable("idCat") Long idCat) {
		return produitService.findByCategorieIdCat(idCat);
	}
	
	@RequestMapping(value="/prodsByName/{nom}",method = RequestMethod.GET)
	public List<Produit> findByNomProduitContains(@PathVariable("nom") String nom) {
		return produitService.findByNomProduitContains(nom);
	}

}

