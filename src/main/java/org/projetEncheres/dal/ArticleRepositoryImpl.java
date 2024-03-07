package org.projetEncheres.dal;

import java.util.List;

import org.projetEncheres.bo.Article;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public ArticleRepositoryImpl(JdbcTemplate jdbcTemplate,NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public List<Article> findAll() {
		try {
			String sql = "SELECT * FROM ARTICLES";
			return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Article>(Article.class));
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Article> search(String query, Integer category, String type) {
		try {
			String sql = "SELECT  a.*,c.libelle "
					+ "FROM ARTICLES a "
					+ "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
					+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie ";
			sql +="WHERE ";
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			boolean flag = false;
			if(query!=null) {
				sql +="a.nom_article LIKE :query ";
				parameters.addValue("query", "%"+query+"%");
				flag = true;
			}
			if(category>0 && flag == true) {
				sql +="AND c.no_categorie = :category ";
				parameters.addValue("category", category);
			}else if(category>0) {
				sql +="c.no_categorie = :category ";
				parameters.addValue("category", category);
			}
			return namedParameterJdbcTemplate.query(sql,parameters,new BeanPropertyRowMapper<Article>(Article.class));
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
