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

import sopra.formation.model.Dispositif;
import sopra.formation.model.Filiere;
import sopra.formation.repository.IFiliereRepository;

@Controller
@RequestMapping("/filiere")
public class FiliereController {
	@Autowired
	private IFiliereRepository filiereRepo;

	public FiliereController() {
		super();
	}

	@GetMapping("")
	public String home() {
		return "forward:list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<Filiere> filieres = filiereRepo.findAll();

		model.addAttribute("mesFilieres", filieres);

		return "filiere/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("dispositif", Dispositif.values());
		model.addAttribute("maFiliere", new Filiere());

		return "filiere/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, Model model) {
		model.addAttribute("dispositif", Dispositif.values());
		model.addAttribute("maFiliere", filiereRepo.findById(id).get());

		return "filiere/form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("maFiliere") @Valid Filiere filiere, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("dispositif", Dispositif.values());
			return "filiere/form";
		}

		filiereRepo.save(filiere);

		return "redirect:/filiere/list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/filiere/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id, Model model) {
		filiereRepo.deleteById(id);

		return list(model);
	}
}
