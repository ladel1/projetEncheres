package org.projetEncheres.dal;


import java.util.Optional;

import org.projetEncheres.bo.Utilisateur;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UtilisateurRepositoryImpl implements UserRepository{

	private JdbcTemplate jdbcTemplate;

	public UtilisateurRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public Optional<Utilisateur> findByPseudo(String pseudo) {
		String sql="SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe ,credit,administrateur FROM UTILISATEURS WHERE pseudo = ?";
		Optional<Utilisateur> optUtilisateur =null;
		try {
			Utilisateur utilisateur = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Utilisateur>(Utilisateur.class), pseudo);
			optUtilisateur = Optional.of(utilisateur);
		} catch (EmptyResultDataAccessException exc) {
			optUtilisateur = Optional.empty();
		}
		return optUtilisateur;
	}


}
