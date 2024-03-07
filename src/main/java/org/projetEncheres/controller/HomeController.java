package org.projetEncheres.controller;

import java.util.List;

import org.projetEncheres.bll.CategoryService;
import org.projetEncheres.bll.EnchereService;
import org.projetEncheres.bo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	private CategoryService categoryService;
	private EnchereService enchereService;
	
	public HomeController(CategoryService categoryService,
			EnchereService enchereService) {
		this.categoryService = categoryService;
		this.enchereService=enchereService;
	}
	
	@GetMapping("/")
	public String index(Model model,
			@RequestParam(name = "q",required = false) String query,
			@RequestParam(name = "category",required = false) Integer category,
			@RequestParam(name = "type",required = false) String type,
			@RequestParam(name = "vente",required = false) List<String> ventesChecked,
			@RequestParam(name = "achat",required = false) List<String> achatsChecked
			) {		
		
		System.err.println(query);
		System.err.println(type);
		System.err.println(ventesChecked);
		System.err.println(achatsChecked);
		model.addAttribute("categories", categoryService.getAll());
		List<Article> articles = enchereService.getAllArticles(); 
		
		if(query!=null || category!=null ) {
			articles = enchereService.search(query, category, type);
		}
		
		model.addAttribute("articles", articles);
		return "home";
	}
	
	
}
