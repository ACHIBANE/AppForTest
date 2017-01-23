package ine.wmd.Controller;

import ine.wmd.Security.JaaSCallBackHandler;
import ine.wmd.Security.JaaSLoginModel;

import java.util.List;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ine.wmd.dao.ProduitRepository;
import ine.wmd.entities.Produit;
import ine.wmd.entities.User;

@Controller
public class ProduitController {
	@Autowired
	private ProduitRepository produitRepository;
	private LoginContext lc = null;
	@RequestMapping("/")
	public String redirectIndex() {
		return "redirect:/index";
	}

	@RequestMapping("/welcome")
	public String redirectIndex(Model model) {
		model.addAttribute("produit", new Produit());
		return "produit";
	}

	@RequestMapping("/login")
	public String loginn(Model model, @ModelAttribute User user) {
		String username = user.getLogin();
		String password = user.getPswd();

		if (!(username =="") && !(password == "")){
			
			
		      try {
		          lc = new LoginContext("AppForTest", new JaaSCallBackHandler(username, password));
		         
		          lc.login();
		          
		          return "redirect:/welcome";
		          
		      } catch (LoginException le) {
		          System.err.println("Cannot create LoginContext. loginEX " + le.getMessage());
		          
		          return "erreur";
		          
		      } catch (SecurityException se) {
		          System.err.println("Cannot create LoginContext. SecurityEX" + se.getMessage());
		          
		          return "erreur";
		      }
			
			}else{
			return "erreur";

			}

	}

	@RequestMapping("/index")
	public String accueil(Model model, @ModelAttribute User user) {
		model.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping("/saveproduit")
	public String saveproduit(Model model, @RequestParam("reference") String reference,
			@ModelAttribute Produit produit) {
		if(!(lc==null)){
		try {
			if (produitRepository.existsByReference(reference)) {

				String exist = "this reference is already in use, try another one";
				model.addAttribute("exist", exist);
				return "produit";
			} else {
				produitRepository.save(produit);
				List<Produit> produits = produitRepository.findAll();
				model.addAttribute("listeProduits", produits);
				return "result";
			}

		}catch (Exception e) {
			System.out.println(e);
			return "index";
		}
		}
		else{
			return "redirect:/index";
		}

	}
	
	@RequestMapping("/getProduits")
	public String getProduits(Model model, @ModelAttribute Produit produit) {
		
		if(!(lc==null)){
		List<Produit> produits = produitRepository.findAll();
		model.addAttribute("listeProduits", produits);
		return "listProduits";
		}
		else{
			return "redirect:/index";
		}
		
	}
	@RequestMapping("/*")
	public String erreur() {
		return "redirect:/index";
	}
	
	@RequestMapping("/delete")
	public String deleteproduit(Model model, @RequestParam("reference") String reference) {
		
		if(!(lc==null)){
		Produit p = produitRepository.findByReference(reference);
		produitRepository.delete(p);
		List<Produit> produits = produitRepository.findAll();
		model.addAttribute("listeProduits", produits);
		model.addAttribute("produit", new Produit());
		return "result";
		}
		else{
			return "redirect:/index";
		}
	}
	
	@RequestMapping("/update")
	public String editerproduit(Model model, @RequestParam("reference") String reference,
			@RequestParam("nom") String nom, @RequestParam("description") String description,
			@RequestParam("etat") String etat, @ModelAttribute Produit produit) {
		
		if(!(lc==null)){
		Produit prd = produitRepository.findByReference(reference);


			prd.setNom(nom);
			prd.setDescription(description);
			prd.setEtat(etat);
			List<Produit> produits = produitRepository.findAll();
			model.addAttribute("listeProduits",produits);
		
		    return "result";
		}
		else{
			return "redirect:/index";
		}
		
	  }

	@RequestMapping("/editer")
	public String editerproduit(Model model, @RequestParam("reference") String reference) {
		
		if(!(lc==null)){
		Produit p = produitRepository.findByReference(reference);
		model.addAttribute("prd", p);
		return "editer";
		}
		else{
			return "redirect:/index";
		}
	}
}
