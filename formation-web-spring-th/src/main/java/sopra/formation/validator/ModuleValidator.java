package sopra.formation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sopra.formation.model.Module;
import sopra.formation.model.Stagiaire;

public class ModuleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Stagiaire.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Module module = (Module) target;		
		
		if(module.getSalle() == null || module.getSalle().getNom() == null) {
			module.setSalle(null);
		}
//		if(module.getMatiere() == null || module.getMatiere().getId() == null) {
//			errors.rejectValue("matiere.id", "notnull", "{module.matiere.notnull}");
//		}
		if(module.getMatiere() == null || module.getMatiere().getId() == null) {
			module.setMatiere(null);
		}
		if(module.getFormateur() == null || module.getFormateur().getId() == null) {
			module.setFormateur(null);
		}
		if(module.getFiliere() == null || module.getFiliere().getId() == null) {
			module.setFiliere(null);
		}
	}

}
