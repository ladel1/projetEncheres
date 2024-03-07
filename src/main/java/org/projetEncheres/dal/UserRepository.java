package org.projetEncheres.dal;

import java.util.Optional;

import org.projetEncheres.bo.Utilisateur;

public interface UserRepository {

	Optional<Utilisateur> findByPseudo(String username);

}
