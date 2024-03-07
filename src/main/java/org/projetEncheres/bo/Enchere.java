package org.projetEncheres.bo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enchere {

	private Date dateEnchere;
	private Integer montantEnchere;
	
	private Utilisateur enchérisseur;
	private Article article;
	
}
