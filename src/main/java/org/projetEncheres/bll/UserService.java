package org.projetEncheres.bll;

import java.util.Optional;

import org.projetEncheres.bo.Utilisateur;
import org.projetEncheres.dal.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Utilisateur> utilisateur = userRepository.findByPseudo(username);

		if (utilisateur.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}

		return utilisateur.get();
	}

}