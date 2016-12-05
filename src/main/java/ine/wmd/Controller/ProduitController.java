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
		model.addAttribute("listeProduit",produits);
	      return "listeproduit";
	  }
//	@RequestMapping("/editer")
//	  public String editerproduit(Model model,@RequestParam("prd")Produit prd,@ModelAttribute Produit produit) {
//		produitRepository.saveAndFlush(produit);
//		Produit prd = produitRepository.findByReference(reference);
//		prd.setReference(produit.getReference());
//		prd.setNom(produit.getNom());
//		prd.setDescription(produit.getDescription());
//		prd.setEtat(produit.getEtat());
//		System.out.println(prd.getNom())
//		produitRepository.save(prd); 
//		List<Produit> produits = produitRepository.findAll();
//		model.addAttribute("listeProduit",produits);
//	    return "listeproduit";
//	  }
	@RequestMapping("/editer")
	  public String editerproduit(Model model,@RequestParam("reference")String reference) {
		Produit prd = produitRepository.findByReference(reference);
		model.addAttribute("prd", prd);
	    return "editer";
	  }
	@RequestMapping("/update")
	  public String editerproduit(@ModelAttribute Produit prd) {
		produitRepository.saveAndFlush(prd);
	    return "listeproduit";
	  }
}
