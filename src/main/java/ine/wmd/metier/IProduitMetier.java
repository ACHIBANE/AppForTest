package ine.wmd.metier;

import java.util.List;

import ine.wmd.entities.Produit;


public interface IProduitMetier {
	
	public Produit creerDossier(Produit p);
	public void  supprimerProduit(String reference);
	public Produit modifierProduit(String reference);
	public List<Produit> consulterProduit();

}
