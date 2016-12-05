package ine.wmd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ine.wmd.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	public Produit findByReference(String reference);
	
}
