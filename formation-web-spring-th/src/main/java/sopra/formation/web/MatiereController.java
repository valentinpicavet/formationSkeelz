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

import sopra.formation.model.Matiere;
import sopra.formation.model.MatiereId;
import sopra.formation.model.NiveauMatiere;
import sopra.formation.repository.IMatiereRepository;

@Controller
@RequestMapping("/matiere")
public class MatiereController {
	@Autowired
	private IMatiereRepository matiereRepo;
	
	public MatiereController() {
		super();
	}
	
	@GetMapping("")
	public String home() {
		return "forward:list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<Matiere> matieres = matiereRepo.findAll();

		model.addAttribute("mesMatieres", matieres);

		return "matiere/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
//		Pour initialiser le formulaire avec des données par défaut (ex : version = 0)
		model.addAttribute("uneditable", false);
		Matiere matiere = new Matiere();
		MatiereId id = new MatiereId();
		matiere.setId(id);
		
		model.addAttribute("niveau", NiveauMatiere.values());
		model.addAttribute("maMatiere", matiere);
		
		return "matiere/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam String nom, NiveauMatiere niveau, Model model) {
		model.addAttribute("niveau", NiveauMatiere.values());
		MatiereId id = new MatiereId(nom, niveau);
		model.addAttribute("maMatiere", matiereRepo.findById(id).get());
		model.addAttribute("uneditable", true);
		
		return "matiere/form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("maMatiere") @Valid Matiere matiere, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("niveau", NiveauMatiere.values());
			return "matiere/form";
		}
		
		matiereRepo.save(matiere);

		return "redirect:/matiere/list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/matiere/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam String nom, @RequestParam NiveauMatiere niveau, Model model) {
		MatiereId id = new MatiereId(nom, niveau);
		
		matiereRepo.deleteById(id);

		return list(model);
	}
}
