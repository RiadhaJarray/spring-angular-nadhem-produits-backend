package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder   //nous permettre d'utiliser le design pattern DTO
public class ProduitDTO {
private Long idProduit;
private String nomProduit;
private Double prixProduit;
private Date dateCreation;
private Categorie categorie;
private String nomCat;
//private Image image;
List<Image> images;
}
