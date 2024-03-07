package org.projetEncheres.bo;


import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private Integer miseAPrix;
	private Integer prixVente;
	private String etatVente;
	
	private Utilisateur acheteur;
	private Utilisateur vendeur;
	List<Enchere> encheres;
	private Categorie categorieArticle;
	private Retrait lieuRetrait;

}
