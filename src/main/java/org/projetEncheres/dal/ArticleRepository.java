package org.projetEncheres.dal;

import java.util.List;

import org.projetEncheres.bo.Article;

public interface ArticleRepository {

	List<Article> findAll();
	List<Article> search(String query,Integer category,String type);
}
