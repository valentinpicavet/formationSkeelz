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

import Dto.ModuleDto;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.MatiereId;
import sopra.formation.model.Module;
import sopra.formation.model.NiveauMatiere;
import sopra.formation.model.SalleId;
import sopra.formation.repository.IFiliereRepository;
import sopra.formation.repository.IMatiereRepository;
import sopra.formation.repository.IModuleRepository;
import sopra.formation.repository.IPersonneRepository;
import sopra.formation.repository.ISalleRepository;

@Controller
@RequestMapping("/module")
public class ModuleController {
	@Autowired
	private IModuleRepository moduleRepo;
	@Autowired
	private IPersonneRepository personneRepo;
	@Autowired
	private ISalleRepository salleRepo;
	@Autowired
	private IMatiereRepository matiereRepo;
	@Autowired
	private IFiliereRepository filiereRepo;

	public ModuleController() {
		super();
	}

	@GetMapping({ "", "/list" })
	public String list(Model model) {
		List<Module> modules = moduleRepo.findAll();

		model.addAttribute("modules", modules);

		return "module/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("module", new ModuleDto());
		model.addAttribute("salles", salleRepo.findAll());
		model.addAttribute("matieres", matiereRepo.findAll());
		model.addAttribute("filiere", filiereRepo.findAll());
		model.addAttribute("formateurs", personneRepo.findAllFormateur());

		return "module/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Integer id, Model model) {
		Module module = moduleRepo.findById(id).get();

		ModuleDto moduleDto = new ModuleDto();
		moduleDto.setCode(module.getCode());
		moduleDto.setVersion(module.getVersion());
		moduleDto.setDuree(module.getDuree());
		moduleDto.setOrdre(module.getOrdre());
		if (module.getFiliere() == null) {
			moduleDto.setFiliereId(null);
		} else {
			moduleDto.setFiliereId(module.getFiliere().getId());
		}
		if (module.getFormateur() == null) {
			moduleDto.setFormateurId(null);
		} else {
			moduleDto.setFormateurId(module.getFormateur().getId());
		}
		if (module.getMatiere() == null) {
			moduleDto.setNomMatiereNiveau(null);
		} else {
			String nomMatiere = module.getMatiere().getId().getNom();
			String niveauMatiere = module.getMatiere().getId().getNiveau().toString();
			moduleDto.setNomMatiereNiveau(nomMatiere + "-" + niveauMatiere);
		}
		if (module.getSalle() == null) {
			moduleDto.setNomSalleCapacite(null);
		} else {
			String nomSalle = module.getSalle().getNom();
			String capaciteSalle = module.getSalle().getCapacite().toString();
			moduleDto.setNomSalleCapacite(nomSalle + "-" + capaciteSalle);
		}

		model.addAttribute("module", moduleDto);
		model.addAttribute("salles", salleRepo.findAll());
		model.addAttribute("matieres", matiereRepo.findAll());
		model.addAttribute("filiere", filiereRepo.findAll());
		model.addAttribute("formateurs", personneRepo.findAllFormateur());

		return "module/form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("module") @Valid ModuleDto moduleDto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("salles", salleRepo.findAll());
			model.addAttribute("matieres", matiereRepo.findAll());
			model.addAttribute("filiere", filiereRepo.findAll());
			model.addAttribute("formateurs", personneRepo.findAllFormateur());

			return "module/form";
		}
		Module module = new Module();
		module.setCode(moduleDto.getCode());
		module.setVersion(moduleDto.getVersion());
		module.setDuree(moduleDto.getDuree());
		module.setOrdre(moduleDto.getOrdre());
		Filiere filiere = null;
		if (moduleDto.getFiliereId() == null) {
			filiere = null;
		} else {
			filiere = filiereRepo.findById(moduleDto.getFiliereId()).get();
		}
		module.setFiliere(filiere);
		Formateur formateur = null;
		if (moduleDto.getFormateurId() == null) {
			formateur = null;
		} else {
			formateur = (Formateur) personneRepo.findById(moduleDto.getFormateurId()).get();
		}
		module.setFormateur(formateur);
		SalleId salleId = new SalleId();
		if (moduleDto.getNomSalleCapacite() == null || moduleDto.getNomSalleCapacite() == "") {
			module.setSalle(null);
		} else {
			salleId.setCapacite(Integer.parseInt(moduleDto.getNomSalleCapacite().split("-")[1]));
			salleId.setNom(moduleDto.getNomSalleCapacite().split("-")[0]);
			module.setSalle(salleRepo.findById(salleId).get());
		}
		MatiereId matiereId = new MatiereId();
		if (moduleDto.getNomMatiereNiveau() == null || moduleDto.getNomMatiereNiveau() == "") {
			module.setMatiere(null);
		} else {
			matiereId.setNom(moduleDto.getNomMatiereNiveau().split("-")[0]);
			matiereId.setNiveau(NiveauMatiere.valueOf(moduleDto.getNomMatiereNiveau().split("-")[1]));
			module.setMatiere(matiereRepo.findById(matiereId).get());
		}

		moduleRepo.save(module);

		return "redirect:/module/list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/module/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id, Model model) {
		moduleRepo.deleteById(id);
		return "forward:/module/list";
	}
}
