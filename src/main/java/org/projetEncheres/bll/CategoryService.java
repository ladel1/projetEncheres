package org.projetEncheres.bll;

import java.util.List;

import org.projetEncheres.bo.Categorie;
import org.projetEncheres.dal.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	
	public List<Categorie> getAll(){
		return categoryRepository.findall();
	}
	
}
