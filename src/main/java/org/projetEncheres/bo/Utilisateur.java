package org.projetEncheres.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer noUtilisateur;
	

	private String pseudo;
	

	private String nom;
	

	private String prenom;
	

	private String email;
	

	private String telephone;
	

	private String rue;
	

	private String codePostal;
	

	private String ville;
	

	private String motDePasse;
	
	
	private Integer credit = 0;
	private boolean administrateur = false;
	
	
	private List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
	

	public void addRole(String role) {
		roles.add(new SimpleGrantedAuthority(role));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return motDePasse;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return pseudo;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
