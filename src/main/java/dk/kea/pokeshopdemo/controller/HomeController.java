package dk.kea.pokeshopdemo.controller;

import dk.kea.pokeshopdemo.model.Pokemon;
import dk.kea.pokeshopdemo.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController
	{

		ProductRepository productRepository;
		public HomeController(ProductRepository productRepository){
			this.productRepository=productRepository;

		}
		@GetMapping("/index")
		public String Index(Model model){
			model.addAttribute("products",productRepository.getAll());
			return "index";
		}
		@GetMapping("/foundpokemon")
		public String addPokemon(){
			return "/foundpokemon";
		}
		//pokedex_number, `name`, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type-->

		@PostMapping("/foundpokemon")
		public String  addPokemon(@RequestParam("pokedex_number") int pokedex_number, @RequestParam("name") String name,@RequestParam("speed") int speed, @RequestParam("special_defence") int special_defence, @RequestParam("special_attack") int special_attack, @RequestParam("defence") int defence, @RequestParam("attack") int attack, @RequestParam("hp") int hp, @RequestParam("primary_type") String primary_type, @RequestParam("secondary_type") String secondary_type){
			Pokemon newPokemon= new Pokemon();
			newPokemon.setId(pokedex_number);
			newPokemon.setName(name);
			newPokemon.setSpeed(speed);
			newPokemon.setSpecial_defence(special_defence);
			newPokemon.setSpecial_attack(special_attack);
			newPokemon.setDefence(defence);
			newPokemon.setAttack(attack);
			newPokemon.setHp(hp);
			newPokemon.setPrimary_type(primary_type);
			newPokemon.setSecondary_type(secondary_type);
			productRepository.addPokemon(newPokemon);

			return "redirect:/index";


		}
	}
