package sopra.formation.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.model.Salle;
import sopra.formation.model.SalleId;
import sopra.formation.repository.ISalleRepository;

@Controller
@RequestMapping("/salle")
public class SalleController {
	@Autowired
	private ISalleRepository salleRepo;

	public SalleController() {
		super();
	}

	@GetMapping("")
	public String home() {
		return "forward:list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<Salle> salles = salleRepo.findAll();

		model.addAttribute("salles", salles);

		return "salle/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
//		Pour initialiser le formulaire avec des données par défaut (ex : version = 0)
		model.addAttribute("salle", new Salle());
		model.addAttribute("ReadOnly", false);
		return "salle/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("nom") String nomSalle, @RequestParam("capacite") Integer capaciteSalle,
			Model model) {
		SalleId salleId = new SalleId(nomSalle, capaciteSalle);
		model.addAttribute("salle", salleRepo.findById(salleId).get());
		model.addAttribute("ReadOnly", true);
		return "salle/form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("salle") @Valid Salle salle, BindingResult result) {
		if (result.hasErrors()) {
			return "salle/form";
		}

		salleRepo.save(salle);

		return "redirect:/salle/list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/salle/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("nom") String nomSalle, @RequestParam("capacite") Integer capaciteSalle,
			Model model) {
		SalleId salleId = new SalleId(nomSalle, capaciteSalle);
		salleRepo.deleteById(salleId);

		return list(model);
	}
}
