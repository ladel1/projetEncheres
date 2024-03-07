package org.projetEncheres.dal;

import java.util.List;

import org.projetEncheres.bo.Categorie;

public interface CategoryRepository {

	List<Categorie> findall();
	
}
