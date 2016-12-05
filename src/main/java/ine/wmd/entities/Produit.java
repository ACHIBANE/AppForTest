package ine.wmd.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="PRODUIT")
public class Produit implements Serializable{
	
	
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long IdProduit;
	public Produit(String nom, String reference, String description, String etat) {
		super();
		this.nom = nom;
		this.reference = reference;
		this.description = description;
		this.etat = etat;
	}
	@NotNull
	private String nom;
	@NotNull
	private String reference;
	@NotNull
	private String description;
	@NotNull
	private String etat;
	
	public Long getIdProduit() {
		return IdProduit;
	}
	public void setIdProduit(Long idProduit) {
		IdProduit = idProduit;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	

}
