package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.repositories.ProduitRepository;

@Service
public class ProduitServiceImpl implements ProduitService{

	@Autowired
	ProduitRepository produitRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ModelMapper modelMapper;

	
	@Override
	public ProduitDTO saveProduit(ProduitDTO p) {
		return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
	}

	/*@Override
	public ProduitDTO updateProduit(ProduitDTO p) {
		return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
	}*/
	
	@Override
	public ProduitDTO updateProduit(ProduitDTO p) {
		//Long oldProdImageId =  this.getProduit(p.getIdProduit()).getImage().getIdImage();
		//Long newProdImageId = p.getImage().getIdImage();
		Produit prodUpdated = produitRepository.save(convertDtoToEntity(p));
		//if (oldProdImageId != newProdImageId) //si l'image a été modifiée
			//imageRepository.deleteById(oldProdImageId);
		return convertEntityToDto(prodUpdated);
	}


	@Override
	public void deleteProduit(Produit p) {
		produitRepository.delete(p);
	}

	@Override
	public void deleteProduitById(Long id) {
		produitRepository.deleteById(id);
	}

	@Override
	public ProduitDTO getProduit(Long id) {
		return convertEntityToDto(produitRepository.findById(id).get());
	}

	@Override
	public List<ProduitDTO> getAllProduits() {
		return produitRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
		
		//OU BIEN
		/*List<Produit> prods = produitRepository.findAll();
		List<ProduitDTO> listprodDto = new ArrayList<>(prods.size());
		for (Produit p : prods)
		listprodDto.add(convertEntityToDto(p));
		return listprodDto;*/
	}

	@Override
	public List<Produit> findByNomProduit(String nom) {
		return produitRepository.findByNomProduit(nom);
	}

	@Override
	public List<Produit> findByNomProduitContains(String nom) {
		return produitRepository.findByNomProduitContains(nom);
	}

	@Override
	public List<Produit> findByNomPrix(String nom, Double prix) {
		return produitRepository.findByNomPrix(nom, prix);
	}

	@Override
	public List<Produit> findByCategorie(Categorie categorie) {
		return produitRepository.findByCategorie(categorie);
	}

	@Override
	public List<Produit> findByCategorieIdCat(Long id) {
		return produitRepository.findByCategorieIdCat(id);
	}

	@Override
	public List<Produit> findByOrderByNomProduitDesc() {
		return produitRepository.findByOrderByNomProduitDesc();
	}

	@Override
	public List<Produit> trierProduitsNomsPrix() {
		return produitRepository.trierProduitsNomsPrix();
	}
	
	@Override
	public ProduitDTO convertEntityToDto(Produit produit) {
		
		//on utilise LOOSE pour get deep on the entity 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProduitDTO produitDTO = modelMapper.map(produit, ProduitDTO.class);
		
		return produitDTO;
		
		//methode classic sans builder
		 /*ProduitDTO produitDTO = new ProduitDTO();
		 produitDTO.setIdProduit(produit.getIdProduit());
		 produitDTO.setNomProduit(produit.getNomProduit());
		 produitDTO.setPrixProduit(produit.getPrixProduit());
		 produitDTO.setDateCreation(produit.getDateCreation());
		 produitDTO.setCategorie(produit.getCategorie()); 
		 return produitDTO; */
		 
		
		//methode avec builder
		/* return ProduitDTO.builder()
		.idProduit(produit.getIdProduit())
		.nomProduit(produit.getNomProduit())
		//.prixProduit(produit.getPrixProduit())
		.dateCreation(produit.getDateCreation())
		//.categorie(produit.getCategorie())
		.nomCat(produit.getCategorie().getNomCat())
		.build();*/
	}

	@Override
	public Produit convertDtoToEntity(ProduitDTO produitDto) {
		Produit	produit = new Produit();
		produit = modelMapper.map(produitDto, Produit.class);
		
		return produit;
		
		/*Produit produit = new Produit();
		
		produit.setIdProduit(produitDto.getIdProduit());
		produit.setNomProduit(produitDto.getNomProduit());
		produit.setPrixProduit(produitDto.getPrixProduit());
		produit.setDateCreation(produitDto.getDateCreation());
		produit.setCategorie(produitDto.getCategorie()); 
		
		return produit; */
	}

}
