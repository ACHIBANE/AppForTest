package ine.wmd.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ine.wmd.dao.ProduitRepository;
import ine.wmd.entities.Produit;

@Controller
public class ProduitController {
	@Autowired
	private ProduitRepository produitRepository;

	
	@RequestMapping("/index")
  public String produitForm(Model model) {
      model.addAttribute("produit", new Produit());
      return "produit";
      
  }
	@RequestMapping("/saveproduit")
	  public String saveproduit(Model model,@ModelAttribute Produit produit) {
	      produitRepository.save(produit);
	      List<Produit> produits = produitRepository.findAll();
	      model.addAttribute("listeProduits",produits);
	      return "result";
	  }
	
	@RequestMapping("/delete")
	  public String deleteproduit(Model model,@RequestParam("reference")String reference,@ModelAttribute Produit produit) {
		Produit p = produitRepository.findByReference(reference);
		produitRepository.delete(p); 
		List<Produit> produits = produitRepository.findAll();
		model.addAttribute("listeProduits",produits);
	      return "result";
	  }

	@RequestMapping("/update")
	  public String editerproduit(Model model,@RequestParam("reference")String reference,
								  @RequestParam("nom")String nom,
								  @RequestParam("description")String description,
								  @RequestParam("etat")String etat,
	  							  @ModelAttribute Produit produit){
		Produit prd = produitRepository.findByReference(reference);
		prd.setReference(reference);
		prd.setNom(nom);
		prd.setDescription(description);
		prd.setEtat(etat);
		List<Produit> produits = produitRepository.findAll();
		model.addAttribute("listeProduits",produits);
		
	    return "result";
	  }
	@RequestMapping("/editer")
	  public String editerproduit(Model model,@RequestParam("reference")String reference) {
		Produit p = produitRepository.findByReference(reference); 
		model.addAttribute("prd",p);
	    return "editer";
	  }
}
