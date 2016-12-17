package ine.wmd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ine.wmd.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	public Produit findByReference(String reference);
	
	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN 'true' ELSE 'false' END FROM Produit p WHERE p.reference = ?1")
    public Boolean existsByReference(String reference);
	
}
