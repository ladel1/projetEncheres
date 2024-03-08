package org.projetEncheres.dal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	public List<Article> search(String query, 
			Integer category,
			List<Integer> ventesChecked,
			List<Integer> achatsChecked,
			Integer user) {
		try {

			String sql = "SELECT  a.*,c.libelle "
					+ "FROM ARTICLES a "
					+ "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
					+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
					+ "WHERE (:query IS NULL OR a.nom_article LIKE :query) "
					+ "AND (:category IS NULL OR :category = 0 OR c.no_categorie = :category) "
					+ "AND ( :user IS NULL OR a.no_utilisateur = :user) ";					

			//sql = checks(ventesChecked,"ventes",sql);
			sql = checks(achatsChecked,"achats",sql);
			sql = checks(ventesChecked,"ventes",sql);
			
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("query", "%"+query+"%");
			parameters.addValue("category", category);
			parameters.addValue("user", user);
			System.err.println(user);
			return namedParameterJdbcTemplate.query(sql,parameters,new BeanPropertyRowMapper<Article>(Article.class));
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String checks(List<Integer> liste,String type,String sql) {
		
		if(liste!=null) {
			String [] checksAchats = {
					"AND a.date_debut_encheres <= GETDATE() AND GETDATE() < date_fin_encheres "
			};				
			String [] checksVentes = {
					" ",
					"AND a.date_debut_encheres > GETDATE() ",
					"AND date_fin_encheres = '%s' ".formatted(LocalDate.now())
			};				
			for (Integer val : liste) {
				if(type.equals("achats")) {
					sql+=checksAchats[val];
				}else {
					sql+=checksVentes[val];
				}
			}
		}
		System.err.println(sql);
		return sql;
	}
	
}
