package dk.kea.pokeshopdemo.controller;

import dk.kea.pokeshopdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
	{

		ProductRepository productRepository;
		public HomeController(ProductRepository productRepository){
			this.productRepository=productRepository;

		}
		@GetMapping("/")
		public String Index(Model model){
			model.addAttribute("products",productRepository.getAll());
			return "index";
		}

	}
