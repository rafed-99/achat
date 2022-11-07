package tn.esprit.rh.achat.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operateur implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOperateur;
	private String nom;
	private String prenom;
	
	private String password;
	@OneToMany
	@JsonIgnore
	private Set<Facture> factures;

	public Operateur(String nom, String prenom, String password) {
		this.nom=nom;
		this.prenom=prenom;
		this.password=password;
	}

	public Operateur(Long idOperateur, String nom, String prenom, String password) {
		this.idOperateur = idOperateur;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
	}
}
