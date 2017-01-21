package ine.wmd.Controller;
import ine.wmd.Security.JaaSCallBackHandler;
import ine.wmd.Security.JaaSLoginModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ine.wmd.dao.ProduitRepository;
import ine.wmd.entities.Produit;
import ine.wmd.entities.User;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

@Controller
public class ProduitController {
	@Autowired
	private ProduitRepository produitRepository;

	@RequestMapping("/")
	  public String redirectIndex() {
	      return "redirect:/index"; 
	  }
	@RequestMapping("/login")
	  public String login(Model model) {
		 model.addAttribute("user", new User());
	      return "login";
	  }
	@RequestMapping("/testlogin")
	  public String testLogin(Model model, @ModelAttribute User user) {
		 System.out.println(user.getPswd());
		return "index";
	  }
	
	@RequestMapping("/indextest")
	  public String test(Model model, @ModelAttribute User user)  {
		model.addAttribute("user", user);
		String username = user.getLogin();
		String password = user.getPswd();
		JaaSCallBackHandler cbh= new JaaSCallBackHandler(username, password);
		LoginContext logincontext;
		try {
			logincontext = new LoginContext("AppForTest",cbh);
			logincontext.login();
			return "login";
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "erreur";
		}
		
		
//		if (!(username =="")){
//			System.out.println(username);
//	     	return "login"; }
//		else{
//			return "erreur";
//		}
	  }
	

	@RequestMapping("/saveproduit")
	  public String saveproduit(Model model,@RequestParam("reference")String reference, @ModelAttribute Produit produit) {
		try {
			
			  if(produitRepository.existsByReference(reference)){
				  
				  String exist="this reference is already in use, try another one";
				  model.addAttribute("exist",exist);
				  return "produit";
			  }
			  else{
			      produitRepository.save(produit);
			      List<Produit> produits = produitRepository.findAll();
			      model.addAttribute("listeProduits",produits);
			      return "result";
			  }
			
		} catch (Exception e) {
			System.out.println(e);
			return "produit";
		}
		  
	  }
	
	@RequestMapping("/delete")
	  public String deleteproduit(Model model,@RequestParam("reference")String reference) {
		Produit p = produitRepository.findByReference(reference);
		produitRepository.delete(p); 
		List<Produit> produits = produitRepository.findAll();
		model.addAttribute("listeProduits",produits);
		model.addAttribute("produit", new Produit());
	      return "result";
	  }

	@RequestMapping("/update")
	  public String editerproduit(Model model,@RequestParam("reference")String reference,
								  @RequestParam("nom")String nom,
								  @RequestParam("description")String description,
								  @RequestParam("etat")String etat,
	  							  @ModelAttribute Produit produit){
		Produit prd = produitRepository.findByReference(reference);
//		if(produitRepository.existsByReference(reference)){
//			  
//			  String exist="this reference is already in use, try another one";
//			  model.addAttribute("exist",exist);
//			
//			  return "editer";
//		  }
//		else{
//			prd.setReference(reference);
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
