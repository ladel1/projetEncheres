package org.projetEncheres.bll;

import java.util.List;

import org.projetEncheres.bo.Article;
import org.projetEncheres.dal.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class EnchereService {

	private ArticleRepository articleRepository;
	
	public EnchereService(ArticleRepository articleRepository) {
		this.articleRepository=articleRepository;
	}
	
	public List<Article> getAllArticles(){
		return articleRepository.findAll();
	}
	public List<Article> search(String query,int category,List<Integer> ventesChecked,List<Integer> achatsChecked,
			Integer user){
		return articleRepository.search(query, category, ventesChecked,achatsChecked,
				 user);
	}
	

	
}
