package org.projetEncheres.dal;

import java.util.List;

import org.projetEncheres.bo.Categorie;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	private JdbcTemplate jdbcTemplate;

	public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public List<Categorie> findall() {
		try {
			String sql = "SELECT * FROM CATEGORIES";
			return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Categorie>(Categorie.class));
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
