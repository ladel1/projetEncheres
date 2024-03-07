package org.projetEncheres.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Retrait {
	
	private String rue;
	private String codePostal;
	private String ville;
	
}
